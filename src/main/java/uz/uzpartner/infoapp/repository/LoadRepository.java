package uz.uzpartner.infoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.uzpartner.infoapp.entity.Load;
import uz.uzpartner.infoapp.entity.Shipping;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {
    List<Load> findAllByShipping(Shipping shipping);
}