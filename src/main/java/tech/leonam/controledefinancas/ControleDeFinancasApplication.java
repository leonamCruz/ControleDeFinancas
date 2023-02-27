package tech.leonam.controledefinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class ControleDeFinancasApplication {
    public static void main(String[] args) {
        try {var existeOArquivo = Files.deleteIfExists(Paths.get("gasto.csv"));} catch (Exception ignored) {}
        SpringApplication.run(ControleDeFinancasApplication.class, args);
    }
}
