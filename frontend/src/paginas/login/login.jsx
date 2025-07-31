import React, { useState } from 'react';
import './Login.css'; 
import { Link } from 'react-router-dom';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true);
        setError('');

        const loginData = { email, password };
        
        /*
        try {
            const response = await fetch('http://localhost:8080/login', { // URL da sua API
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Login bem-sucedido:', result);
                // Ex: Salvar token e redirecionar
                // localStorage.setItem('authToken', result.token);
                // window.location.href = '/dashboard'; 
            } else {
                const errorData = await response.json();
                setError(errorData.message || 'E-mail ou senha inválidos.');
            }
        } catch (err) {
            setError('Falha na comunicação com o servidor. Tente novamente.');
            console.error(err);
        } finally {
            setIsLoading(false);
        }
        */
        // ===================================================================
        //  Remover quando integrar com o back-end
        // ===================================================================
        console.log('Dados que seriam enviados:', loginData);
        setTimeout(() => {
            if (email === 'usuario@teste.com' && password === 'senha123') {
                alert('Login simulado com sucesso!');
            } else {
                setError('E-mail ou senha inválidos (dados de simulação).');
            }
            setIsLoading(false);
        }, 1000);
        // ===================================================================
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h1>Bem-vindo!</h1>
                <p>Acesse sua conta para continuar.</p>
                
                <form onSubmit={handleSubmit}>
                    <div className="input-group">
                        <label htmlFor="email">E-mail</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="seuemail@exemplo.com"
                            required
                            disabled={isLoading}
                        />
                    </div>
                    
                    <div className="input-group">
                        <label htmlFor="password">Senha</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Sua senha"
                            required
                            disabled={isLoading}
                        />
                    </div>
                    
                    {error && <div className="error-message">{error}</div>}
                    
                    <button type="submit" className="login-button" disabled={isLoading}>
                        {isLoading ? 'Entrando...' : 'Entrar'}
                    </button>
                </form>
                
                <div className="footer-links">
                    <Link to="/cadastro">Criar uma conta</Link>
                </div>
            </div>
        </div>
    );
}

export default Login;