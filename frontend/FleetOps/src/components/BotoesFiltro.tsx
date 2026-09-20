import "../styles/app.css";

type PropriedadesFiltro = {
  filtroAtual: string;
  definirFiltro: (filtro: string) => void;
};

export function BotoesFiltro({ filtroAtual, definirFiltro }: PropriedadesFiltro) {
  const filtros = [
    { rotulo: "Todos", valor: "Todos", cor: "bg-gray" },
    { rotulo: "Disponível", valor: "Disponível", cor: "bg-green" },
    { rotulo: "Em Rota", valor: "Indisponível em Rota", cor: "bg-yellow" },
    { rotulo: "Indisponível", valor: "Indisponível", cor: "bg-purple" },
    { rotulo: "Indesejado", valor: "Indesejado", cor: "bg-red" },
  ];

  return (
    <div className="status-pills">
      {filtros.map(f => (
        <button
          key={f.valor}
          className={`pill-btn ${filtroAtual === f.valor ? "active" : ""}`}
          onClick={() => definirFiltro(f.valor)}
        >
          {f.rotulo !== "Todos" && <span className={`dot ${f.cor}`}></span>}
          {f.rotulo}
        </button>
      ))}
    </div>
  );
}
