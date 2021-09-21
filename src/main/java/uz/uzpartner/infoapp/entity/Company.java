package uz.uzpartner.infoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.uzpartner.infoapp.entity.template.RootEntity;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Company extends RootEntity {
    String name;
    String address;
}