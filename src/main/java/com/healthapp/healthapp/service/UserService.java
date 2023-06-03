package com.healthapp.healthapp.service;

import com.healthapp.healthapp.configuration.AppConstants;
import com.healthapp.healthapp.entity.User;
import com.healthapp.healthapp.repository.RoleRepository;
import com.healthapp.healthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> login(User user) {
        Optional<User> userAttempt = userRepository.findByEmail(user.getEmail());
        if (userAttempt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Ошибка! Пользователь не найден");
        } else if (!encoder.matches(user.getPassword(), userAttempt.get().getPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body("Ошибка! Неверный пароль");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok().body(userAttempt);
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    public ResponseEntity<?> createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email уже зарегистрирован. Пользователь не создан.");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        if (user.getEdu() == null || user.getEdu().isEmpty()) {
            user.getRoles().add(roleRepository.findById(AppConstants.ROLE_USER_ID).get());
        } else {
            user.getRoles().add(roleRepository.findById(AppConstants.ROLE_DOCTOR_ID).get());
        }
        user.setAvatar(AppConstants.USER_AVATAR_URL);
        userRepository.save(user);
        return ResponseEntity.ok().body("");
    }

    public ResponseEntity<?> updateUser(User user) {
        if (user.getAvatar() == null || user.getAvatar().isEmpty()) {
            if (Objects.equals(user.getRoles().stream().findFirst().get().getId(), AppConstants.ROLE_USER_ID)) {
                user.setAvatar(AppConstants.USER_AVATAR_URL);
            } else {
                user.setAvatar(AppConstants.DOCTOR_AVATAR_URL);
            }
        }
        userRepository.save(user);
        return ResponseEntity.ok().body("");
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public ResponseEntity<?> getDoctors(String fts) {
        if (fts != null && !fts.isEmpty()) {
            return ResponseEntity.ok().body(userRepository.findDoctors().stream()
                    .filter(e -> e.getName().contains(fts) ||
                            e.getEmail().contains(fts) ||
                            e.getAbout().contains(fts) ||
                            e.getPhone().contains(fts) ||
                            e.getEdu().contains(fts) ||
                            e.getProf().contains(fts)
                    )
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok().body(userRepository.findDoctors());
        }
    }

    public ResponseEntity<?> getUsers(String fts) {
        if (fts != null && !fts.isEmpty()) {
            return ResponseEntity.ok().body(userRepository.findUsers().stream()
                    .filter(e -> e.getName().contains(fts) ||
                            e.getEmail().contains(fts) ||
                            e.getAbout().contains(fts) ||
                            e.getPhone().contains(fts)
                    )
                    .collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok().body(userRepository.findUsers());
        }
    }
}