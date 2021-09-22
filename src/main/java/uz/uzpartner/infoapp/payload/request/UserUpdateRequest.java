package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank
    @Size(min = 2, max = 64, message = "firstname size must be between 2 and 64")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 64, message = "lastname size must be between 2 and 64")
    private String lastName;

    @NotBlank
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank
//    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?(\\d{2,3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "phone number not valid!")
    private String phoneNumber;

    @Size(min = 6, message = "Password min size 6")
    private String password;

    @Size(min = 6, message = "Password min size 6")
    private String newPassword;
}
