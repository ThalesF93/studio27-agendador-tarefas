package io.github.thalesf93.services;

import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.entities.Employees;
import io.github.thalesf93.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeesRepository repository;

    public Employees saveEmployee(Employees employees){
        return repository.save(employees);
    }
}
