import { Box, Typography, Alert, CircularProgress } from '@mui/material';
import { useClienteContext } from '../context/ClienteContext';
import ListaTransacoes from '../components/ListaTransacao';
import { useExtrato } from '../hooks/useExtrato';
import { useStorageSync } from '../hooks/useStorageSync ';
import CabecalhoExtrato from '../components/CabecalhoExtrato';
import { useMemo } from 'react';

const Extrato = () => {
  const { clienteId, setClienteId, saldo, limite } = useClienteContext();
  const { data, loading, error } = useExtrato(clienteId);

  useStorageSync('clienteId', (newValue) => {
    const newId = Number(newValue);
    if (newId !== clienteId) setClienteId(newId);
  });

  const transacoes = useMemo(()=> data?.ultimasTransacoes || [], [data])
  const ultimaAtualizacao = data?.saldo.data_extrato;

  return (
    <Box component="main" sx={{ p: 3, maxWidth: 800, margin: '0 auto' }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Extrato Bancário
      </Typography>

      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

      {clienteId && (
        <>
          <CabecalhoExtrato saldo={saldo} limite={limite} />
          
          {loading ? (
            <CircularProgress sx={{ display: 'block', mx: 'auto' }} />
          ) : (
            <ListaTransacoes 
              transacoes={transacoes} 
              
              ultimaAtualizacao={ultimaAtualizacao} 
            />
          )}
        </>
      )}
    </Box>
  );
};

export default Extrato;