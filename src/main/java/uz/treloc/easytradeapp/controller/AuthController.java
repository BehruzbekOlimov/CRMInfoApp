package uz.treloc.easytradeapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.treloc.easytradeapp.entity.User;
import uz.treloc.easytradeapp.payload.request.UserRegisterRequest;
import uz.treloc.easytradeapp.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("save")
    User save(@Valid @RequestBody UserRegisterRequest req){
        return authService.register(req);
    }

}
