package uz.uzpartner.infoapp.service.template;

import uz.uzpartner.infoapp.payload.request.PageAttributes;
import uz.uzpartner.infoapp.payload.response.PageableResponse;

public interface SimpleCrud<E, I, R> {
    E create(R req);

    E update(I id, R req);

    E getOne(I id);

    PageableResponse<E> getAll(PageAttributes attributes);
}