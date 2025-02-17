import { Typography } from '@mui/material';
import TransacaoForm from '../components/TransacaoForm';

const Transacoes = () => {
  return (
    <>
      <Typography variant="h4" component="h1" gutterBottom align="center">
        Nova Transação
      </Typography>
      <TransacaoForm />
    </>
  );
};

export default Transacoes;