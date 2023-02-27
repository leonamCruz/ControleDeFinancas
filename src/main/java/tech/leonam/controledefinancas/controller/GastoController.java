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
        } catch (Exception ignored) {
        }

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
    public ResponseEntity<Object> getBigExpense(){
        return ResponseEntity.status(HttpStatus.OK).body(gastoService.getBigExpense());
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
