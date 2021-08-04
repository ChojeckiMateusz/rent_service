package pl.chojecki.rent_service.service;

import pl.chojecki.rent_service.model.User;

import java.util.Optional;

public interface UserService {

    User addUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Long id);
}
