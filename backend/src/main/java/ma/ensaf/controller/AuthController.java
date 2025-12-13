package ma.ensaf.controller;

import ma.ensaf.model.Role;
import ma.ensaf.security.JwtUtil;
import ma.ensaf.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {


private  UserService userService;
private  JwtUtil jwtUtil;
private  AuthenticationManager authenticationManager;
private  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
this.userService = userService;
this.jwtUtil = jwtUtil;
this.authenticationManager = authenticationManager;
}


@PostMapping("/register-client")
public ResponseEntity<?> registerClient(@RequestBody Map<String, String> body) {
var user = userService.register(body.get("name"), body.get("email"), body.get("password"), Role.ROLE_CLIENT);
return ResponseEntity.ok(Map.of("id", user.getId(), "email", user.getEmail()));
}


@PostMapping("/register-organisateur")
public ResponseEntity<?> registerOrganizer(@RequestBody Map<String, String> body) {
var user = userService.register(body.get("name"), body.get("email"), body.get("password"), Role.ROLE_ORGANISATEUR);
return ResponseEntity.ok(Map.of("id", user.getId(), "email", user.getEmail()));
}


@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
String email = body.get("email");
String password = body.get("password");
var opt = userService.findByEmail(email);
if (opt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
var user = opt.get();
if (!passwordEncoder.matches(password, user.getPassword())) return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
return ResponseEntity.ok(Map.of("token", token, "role", user.getRole().name()));
}


@GetMapping("/me")
public ResponseEntity<?> me(Authentication authentication) {
if (authentication == null) return ResponseEntity.status(401).build();
return ResponseEntity.ok(Map.of("username", authentication.getName(), "authorities", authentication.getAuthorities()));
}
}