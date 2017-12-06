package auth2.users.dao;

import auth2.users.model.User;

public interface UserDao {

    User findByUserName(String username);

}