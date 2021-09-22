package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.Company;
import uz.uzpartner.infoapp.entity.User;
import uz.uzpartner.infoapp.entity.enums.Role;
import uz.uzpartner.infoapp.repository.CompanyRepository;
import uz.uzpartner.infoapp.repository.UserRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManagementService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AuthService authService;

    public String setEnabled(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setEnabled(user.getEnabled());
        user = userRepository.save(user);
        return "user " + (user.getEnabled() ? "enabled" : "disabled") + " successfully";
    }

    public String setCompany(UUID id, UUID companyId) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Company company = companyRepository.findById(companyId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "company not found"));
        user.setCompany(company);
        userRepository.save(user);
        return "user company changed to " + (company.getName()) + " successfully";
    }

    public String setRole(UUID id, Role role) {
        if (role.equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        if (authService.getMe().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setRole(role);
        user = userRepository.save(user);
        return "user role changed to" + (user.getRole()) + " successfully";
    }

}