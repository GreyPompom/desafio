import { useState, useCallback } from 'react';
import api from '../services/api';
import { useClienteContext } from '../context/ClienteContext';
import Transacao,{  TransacaoResponse, TipoTransacao } from '../types/TransacoesContextType';

export const useTransacao = () => {
  const [error, setError] = useState<string>('');
  const [message, setMessage] = useState<string>('');
  const { clienteId, setSaldo, setLimite } = useClienteContext();

  const handleTransacao = useCallback(async (
    valor: number,
    tipo: TipoTransacao,
    descricao: string
  ): Promise<TransacaoResponse | null> => {
    setError('');
    setMessage('');

    try {
      if (!clienteId) throw new Error('Cliente não selecionado');
      if (!descricao || descricao.length > 10) throw new Error('Descrição inválida');
      if (valor <= 0) throw new Error('Valor deve ser positivo');

      const payload: Transacao = {
        valor,
        tipo,
        descricao,
        realizada_em: new Date().toISOString()
      };

      const { data } = await api.post<TransacaoResponse>(
        `/clientes/${clienteId}/transacoes`,
        payload
      );

      setMessage(`Transação realizada! Saldo: ${data.saldo}`);
      setSaldo(data.saldo);
      setLimite(data.limite);
      
      localStorage.setItem('clienteId', clienteId.toString());
      window.dispatchEvent(new Event('storage'));

      return data;
    } catch (err) {
      const errorMessage = err instanceof Error ? err.message : 'Erro desconhecido';
      setError(errorMessage);
      return null;
    }
  }, [clienteId, setSaldo, setLimite]);

  return { handleTransacao, message, error };
};