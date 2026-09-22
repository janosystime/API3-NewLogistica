import type { Role } from "../../types/app";
import type { Driver, DriverStatus } from "../../types/driver";
import { Calendar, LayoutGrid, Lock, Phone, Star, Truck, X } from "lucide-react";
import { STATUS, STATUS_KEYS } from "../../constants/status";
import { ageDays } from "../../utils/date";
import Stars from "../common/Stars";
import StatusPill from "../common/StatusPill";

type Props = {
  driver: Driver | null;
  role: Role;
  onChangeStatus: (id: string, status: DriverStatus) => void;
  onOpenEvalModal: (id: string) => void;
  onClose: (() => void) | null;
};

// Avaliação só aparece ao concluir uma rota (modal) e no histórico da gerência
export default function DriverDetail({ driver, role, onChangeStatus, onOpenEvalModal, onClose }: Props) {
  if (!driver) {
    return (
      <div className="detail-empty">
        <LayoutGrid size={28} strokeWidth={1.4} />
        <p>Selecione um motorista na lista para ver os detalhes.</p>
      </div>
    );
  }

  const avg = driver.avaliacoes.length
    ? (driver.avaliacoes.reduce((s, a) => s + a.nota, 0) / driver.avaliacoes.length).toFixed(1)
    : "—";
  const isLockedByOther = !!driver.lockedBy && driver.lockedBy !== "Você";

  return (
    <div className="detail-panel">
      <div className="detail-header">
        <div>
          <h3>{driver.nome}</h3>
          <span className="detail-sub">
            <Truck size={13} />
            {" "}
            {driver.veiculo}
            {" "}
            ·
            {" "}
            {driver.placa}
          </span>
        </div>
        <div className="detail-header-right">
          <StatusPill status={driver.status} />
          {onClose && <button className="icon-btn" title="Fechar" onClick={onClose}><X size={14} /></button>}
        </div>
      </div>

      <div className="detail-grid">
        <div className="info-cell">
          <span>Telefone</span>
          <strong>
            <Phone size={12} />
            {" "}
            {driver.telefone}
          </strong>
        </div>
        <div className="info-cell">
          <span>Manifesto</span>
          <strong>
            <Calendar size={12} />
            {" "}
            {driver.manifestoData}
            {" "}
            (
            {ageDays(driver.manifestoData)}
            d)
          </strong>
        </div>
        {role === "gerente" && (
          <div className="info-cell">
            <span>Média geral</span>
            <strong>
              <Star size={12} />
              {" "}
              {avg}
              {" "}
              / 10
            </strong>
          </div>
        )}
      </div>

      {isLockedByOther
        ? (
            <div className="lock-warning">
              <Lock size={14} />
              {" "}
              Em contato por
              {" "}
              <b>{driver.lockedBy}</b>
              {" "}
              — aguarde a liberação.
            </div>
          )
        : (
            <div className="contact-note">
              <Lock size={12} />
              {" "}
              Em contato com você
            </div>
          )}

      <div className="status-actions">
        <span className="section-label">Alterar status</span>
        <div className="status-buttons">
          {STATUS_KEYS.map((key) => {
            const s = STATUS[key];
            const isConclude = key === "disponivel" && driver.status === "em_rota";
            return (
              <button
                key={key}
                disabled={isLockedByOther}
                className={`status-btn${driver.status === key ? " active" : ""}${isConclude ? " conclude" : ""}`}
                style={driver.status === key && !isConclude ? { borderColor: s.color, color: s.color } : {}}
                onClick={() => isConclude ? onOpenEvalModal(driver.id) : onChangeStatus(driver.id, key)}
              >
                {isConclude ? "Concluir rota (avaliar)" : s.label}
              </button>
            );
          })}
        </div>
      </div>

      {role === "gerente" && (
        <div className="eval-history">
          <span className="section-label">Histórico de avaliações</span>
          {driver.avaliacoes.length === 0 && <p className="empty-hint">Sem avaliações registradas.</p>}
          {driver.avaliacoes.map((a, i) => (
            <div className="eval-item" key={i}>
              <Stars value={a.nota} />
              <div>
                <p>{a.obs}</p>
                <span className="eval-meta">
                  {a.operador}
                  {" "}
                  ·
                  {" "}
                  {a.data}
                </span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
