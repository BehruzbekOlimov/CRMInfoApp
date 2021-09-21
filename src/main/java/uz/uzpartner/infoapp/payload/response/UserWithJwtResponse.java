package uz.uzpartner.infoapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.uzpartner.infoapp.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithJwtResponse {
    private User user;
    private String jwt;
}
