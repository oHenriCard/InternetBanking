import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import './Header.css';

function Header() {
  const navigate = useNavigate(); 

  const handleNavigateToHome = () => {
    navigate('/home-page'); 
  };

  const handleNavigateToDeposito = () => {
    navigate('/deposito');
  };

  const handleNavigateToSaque = () => {
    navigate('/saque');
  };

  const handleNavigateToPagamento = () => {
    navigate('/pagamento');
  };

  const handleLogout = async () => {
    const token = localStorage.getItem('jwtToken');

    try {
      await fetch('http://localhost:8080/logout', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });
    } catch (error) {
      console.error("Erro ao tentar fazer logout no servidor:", error);
    } finally {
      localStorage.removeItem('jwtToken');
      navigate('/login'); 
    }
  };

  return (
    <header className="main-header">
      <h2>Bem-vindo (a)!</h2>
      <nav className="menu-opcoes">
        <button onClick={handleNavigateToHome}>Início</button>
        <button onClick={handleNavigateToDeposito}>Depósito</button>
        <button onClick={handleNavigateToSaque}>Saque</button>
        <button onClick={handleNavigateToPagamento}>Pagamento</button>
        <button onClick={handleLogout} className="logout-button">Logout</button>
      </nav>
    </header>
  );
}

export default Header;
