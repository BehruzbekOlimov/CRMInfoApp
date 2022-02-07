package uz.uzpartner.infoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.uzpartner.infoapp.entity.Station;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<Station, UUID> {
    @Query(
            value = "select * from station order by position desc",
            nativeQuery = true
    )
    List<Station> getAllPositionDesc();

    Optional<Station> findByName(String name);
}