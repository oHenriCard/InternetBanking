import { useState } from "react";
import Header from "../../header-login/header";
import "./deposito.css";

function Deposito() {
  const [valor, setValor] = useState("");
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!valor || parseFloat(valor) <= 0) {
      setMensagem("Digite um valor válido para depósito.");
      return;
    }

    setMensagem(`Depósito de R$ ${parseFloat(valor).toFixed(2)} realizado com sucesso!`);
    setValor("");
  };

  return (
    <div className="deposito-container">
      <Header />

      <div className="deposito-content">
        <h1>Realizar Depósito</h1>
        <form onSubmit={handleSubmit} className="deposito-form">
          <label htmlFor="valor">Valor do Depósito</label>
          <input
            type="number"
            id="valor"
            value={valor}
            onChange={(e) => setValor(e.target.value)}
            placeholder="Digite o valor"
            min="0.01"
            step="0.01"
          />
          <button type="submit">Depositar</button>
        </form>

        {mensagem && <p className="mensagem">{mensagem}</p>}
      </div>
    </div>
  );
}

export default Deposito;
