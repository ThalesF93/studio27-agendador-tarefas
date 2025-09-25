package io.github.thalesf93.repository;

import io.github.thalesf93.entities.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CostumersRepository extends JpaRepository<Costumer, UUID> {
}
