import type { Driver, StatusFilter } from "../../types/driver";
import { Calendar, Lock, Truck } from "lucide-react";
import { formatDate } from "../../utils/date";
import StatusPill from "../common/StatusPill";

type Props = {
  drivers: Driver[];
  selectedId: string | null;
  onSelect: (id: string) => void;
  query: string;
  statusFilter: StatusFilter;
};

export default function DriverList({ drivers, selectedId, onSelect, query, statusFilter }: Props) {
  const q = query.toLowerCase();
  const filtered = drivers.filter(d =>
    (statusFilter === "all" || d.status === statusFilter)
    && (d.nome.toLowerCase().includes(q) || d.veiculo.toLowerCase().includes(q)),
  );

  return (
    <div className="driver-list">
      {filtered.map((d) => {
        const locked = !!d.lockedBy && d.lockedBy !== "Você" && d.id !== selectedId;
        return (
          <button
            key={d.id}
            className={`driver-row${d.id === selectedId ? " active" : ""}${locked ? " locked" : ""}`}
            onClick={() => onSelect(d.id)}
          >
            <div className="driver-row-main">
              <span className="driver-name">{d.nome}</span>
              <span className="driver-meta">
                <Truck size={12} />
                {" "}
                {d.veiculo}
              </span>
            </div>
            <div className="driver-row-side">
              <span className="manifest-age">
                <Calendar size={11} />
                {" "}
                {formatDate(d.manifestoData)}
              </span>
              <StatusPill status={d.status} compact />
            </div>
            {locked && (
              <span className="locked-tag">
                <Lock size={11} />
                {" "}
                {d.lockedBy}
              </span>
            )}
          </button>
        );
      })}
      {filtered.length === 0 && <div className="empty-hint">Nenhum motorista encontrado.</div>}
    </div>
  );
}
