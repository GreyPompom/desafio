


  export default interface Transacao {
    valor: number;
    tipo: TipoTransacao;
    descricao: string;
    realizada_em: string;
  }


export type TipoTransacao = 'r' | 'd';


export interface TransacaoResponse {
  saldo: number;
  limite: number;
}

export interface ExtratoResponse {
  saldo: {
    total: number;
    data_extrato: string;
    limite: number;
  };
  ultimasTransacoes: Transacao[];
}