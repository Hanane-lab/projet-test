package ma.ensaf.service;

import ma.ensaf.model.Role;
import ma.ensaf.model.User;
import ma.ensaf.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UserService {
private final UserRepository userRepository;
private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


public UserService(UserRepository userRepository) {
this.userRepository = userRepository;
}


public User register(String name, String email, String rawPassword, Role role) {
if (userRepository.findByEmail(email).isPresent()) {
throw new RuntimeException("Email already used");
}
User u = new User();
u.setName(name);
u.setEmail(email);
u.setPassword(passwordEncoder.encode(rawPassword));
u.setRole(role);
return userRepository.save(u);
}


public Optional<User> findByEmail(String email) {
return userRepository.findByEmail(email);
}
}