import type { Role, Theme } from "../types/app";
import type { StatusFilter } from "../types/driver";
import { useState } from "react";
import DriverDetail from "../components/drivers/DriverDetail";
import DriverList from "../components/drivers/DriverList";
import ListToolbar from "../components/drivers/ListToolbar";
import Sidebar from "../components/layout/Sidebar";
import EvalModal from "../components/modals/EvalModal";
import ImportModal from "../components/modals/ImportModal";
import { useDrivers } from "../hooks/useDrivers";

type Props = {
  role: Role;
  userName: string;
  theme: Theme;
  onToggleTheme: () => void;
  onLogout: () => void;
};

export default function PainelPage({ role, userName, theme, onToggleTheme, onLogout }: Props) {
  const { drivers, sortedDrivers, selected, selectedId, select, closeDetail, changeStatus, concludeRoute, reset, importar } = useDrivers(userName);

  const [query, setQuery] = useState("");
  const [statusFilter, setStatusFilter] = useState<StatusFilter>("all");
  const [importOpen, setImportOpen] = useState(false);
  const [evalDriverId, setEvalDriverId] = useState<string | null>(null);

  const evalDriver = drivers.find(d => d.id === evalDriverId) ?? null;

  function handleConcludeRoute(nota: number, obs: string) {
    if (!evalDriverId)
      return;
    concludeRoute(evalDriverId, nota, obs);
    setEvalDriverId(null);
  }

  return (
    <>
      <div className="app-shell">
        <Sidebar
          role={role}
          userName={userName}
          theme={theme}
          onToggleTheme={onToggleTheme}
          onLogout={onLogout}
          onReset={reset}
          onImport={() => setImportOpen(true)}
        />
        <div className="app-content">
          <div className="col list-col">
            <ListToolbar query={query} setQuery={setQuery} statusFilter={statusFilter} setStatusFilter={setStatusFilter} count={sortedDrivers.length} />
            <DriverList drivers={sortedDrivers} selectedId={selectedId} onSelect={select} query={query} statusFilter={statusFilter} />
          </div>
          <div className="col detail-col">
            <DriverDetail
              driver={selected}
              role={role}
              onChangeStatus={changeStatus}
              onOpenEvalModal={setEvalDriverId}
              onClose={selected ? closeDetail : null}
            />
          </div>
        </div>
      </div>
      {importOpen && (
        <ImportModal
          onClose={() => setImportOpen(false)}
          onImport={(res) => {
            if (res.veiculosValidos.length > 0)
              importar(res.veiculosValidos);
          }}
        />
      )}
      {evalDriver && <EvalModal driver={evalDriver} onSubmit={handleConcludeRoute} onClose={() => setEvalDriverId(null)} />}
    </>
  );
}
