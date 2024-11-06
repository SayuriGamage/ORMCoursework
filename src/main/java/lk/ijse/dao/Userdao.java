package lk.ijse.dao;

import lk.ijse.entity.User;


public interface Userdao extends Cruddao<User> {
    boolean ifHaveAdmins();

    User getdata(String username);

    boolean registerAdmin(User user);

}
