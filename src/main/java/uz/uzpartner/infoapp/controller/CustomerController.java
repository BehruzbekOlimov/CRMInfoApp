package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.uzpartner.infoapp.entity.Customer;
import uz.uzpartner.infoapp.payload.request.CustomerDto;
import uz.uzpartner.infoapp.payload.request.PageAttributes;
import uz.uzpartner.infoapp.payload.request.ShippingDto;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.service.CustomerService;
import uz.uzpartner.infoapp.service.ShippingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer/")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("save")
    ResponseEntity<?> save(@Valid @RequestBody CustomerDto req){
        return ResponseEntity.status(201).body(customerService.create(req));
    }

    @GetMapping("all")
    PageableResponse<Customer> getAll(@Valid PageAttributes attributes){
        return customerService.getAll(attributes);
    }
}
