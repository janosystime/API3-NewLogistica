import type { Theme } from "../types/app";
import type { StatusFilter } from "../types/driver";
import { useState } from "react";
import DriverDetail from "../components/drivers/DriverDetail";
import DriverList from "../components/drivers/DriverList";
import ListToolbar from "../components/drivers/ListToolbar";
import Sidebar from "../components/layout/Sidebar";
import EvalModal from "../components/modals/EvalModal";
import ImportModal from "../components/modals/ImportModal";
import { useDrivers } from "../hooks/useDrivers";
import useUsuario from "../hooks/useUsuario";

type Props = {
  theme: Theme;
  onToggleTheme: () => void;
};

export default function PainelPage({ theme, onToggleTheme }: Props) {
  const { session, setSession, setIsAuthenticated } = useUsuario();
  const { drivers, sortedDrivers, selected, selectedId, select, closeDetail, changeStatus, reset, importar } = useDrivers(session.userName);
  const [query, setQuery] = useState("");
  const [statusFilter, setStatusFilter] = useState<StatusFilter>("all");
  const [importOpen, setImportOpen] = useState(false);
  const [evalDriverId, setEvalDriverId] = useState<string | null>(null);

  const evalDriver = drivers.find(d => d.id === evalDriverId) ?? null;

  function logout() {
    setSession({ userName: "", role: "operador" });
    setIsAuthenticated(false);
  }

  return (
    <>
      <div className="app-shell">
        <Sidebar
          role={session.role}
          userName={session.userName}
          theme={theme}
          onToggleTheme={onToggleTheme}
          onLogout={logout}
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
              role={session.role}
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
      {evalDriver && <EvalModal driver={evalDriver} onClose={() => setEvalDriverId(null)} />}
    </>
  );
}
