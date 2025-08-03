import { useState, useEffect } from "react";
import Header from "../../header-login/header";
import "./saque.css";
import { jwtDecode } from "jwt-decode";

function Saque() {
  const [value, setValue] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const [account, setAccount] = useState(null);

  useEffect(() => {
    const fetchAccountData = async () => {
      const token = localStorage.getItem('authToken');
      if (!token) {
        setError("Autenticação necessária.");
        return;
      }
      try {
        const response = await fetch('http://localhost:8080/contas', {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        if (!response.ok) throw new Error('Falha ao buscar dados da conta.');
        
        const allAccounts = await response.json();
        const userEmail = jwtDecode(token).sub;
        const userAccount = allAccounts.find(acc => acc.user.email === userEmail);
        
        if (userAccount) {
          setAccount(userAccount);
        } else {
          setError("Conta do usuário não encontrada.");
        }
      } catch (err) {
        setError(err.message);
      }
    };
    fetchAccountData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError("");

    if (!account) {
      setError("Não foi possível identificar a conta de origem.");
      return;
    }

    const withdrawValue = parseFloat(value);
    if (!value || withdrawValue <= 0) {
      setError("Digite um valor válido para o saque.");
      return;
    }
    if (withdrawValue > account.balance) {
      setError("Saldo insuficiente para realizar o saque.");
      return;
    }

    setIsLoading(true);
    const token = localStorage.getItem('authToken');
    const withdrawData = {
      accountNum: account.accountNum,
      value: withdrawValue
    };

    try {
      const response = await fetch('http://localhost:8080/operacoes/saque', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(withdrawData)
      });

      if (response.ok) {
        const updatedAccount = await response.json();
        setMessage(`Saque de ${withdrawValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })} realizado com sucesso!`);
        setAccount(updatedAccount); // Atualiza o saldo
        setValue("");
      } else {
        const errorData = await response.json();
        setError(errorData.message || 'Falha ao processar o saque.');
      }
    } catch (err) {
      setError("Falha na comunicação com o servidor.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="saque-container">
      <Header />
      <div className="saque-content">
        <h1>Realizar Saque</h1>
        {account && (
            <p className="saldo-disponivel">
                Saldo disponível: {account.balance.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
            </p>
        )}
        <form onSubmit={handleSubmit} className="saque-form">
          <label htmlFor="valor">Valor do Saque</label>
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
            {isLoading ? 'Processando...' : 'Sacar'}
          </button>
        </form>
        {message && <p className="mensagem-sucesso">{message}</p>}
        {error && <p className="mensagem-erro">{error}</p>}
      </div>
    </div>
  );
}

export default Saque;