package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.enums.Role;
import uz.uzpartner.infoapp.service.UserManagementService;

import java.util.UUID;

@RestController
@RequestMapping("/api/user-management/")
@AllArgsConstructor
public class UserManagementController {

    private final UserManagementService userManagementService;

    @PatchMapping("set-enabled/{id}")
    ResponseEntity<?> setEnabled(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(userManagementService.setEnabled(id));
    }

    @PatchMapping("set-role/{id}")
    ResponseEntity<?> setRole(@PathVariable UUID id, @RequestParam String role) {
        Role roleEnum;
        try {
            roleEnum = Role.valueOf(role.toUpperCase().trim());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid role");
        }
        return ResponseEntity.status(203).body(userManagementService.setRole(id, roleEnum));
    }
}
