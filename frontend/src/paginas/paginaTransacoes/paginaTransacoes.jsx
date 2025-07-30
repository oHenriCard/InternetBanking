// src/pages/PaginaTransacoes.jsx

import { useState } from 'react';
import './PaginaTransacoes.css';

function PaginaTransacoes() {
  const [saldo, setSaldo] = useState(5000.75); // valor fake pra saldo

  const [contaDestino, setContaDestino] = useState('');
  const [valor, setValor] = useState('');

  const handleTransferencia = (event) => {
    event.preventDefault();

    const valorNumerico = parseFloat(valor);

    if (!contaDestino || valorNumerico <= 0) {
      alert('Por favor, preencha a conta de destino e um valor válido.');
      return;
    }
    if (valorNumerico > saldo) {
      alert('Saldo insuficiente para realizar esta transferência.');
      return;
    }

    alert(`Transferência de R$ ${valor} para a conta ${contaDestino} enviada com sucesso!`);

    setSaldo(saldo - valorNumerico);

    setContaDestino('');
    setValor('');
  };

  const formatarMoeda = (valor) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(valor);
  };

  return (
    <div className="transacoes-container">
      <div className="saldo-display">
        <h3>Saldo Atual</h3>
        <p>{formatarMoeda(saldo)}</p>
      </div>

      <form className="transacoes-form" onSubmit={handleTransferencia}>
        <h1>Realizar Transferência</h1>
        
        <div className="form-group">
          <label htmlFor="conta-destino">Conta de Destino</label>
          <input
            type="text"
            id="conta-destino"
            placeholder="Digite o número da conta"
            value={contaDestino}
            onChange={(e) => setContaDestino(e.target.value)}
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
          />
        </div>
        
        <button type="submit" className="submit-btn">Transferir</button>
      </form>
    </div>
  );
}

export default PaginaTransacoes;