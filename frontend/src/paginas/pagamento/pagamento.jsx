import { useState, useEffect } from "react";
import Header from "../../header-login/header";
import "./pagamento.css";
import { jwtDecode } from "jwt-decode"; 

function Pagamento() {
  const [description, setDescription] = useState("");
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

    const paymentValue = parseFloat(value);
    if (!description) {
      setError("Por favor, insira uma descrição para o pagamento.");
      return;
    }
    if (!value || paymentValue <= 0) {
      setError("Digite um valor de pagamento válido.");
      return;
    }
    if (paymentValue > account.balance) {
      setError("Saldo insuficiente para realizar o pagamento.");
      return;
    }

    setIsLoading(true);
    const token = localStorage.getItem('authToken');
    const paymentData = {
      accountNum: account.accountNum,
      value: paymentValue,
      description: description
    };

    try {
      const response = await fetch('http://localhost:8080/operacoes/pagamento', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(paymentData)
      });

      if (response.ok) {
        const updatedAccount = await response.json();
        setMessage(`Pagamento de ${paymentValue.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })} realizado com sucesso!`);
        setAccount(updatedAccount); 
        setValue("");
        setDescription("");
      } else {
        const errorData = await response.json();
        setError(errorData.message || 'Falha ao processar o pagamento.');
      }
    } catch (err) {
      setError("Falha na comunicação com o servidor.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="pagamento-container">
      <Header />
      <div className="pagamento-content">
        <h1>Realizar Pagamento</h1>
        {account && (
          <p className="saldo-disponivel">
            Saldo disponível: {account.balance.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
          </p>
        )}
        <form onSubmit={handleSubmit} className="pagamento-form">
          <label htmlFor="description">Descrição</label>
          <input
            type="text"
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Ex: Conta de luz"
            disabled={isLoading}
          />
          <label htmlFor="valor">Valor a pagar</label>
          <input
            type="number"
            id="valor"
            value={value}
            onChange={(e) => setValue(e.target.value)}
            placeholder="Digite o valor do pagamento"
            min="0.01"
            step="0.01"
            disabled={isLoading}
          />
          <button type="submit" disabled={isLoading}>
            {isLoading ? 'Processando...' : 'Pagar'}
          </button>
        </form>
        {message && <p className="mensagem-sucesso">{message}</p>}
        {error && <p className="mensagem-erro">{error}</p>}
      </div>
    </div>
  );
}

export default Pagamento;