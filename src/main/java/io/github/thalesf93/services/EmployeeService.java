package io.github.thalesf93.services;

import io.github.thalesf93.entities.Employee;
import io.github.thalesf93.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeesRepository repository;

    public Employee saveEmployee(Employee employee){
        return repository.save(employee);
    }

    public void delete(Employee employee){
        repository.delete(employee);
    }

    @Transactional
    public void deleteByName(String name){
        repository.deleteByName(name);
    }

    public Optional<Employee> getEmployeeById(UUID id){
        return repository.findById(id);
    }

    public List<Employee> searchByExample(String name, String email, String cpf, List<String> roles){

        //Criando primeiro os paramretos para a busca
        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setCpf(cpf);
        emp.setRoles(roles);

        //Criando como será feita a busca

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id", "registrationDate", "login", "password")//Passar os campos que nao foram inseridos no mathcer
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Employee> example = Example.of(emp, matcher);
        return repository.findAll(example);
    }

    public void update(Employee employee){
        repository.save(employee);
    }
}
