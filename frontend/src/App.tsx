import{ JSX } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { ClienteProvider } from './context/ClienteContext';
import Transacoes from './pages/Transacoes';
import Extrato from './pages/Extrato';


const App = () :JSX.Element => {
  return (
  <ClienteProvider>
    <Router>
      <Routes>
      <Route path="/" element={<Transacoes />} />
        <Route path="/transacoes" element={<Transacoes />} />
        <Route path="/extrato" element={<Extrato />} />
      </Routes>
    </Router>
  </ClienteProvider>
  )
};


export default App;