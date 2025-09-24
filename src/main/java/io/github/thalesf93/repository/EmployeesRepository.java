package io.github.thalesf93.repository;

import io.github.thalesf93.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<Employees, UUID> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Employees e WHERE e.name = :name")
    void deleteByName(String name);


   Optional<Employees> findByName(String name);
}
