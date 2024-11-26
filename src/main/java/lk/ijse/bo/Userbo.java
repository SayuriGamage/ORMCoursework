package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

public interface Userbo extends SuperBO{
    UserDTO getdata(String username);

    boolean registerAdmin(UserDTO admin);

    boolean saveUser(UserDTO userDTO);

    UserDTO getUser(String ids);


    boolean deleteUser(String ids);

    ObservableList<UserDTO> getAllUsers();

    void changePassword(String email, String password);

    boolean checkemail(String email);

    String getuserId(String username);

    User getUsers(String username);
}
