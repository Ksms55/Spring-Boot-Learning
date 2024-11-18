package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import request.CreateStudentRequest;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email_id")
    private String emailId;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Transient
    private String fullName;

    public Student (CreateStudentRequest createStudentRequest){
        this.firstName=createStudentRequest.getFirstName();
        this.lastName=createStudentRequest.getLastName();
        this.emailId=createStudentRequest.getEmailId();
        this.fullName=createStudentRequest.getFirstName() + " " + createStudentRequest.getLastName();
    }

    
}
