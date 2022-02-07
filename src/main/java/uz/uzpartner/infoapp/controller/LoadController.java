package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzpartner.infoapp.payload.request.LoadDto;
import uz.uzpartner.infoapp.service.LoadService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/load/")
@AllArgsConstructor
public class LoadController {
    private final LoadService loadService;

    @PostMapping("save")
    ResponseEntity<?> save(@Valid @RequestBody LoadDto req){
        return ResponseEntity.status(201).body(loadService.create(req));
    }

    @PutMapping("update/{id}")
    ResponseEntity<?> update(@Valid @RequestBody LoadDto req, @PathVariable UUID id){
        return ResponseEntity.status(200).body(loadService.update(id,req));
    }

    @GetMapping("{id}")
    ResponseEntity<?> getOne(@PathVariable UUID id){
        return ResponseEntity.status(201).body(loadService.getOne(id));
    }
    @GetMapping("shipping/{shippingId}")
    ResponseEntity<?> getAllByShipping(@PathVariable UUID shippingId){
        return ResponseEntity.status(201).body(loadService.getAllByShipping(shippingId));
    }
}
