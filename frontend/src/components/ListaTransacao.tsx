import { memo } from 'react';
import { List, ListItem, ListItemText, Typography } from '@mui/material';
import  Transacao  from '../types/TransacoesContextType';
import { CurrencyBRL, formatarData } from '../utils/formatadorReal';

interface ListaTransacoesProps {
  transacoes: Transacao[];
  ultimaAtualizacao?: string;
}

const ListaTransacoes = memo(({ transacoes, ultimaAtualizacao }: ListaTransacoesProps) => {
    console.log('[ListaTransacoes] Transações recebidas:', transacoes);
  console.log('[ListaTransacoes] Última atualização:', ultimaAtualizacao);
  return(
    
  <List sx={{ bgcolor: 'background.paper', borderRadius: 2, overflow: 'hidden' }}>
    {transacoes.map((transacao, index) => (
      <TransacaoItem
        key={`${transacao.realizada_em}-${index}`}
        transacao={transacao}
        isLast={index === transacoes.length - 1}
      />
    ))}
    
    {ultimaAtualizacao && (
      <Typography variant="caption" color="text.secondary" sx={{ mt: 2, display: 'block' }}>
        Última atualização: {formatarData(ultimaAtualizacao)}
      </Typography>
    )}
  </List>
  )
    
});

const TransacaoItem = memo(({ transacao, isLast }: { transacao: Transacao; isLast: boolean }) => (
  <ListItem
    divider={!isLast}
    sx={{
      borderLeft: `4px solid ${transacao.tipo === 'r' ? '#4caf50' : '#f44336'}`,
      transition: 'background-color 0.2s',
      '&:hover': { bgcolor: 'action.hover' }
    }}
  >
    <ListItemText
      primary={
        <Typography variant="body1" component="div">
          {transacao.tipo === 'r' ? 'Crédito' : 'Débito'}:{' '}
          <CurrencyBRL value={transacao.valor} />
        </Typography>
      }
      secondary={
        <>
          <Typography component="span" variant="body2" color="text.secondary" display="block">
            {transacao.descricao}
          </Typography>
          <Typography component="span" variant="caption" color="text.secondary">
            {formatarData(transacao.realizada_em)}
          </Typography>
        </>
      }
      secondaryTypographyProps={{ component: 'div' }}
    />
  </ListItem>
));

export default ListaTransacoes;