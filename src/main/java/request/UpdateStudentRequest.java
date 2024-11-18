package request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {
    @NotNull(message = "Student ID is not available")
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String fullName;
}
