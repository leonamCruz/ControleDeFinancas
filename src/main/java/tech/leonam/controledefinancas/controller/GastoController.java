package tech.leonam.controledefinancas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leonam.controledefinancas.model.entity.Gasto;
import tech.leonam.controledefinancas.service.GastoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/inserir")
public class GastoController {
    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody Gasto gasto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoService.save(gasto));
    }

    @GetMapping()
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoService.get());
    }
    @GetMapping("/sumAll")
    public ResponseEntity<Object> getSum(){
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.sum());
    }
}
