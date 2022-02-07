package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzpartner.infoapp.payload.request.ShippingDto;
import uz.uzpartner.infoapp.service.ShippingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shipping/")
@AllArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("save")
    ResponseEntity<?> save(@Valid @RequestBody ShippingDto req){
        return ResponseEntity.status(201).body(shippingService.create(req));
    }
}
