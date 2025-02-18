package com.example.rinha.controller;

import com.example.rinha.response.TransacaoResponse;
import com.example.rinha.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/clientes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<TransacaoResponse> criarTransacao(@PathVariable Long id, @RequestBody com.example.rinha.model.Transacao transacao) {
        return transacaoService.processarTransacao(id, transacao);
    }
}
