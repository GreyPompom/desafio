package com.example.rinha.controller;

import com.example.rinha.model.Cliente;
import com.example.rinha.model.Transacao;
import com.example.rinha.repository.ClienteRepository;
import com.example.rinha.repository.TransacaoRepository;
import com.example.rinha.response.TransacaoResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class TransacaoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
    @Transactional
    @PostMapping("/{id}/transacoes")
    public ResponseEntity<TransacaoResponse> criarTransacao(@PathVariable Long id, @RequestBody Transacao transacao) {
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

        // Atualizar saldo do cliente e registrar a transação
        cliente.setSaldo(novoSaldo);
        transacao.setCliente(cliente);
        transacao.setRealizadaEm(LocalDateTime.now());

        try {
            clienteRepository.save(cliente);
            transacaoRepository.save(transacao);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar transação: " + e.getMessage());
        }

        // Criar resposta com o saldo atualizado
        TransacaoResponse response = new TransacaoResponse(cliente.getLimite(), cliente.getSaldo());
        return ResponseEntity.ok(response);
    }
}
