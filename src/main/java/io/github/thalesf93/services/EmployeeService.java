package io.github.thalesf93.services;

import io.github.thalesf93.entities.Employees;
import io.github.thalesf93.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeesRepository repository;

    public Employees saveEmployee(Employees employees){
        return repository.save(employees);
    }

    public void delete(Employees employees){
        repository.delete(employees);
    }

    @Transactional
    public void deleteByName(String name){
        repository.deleteByName(name);
    }
}
