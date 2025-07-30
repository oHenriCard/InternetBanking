import './Header.css';

import './Header.css';

function Header({ onNavigate }){
    const handleNavigationClick = (e, pagina) => {
        e.preventDefault(); 
        onNavigate(pagina);
    };

    return (
        <header className='header-menu'>
            <nav>
                <ul>
                    <li><a href='#' onClick={(e) => handleNavigationClick(e, 'inicio')}>Início</a></li>
                    <li><a href='#'>Extrato</a></li>
                    <li><a href='#' onClick={(e) => handleNavigationClick(e, 'saque')}>Saque</a></li>
                    <li><a href='#' onClick={(e) => handleNavigationClick(e, 'deposito')}>Depósito</a></li>
                    <li><a href='#'>Pagamento</a></li>
                </ul>
            </nav>
        </header>
    )
}

export default Header;