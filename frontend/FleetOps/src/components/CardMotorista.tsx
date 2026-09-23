import type { Motorista } from "../types/motorista";
import "../styles/app.css";

type PropriedadesCardMotorista = {
  motorista: Motorista;
  onAlterarStatus: (id: number, novoStatus: string) => void;
};

export function CardMotorista({ motorista, onAlterarStatus }: PropriedadesCardMotorista) {
  
  // 1. Corrigido para as palavras em MAIÚSCULAS do Java
  const obterCorStatus = (status: string) => {
    switch (status) {
      case "DISPONIVEL": return "bg-green";
      case "EM_ROTA": return "bg-yellow";
      case "INDISPONIVEL": return "bg-purple";
      case "INDESEJADO": return "bg-red";
      default: return "bg-gray";
    }
  };

  // 2. Criado para deixar o texto bonito no ecrã (com acentos)
  const formatarNomeStatus = (status: string) => {
    switch (status) {
      case "DISPONIVEL": return "Disponível";
      case "EM_ROTA": return "Em Rota";
      case "INDISPONIVEL": return "Indisponível";
      case "INDESEJADO": return "Indesejado";
      default: return status;
    }
  };

  // 3. Opções do Select corrigidas com os 'valores' idênticos ao banco de dados
  const opcoesStatus = [
    { rotulo: "Disponível", valor: "DISPONIVEL" },
    { rotulo: "Em Rota", valor: "EM_ROTA" },
    { rotulo: "Indisponível", valor: "INDISPONIVEL" },
    { rotulo: "Indesejado", valor: "INDESEJADO" },
  ];

  return (
    <div className="driver-card-container">
      <div className="card-left-panel">
        <div className="portrait-box">
          <div className="portrait-placeholder">
            <span className="portrait-initials">
              {motorista.nome_motorista ? motorista.nome_motorista.charAt(0) : "?"}
            </span>
          </div>
        </div>
        <h2 className="driver-name">{motorista.nome_motorista}</h2>

        <div className="status-badge">
          <span className={`dot ${obterCorStatus(motorista.status)}`}></span>
          <span>{formatarNomeStatus(motorista.status)}</span>
        </div>

        <div className="bottom-left-info">
          <div className="info-item">
            <span className="icon">📞</span>
            <span>{motorista.contato_motorista}</span>
          </div>
          <div className="info-item">
            <span className="icon">🗺️</span>
            <span>
              Rotas SP: {motorista.contador_rota_sp || 0}
            </span>
          </div>
        </div>
      </div>

      <div className="card-right-panel">
        <div className="info-section">
          <h3 className="section-title">Veículo & Agregado</h3>
          <ul className="info-list">
            <li>
              <strong>Tipo:</strong>{" "}
              {motorista.veiculo 
                ? `${motorista.veiculo.tipo_veiculo} (${motorista.veiculo.subtipo_veiculo || ""})` 
                : "Não informado"}
            </li>
            <li>
              <strong>Placa:</strong>{" "}
              {motorista.veiculo ? motorista.veiculo.placa_veiculo : "Não informada"}
            </li>
            {/* Proteção adicionada aqui para evitar Crash caso o agregado não venha no JSON */}
            <li>
              <strong>Agregado:</strong>{" "}
              {motorista.agregado ? motorista.agregado.nome_agregado : "Não informado (Oculto no BD)"}
            </li>
            <li>
              <strong>Contato:</strong>{" "}
              {motorista.agregado ? motorista.agregado.contato_agregado : "Não informado"}
            </li>
          </ul>
        </div>

        <div className="info-section">
          <h3 className="section-title">Último Manifesto</h3>
          <div className="manifesto-grid">
            <div className="manifesto-box">
              <span className="manifesto-label">Data</span>
              <span className="manifesto-value">
                {motorista.ultimo_manifesto 
                  ? new Date(motorista.ultimo_manifesto).toLocaleDateString("pt-BR")
                  : "Sem data"}
              </span>
            </div>
          </div>
        </div>

        <div className="info-section">
          <h3 className="section-title">Alterar Status</h3>
          <div className="status-dropdown-container">
            <span className={`dot ${obterCorStatus(motorista.status)}`}></span>
            <select
              className="status-dropdown"
              value={motorista.status}
              onChange={(e) => onAlterarStatus(motorista.id_motorista, e.target.value)}
            >
              {opcoesStatus.map((opcao) => (
                <option key={opcao.valor} value={opcao.valor}>
                  {opcao.rotulo}
                </option>
              ))}
            </select>
          </div>
        </div>
      </div>
    </div>
  );
}
