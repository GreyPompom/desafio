import { useState } from 'react';
import { TextField, Button, Box, Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { TipoTransacao } from '../types/TransacoesContextType';
import { useTransacao } from '../hooks/useTransacao';

const TransacaoForm = () => {
  const navigate = useNavigate();
  const [valor, setValor] = useState<number>(0);
  const [tipo, setTipo] = useState<TipoTransacao>('r');
  const [descricao, setDescricao] = useState<string>('');
  const { handleTransacao, message, error } = useTransacao();

  const isValidForm = descricao.length > 0 && descricao.length <= 10 && valor > 0;

  const handleSubmit = async () => {
    const result = await handleTransacao(valor, tipo, descricao);
    if (result) {
      navigate('/extrato', { state: { success: true } });
    }
  };

  return (
    <Box component="form" sx={{ padding: 2, maxWidth: 500, margin: '0 auto' }}>
      <TextField
        label="Valor (centavos)"
        type="number"
        value={valor}
        onChange={(e) => setValor(Math.abs(Number(e.target.value)))}
        fullWidth
        margin="normal"
        inputProps={{ min: 1 }}
      />

      <TextField
        select
        label="Tipo"
        value={tipo}
        onChange={(e) => setTipo(e.target.value as TipoTransacao)}
        fullWidth
        margin="normal"
        SelectProps={{ native: true }}
      >
        <option value="r">Recebível</option>
        <option value="d">Débito</option>
      </TextField>

      <TextField
        label="Descrição"
        value={descricao}
        onChange={(e) => setDescricao(e.target.value.slice(0, 10))}
        fullWidth
        margin="normal"
        inputProps={{ maxLength: 10 }}
        error={descricao.length === 0}
        helperText={descricao.length === 0 ? 'Campo obrigatório' : `${10 - descricao.length} caracteres restantes`}
      />

      

      <Button
        variant="contained"
        onClick={handleSubmit}
        fullWidth
        sx={{ mt: 3 }}
        disabled={!isValidForm}
      >
        Registrar Transação
      </Button>
      
      {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}
      {message && <Alert severity="success" sx={{ mt: 2 }}>{message}</Alert>}
    </Box>
  );
};

export default TransacaoForm;