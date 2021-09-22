package uz.uzpartner.infoapp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageableResponse<E> {
    List<E> data;
    Long totalCount;
}
