package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.uzpartner.infoapp.payload.request.ShippingDto;
import uz.uzpartner.infoapp.payload.request.StationDto;
import uz.uzpartner.infoapp.service.ShippingService;
import uz.uzpartner.infoapp.service.StationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/station/")
@AllArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping("save")
    ResponseEntity<?> save(@Valid @RequestBody StationDto req){
        return ResponseEntity.status(201).body(stationService.create(req));
    }
}
