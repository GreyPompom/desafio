import { createContext, useState, ReactNode, useContext, JSX } from 'react';

import ClienteContextType from '../types/ClienteContextType';

const ClienteContext = createContext<ClienteContextType | undefined>(undefined);

type ClienteProviderProps = {
  children: ReactNode;
};

export const ClienteProvider = ({ children }: ClienteProviderProps): JSX.Element => {
  const [clienteId, setClienteId] = useState(1)
  const [saldo, setSaldo] = useState<number>(0);
  const [limite, setLimite] = useState<number>(0);

  return (
    <ClienteContext.Provider value={{ clienteId, setClienteId, saldo, setSaldo, limite, setLimite }}>
      {children}
    </ClienteContext.Provider>
  );
};

export const useClienteContext = (): ClienteContextType => {
  const context = useContext(ClienteContext);
  if (!context) {
    throw new Error('useClienteContext deve ser usado dentro de ClienteProvider');
  }
  return context;
};