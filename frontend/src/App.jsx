import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './paginas/login/login';
import Cadastro from './paginas/cadastro/cadastro';
import Home from './paginas/homepage/home'
import Deposito from './paginas/deposito/deposito'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/login" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
      <Route path="/home-page" element={<Home />} />
      <Route path="/deposito" element={<Deposito />} /> 
    </Routes>
  );
}

export default App;