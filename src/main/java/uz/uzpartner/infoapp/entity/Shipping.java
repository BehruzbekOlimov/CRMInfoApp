package uz.uzpartner.infoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;
import org.hibernate.Hibernate;
import uz.uzpartner.infoapp.entity.enums.ShippingStatus;
import uz.uzpartner.infoapp.entity.enums.TypeShipping;
import uz.uzpartner.infoapp.entity.template.RootEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@PackagePrivate
@Getter
@Setter
@ToString
@Entity
public class Shipping extends RootEntity {
    @Column(nullable = false, unique = true, length = 64)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Station station;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    List<Load> loads;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TypeShipping type = TypeShipping.CARRIAGE;

    @Enumerated(EnumType.STRING)
    ShippingStatus status = ShippingStatus.IN_THE_WAREHOUSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Shipping shipping = (Shipping) o;
        return getId() != null && Objects.equals(getId(), shipping.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
