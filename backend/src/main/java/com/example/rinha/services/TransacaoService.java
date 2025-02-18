package com.example.rinha.services;

import com.example.rinha.model.Cliente;
import com.example.rinha.model.Transacao;
import com.example.rinha.repository.ClienteRepository;
import com.example.rinha.repository.TransacaoRepository;
import com.example.rinha.response.TransacaoResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class TransacaoService {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public ResponseEntity<TransacaoResponse> processarTransacao(Long id, Transacao transacao) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if (transacao.getValor() <= 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Valor inválido");
        }

        if (!"r".equals(transacao.getTipo()) && !"d".equals(transacao.getTipo())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Tipo inválido");
        }

        if (transacao.getDescricao() == null || transacao.getDescricao().trim().isEmpty() || transacao.getDescricao().length() > 10) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Descrição inválida");
        }

        int novoSaldo = cliente.getSaldo();
        if ("d".equals(transacao.getTipo())) {
            novoSaldo -= transacao.getValor();
            if (novoSaldo < -cliente.getLimite()) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Saldo insuficiente");
            }
        } else {
            novoSaldo += transacao.getValor();
        }

        cliente.setSaldo(novoSaldo);
        transacao.setCliente(cliente);
        transacao.setRealizadaEm(LocalDateTime.now());

        try {
            clienteRepository.save(cliente);
            transacaoRepository.save(transacao);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar transação: " + e.getMessage());
        }

        TransacaoResponse response = new TransacaoResponse(cliente.getLimite(), cliente.getSaldo());
        return ResponseEntity.ok(response);
    }
}