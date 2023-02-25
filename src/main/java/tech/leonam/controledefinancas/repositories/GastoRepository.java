package tech.leonam.controledefinancas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.leonam.controledefinancas.model.entity.Gasto;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, UUID> {
    @Query("SELECT SUM(f.gasto) FROM Gasto f")
    BigDecimal sumGastos();
}

