package tech.leonam.controledefinancas.controller;

import com.opencsv.CSVWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leonam.controledefinancas.model.entity.Gasto;
import tech.leonam.controledefinancas.service.GastoService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/home")
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
    public ResponseEntity<Object> getSum() {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.sum());
    }
    @GetMapping("/getDocument")
    public ResponseEntity<?> getDocument() {
        try {
            var existeOArquivo = Files.deleteIfExists(Paths.get("gasto.csv"));
        } catch (Exception ignored) {}

        var json = ResponseEntity.status(HttpStatus.OK).body(gastoService.getGastoRepository().findAll());
        var list = json.getBody();
        var createCsv = new CreateCsv(list);
        try {
            createCsv.createCsvByList();

            var file = new File("gasto.csv");
            var path = Paths.get(file.getAbsolutePath());
            var resource = new ByteArrayResource(Files.readAllBytes(path));

            var header = new HttpHeaders();

            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gasto.csv");
            header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
            return ResponseEntity.ok().headers(header).contentLength(file.length()).body(resource);

        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }
    @GetMapping("/getBigExpense")
    public ResponseEntity<Object> getBigExpense() {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.getBigExpense());
    }
    @GetMapping("/getSmallExpense")
    public ResponseEntity<Object> getSmallExpense() {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.getSmallExpense());
    }
    @GetMapping("/getBiggestExpense")
    public ResponseEntity<Object> getBiggestExpense() {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.getBiggestExpense());
    }
    @GetMapping("/getBiggerDaySpent")
    public ResponseEntity<Object> getBiggerDaySpent() {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.getBiggerDaySpent());
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable(value = "uuid") UUID uuid) {
        var gastoOptional = gastoService.getGastoRepository().findById(uuid);

        if (gastoOptional.isPresent()) {
            gastoService.getGastoRepository().deleteById(uuid);
            return ResponseEntity.status(HttpStatus.OK).body("Deletado");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID n??o localizado");
    }
    @GetMapping("/ascOrDesc/{booleano}")
    public ResponseEntity<Object> getPerMonth(@PathVariable(value = "booleano") boolean booleano) {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.ascOrDesc(booleano));
    }
    @GetMapping("/getBySpecificMonth/{monthAndYear}")
    public ResponseEntity<Object> getBySpecificMonth(@PathVariable(value = "monthAndYear") String mesEAno) {
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.getBySpecificMonthAndYear(mesEAno));
    }
    private record CreateCsv(List<Gasto> list) {
        public void createCsvByList() throws IOException {
            var cabecalho = new String[]{"Descricao", "R$", "Data", "Gasto com o que"};
            List<String[]> listaStringTemporaria = new ArrayList<>();

            for (var gasto : list) {
                listaStringTemporaria.add(new String[]{gasto.getDescricao(),
                        gasto.getGasto().toString(), gasto.getDate().toString(), gasto.getOpc()});
            }
            var writer = Files.newBufferedWriter(Paths.get("gasto.csv"));
            var csvWriter = new CSVWriter(writer);

            csvWriter.writeNext(cabecalho);
            csvWriter.writeAll(listaStringTemporaria);

            csvWriter.flush();
            csvWriter.close();
        }
    }
}