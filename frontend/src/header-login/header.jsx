import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import './Header.css';

function Header() {
  const navigate = useNavigate(); 

  const handleNavigateToHome = () => {
    navigate('/home-page'); 
  };

  const handleNavigateToDeposito = () => {
    navigate('/deposito')
  };

  const handleNavigateToSaque = () => {
    navigate('/saque')
  }

  const handleNavigateToPagamento = () => {
    navigate('/pagamento')
  }

  return (
    <header className="main-header">
      <h2>Bem-vindo (a)!</h2>
      <nav className="menu-opcoes">
        <button onClick={handleNavigateToHome}>Início</button>
        <button onClick={handleNavigateToDeposito}>Depósito</button>
        <button onClick={handleNavigateToSaque}>Saque</button>
        <button onClick={handleNavigateToPagamento}>Pagamento</button>
      </nav>
    </header>
  );
}

export default Header;