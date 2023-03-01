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
    @Query("SELECT opc, COUNT(*) AS contagem FROM Gasto GROUP BY opc ORDER BY contagem DESC LIMIT 1")
    Object getBigExpense();
    @Query("SELECT opc, COUNT(*) AS contagem FROM Gasto GROUP BY opc ORDER BY contagem ASC LIMIT 1")
    Object getSmallExpense();
    @Query("SELECT g.descricao,g.date,g.opc,g.gasto FROM Gasto g WHERE g.gasto = (SELECT MAX(g2.gasto) FROM Gasto g2)")
    Object getBiggestExpense();
    @Query("SELECT date, SUM(gasto) AS total_gastos FROM Gasto GROUP BY date ORDER BY total_gastos DESC LIMIT 1")
    Object getBiggerDaySpent();
}

