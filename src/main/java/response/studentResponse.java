package response;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class studentResponse {

    @SuppressWarnings("unused")
    private int id;
    @SuppressWarnings("unused")
    private String firstName;
    @SuppressWarnings("unused")
    private String lastName;
    @SuppressWarnings("unused")
    private String emailId;

    private String city;

    private String state;

    private String fullName;

    public studentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.emailId = student.getEmailId();
        this.city = student.getAddress().getCity();
        this.state = student.getAddress().getState();
        this.fullName = student.getFirstName() + " " + student.getLastName();
    }


}
