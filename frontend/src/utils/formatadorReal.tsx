export const CurrencyBRL = ({ value }: { value: number }) => (
    <>{new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(value / 100)}</>
  );
  
  export const formatarData = (isoString: string) => 
    new Date(isoString).toLocaleString('pt-BR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
    });