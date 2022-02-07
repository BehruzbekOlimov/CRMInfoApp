package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.Load;
import uz.uzpartner.infoapp.payload.request.LoadDto;
import uz.uzpartner.infoapp.payload.request.PageAttributes;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.repository.LoadRepository;
import uz.uzpartner.infoapp.service.template.SimpleCrud;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LoadService implements SimpleCrud<Load, UUID, LoadDto> {
    private final CustomerService customerService;
    private final ShippingService shippingService;
    private final LoadRepository loadRepository;

    @Override
    public Load create(LoadDto req) {
        Load load = new Load();
        load.setName(req.getName().trim());
        load.setVolume(req.getVolume());
        load.setNotification(req.getNotification());
        load.setOwner(customerService.getOne(req.getOwnerId()));
        load.setShipping(shippingService.getOne(req.getShippingId()));
        return loadRepository.save(load);
    }

    @Override
    public Load update(UUID id, LoadDto req) {
        Load load = loadRepository.findById(id)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        load.setName(req.getName().trim());
        load.setVolume(req.getVolume());
        load.setNotification(req.getNotification());
        return loadRepository.save(load);
    }

    @Override
    public Load getOne(UUID id) {
        return loadRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public PageableResponse<Load> getAll(PageAttributes attributes) {
        return null;
    }

    public List<Load> getAllByShipping(UUID shippingId) {
        return loadRepository.findAllByShipping(shippingService.getOne(shippingId));
    }
}
