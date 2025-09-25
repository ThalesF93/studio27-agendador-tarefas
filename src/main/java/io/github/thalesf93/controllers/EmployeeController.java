package io.github.thalesf93.controllers;

import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.entities.Employee;
import io.github.thalesf93.mappers.EmployeesMapper;
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


    private final EmployeeService service;
    private final EmployeesMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeesDTO dto){

        Employee employee = mapper.toEntity(dto);
        service.saveEmployee(employee);
        log.info("Employee saved to database" + employee);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam(value = "id", required = false) UUID id,
                                                 @RequestParam(value = "name", required = false) String name){

        boolean hasId = id != null;
        boolean hasName = name != null && !name.isBlank();


        if (!hasId && !hasName ){
            return ResponseEntity.badRequest().body("Enter the ID or Name of the employee you wish to delete");
        }

        if (hasId){
            Optional<Employee> subjectId = service.findById(id);
            service.delete(subjectId.orElse(null));
        }

        else {
            List<Employee> searchByName = service.findByName(name);
            if (searchByName.isEmpty()){
                return ResponseEntity.badRequest().body("Employee not found");
            }

            if (searchByName.size() > 1){
                return ResponseEntity.badRequest().body("Found more than 1 employee, please enter the ID too");
            }
            service.delete(searchByName.get(0));
        }

        log.info("Deleted successfully");
        return ResponseEntity.ok("Deleted successfully! ");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployee(@RequestParam(value = "name", required = false) String name,
                                                      @RequestParam(value = "email", required = false) String email,
                                                      @RequestParam(value = "cpf", required = false) String cpf,
                                                      @RequestParam(value = "roles", required = false) List<String> roles){
        List<Employee> list = service.searchByExample(name, email, cpf, roles);

        // apenas forma de converter a lista
        // List<EmployeesDTO> listConverter = list.stream().map(mapper::toDTO).toList();
        log.info("Search Successfully{}", list);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/search/id")
    public ResponseEntity<EmployeesDTO> getEmployeeById(@RequestParam(value = "id") UUID id){
        return service.getEmployeeById(id).map(employee -> {EmployeesDTO dto = mapper.toDTO(employee);
            log.info("Search result:{}", dto);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") UUID id, @RequestBody EmployeesDTO dto){
        Optional<Employee> employee = service.getEmployeeById(id);

        if (employee.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Employee emp = employee.get();
        mapper.updateFromDTO(dto, emp);

        service.saveEmployee(emp);
        log.info("Update successfully: {}", emp);
        return ResponseEntity.ok().build();

    }


}
