package uz.uzpartner.infoapp.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import uz.uzpartner.infoapp.payload.request.PageAttributes;

@Component
public class CustomUtilities {

    public Pageable attributesToPageable(PageAttributes attributes){
        return PageRequest.of(
                attributes.getPage(),
                attributes.getSize(),
                Sort.by(
                        attributes.getIsDesc() ? Sort.Direction.DESC: Sort.Direction.ASC,
                        attributes.getOrderBy()
                )
        );
    }
}
