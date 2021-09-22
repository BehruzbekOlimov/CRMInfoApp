package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.Company;
import uz.uzpartner.infoapp.payload.request.CompanyRequest;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.repository.CompanyRepository;
import uz.uzpartner.infoapp.service.template.SimpleCrud;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyService implements SimpleCrud<Company, UUID, CompanyRequest> {
    private final CompanyRepository companyRepository;

    @Override
    public Company create(CompanyRequest req) {
        Company company = new Company();
        company.setName(req.getName());
        company.setName(req.getAddress());
        company = companyRepository.save(company);
        return company;
    }

    @Override
    public Company update(UUID id, CompanyRequest req) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "company not found"));
        company.setName(company.getName());
        company.setAddress(company.getAddress());
        company = companyRepository.save(company);
        return company;
    }

    @Override
    public Company getOne(UUID id) {
        return companyRepository.getById(id);
    }

    @Override
    public PageableResponse<Company> getAll() {
        PageableResponse<Company> response = new PageableResponse<>();
        Page<Company> page = companyRepository.findAll(PageRequest.of(0, 20, Sort.Direction.ASC, "id"));
        response.setData(page.getContent());
        response.setTotalCount(page.getTotalElements());
        return response;
    }

}