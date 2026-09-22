import type { StatusFilter } from "../../types/driver";
import { Search } from "lucide-react";
import { STATUS, STATUS_KEYS } from "../../constants/status";

type Props = {
  query: string;
  setQuery: (q: string) => void;
  statusFilter: StatusFilter;
  setStatusFilter: (f: StatusFilter) => void;
  count: number;
};

export default function ListToolbar({ query, setQuery, statusFilter, setStatusFilter, count }: Props) {
  return (
    <div className="list-toolbar">
      <div className="search-box">
        <Search size={14} />
        <input placeholder="Buscar motorista ou veículo" value={query} onChange={e => setQuery(e.target.value)} />
      </div>
      <div className="filter-chips">
        <button className={statusFilter === "all" ? "active" : ""} onClick={() => setStatusFilter("all")}>Todos</button>
        {STATUS_KEYS.map((key) => {
          const s = STATUS[key];
          return (
            <button
              key={key}
              className={statusFilter === key ? "active" : ""}
              style={statusFilter === key ? { borderColor: s.color, color: s.color } : {}}
              onClick={() => setStatusFilter(key)}
            >
              <span className="dot" style={{ background: s.color }} />
              {" "}
              {s.label.split(" · ")[0]}
            </button>
          );
        })}
      </div>
      <span className="list-count">
        {count}
        {" "}
        motoristas · fila por manifesto
      </span>
    </div>
  );
}
