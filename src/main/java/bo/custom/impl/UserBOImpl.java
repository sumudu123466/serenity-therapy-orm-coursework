package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;
import exception.LoginException;
import exception.RegistrationException;
import org.mindrot.jbcrypt.BCrypt;
import util.ValidationUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserBOImpl
        implements UserBO {

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_DURATION_MILLIS = 5 * 60 * 1000L;
    private static final Map<String, Integer> FAILED_ATTEMPTS = new ConcurrentHashMap<>();
    private static final Map<String, Long> LOCKED_UNTIL = new ConcurrentHashMap<>();

    UserDAO userDAO =
            (UserDAO) DAOFactory
                    .getInstance()
                    .getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean login(String username,
                         String password) {

        if (username == null || password == null) {
            return false;
        }

        String normalizedUsername = username.trim();
        String normalizedPassword = password.trim();

        if (normalizedUsername.isEmpty() || normalizedPassword.isEmpty()) {
            return false;
        }

        if (isLocked(normalizedUsername)) {
            throw new LoginException("Account temporarily locked. Please try again later.");
        }

        User user =
                userDAO.findByUsername(normalizedUsername);

        if (user == null || user.getPassword() == null) {
            registerFailure(normalizedUsername);
            throw new LoginException("Invalid username or password");
        }

        String storedPassword = user.getPassword();

        // Accept both legacy plain-text records and BCrypt-hashed records.
        if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
            boolean valid = BCrypt.checkpw(normalizedPassword, storedPassword);
            if (!valid) {
                registerFailure(normalizedUsername);
                throw new LoginException("Invalid username or password");
            }
            clearFailures(normalizedUsername);
            return true;
        }

        if (!storedPassword.equals(normalizedPassword)) {
            registerFailure(normalizedUsername);
            throw new LoginException("Invalid username or password");
        }

        clearFailures(normalizedUsername);
        return true;
    }

    @Override
    public void saveUser(UserDTO dto) {

        if (dto == null) {
            throw new RegistrationException("User data is required");
        }

        ValidationUtil.requireNonBlank(dto.getUserId(), "User ID");
        ValidationUtil.requireNonBlank(dto.getUsername(), "Username");
        ValidationUtil.requireNonBlank(dto.getPassword(), "Password");
        ValidationUtil.requireNonBlank(dto.getRole(), "Role");

        String hashedPassword = BCrypt.hashpw(dto.getPassword().trim(), BCrypt.gensalt());

        userDAO.save(

                new User(
                        dto.getUserId(),
                        dto.getUsername().trim(),
                        hashedPassword,
                        dto.getRole().trim()
                )
        );
    }

    private boolean isLocked(String username) {
        Long lockedUntil = LOCKED_UNTIL.get(username);
        if (lockedUntil == null) {
            return false;
        }

        if (System.currentTimeMillis() >= lockedUntil) {
            LOCKED_UNTIL.remove(username);
            FAILED_ATTEMPTS.remove(username);
            return false;
        }

        return true;
    }

    private void registerFailure(String username) {
        int attempts = FAILED_ATTEMPTS.getOrDefault(username, 0) + 1;
        FAILED_ATTEMPTS.put(username, attempts);

        if (attempts >= MAX_FAILED_ATTEMPTS) {
            LOCKED_UNTIL.put(username, System.currentTimeMillis() + LOCK_DURATION_MILLIS);
        }
    }

    private void clearFailures(String username) {
        FAILED_ATTEMPTS.remove(username);
        LOCKED_UNTIL.remove(username);
    }
}