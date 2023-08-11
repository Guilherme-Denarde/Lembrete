package com.uniamerica.reminder.repository;

import com.uniamerica.reminder.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM Brand WHERE active = true")
    List<User> findByActive(@Param("active")final boolean active);

    @Query("FROM Brand WHERE name = :name AND id = :id")
    List<User> findByNomePut(@Param("name") final String name, @Param("id")final Long id);

}