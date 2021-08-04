package pl.chojecki.rent_service.service.implementation;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.chojecki.rent_service.model.User;
import pl.chojecki.rent_service.model.UserRole;
import pl.chojecki.rent_service.repository.UserRepository;
import pl.chojecki.rent_service.repository.UserRoleRepository;
import pl.chojecki.rent_service.service.UserService;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        UserRole userRole = userRoleRepository.findByType("USER").orElseThrow(() -> new ResourceNotFoundException("USER"));
        user.getUserRoleSet().add(userRole);
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

}
