package com.example.rinha.response;
import java.time.LocalDateTime;


public class SaldoResponse {
    private final int total;
    private final LocalDateTime dataExtrato;
    private final int limite;

    public SaldoResponse(int total, LocalDateTime dataExtrato, int limite) {
        this.total = total;
        this.dataExtrato = dataExtrato;
        this.limite = limite;
    }

    public int getTotal() {
        return total;
    }

    public LocalDateTime getDataExtrato() {
        return dataExtrato;
    }

    public int getLimite() {
        return limite;
    }
}
