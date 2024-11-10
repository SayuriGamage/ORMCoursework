package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CourseDTO {
    private String pro_id;
    private String pro_name;
    private String fee;
    private String duration;

    public CourseDTO(String proName, String fee) {
        this.pro_name=proName;
        this.fee=fee;
    }
}

