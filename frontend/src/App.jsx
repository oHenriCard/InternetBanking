import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './paginas/login/login';
import Cadastro from './paginas/cadastro/cadastro';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/login" />} />
      <Route path="/login" element={<Login />} />
      <Route path="/cadastro" element={<Cadastro />} />
    </Routes>
  );
}

export default App;