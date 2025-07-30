import { useState } from 'react';
import Header from './menu/Header';
import PaginaInicial from './paginas/paginaCadastro/paginaInicial';
import PaginaSaque from './paginas/paginaSaque/paginaSaque';
import PaginaDeposito from './paginas/PaginaDeposito/paginaDeposito';

function App() {
  const [paginaAtiva, setPaginaAtiva] = useState('inicio');

  const renderizarPagina = () => {
    switch (paginaAtiva) {
      case 'saque':
        return <PaginaSaque />;
      case 'deposito':
        return <PaginaDeposito />;
      case 'inicio':
      default:
        return <PaginaInicial />;
    }
  };

  return (
    <>
      <Header onNavigate={setPaginaAtiva} />

      <main style={{ padding: '20px' }}>
        {renderizarPagina()}
      </main>
    </>
  );
}

export default App;
