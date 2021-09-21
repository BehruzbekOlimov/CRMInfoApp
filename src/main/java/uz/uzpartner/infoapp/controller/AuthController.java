package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.uzpartner.infoapp.payload.request.UserAuthRequest;
import uz.uzpartner.infoapp.payload.request.UserRegisterRequest;
import uz.uzpartner.infoapp.payload.response.UserWithJwtResponse;
import uz.uzpartner.infoapp.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("register")
    UserWithJwtResponse register(@Valid @RequestBody UserRegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("sign-in")
    UserWithJwtResponse signIn(@Valid @RequestBody UserAuthRequest req) {
        return authService.auth(req);
    }

}
