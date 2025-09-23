package io.github.thalesf93.controllers;

import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.entities.Employees;
import io.github.thalesf93.mappers.EmployeesMapper;
import io.github.thalesf93.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeesMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<Employees> saveEmployee(@RequestBody EmployeesDTO dto){

        Employees employee = mapper.toEntity(dto);
        service.saveEmployee(employee);
        return ResponseEntity.accepted().body(employee);
    }

}
