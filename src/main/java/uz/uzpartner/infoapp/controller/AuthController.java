package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.uzpartner.infoapp.entity.User;
import uz.uzpartner.infoapp.payload.request.UserAuthRequest;
import uz.uzpartner.infoapp.payload.request.UserRegisterRequest;
import uz.uzpartner.infoapp.payload.request.UserUpdateRequest;
import uz.uzpartner.infoapp.payload.response.UserWithJwtResponse;
import uz.uzpartner.infoapp.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @GetMapping("get-me")
    User getMe() {
        return authService.getMe();
    }

    @PostMapping("register")
    UserWithJwtResponse register(@Valid @RequestBody UserRegisterRequest req) {
        return authService.register(req);
    }

    @PutMapping("update")
    UserWithJwtResponse update(@Valid @RequestBody UserUpdateRequest req) {
        return authService.update(req);
    }

    @PostMapping("sign-in")
    UserWithJwtResponse signIn(@Valid @RequestBody UserAuthRequest req) {
        return authService.auth(req);
    }

}
