package io.github.thalesf93.controllers;

import io.github.thalesf93.dto.CostumersDTO;
import io.github.thalesf93.entities.Costumer;
import io.github.thalesf93.mappers.CostumersMapper;
import io.github.thalesf93.services.CostumersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody CostumersDTO dto){
        Optional<Costumer> costumer = service.findById(id);

        if (costumer.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Costumer costumerToUpdate = costumer.get();
        mapper.updateFromDTO(dto, costumerToUpdate);
        service.save(costumerToUpdate);

        log.info("Update successfully: {}", costumerToUpdate);
        return ResponseEntity.ok().body("Save successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<CostumersDTO>> getCostumer(@RequestParam(name = "name", required = false) String name,
                                                      @RequestParam(name = "phone", required = false)String phone,
                                                      @RequestParam(name = "email", required = false) String email){

        List<Costumer> list = service.getCostumer(name, phone, email);
        List<CostumersDTO> listDTO = list.stream().map( costumer -> mapper.toDTO(costumer)).toList();
        log.info("Search ok");
        return ResponseEntity.ok(listDTO);


    }

    @GetMapping("/search/id")
    public ResponseEntity<CostumersDTO> getById(@RequestParam(name = "id") UUID id){
              return service.findById(id).map(costumer1 ->
                      ResponseEntity.ok(mapper.toDTO(costumer1)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
