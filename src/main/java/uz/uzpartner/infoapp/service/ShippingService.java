package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.Shipping;
import uz.uzpartner.infoapp.payload.request.ShippingDto;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.repository.ShippingRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShippingService {
    private final ShippingRepository shippingRepository;

    public Shipping create(ShippingDto req) {
        req.setName(req.getName().toLowerCase().trim());
        Shipping shipping = shippingRepository.findByName(req.getName()).orElse(null);
        if (shipping != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This shipping already exists!");
        shipping = new Shipping();
        shipping.setName(req.getName());
        return shippingRepository.save(shipping);
    }

    public Shipping update(UUID id, ShippingDto req) {
        return null;
    }

    public Shipping getOne(UUID id) {
        return shippingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public PageableResponse<Shipping> getAll() {
        return null;
    }
}
