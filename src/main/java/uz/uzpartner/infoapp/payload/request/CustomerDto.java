package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    private String phoneNumber;
    private Long telegramChatId;
    private String enterprise;
}
