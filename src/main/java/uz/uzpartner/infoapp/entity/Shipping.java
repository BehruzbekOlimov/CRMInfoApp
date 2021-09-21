package uz.uzpartner.infoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.uzpartner.infoapp.entity.enums.TypeShipping;
import uz.uzpartner.infoapp.entity.template.RootEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Shipping extends RootEntity {
    @Column(unique = true, nullable = false)
    String number;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TypeShipping type;

}
