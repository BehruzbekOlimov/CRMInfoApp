package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.User;
import uz.uzpartner.infoapp.entity.enums.Role;
import uz.uzpartner.infoapp.repository.UserRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManagementService {
    private final UserRepository userRepository;
    private final AuthService authService;

    public String setEnabled(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        User me = authService.getMe();
        if (me.getId().equals(id) || user.getRole().equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        if (user.getRole().equals(Role.MODERATOR) && !me.getRole().equals(Role.ADMIN))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        user.setEnabled(user.getEnabled());
        user = userRepository.save(user);
        return "user " + (user.getEnabled() ? "enabled" : "disabled") + " successfully";
    }

    public String setRole(UUID id, Role role) {
        User me = authService.getMe();
        if (role.equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (me.getId().equals(id) || user.getRole().equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        if (me.getRole().equals(Role.MODERATOR) && (role.equals(Role.MODERATOR) || user.getRole().equals(Role.MODERATOR))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }

        user.setRole(role);
        user = userRepository.save(user);
        return "user role changed to" + (user.getRole()) + " successfully";
    }

}