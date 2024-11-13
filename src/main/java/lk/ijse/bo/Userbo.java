package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.UserDTO;

public interface Userbo extends SuperBO{
    UserDTO getdata(String username);

    boolean registerAdmin(UserDTO admin);

    boolean saveUser(UserDTO userDTO);

    UserDTO getUser(String ids);


    boolean deleteUser(String ids);

    ObservableList<UserDTO> getAllUsers();

}
