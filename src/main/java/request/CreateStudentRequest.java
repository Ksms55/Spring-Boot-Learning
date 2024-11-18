package request;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {
    @NotBlank(message = "Please enter your first name")
    //private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String city;
    private String state;
    private String fullName;
}
