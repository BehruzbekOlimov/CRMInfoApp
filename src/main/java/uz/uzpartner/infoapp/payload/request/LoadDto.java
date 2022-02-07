package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 64)
    private String name;
    @NotNull
    private Double volume;
    @NotNull
    @Size(min = 2, max = 255)
    private String additionalInformation;
    private Boolean notification = true;
    @NotNull
    private UUID ownerId;
    @NotNull
    private UUID shippingId;
}
