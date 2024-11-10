package lk.ijse.dto;


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


    public StudentDTO(String id) {
        this.id=id;
    }
}
