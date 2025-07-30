import { useState } from 'react';
import Header from './menu/Header';
import PaginaInicial from './paginas/paginaCadastro/PaginaInicial';
import PaginaTransacoes from './paginas/paginaTransacoes/paginaTransacoes';

function App() {
  const [paginaAtiva, setPaginaAtiva] = useState('inicio');

  const renderizarPagina = () => {
    switch (paginaAtiva) {
      case 'transacao':
        return <PaginaTransacoes />;
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
