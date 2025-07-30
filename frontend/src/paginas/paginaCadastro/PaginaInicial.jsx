import { useState } from 'react';
import axios from 'axios'; // 1. Importar o axios
import './PaginaInicial.css';

function PaginaCadastro() {
  const [nome, setNome] = useState('');
  const [cpf, setCpf] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault(); 
    
    
    const userData = {
      name: nome,
      cpf: cpf,
      email: email,
      password: senha 
    };

    try {
      const response = await axios.post('http://localhost:8080/usuarios', userData);
      
      console.log('Resposta do servidor:', response.data);
      alert('Cadastro realizado com sucesso!');
      
      setNome('');
      setCpf('');
      setEmail('');
      setSenha('');

    } catch (error) {
      console.error('Ocorreu um erro ao cadastrar:', error);
      
      if (error.response && error.response.data) {
        alert(`Erro no cadastro: ${error.response.data.message || error.response.data}`);
      } else {
        alert('Não foi possível realizar o cadastro. Tente novamente.');
      }
    }
  };

  return (
    <div className="cadastro-container">
      
      <div className="welcome-message">
        <h2>Bem-vindo(a)!</h2>
        <p>Preencha o formulário abaixo para criar sua conta.</p>
      </div>

      <form className="cadastro-form" onSubmit={handleSubmit}>
        <h1>Cadastro de Usuário</h1>

        <div className="form-group">
          <label htmlFor="nome">Nome completo</label>
          <input
            type="text"
            id="nome"
            value={nome}
            placeholder="Digite o seu nome completo"
            onChange={(e) => setNome(e.target.value)}
            required
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            placeholder="Digite o seu email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="cpf">CPF</label>
          <input
            type="text"
            id="cpf"
            placeholder="Digite o seu CPF"
            value={cpf}
            onChange={(e) => setCpf(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="senha">Senha</label>
          <input
            type="password"
            id="senha"
            placeholder="Crie sua senha (mínimo 6 caracteres)"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            required
          />
        </div>
        
        <button type="submit" className="submit-btn">Cadastrar</button>
      </form>
    </div>
  );
}

export default PaginaCadastro;