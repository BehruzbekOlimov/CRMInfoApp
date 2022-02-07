package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDto implements Serializable {
    @NotNull
    @Size(min = 2,max = 64,message = "name length must be between 2 and 64")
    private String name;
    @Min(value = 1, message = "position min 1")
    @Max(value = Integer.MAX_VALUE, message = "position max "+Integer.MAX_VALUE)
    private Integer position;
}
