import { useState } from 'react';
import Header from './menu/Header';
import PaginaInicial from './paginas/paginaCadastro/PaginaInicial';
import PaginaSaque from './paginas/paginaSaque/paginaSaque';

function App() {
  const [paginaAtiva, setPaginaAtiva] = useState('inicio');

  const renderizarPagina = () => {
    switch (paginaAtiva) {
      case 'saque':
        return <PaginaSaque />;
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
