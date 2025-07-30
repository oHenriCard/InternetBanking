import './Header.css';

function Header({ onNavigate }){
    return (
        <header className='header-menu'>
            <nav>
                <ul>
                    <li><a href='#' onClick={() => onNavigate('inicio')}>Início</a></li>
                    <li><a href='#'onClick={() => onNavigate('transacao')}>Transações</a></li>
                    <li><a href='#'>Extrato</a></li>
                    <li><a href='#'>Conta corrente</a></li>
                </ul>
            </nav>
        </header>
    )
}

export default Header;