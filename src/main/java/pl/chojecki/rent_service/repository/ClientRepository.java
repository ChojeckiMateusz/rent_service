package pl.chojecki.rent_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.chojecki.rent_service.model.Client;

import java.awt.print.Pageable;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c")
    List<Client> findAllClients(Pageable page);
}
