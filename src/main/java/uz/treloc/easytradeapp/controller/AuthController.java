package uz.treloc.easytradeapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.treloc.easytradeapp.payload.request.UserAuthRequest;
import uz.treloc.easytradeapp.payload.request.UserRegisterRequest;
import uz.treloc.easytradeapp.payload.response.UserWithJwtResponse;
import uz.treloc.easytradeapp.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    UserWithJwtResponse register(@Valid @RequestBody UserRegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("signIn")
    UserWithJwtResponse signIn(@Valid @RequestBody UserAuthRequest req) {
        return authService.auth(req);
    }

}
