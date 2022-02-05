package uz.uzpartner.infoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.uzpartner.infoapp.entity.BotUser;

import java.util.UUID;

@Repository
public interface BotUserRepository extends JpaRepository<BotUser, UUID> {
}