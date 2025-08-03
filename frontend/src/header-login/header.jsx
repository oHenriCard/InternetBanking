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
  };

  const handleNavigateToPagamento = () => {
    navigate('/pagamento')
  };
  
  }

  // NOVO: Função para lidar com o logout
  const handleLogout = async () => {
    // Assumindo que o token está salvo no localStorage com a chave 'jwtToken'
    const token = localStorage.getItem('jwtToken');

    try {
      // Chama a API de logout no back-end
      await fetch('http://localhost:8080/logout', { // ATENÇÃO: Use a URL correta da sua API
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });

    } catch (error) {
      console.error("Erro ao tentar fazer logout no servidor:", error);

    } finally {
      // Limpa o token do armazenamento local
      localStorage.removeItem('jwtToken');

      // Redireciona para a página de login
      // ATENÇÃO: Use a rota correta da sua página de login
      navigate('/'); 
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

        {/* NOVO: Botão de logout */}
        <button onClick={handleLogout} className="logout-button">Logout</button>
      </nav>
    </header>
  );

export default Header;