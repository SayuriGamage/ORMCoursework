package lk.ijse.dao;

import lk.ijse.entity.User;

import java.util.List;


public interface Userdao extends Cruddao<User> {
    boolean ifHaveAdmins();

    User getdata(String username);

    boolean registerAdmin(User user);

    boolean saveUser(User user);

    User getdatas(String ids);

    boolean updateUser(User user);

    boolean deleteUser(String ids);

    List<User> getAllUsers();

}
