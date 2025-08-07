import React, { useState } from 'react';
import './Login.css';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    
    const [errors, setErrors] = useState({});
    
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true);
        setErrors({}); 
        const loginData = { email, password };

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Login bem-sucedido:', result);
                
                localStorage.setItem('authToken', result.token); 
                
                navigate('/home-page');
            } else {
                const errorData = await response.json();
                
                if (typeof errorData === 'object' && errorData !== null && errorData.message) {
                    setErrors({ general: errorData.message || 'E-mail ou senha inválidos.' });
                } else if (typeof errorData === 'object' && errorData !== null) {
                    setErrors(errorData); 
                } else {
                    setErrors({ general: 'E-mail ou senha inválidos.' });
                }
            }
        } catch (err) {
            setErrors({ general: 'Falha na comunicação com o servidor. Tente novamente.' });
            console.error(err);
        } finally {
            setIsLoading(false);
        }
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
                        {errors.email && <span className="field-error-message">{errors.email}</span>}
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
                        {errors.password && <span className="field-error-message">{errors.password}</span>}
                    </div>
                    
                    {errors.general && <div className="error-message">{errors.general}</div>}
                    
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