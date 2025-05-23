package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String username;
    private String email;
    private String password;
    private String role;

    public UserDTO(String username, String email, String password, String admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = admin;
    }


}
