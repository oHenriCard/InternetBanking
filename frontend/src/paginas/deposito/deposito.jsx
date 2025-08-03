import { useState, useEffect } from "react";
import Header from "../../header-login/header";
import "./deposito.css";
import { jwtDecode } from "jwt-decode";

function Deposito() {
  const [value, setValue] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [accountNumToDeposit, setAccountNumToDeposit] = useState("");

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
    
        const fetchAccountData = async () => {
          try {
            const response = await fetch('http://localhost:8080/contas', {
              headers: { 'Authorization': `Bearer ${token}` }
            });
            if (!response.ok) throw new Error('Falha ao buscar contas.');
            
            const allAccounts = await response.json();
            const userEmail = jwtDecode(token).sub;
            const userAccount = allAccounts.find(acc => acc.user.email === userEmail);
            
            if (userAccount) {
              setAccountNumToDeposit(userAccount.accountNum);
            }
          } catch (err) {
            setError(err.message);
          }
        };
        fetchAccountData();
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError("");

    const depositValue = parseFloat(value);
    if (!value || depositValue <= 0) {
      setError("Digite um valor válido para depósito.");
      return;
    }
    if (!accountNumToDeposit) {
        setError("Número da conta para depósito não encontrado.");
        return;
    }

    setIsLoading(true);
    const token = localStorage.getItem('authToken');
    const depositData = {
      accountNum: accountNumToDeposit,
      value: depositValue
    };

    try {
      const response = await fetch('http://localhost:8080/operacoes/deposito', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(depositData)
      });

      if (response.ok) {
        setMessage(`Depósito de ${depositValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })} realizado com sucesso!`);
        setValue("");
      } else {
        const errorData = await response.json();
        setError(errorData.message || 'Falha ao processar o depósito.');
      }
    } catch (err) {
      setError("Falha na comunicação com o servidor.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="deposito-container">
      <Header />
      <div className="deposito-content">
        <h1>Realizar Depósito</h1>
        <p>Depósito para a conta: <strong>{accountNumToDeposit || "Carregando..."}</strong></p>
        <form onSubmit={handleSubmit} className="deposito-form">
          <label htmlFor="valor">Valor do Depósito</label>
          <input
            type="number"
            id="valor"
            value={value}
            onChange={(e) => setValue(e.target.value)}
            placeholder="Digite o valor"
            min="0.01"
            step="0.01"
            disabled={isLoading}
          />
          <button type="submit" disabled={isLoading}>
            {isLoading ? 'Processando...' : 'Depositar'}
          </button>
        </form>
        {message && <p className="mensagem-sucesso">{message}</p>}
        {error && <p className="mensagem-erro">{error}</p>}
      </div>
    </div>
  );
}

export default Deposito;