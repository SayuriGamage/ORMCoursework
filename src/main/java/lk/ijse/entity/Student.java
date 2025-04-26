package lk.ijse.entity;


import jakarta.persistence.*;
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
public class Student {
    @Id
    private  String id;
    private String name;
    private String address;
    private String tell;

    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User user;

    /*  @OneToMany(mappedBy = "student", fetch = FetchType.LAZY) // FetchType.LAZY*/
    @OneToMany(mappedBy = "student", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Registration> registrations;


    public Student(String id,String name,String address,String tell){
        this.id=id;
        this.name=name;
        this.address=address;
        this.tell=tell;
    }

}
