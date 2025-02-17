package com.example.rinha.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name ="transacoes")
@Entity(name = "Transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int valor;
    private String tipo;
    private String descricao;
    private LocalDateTime realizadaEm;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;  // Relacionamento com Cliente

    public Transacao() {
    }

    public Transacao(String descricao, String tipo, int valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Transacao(LocalDateTime realizadaEm, String descricao, String tipo, int valor, Cliente cliente) {
        this.realizadaEm = realizadaEm;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(LocalDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
