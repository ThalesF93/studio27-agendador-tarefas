package io.github.thalesf93.services;

import io.github.thalesf93.entities.Costumer;
import io.github.thalesf93.repository.CostumersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Costumer> findById(UUID id){
       return repository.findById(id);
    }

    public List<Costumer> getCostumer(String name, String phone, String email){
        Costumer costumer = new Costumer();
        costumer.setName(name);
        costumer.setPhone(phone);
        costumer.setEmail(email);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnorePaths("id", "cpf", "address", "idEmployee")
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Costumer> example = Example.of(costumer, matcher);

        return repository.findAll(example);



    }
}
