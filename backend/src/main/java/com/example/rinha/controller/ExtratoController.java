package com.example.rinha.controller;

import com.example.rinha.model.Cliente;
import com.example.rinha.model.Transacao;
import com.example.rinha.repository.ClienteRepository;
import com.example.rinha.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.rinha.response.ExtratoResponse;
import com.example.rinha.response.SaldoResponse;
import com.example.rinha.response.TransacaoExtratoResponse;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class ExtratoController {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    @Autowired
    public ExtratoController(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }


    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
    @GetMapping("/{id}/extrato")
    public ResponseEntity<?> getExtrato(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        List<Transacao> transacoes = transacaoRepository.findTop10ByClienteIdOrderByRealizadaEmDesc(cliente.getId());

        ExtratoResponse extrato = new ExtratoResponse(
                new SaldoResponse(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite()),
                transacoes.stream().map(t -> new TransacaoExtratoResponse(
                        t.getValor(), t.getTipo(), t.getDescricao(), t.getRealizadaEm()
                )).collect(Collectors.toList())
        );

        return ResponseEntity.ok().body(extrato);
    }





}