import { useState } from "react";
import Header from "../../header-login/header";
import "./home.css";

function Home() {
  const [transacoes] = useState([
    { id: 1, data: "2025-07-20", descricao: "Depósito", valor: 1500.0, tipo: "credito" },
    { id: 2, data: "2025-07-22", descricao: "Supermercado", valor: -230.5, tipo: "debito" },
    { id: 3, data: "2025-07-25", descricao: "Transferência Recebida", valor: 750.0, tipo: "credito" },
    { id: 4, data: "2025-07-30", descricao: "Conta de Luz", valor: -180.0, tipo: "debito" },
  ]);
  const [agencia] = useState("0001");
  const [conta] = useState("12345-6");

  const saldo = transacoes.reduce((acc, transacao) => acc + transacao.valor, 0);

  return (
    <div className="home-container">
      <Header />

      <div className="home-content">
        <div className="saldo-container">
          <div className="conta-info">
            <span>Agência: {agencia}</span>
            <span>Conta: {conta}</span>
          </div>

          <h2>Saldo Atual</h2>
          <p className="saldo-valor">
            {saldo.toLocaleString("pt-BR", {
              style: "currency",
              currency: "BRL",
            })}
          </p>
        </div>

        <div className="extrato">
          <h1>Extrato da Conta</h1>
          <table className="extrato-tabela">
            <thead>
              <tr>
                <th>Data</th>
                <th>Descrição</th>
                <th>Valor</th>
              </tr>
            </thead>
            <tbody>
              {transacoes.map((t) => (
                <tr key={t.id} className={t.tipo}>
                  <td>{t.data}</td>
                  <td>{t.descricao}</td>
                  <td className={t.tipo}>
                    {t.valor.toLocaleString("pt-BR", {
                      style: "currency",
                      currency: "BRL",
                    })}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default Home;