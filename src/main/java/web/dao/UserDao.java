package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<?> getUsersList();

    User getUserById(int id);

    void updateUser(int id, String name, String lastname, int age);

    void deleteUser(int id);


}
