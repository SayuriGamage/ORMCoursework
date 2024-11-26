package lk.ijse.dto;


import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {

    private  String id;
    private String name;
    private String address;
    private String tell;
    private User user;


    public StudentDTO(String id) {
        this.id=id;
    }

    public StudentDTO(String id, String name, String address, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tell = tel;

    }
}
