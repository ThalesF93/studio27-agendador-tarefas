package io.github.thalesf93.controllers;

import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.entities.Employees;
import io.github.thalesf93.mappers.EmployeesMapper;
import io.github.thalesf93.repository.EmployeesRepository;
import io.github.thalesf93.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeesRepository repository;
    private final EmployeeService service;
    private final EmployeesMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<Employees> saveEmployee(@RequestBody EmployeesDTO dto){

        Employees employee = mapper.toEntity(dto);
        service.saveEmployee(employee);
        log.info("Employee saved to database" + employee);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam(value = "id", required = false) UUID id,
                                                 @RequestParam(value = "name", required = false) String name){

        boolean hasId = id != null;
        boolean hasName = name != null && !name.isBlank();

        if (hasId == hasName){
            return ResponseEntity.badRequest().body("Enter only the ID or Name of the employee you wish to delete");
        }

        if (hasName) {
            var subjectName = repository.findByName(name).orElse(null);
            service.delete(subjectName);
        }

        else {
            Optional<Employees> subjectId = repository.findById(id);
            service.delete(subjectId.orElse(null));
            log.info("Usuário deletado com sucesso");
        }
        return ResponseEntity.ok("Usuário deletado com sucesso! ");
    }

}
