package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Course {
    @Id
    private String pro_id;
    private String pro_name;
    private String fee;
    private String duration;

    @OneToMany(mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Registration> registrations;

    public Course(String proId, String proName, String fee, String duration) {
        this.pro_id=proId;
        this.pro_name=proName;
        this.fee=fee;
        this.duration=duration;
    }
}
