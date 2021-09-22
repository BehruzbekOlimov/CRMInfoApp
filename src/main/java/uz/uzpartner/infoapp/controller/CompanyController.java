package uz.uzpartner.infoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.uzpartner.infoapp.entity.Company;
import uz.uzpartner.infoapp.payload.request.CompanyRequest;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.service.CompanyService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/company/")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("create")
    Company create(@Valid @RequestBody CompanyRequest request) {
        return companyService.create(request);
    }

    @PutMapping("update/{id}")
    Company update(@Valid @RequestBody CompanyRequest request, @PathVariable UUID id) {
        return companyService.update(id, request);
    }

    @GetMapping("get/{id}")
    Company getOne(@PathVariable UUID id) {
        return companyService.getOne(id);
    }

    @GetMapping("get/all")
    PageableResponse<Company> getAll() {
        return companyService.getAll();
    }
}
