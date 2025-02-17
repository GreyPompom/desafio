import { useState, useEffect } from 'react';
import axios from 'axios';
import api from '../services/api';
import { ExtratoResponse } from '../types/TransacoesContextType';

export const useExtrato = (clienteId: number | null) => {
  const [data, setData] = useState<ExtratoResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchExtrato = async () => {
        if (!clienteId) {
            setData(null);
            return;
          }

      try {
        setLoading(true);
        const response = await api.get<ExtratoResponse>(`/clientes/${clienteId}/extrato`);
        setData(response.data);
        console.log(data?.ultimasTransacoes)
        setError('');
      } catch (err) {
        let errorMessage = 'Erro desconhecido ao buscar extrato';
        
        if (axios.isAxiosError(err)) {
          errorMessage = err.response?.status === 404 
            ? 'Cliente não encontrado' 
            : err.response?.data?.message || errorMessage;
        }

        setError(errorMessage);
      } finally {
        setLoading(false);
      }
    };

    fetchExtrato();
  }, [clienteId]);

  return { data, loading, error };
};