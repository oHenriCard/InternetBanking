import { useState, useEffect } from "react";
import Header from "../../header-login/header";
import "./home.css";
import { jwtDecode } from "jwt-decode"; 

function Home() {
  // Remova os dados estáticos
  // const [transacoes, setTransacoes] = useState([...]); 
  // const [agencia, setAgencia] = useState("0001");
  // const [conta, setConta] = useState("12345-6");

  const [transacoes, setTransacoes] = useState([]);
  const [agencia, setAgencia] = useState("");
  const [conta, setConta] = useState("");
  const [saldo, setSaldo] = useState(0);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const token = localStorage.getItem('authToken');
      if (!token) {

        console.error("Nenhum token encontrado.");
        setError("Autenticação necessária.");
        return;
      }

      try {
        
        const contasResponse = await fetch('http://localhost:8080/contas', {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        if (!contasResponse.ok) throw new Error("Falha ao buscar contas.");

        const todasContas = await contasResponse.json();
        
        const decodedToken = jwtDecode(token);
        const userEmail = decodedToken.sub; 

        const minhaConta = todasContas.find(c => c.user.email === userEmail);

        if (!minhaConta) {
            throw new Error("Conta para o usuário logado não encontrada.");
        }

        setAgencia(minhaConta.agency);
        setConta(minhaConta.accountNum);
        setSaldo(minhaConta.balance);
        
        const extratoResponse = await fetch(`http://localhost:8080/operacoes/extrato/${minhaConta.accountNum}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        if (!extratoResponse.ok) throw new Error("Falha ao buscar extrato.");

        const extratoData = await extratoResponse.json();
        setTransacoes(extratoData);

      } catch (err) {
        setError(err.message);
        console.error(err);
      }
    };

    fetchData();
  }, []); 


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
          {error && <p className="error-message">{error}</p>}
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
                <tr key={t.id} className={t.value > 0 ? "credito" : "debito"}>
                  <td>{new Date(t.dateTime).toLocaleDateString()}</td>
                  <td>{t.description}</td>
                  <td className={t.value > 0 ? "credito" : "debito"}>
                    {t.value.toLocaleString("pt-BR", {
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