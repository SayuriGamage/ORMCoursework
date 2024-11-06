package lk.ijse.bo.impl;

import lk.ijse.bo.Userbo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Userdao;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;



public class UserboImpl implements Userbo {
    Userdao userdao= (Userdao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.user);
    @Override
    public UserDTO getdata(String username) {
        User user = userdao.getdata(username);
        if (user == null) {
            return null;
        } else {
            return new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),user.getRole());
        }
        }

    @Override
    public boolean registerAdmin(UserDTO admin) {
        return userdao.registerAdmin(new User(admin.getId(), admin.getUsername(), admin.getEmail(), admin.getPassword(), admin.getRole()));

    }
    }



