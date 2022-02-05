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
import uz.uzpartner.infoapp.payload.request.UserUpdateRequest;
import uz.uzpartner.infoapp.payload.response.UserWithJwtResponse;
import uz.uzpartner.infoapp.repository.UserRepository;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    public UserWithJwtResponse register(UserRegisterRequest req) {
        if (req.getUsername().toLowerCase().trim().length() < 4)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        User match = userRepository.findByUsername(req.getUsername().toLowerCase().trim()).orElse(null);
        if (match != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this email already exists");
        }
        match = userRepository.findByPhoneNumber(req.getPhoneNumber()).orElse(null);
        if (match != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this phone number already exists");
        }
        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setUsername(req.getUsername().toLowerCase().trim());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.GUEST);

        user = userRepository.save(user);
        String jwt = JwtUtils.TOKEN_PREFIX + jwtUtils.generateToken(user.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new UserWithJwtResponse(
                user,
                jwt
        );
    }

    public User getMe() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    public UserWithJwtResponse update(UserUpdateRequest req) {
        req.setUsername(req.getUsername().toLowerCase().trim());
        if (req.getUsername().length() < 4)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        User user = getMe();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found");
        }
        if (!Objects.equals(user.getUsername(), req.getUsername())) {
            User match = userRepository.findByUsername(req.getUsername()).orElse(null);
            if (match != null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "current email already exists");
        }
        if (!Objects.equals(user.getPhoneNumber(), req.getPhoneNumber())) {
            User match = userRepository.findByPhoneNumber(req.getPhoneNumber()).orElse(null);
            if (match != null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "current phone number already exists");
        }
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setUsername(req.getUsername());
        if (req.getPassword() != null && req.getNewPassword() != null) {
            if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "incorrect password");

            user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        }

        user = userRepository.save(user);
        String jwt = JwtUtils.TOKEN_PREFIX + jwtUtils.generateToken(user.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new UserWithJwtResponse(
                user,
                jwt
        );
    }

    public UserWithJwtResponse auth(UserAuthRequest req) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(req.getUsername().toLowerCase().trim(), req.getPassword());

        User user = userRepository.findByUsername(req.getUsername().toLowerCase().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "username or password incorrect"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username or password incorrect");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String jwt = JwtUtils.TOKEN_PREFIX + jwtUtils.generateToken(user.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new UserWithJwtResponse(
                user,
                jwt
        );
    }
}
