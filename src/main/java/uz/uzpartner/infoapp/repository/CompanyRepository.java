package uz.uzpartner.infoapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzpartner.infoapp.entity.Company;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Page<Company> findAllByUpdatedAtBetween(Timestamp updatedAt, Timestamp updatedAt2, Pageable pageable);
}