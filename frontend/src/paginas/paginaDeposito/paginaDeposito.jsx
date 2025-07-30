import { useState, useEffect } from 'react'; 
import './paginaDeposito.css';
import axios from 'axios';

function PaginaDeposito() {
  const [numeroConta, setNumeroConta] = useState('');
  const [valor, setValor] = useState('');

  const handledepósito = async (event) => {
    event.preventDefault();

    const dataToSend = {
      accountNum: numeroConta, 
      value: valor      
    };

    try {
      const response = await axios.post('http://localhost:8080/operacoes/deposito', dataToSend);
      
      const updatedAccount = response.data;

      alert(`Depósito de ${formatarMoeda(parseFloat(valor))} realizado com sucesso!`);

      setNumeroConta('');
      setValor('');

    } catch (error) {
      console.error('Erro ao realizar o depósito:', error);
      if (error.response && error.response.data) {
        alert(`Erro: ${error.response.data.message || 'Ocorreu um problema.'}`);
      } else {
        alert('Não foi possível conectar ao servidor. Tente novamente.');
      }
    }
  };

  const formatarMoeda = (valor) => {
    if (valor === null || valor === undefined) return 'R$ 0,00'; 
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(valor);
  };

  return (
    <div className="transacoes-container">

      <form className="transacoes-form" onSubmit={handledepósito}>
        <h1>Depositar dinheiro na conta</h1>
        
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
        
        <button type="submit" className="submit-btn">Depositar</button>
      </form>
    </div>
  );
}

export default PaginaDeposito;