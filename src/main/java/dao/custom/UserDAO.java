package dao.custom;

import dao.crud.CrudDAO;
import entity.User;

public interface UserDAO
        extends CrudDAO<User,String> {

    User findByUsername(String username);
}