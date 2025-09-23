package io.github.thalesf93.repository;

import io.github.thalesf93.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<Employees, UUID> {
}
