package uz.uzpartner.infoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRequest {
    @NotBlank
    @Size(min = 3, message = "name min size 3")
    String name;
    @NotBlank
    @Size(min = 3, message = "address min size 3")
    String address;
}
