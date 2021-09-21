package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.config.jwt.JwtUtils;
import uz.uzpartner.infoapp.entity.User;
import uz.uzpartner.infoapp.entity.enums.Role;
import uz.uzpartner.infoapp.payload.request.UserAuthRequest;
import uz.uzpartner.infoapp.payload.request.UserRegisterRequest;
import uz.uzpartner.infoapp.payload.response.UserWithJwtResponse;
import uz.uzpartner.infoapp.repository.UserRepository;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;


    public UserWithJwtResponse register(UserRegisterRequest req) {
        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setEmail(req.getEmail().toLowerCase().trim());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.CUSTOMER);

        userRepository.save(user);
        String jwt = JwtUtils.TOKEN_PREFIX + jwtUtils.generateToken(user.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new UserWithJwtResponse(
                user,
                jwt
        );
    }

    public UserWithJwtResponse auth(UserAuthRequest req) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(req.getEmail().toLowerCase().trim(), req.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        User user = userRepository.findByEmail(req.getEmail().toLowerCase().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String jwt = JwtUtils.TOKEN_PREFIX + jwtUtils.generateToken(user.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new UserWithJwtResponse(
                user,
                jwt
        );
    }
}
