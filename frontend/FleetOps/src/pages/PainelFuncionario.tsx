import { useMemo, useState } from "react";
import { BotoesFiltro } from "../components/BotoesFiltro";
import { CardMotorista } from "../components/CardMotorista";
import { mockMotoristas } from "../services/mockMotoristas";
import "../styles/app.css";

export default function PainelFuncionario() {
  const [motoristas, setMotoristas] = useState(mockMotoristas);
  const [idMotoristaSelecionado, setIdMotoristaSelecionado] = useState<number | null>(null);
  const [filtroStatus, setFiltroStatus] = useState<string>("Todos");
  const [buscaVeiculo, setBuscaVeiculo] = useState<string>("");

  const motoristasFiltrados = useMemo(() => {
    return motoristas
      .filter((motorista) => {
        const correspondeStatus = filtroStatus === "Todos" || motorista.status === filtroStatus;
        const correspondeVeiculo = motorista.veiculo.tipo_veiculo.toLowerCase().includes(buscaVeiculo.toLowerCase());
        return correspondeStatus && correspondeVeiculo;
      })
      .sort((a, b) => new Date(a.ultimo_manifesto).getTime() - new Date(b.ultimo_manifesto).getTime());
  }, [motoristas, filtroStatus, buscaVeiculo]);

  const motoristaSelecionado = motoristas.find(m => m.id_motorista === idMotoristaSelecionado);

  const handleAlterarStatus = (id: number, novoStatus: string) => {
    setMotoristas(listaAnterior =>
      listaAnterior.map(m =>
        m.id_motorista === id ? { ...m, status: novoStatus } : m,
      ),
    );
  };

  const obterCorStatus = (status: string) => {
    switch (status) {
      case "Disponível":
        return "bg-green";
      case "Indisponível em Rota":
        return "bg-yellow";
      case "Indisponível":
        return "bg-purple";
      case "Indesejado":
        return "bg-red";
      default:
        return "bg-gray";
    }
  };

  // Extraímos a lógica do ternário para uma função limpa e com indentação padrão
  const renderizarConteudoPrincipal = () => {
    if (motoristaSelecionado) {
      return (
        <div className="detail-view">
          <div className="operator-warning">
            🔒 Em contacto com você (Outros operadores veem em uso)
          </div>
          <CardMotorista
            motorista={motoristaSelecionado}
            onAlterarStatus={handleAlterarStatus}
          />
        </div>
      );
    }

    return (
      <div className="empty-state">
        Selecione um motorista na lista para ver os detalhes
      </div>
    );
  };

  return (
    <div className="dashboard-layout">
      <aside className="sidebar">
        <div className="sidebar-filters">
          <input
            type="text"
            placeholder="Buscar por veículo..."
            className="search-input"
            value={buscaVeiculo}
            onChange={e => setBuscaVeiculo(e.target.value)}
          />

          <BotoesFiltro filtroAtual={filtroStatus} definirFiltro={setFiltroStatus} />

          <div className="list-counter">
            {motoristasFiltrados.length}
            {" "}
            motoristas · fila por manifesto
          </div>
        </div>

        <div className="driver-list">
          {motoristasFiltrados.map(motorista => (
            <div
              key={motorista.id_motorista}
              className={`list-item ${idMotoristaSelecionado === motorista.id_motorista ? "selected" : ""}`}
              onClick={() => setIdMotoristaSelecionado(motorista.id_motorista)}
            >
              <div className="item-header">
                <span className="item-name">{motorista.nome_motorista}</span>
                <span className="item-vehicle">
                  🚛
                  {" "}
                  {motorista.veiculo.tipo_veiculo}
                </span>
              </div>
              <div className="item-footer">
                <span className="item-date">
                  📅
                  {" "}
                  {new Date(motorista.ultimo_manifesto).toLocaleDateString("pt-BR")}
                </span>
                <div className="item-status">
                  <span className={`dot ${obterCorStatus(motorista.status)}`}></span>
                  <span>{motorista.status}</span>
                </div>
              </div>
            </div>
          ))}
        </div>
      </aside>

      <main className="main-content">
        {renderizarConteudoPrincipal()}
      </main>
    </div>
  );
}
