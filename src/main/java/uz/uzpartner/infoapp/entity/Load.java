package uz.uzpartner.infoapp.entity;

import lombok.*;
import lombok.experimental.PackagePrivate;
import org.hibernate.Hibernate;
import uz.uzpartner.infoapp.entity.template.RootEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "load")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@PackagePrivate
public class Load extends RootEntity {
    @Column(nullable = false)
    Double volume;

    @Column(nullable = false)
    String additionalInformation;

    @Column(nullable = false)
    Boolean notification = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Customer owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Shipping shipping;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Load load = (Load) o;
        return getId() != null && Objects.equals(getId(), load.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}