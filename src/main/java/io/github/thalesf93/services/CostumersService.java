package io.github.thalesf93.services;

import io.github.thalesf93.entities.Costumer;
import io.github.thalesf93.repository.CostumersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CostumersService {

    private final CostumersRepository repository;

    public Costumer save(Costumer costumer){
        return repository.save(costumer);
    }

    public void deleteById(UUID id){
        repository.deleteById(id);
    }
}
