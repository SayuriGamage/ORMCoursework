package lk.ijse.dao;

import lk.ijse.entity.User;

import java.util.List;


public interface Userdao extends Cruddao<User> {
    boolean ifHaveAdmins();

    User getdata(String username);

    boolean registerAdmin(User user);

    boolean saveUser(User user);

    User getdatas(String ids);


    boolean deleteUser(String ids);

    List<User> getAllUsers();

    void changePassword(String email, String password);


    boolean checkemail(String email);

    String getuserId(String username);

    User getusera(String username);
}
