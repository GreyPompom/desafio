package com.example.rinha.repository;

import com.example.rinha.model.Cliente;
import com.example.rinha.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findTop10ByClienteIdOrderByRealizadaEmDesc(int clienteId);
}
