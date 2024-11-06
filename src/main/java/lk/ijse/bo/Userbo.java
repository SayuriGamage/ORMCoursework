package lk.ijse.bo;

import lk.ijse.dto.UserDTO;

public interface Userbo extends SuperBO{
    UserDTO getdata(String username);

    boolean registerAdmin(UserDTO admin);

}
