package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.Customer;
import uz.uzpartner.infoapp.payload.request.CustomerDto;
import uz.uzpartner.infoapp.payload.request.PageAttributes;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.repository.CustomerRepository;
import uz.uzpartner.infoapp.service.template.SimpleCrud;
import uz.uzpartner.infoapp.utils.CustomUtilities;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService implements SimpleCrud<Customer, UUID,CustomerDto> {
    private final CustomerRepository customerRepository;
    private final CustomUtilities utilities;

    @Override
    public Customer create(CustomerDto req) {
        req.setEmail(req.getEmail().toLowerCase().trim());
        Customer customer = customerRepository.findCustomerByEmail(req.getEmail()).orElse(null);
        if (customer!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email already exists");
        customer = new Customer();
        customer.setEmail(req.getEmail());
        customer.setFirstName(req.getFirstName());
        if (req.getLastName()!=null)
        customer.setLastName(req.getLastName());
        if (req.getPhoneNumber()!=null)
        customer.setPhoneNumber(req.getPhoneNumber());
        if (req.getEnterprise()!=null)
        customer.setEnterprise(req.getEnterprise());
        if (req.getTelegramChatId()!=null)
        customer.setTelegramChatId(req.getTelegramChatId());
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer update(UUID id, CustomerDto req) {
        req.setEmail(req.getEmail().toLowerCase().trim());
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        customer.setEmail(req.getEmail());
        customer.setFirstName(req.getFirstName());
        if (req.getLastName()!=null)
            customer.setLastName(req.getLastName());
        if (req.getPhoneNumber()!=null)
            customer.setPhoneNumber(req.getPhoneNumber());
        if (req.getEnterprise()!=null)
            customer.setEnterprise(req.getEnterprise());
        if (req.getTelegramChatId()!=null)
            customer.setTelegramChatId(req.getTelegramChatId());
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer getOne(UUID id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public PageableResponse<Customer> getAll(PageAttributes attributes) {
        PageableResponse<Customer> response =new PageableResponse<>();
        Page<Customer> customerPage = customerRepository.findAll(utilities.attributesToPageable(attributes));
        response.setData(customerPage.getContent());
        response.setTotalCount(customerPage.getTotalElements());
        return response;
    }

}
