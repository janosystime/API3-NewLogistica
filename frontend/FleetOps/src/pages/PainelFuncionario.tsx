import { useMemo, useState, useEffect } from "react";
import { BotoesFiltro } from "../components/BotoesFiltro";
import { CardMotorista } from "../components/CardMotorista";
import "../styles/app.css";

export default function PainelFuncionario() {
  const [motoristas, setMotoristas] = useState<any[]>([]);
  const [idMotoristaSelecionado, setIdMotoristaSelecionado] = useState<number | null>(null);
  const [filtroStatus, setFiltroStatus] = useState<string>("Todos");
  const [buscaVeiculo, setBuscaVeiculo] = useState<string>("");

  // 1. Busca os dados reais do Java
  useEffect(() => {
    fetch("http://localhost:8080/api/motoristas")
      .then((resposta) => resposta.json())
      .then((dadosReais) => {
        const dadosFormatados = dadosReais.map((dado: any) => ({
          ...dado,
          id_motorista: dado.idMotorista,
          nome_motorista: dado.nomeMotorista,
          cpf_motorista: dado.cpfMotorista,
          contato_motorista: dado.contatoMotorista,
          ultimo_manifesto: dado.ultimoManifesto,
          veiculo: {
            ...dado.veiculo,
            tipo_veiculo: dado.veiculo ? dado.veiculo.tipoVeiculo : "N/A",
            placa_veiculo: dado.veiculo ? dado.veiculo.placaVeiculo : "N/A",
          },
        }));
        setMotoristas(dadosFormatados);
      })
      .catch((erro) => console.error("Erro ao buscar motoristas do banco:", erro));
  }, []);

  // 2. Traduz o nome do filtro do React para o formato que vem do Java
  const mapearStatusParaBackend = (statusFrontend: string) => {
    const mapa: Record<string, string> = {
      "Disponível": "DISPONIVEL",
      "Em Rota": "EM_ROTA",
      "Indisponível": "INDISPONIVEL",
      "Indesejado": "INDESEJADO",
    };
    return mapa[statusFrontend] || statusFrontend;
  };

  // 3. Aplica os filtros e a busca
  const motoristasFiltrados = useMemo(() => {
    const statusBackendDesejado = mapearStatusParaBackend(filtroStatus);

    return motoristas
      .filter((motorista) => {
        const correspondeStatus =
          filtroStatus === "Todos" || motorista.status === statusBackendDesejado;
        const correspondeVeiculo = motorista.veiculo.tipo_veiculo
          .toLowerCase()
          .includes(buscaVeiculo.toLowerCase());
        return correspondeStatus && correspondeVeiculo;
      })
      .sort(
        (a, b) =>
          new Date(a.ultimo_manifesto).getTime() - new Date(b.ultimo_manifesto).getTime()
      );
  }, [motoristas, filtroStatus, buscaVeiculo]);

  // 4. Encontra o motorista clicado para mandar para o cartão grande
  const motoristaSelecionado = motoristas.find(
    (m) => m.id_motorista === idMotoristaSelecionado
  );

  const handleAlterarStatus = (id: number, novoStatus: string) => {
    setMotoristas((listaAnterior) =>
      listaAnterior.map((m) =>
        m.id_motorista === id ? { ...m, status: novoStatus } : m
      )
    );
  };

  // 5. Verifica os status em MAIÚSCULAS (padrão do banco de dados Java)
  const obterCorStatus = (status: string) => {
    switch (status) {
      case "DISPONIVEL":
        return "bg-green";
      case "EM_ROTA":
        return "bg-yellow";
      case "INDISPONIVEL":
        return "bg-purple";
      case "INDESEJADO":
        return "bg-red";
      default:
        return "bg-gray";
    }
  };

  // 6. Deixa o status mais bonito e legível para o usuário ler
  const formatarNomeStatus = (status: string) => {
    switch (status) {
      case "DISPONIVEL": return "Disponível";
      case "EM_ROTA": return "Em Rota";
      case "INDISPONIVEL": return "Indisponível";
      case "INDESEJADO": return "Indesejado";
      default: return status;
    }
  };

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
            onChange={(e) => setBuscaVeiculo(e.target.value)}
          />

          <BotoesFiltro filtroAtual={filtroStatus} definirFiltro={setFiltroStatus} />

          <div className="list-counter">
            {motoristasFiltrados.length} motoristas · fila por manifesto
          </div>
        </div>

        <div className="driver-list">
          {motoristasFiltrados.map((motorista) => (
            <div
              key={motorista.id_motorista}
              className={`list-item ${
                idMotoristaSelecionado === motorista.id_motorista ? "selected" : ""
              }`}
              // AQUI ESTÁ O CLIQUE QUE FAZ O CARTÃO ABRIR:
              onClick={() => setIdMotoristaSelecionado(motorista.id_motorista)}
            >
              <div className="item-header">
                <span className="item-name">{motorista.nome_motorista}</span>
                <span className="item-vehicle">
                  🚛 {motorista.veiculo.tipo_veiculo}
                </span>
              </div>
              <div className="item-footer">
                <span className="item-date">
                  📅 {new Date(motorista.ultimo_manifesto).toLocaleDateString("pt-BR")}
                </span>
                <div className="item-status">
                  <span className={`dot ${obterCorStatus(motorista.status)}`}></span>
                  <span>{formatarNomeStatus(motorista.status)}</span>
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