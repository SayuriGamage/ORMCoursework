package lk.ijse.bo.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.Userbo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.Userdao;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userdao.saveUser(new User(userDTO.getId(),  userDTO.getUsername(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getRole()));
    }

    @Override
    public UserDTO getUser(String ids) {

        User user = userdao.getdatas(ids);
        if (user == null) {
            return null;
        } else {
            return new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),user.getRole());
        }
    }



    @Override
    public boolean deleteUser(String ids) {
        return userdao.deleteUser(ids);
    }

    @Override
    public ObservableList<UserDTO> getAllUsers() {
        List<User> users=userdao.getAllUsers();
        List<UserDTO> userDTOS=new ArrayList<>();
        for(User user:users){
            userDTOS.add(new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),user.getRole()));
        }
        return FXCollections.observableArrayList(userDTOS);
    }
}



