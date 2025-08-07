import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './cadastro.css';

function Register() {
    const [name, setName] = useState('');
    const [cpf, setCpf] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const [errors, setErrors] = useState({});
    
    const [success, setSuccess] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true);
        setErrors({});
        setSuccess('');

        if (password !== confirmPassword) {
            setErrors({ confirmPassword: 'As senhas não coincidem!' });
            setIsLoading(false);
            return;
        }

        const userData = {
            name,
            cpf,
            email,
            password
        };

        try {
            const response = await fetch('http://localhost:8080/usuarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });
            
            if (response.status === 201) {
                setSuccess('Cadastro realizado com sucesso! Redirecionando para o login...');
                setErrors({});
                setName('');
                setCpf('');
                setEmail('');
                setPassword('');
                setConfirmPassword('');
                
                setTimeout(() => {
                    navigate('/login');
                }, 2000);

            } else if (response.status === 400) {
                const errorData = await response.json();
                setErrors(errorData); 
            } else {
                const errorData = await response.json();
                setErrors({ general: errorData.message || `Erro ${response.status}: Não foi possível realizar o cadastro.` });
            }

        } catch (err) {
            setErrors({ general: 'Falha na comunicação com o servidor. Tente novamente mais tarde.' });
            console.error('Erro no cadastro:', err);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="register-container">
            <div className="register-card">
                <h1>Crie sua Conta</h1>
                <p>É rápido e fácil.</p>
                
                <form onSubmit={handleSubmit}>
                    <div className="input-group">
                        <label htmlFor="name">Nome Completo</label>
                        <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} placeholder="Seu Nome" required disabled={isLoading} />
                        {errors.name && <span className="field-error-message">{errors.name}</span>}
                    </div>
                    
                    <div className="input-group">
                        <label htmlFor="cpf">CPF</label>
                        <input type="text" id="cpf" value={cpf} onChange={(e) => setCpf(e.target.value)} placeholder="000.000.000-00" required disabled={isLoading} />
                        {errors.cpf && <span className="field-error-message">{errors.cpf}</span>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="email">E-mail</label>
                        <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="seuemail@exemplo.com" required disabled={isLoading} />
                        {errors.email && <span className="field-error-message">{errors.email}</span>}
                    </div>
                    
                    <div className="input-group">
                        <label htmlFor="password">Senha</label>
                        <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Mínimo 6 caracteres" required disabled={isLoading} />
                        {errors.password && <span className="field-error-message">{errors.password}</span>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="confirmPassword">Confirmar Senha</label>
                        <input type="password" id="confirmPassword" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} placeholder="Confirme sua senha" required disabled={isLoading} />
                        {errors.confirmPassword && <span className="field-error-message">{errors.confirmPassword}</span>}
                    </div>
                    
                    {errors.general && <div className="error-message">{errors.general}</div>}
                    {success && <div className="success-message">{success}</div>}
                    
                    <button type="submit" className="register-button" disabled={isLoading}>
                        {isLoading ? 'Cadastrando...' : 'Cadastrar'}
                    </button>
                </form>
                
                <div className="footer-links">
                    <span>Já tem uma conta? </span>
                    <Link to="/login">Faça o login</Link>
                </div>
            </div>
        </div>
    );
}

export default Register;