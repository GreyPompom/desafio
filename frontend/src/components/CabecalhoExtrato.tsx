import { Box, Typography } from '@mui/material';
import { CurrencyBRL } from '../utils/formatadorReal';

interface CabecalhoExtratoProps {
  saldo: number;
  limite: number;
}

const CabecalhoExtrato = ({ saldo, limite }: CabecalhoExtratoProps) => (
  <Box sx={{ 
    mb: 3,
    p: 2,
    borderRadius: 1,
    bgcolor: 'background.paper',
    boxShadow: 1
  }}>
    <Typography variant='h4' component="p">ID CLIENTE: </Typography>
    <Typography variant="h6" component="p" gutterBottom>
      Saldo Atual: <CurrencyBRL value={saldo} />
    </Typography>
    <Typography variant="subtitle1" component="p">
      Limite Disponível: <CurrencyBRL value={limite} />
    </Typography>
  </Box>
);

export default CabecalhoExtrato;