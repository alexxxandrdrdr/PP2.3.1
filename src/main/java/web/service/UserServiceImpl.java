package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;
public static Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        log.info(" FROM USERSERVICE: added user: " + user.toString());
        userDao.addUser(user);
    }

    @Override
    public List<?> getUsersList() {
        return userDao.getUsersList();
    }

    @Override
    public void updateUser(int id, String name,String lastname,int age) {
        userDao.updateUser(id, name, lastname, age);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

}
