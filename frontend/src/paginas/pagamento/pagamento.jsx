import { useState } from "react";
import Header from "../../header-login/header";
import "./pagamento.css";

function Pagamento() {
  const [codigo, setCodigo] = useState("");
  const [valor, setValor] = useState("");
  const [mensagem, setMensagem] = useState("");
  const [erro, setErro] = useState("");

  const [saldoDisponivel] = useState(1500.00); 

  const handleSubmit = (e) => {
    e.preventDefault();
    setMensagem("");
    setErro("");

    const valorPagamento = parseFloat(valor);

    if (!codigo) {
      setErro("Por favor, insira o código da conta a pagar.");
      return;
    }

    if (!valor || valorPagamento <= 0) {
      setErro("Digite um valor de pagamento válido.");
      return;
    }

    if (valorPagamento > saldoDisponivel) {
      setErro("Saldo insuficiente para realizar o pagamento.");
      return;
    }

    setMensagem(`Pagamento no valor de ${valorPagamento.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })} para a conta ${codigo} realizado com sucesso!`);
    setValor("");
    setCodigo("");
  };

  return (
    <div className="pagamento-container">
      <Header />

      <div className="pagamento-content">
        <h1>Realizar Pagamento</h1>
        <p className="saldo-disponivel">
          Saldo disponível: {saldoDisponivel.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
        </p>

        <form onSubmit={handleSubmit} className="pagamento-form">
          <label htmlFor="codigo">Conta</label>
          <input
            type="text"
            id="codigo"
            value={codigo}
            onChange={(e) => setCodigo(e.target.value)}
            placeholder="Digite o número da conta ou código"
          />

          <label htmlFor="valor">Valor a pagar</label>
          <input
            type="number"
            id="valor"
            value={valor}
            onChange={(e) => setValor(e.target.value)}
            placeholder="Digite o valor do pagamento"
            min="0.01"
            step="0.01"
          />
          <button type="submit">Pagar</button>
        </form>

        {mensagem && <p className="mensagem-sucesso">{mensagem}</p>}
        {erro && <p className="mensagem-erro">{erro}</p>}
      </div>
    </div>
  );
}

export default Pagamento;