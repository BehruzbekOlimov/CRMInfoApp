package uz.uzpartner.infoapp.entity;

import lombok.*;
import lombok.experimental.PackagePrivate;
import org.hibernate.Hibernate;
import uz.uzpartner.infoapp.entity.template.RootEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "station")
@Getter
@Setter
@ToString
@AllArgsConstructor
@PackagePrivate
@NoArgsConstructor
public class Station extends RootEntity {
    @Column(nullable = false, length = 64, unique = true)
    String name;

    @Column(nullable = false)
    Integer position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Station station = (Station) o;
        return getId() != null && Objects.equals(getId(), station.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}