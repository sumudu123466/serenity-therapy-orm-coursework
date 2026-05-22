package util;

import exception.ValidationException;

import java.util.regex.Pattern;

public final class ValidationUtil {

    // Basic email format validation for registration forms.
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    // Sri Lanka mobile/landline variations: 0XXXXXXXXX, +94XXXXXXXXX, 94XXXXXXXXX.
    private static final Pattern LK_PHONE_PATTERN =
            Pattern.compile("^(?:0|94|\\+94)?7[0-9]{8}$");

    private ValidationUtil() {
    }

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " is required");
        }
    }

    public static void validateEmail(String email, String fieldName) {
        requireNonBlank(email, fieldName);
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new ValidationException("Invalid email format for " + fieldName);
        }
    }

    public static void validateSriLankanPhone(String phone, String fieldName) {
        requireNonBlank(phone, fieldName);
        String normalized = phone.trim().replaceAll("\\s+", "");
        if (!LK_PHONE_PATTERN.matcher(normalized).matches()) {
            throw new ValidationException("Invalid Sri Lankan phone number for " + fieldName);
        }
    }
}

