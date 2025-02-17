package com.example.rinha.response;

public class TransacaoResponse {
        private final int limite;
        private final int saldo;

        public TransacaoResponse(int limite, int saldo) {
            this.limite = limite;
            this.saldo = saldo;
        }

        public int getLimite() {
            return limite;
        }

        public int getSaldo() {
            return saldo;
        }

}
