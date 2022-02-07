package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAttributes {
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer page = 0;
    @Min(1)
    @Max(200)
    private Integer size = 20;
    private Boolean isDesc = false;
    private String orderBy = "id";
}
