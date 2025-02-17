package com.example.rinha.response;

import java.util.List;

public class ExtratoResponse {
    private final SaldoResponse saldo;
    private final List<TransacaoExtratoResponse> ultimasTransacoes;

    public ExtratoResponse(SaldoResponse saldo, List<TransacaoExtratoResponse> ultimasTransacoes) {
        this.saldo = saldo;
        this.ultimasTransacoes = ultimasTransacoes;
    }

    public SaldoResponse getSaldo() {
        return saldo;
    }

    public List<TransacaoExtratoResponse> getUltimasTransacoes() {
        return ultimasTransacoes;
    }
}
