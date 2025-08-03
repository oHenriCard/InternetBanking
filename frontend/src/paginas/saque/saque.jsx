import { useState } from "react";
import Header from "../../header-login/header";
import "./saque.css";

function Saque() {
  const [valor, setValor] = useState("");
  const [mensagem, setMensagem] = useState("");
  const [erro, setErro] = useState("");
  
  // Saldo fixo para exemplo. O ideal é que venha do estado global ou API.
  const [saldoDisponivel] = useState(1500.00); 

  const handleSubmit = (e) => {
    e.preventDefault();
    setMensagem("");
    setErro("");

    const valorSaque = parseFloat(valor);

    if (!valor || valorSaque <= 0) {
      setErro("Digite um valor válido para o saque.");
      return;
    }

    if (valorSaque > saldoDisponivel) {
      setErro("Saldo insuficiente para realizar o saque.");
      return;
    }

    setMensagem(`Saque de ${valorSaque.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })} realizado com sucesso!`);
    setValor("");
  };

  return (
    <div className="saque-container">
      <Header />

      <div className="saque-content">
        <h1>Realizar Saque</h1>
        <p className="saldo-disponivel">
          Saldo disponível: {saldoDisponivel.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
        </p>

        <form onSubmit={handleSubmit} className="saque-form">
          <label htmlFor="valor">Valor do Saque</label>
          <input
            type="number"
            id="valor"
            value={valor}
            onChange={(e) => setValor(e.target.value)}
            placeholder="Digite o valor"
            min="0.01"
            step="0.01"
          />
          <button type="submit">Sacar</button>
        </form>

        {mensagem && <p className="mensagem-sucesso">{mensagem}</p>}
        {erro && <p className="mensagem-erro">{erro}</p>}
      </div>
    </div>
  );
}

export default Saque;