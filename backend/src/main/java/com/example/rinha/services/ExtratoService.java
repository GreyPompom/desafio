package com.example.rinha.services;

import com.example.rinha.model.Cliente;
import com.example.rinha.model.Transacao;
import com.example.rinha.repository.ClienteRepository;
import com.example.rinha.repository.TransacaoRepository;
import com.example.rinha.response.ExtratoResponse;
import com.example.rinha.response.SaldoResponse;
import com.example.rinha.response.TransacaoExtratoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtratoService {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public ExtratoService(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public ExtratoResponse gerarExtrato(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return null;
        }

        List<Transacao> transacoes = transacaoRepository.findTop10ByClienteIdOrderByRealizadaEmDesc(cliente.getId());

        return new ExtratoResponse(
                new SaldoResponse(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite()),
                transacoes.stream().map(t -> new TransacaoExtratoResponse(
                        t.getValor(), t.getTipo(), t.getDescricao(), t.getRealizadaEm()
                )).collect(Collectors.toList())
        );
    }
}
