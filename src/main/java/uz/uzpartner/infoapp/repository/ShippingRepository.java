package uz.uzpartner.infoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzpartner.infoapp.entity.Shipping;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
    Optional<Shipping> findByName(String name);
}