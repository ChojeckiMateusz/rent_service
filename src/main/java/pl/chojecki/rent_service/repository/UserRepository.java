package pl.chojecki.rent_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chojecki.rent_service.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
