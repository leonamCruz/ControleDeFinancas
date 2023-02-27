package tech.leonam.controledefinancas.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.leonam.controledefinancas.model.entity.Gasto;
import tech.leonam.controledefinancas.repositories.GastoRepository;

import java.math.BigDecimal;

@Service
public class GastoService {
    private final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    @Transactional
    public Gasto save(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    @Transactional
    public Object get() {
        return gastoRepository.findAll();
    }

    public BigDecimal sum() {
        return gastoRepository.sumGastos();
    }

    public GastoRepository getGastoRepository() {
        return gastoRepository;
    }

    public Object getBigExpense() {
        return gastoRepository.getBigExpense();
    }

    public Object getSmallExpense() {
        return gastoRepository.getSmallExpense();
    }

    public Object getBiggestExpense() {
        return gastoRepository.getBiggestExpense();
    }
}
