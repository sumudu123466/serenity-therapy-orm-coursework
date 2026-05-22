package bo.custom;

import bo.superbo.SuperBO;
import dto.UserDTO;

public interface UserBO
        extends SuperBO {

    boolean login(String username,
                  String password);

    void saveUser(UserDTO dto);
}