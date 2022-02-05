package uz.uzpartner.infoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.uzpartner.infoapp.entity.Load;

import java.util.UUID;

@Repository
public interface LoadRepository extends CrudRepository<Load, UUID> {
}