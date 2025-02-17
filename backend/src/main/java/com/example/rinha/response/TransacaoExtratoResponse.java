package com.example.rinha.response;
import java.time.LocalDateTime;

public class TransacaoExtratoResponse {
    private int valor;
    private LocalDateTime realizadaEm;
    private String descricao;
    private String tipo;

    public TransacaoExtratoResponse(int valor, String tipo, String descricao, LocalDateTime realizadaEm) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

    public int getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }
}