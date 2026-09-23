import type { Role, Theme } from "../../types/app";
import { ChevronRight, LayoutGrid, LogOut, Moon, RotateCcw, Sun, Upload } from "lucide-react";

type Props = {
  role: Role;
  userName: string;
  theme: Theme;
  onToggleTheme: () => void;
  onLogout: () => void;
  onReset: () => void;
  onImport: () => void;
};

export default function Sidebar({ role, userName, theme, onToggleTheme, onLogout, onReset, onImport }: Props) {
  return (
    <div className="nav sidenav">
      <div className="brand-mini">
        <div className="brand-mark small">JS</div>
        {" "}
        Janosys
      </div>
      <div className="nav-items">
        <button className="active">
          <LayoutGrid size={16} />
          {" "}
          Painel operacional
          <ChevronRight size={13} className="chev" />
        </button>
      </div>
      <div className="sidenav-footer">
        <span>{userName}</span>
        <span className="role-tag">{role === "gerente" ? "Gerência" : "Operacional"}</span>
        <button className="btn-ghost small" onClick={onImport}>
          <Upload size={13} />
          {" "}
          Importar .csv
        </button>
        <button className="btn-ghost small" onClick={onToggleTheme}>
          {theme === "dark" ? <Sun size={13} /> : <Moon size={13} />}
          {" "}
          {theme === "dark" ? "Modo claro" : "Modo escuro"}
        </button>
        <button className="btn-ghost small" onClick={onReset}>
          <RotateCcw size={13} />
          {" "}
          Recarregar exemplo
        </button>
        <button className="btn-ghost small" onClick={onLogout}>
          <LogOut size={13} />
          {" "}
          Sair
        </button>
      </div>
    </div>
  );
}
