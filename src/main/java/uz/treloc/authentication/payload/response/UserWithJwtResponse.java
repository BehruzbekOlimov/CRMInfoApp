package uz.treloc.authentication.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.treloc.authentication.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithJwtResponse {
    private User user;
    private String jwt;
}
