package io.github.thalesf93.controllers;

import io.github.thalesf93.dto.CostumersDTO;
import io.github.thalesf93.entities.Costumer;
import io.github.thalesf93.mappers.CostumersMapper;
import io.github.thalesf93.services.CostumersService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("costumer")
@RequiredArgsConstructor
@Slf4j
public class CostumersController {

    private final CostumersService service;
    private final CostumersMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<Costumer> saveCostumer(@RequestBody CostumersDTO dto){
        var costumer = mapper.toEntity(dto);
        service.save(costumer);
        log.info("Costumer saved " + costumer);
        return ResponseEntity.ok(costumer);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCostumer(@RequestParam(name = "id")UUID id){
        service.deleteById(id);
        log.info("Costumer Deleted");
        return ResponseEntity.ok().body("Deleted Successfully");
    }
}
