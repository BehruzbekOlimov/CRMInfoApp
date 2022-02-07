package uz.uzpartner.infoapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import uz.uzpartner.infoapp.entity.template.RootEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends RootEntity {

    @Column(nullable = false,length = 32)
    private String firstName;

    @Column(length = 32)
    private String lastName;

    @Column(length = 64, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 32)
    private String phoneNumber;

    private Boolean enabled = true;

    private Long telegramChatId;

    private String enterprise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer user = (Customer) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}