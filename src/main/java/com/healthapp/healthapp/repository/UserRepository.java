package com.healthapp.healthapp.repository;

import com.healthapp.healthapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //поиск пользователя по email
    Optional<User> findByEmail(String email);

    //поиск свободного врача
    @Query(value = "select * from users inner join users_roles on users.id = users_roles.user_id where users_roles.roles_id = 2 limit 1", nativeQuery = true)
    User findFreeDoctor();

    //поиск пациентов
    @Query(value = "select * from users inner join users_roles on users.id = users_roles.user_id where users_roles.roles_id = 1", nativeQuery = true)
    List<User> findUsers();

    //поиск врачей
    @Query(value = "select * from users inner join users_roles on users.id = users_roles.user_id where users_roles.roles_id = 2", nativeQuery = true)
    List<User> findDoctors();
}
