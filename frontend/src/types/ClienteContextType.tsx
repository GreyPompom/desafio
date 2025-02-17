interface ClienteContextType {
    clienteId: number | null;
    setClienteId: (id: number) => void;
    saldo: number;
    setSaldo: (saldo: number) => void;
    limite: number;
    setLimite: (limite :number) => void;
  }
export default ClienteContextType;  