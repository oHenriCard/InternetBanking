import { useState, useEffect } from 'react'; 
import './paginaSaque.css';
import axios from 'axios';

function PaginaSaque() {
  const [numeroConta, setNumeroConta] = useState('');
  const [valor, setValor] = useState('');

  const handleSaque = async (event) => {
    event.preventDefault();

    const dataToSend = {
      accountNum: numeroConta, 
      value: valor      
    };

    try {
      const response = await axios.post('http://localhost:8080/operacoes/saque', dataToSend);
      
      const updatedAccount = response.data;
      setSaldo(updatedAccount.balance);

      alert(`Saque de ${formatarMoeda(parseFloat(valor))} realizado com sucesso!`);

      setNumeroConta('');
      setValor('');

    } catch (error) {
      console.error('Erro ao realizar o saque:', error);
      if (error.response && error.response.data) {
        alert(`Erro: ${error.response.data.message || 'Ocorreu um problema.'}`);
      } else {
        alert('Não foi possível conectar ao servidor. Tente novamente.');
      }
    }
  };

  return (
    <div className="transacoes-container">

      <form className="transacoes-form" onSubmit={handleSaque}>
        <h1>Retirar dinheiro de conta</h1>
        
        <div className="form-group">
          <label htmlFor="conta">Conta</label>
          <input
            type="text"
            id="conta"
            placeholder="Digite o número da conta"
            value={numeroConta}
            onChange={(e) => setNumeroConta(e.target.value)}
            required
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="valor">Valor (R$)</label>
          <input
            type="number"
            id="valor"
            placeholder="0,00"
            step="0.01"
            min="0.01"
            value={valor}
            onChange={(e) => setValor(e.target.value)}
            required
          />
        </div>
        
        <button type="submit" className="submit-btn">Sacar</button>
      </form>
    </div>
  );
}

export default PaginaSaque;