package uz.uzpartner.infoapp.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.uzpartner.infoapp.entity.Station;
import uz.uzpartner.infoapp.payload.request.PageAttributes;
import uz.uzpartner.infoapp.payload.request.StationDto;
import uz.uzpartner.infoapp.payload.response.PageableResponse;
import uz.uzpartner.infoapp.repository.StationRepository;
import uz.uzpartner.infoapp.service.template.SimpleCrud;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StationService implements SimpleCrud<Station, UUID, StationDto> {
    private final StationRepository stationRepository;

    @Override
    public Station create(StationDto req) {
        Station station = stationRepository.findByName(req.getName().trim()).orElse(null);
        if (station!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This station already added!");
        }
        List<Station> stationList = stationRepository.getAllPositionDesc();
        int position = 1;
        for (Station stationItem : stationList) {
            if (req.getPosition()<=stationItem.getPosition()){
                stationItem.setPosition(stationItem.getPosition()+1);
                stationRepository.save(stationItem);
            }else {
                position = stationItem.getPosition()+1;
                break;
            }
        }
        station = new Station();
        station.setPosition(position);
        station.setName(req.getName().trim());
        return stationRepository.save(station);
    }

    @Override
    public Station update(UUID id, StationDto req) {
        return null;
    }

    @Override
    public Station getOne(UUID id) {
        return null;
    }

    @Override
    public PageableResponse<Station> getAll(PageAttributes attributes) {
        return null;
    }
}
