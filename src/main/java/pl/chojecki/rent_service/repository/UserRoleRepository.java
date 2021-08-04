package pl.chojecki.rent_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.chojecki.rent_service.model.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByType(String role);
}
