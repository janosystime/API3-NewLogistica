import type { Motorista } from "../types/motorista";
import "../styles/app.css";

type PropriedadesCardMotorista = {
  motorista: Motorista;
  onAlterarStatus: (id: number, novoStatus: string) => void;
};

export function CardMotorista({ motorista, onAlterarStatus }: PropriedadesCardMotorista) {
  const obterCorStatus = (status: string) => {
    if (status === "Disponível") {
      return "bg-green";
    }
    if (status === "Indisponível") {
      return "bg-purple";
    }
    if (status === "Indesejado") {
      return "bg-red";
    }
    if (status === "Indisponível em Rota") {
      return "bg-yellow";
    }
    return "bg-gray";
  };

  const opcoesStatus = [
    { rotulo: "Disponível", valor: "Disponível" },
    { rotulo: "Indisponível em Rota", valor: "Indisponível em Rota" },
    { rotulo: "Indisponível (Outros)", valor: "Indisponível" },
    { rotulo: "Indesejado", valor: "Indesejado" },
  ];

  return (
    <div className="driver-card-container">
      <div className="card-left-panel">
        <div className="portrait-box">
          <div className="portrait-placeholder">
            <span className="portrait-initials">
              {motorista.nome_motorista.charAt(0)}
            </span>
          </div>
        </div>
        <h2 className="driver-name">{motorista.nome_motorista}</h2>

        <div className="status-badge">
          <span className={`dot ${obterCorStatus(motorista.status)}`}></span>
          <span>{motorista.status}</span>
        </div>

        <div className="bottom-left-info">
          <div className="info-item">
            <span className="icon">📞</span>
            <span>{motorista.contato_motorista}</span>
          </div>
          <div className="info-item">
            <span className="icon">🛣️</span>
            <span>
              Rotas SP:
              {" "}
              {motorista.contador_rota_sp}
            </span>
          </div>
        </div>
      </div>

      <div className="card-right-panel">
        <div className="info-section">
          <h3 className="section-title">Veículo & Agregado</h3>
          <ul className="info-list">
            <li>
              <strong>Tipo:</strong>
              {" "}
              {`${motorista.veiculo.tipo_veiculo} (${motorista.veiculo.subtipo_veiculo})`}
            </li>
            <li>
              <strong>Placa:</strong>
              {" "}
              {motorista.veiculo.placa_veiculo}
            </li>
            <li>
              <strong>Agregado:</strong>
              {" "}
              {motorista.agregado.nome_agregado}
            </li>
            <li>
              <strong>Contato:</strong>
              {" "}
              {motorista.agregado.contato_agregado}
            </li>
          </ul>
        </div>

        <div className="info-section">
          <h3 className="section-title">Último Manifesto</h3>
          <div className="manifesto-grid">
            <div className="manifesto-box">
              <span className="manifesto-label">Data</span>
              <span className="manifesto-value">
                {new Date(motorista.ultimo_manifesto).toLocaleDateString("pt-BR")}
              </span>
            </div>
          </div>
        </div>

        {/* NOVA SESSÃO: ALTERAR STATUS COM DROPDOWN */}
        <div className="info-section">
          <h3 className="section-title">Alterar Status</h3>
          <div className="status-dropdown-container">
            <span className={`dot ${obterCorStatus(motorista.status)}`}></span>
            <select
              className="status-dropdown"
              value={motorista.status}
              onChange={e => onAlterarStatus(motorista.id_motorista, e.target.value)}
            >
              {opcoesStatus.map(opcao => (
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
