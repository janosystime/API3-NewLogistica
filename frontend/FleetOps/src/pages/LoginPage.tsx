import type { Role, Theme } from "../types/app";
import { LogIn } from "lucide-react";
import { useState } from "react";
import RouteNetworkBackground from "../components/login/RouteNetworkBackground";

type Props = {
  theme: Theme;
  onLogin: (role: Role, userName: string) => void;
};

export default function LoginPage({ theme, onLogin }: Props) {
  const [user, setUser] = useState("");
  const [role, setRole] = useState<Role>("operador");
  return (
    <div className="login-screen">
      <RouteNetworkBackground theme={theme} />
      <div className="login-vignette" />
      <div className="login-card">
        <div className="brand">
          <div className="brand-mark">JS</div>
          <div>
            <div className="brand-name">Janosys</div>
            <div className="brand-sub">Painel de Motoristas</div>
          </div>
        </div>
        <h1>Entrar no painel</h1>
        <p className="login-copy">Acompanhe manifestos, status de rota e avaliações da frota em um só lugar.</p>
        <label className="field">
          Usuário
          <input value={user} onChange={e => setUser(e.target.value)} placeholder="operador.silva" />
        </label>
        <label className="field">
          Senha
          <input type="password" placeholder="••••••••" />
        </label>
        <div className="role-select">
          <span>Perfil (demonstração)</span>
          <div className="role-toggle">
            <span className="role-toggle-thumb" style={{ transform: role === "gerente" ? "translateX(100%)" : "translateX(0)" }} />
            <button className={role === "operador" ? "active" : ""} onClick={() => setRole("operador")}>Operacional</button>
            <button className={role === "gerente" ? "active" : ""} onClick={() => setRole("gerente")}>Gerência</button>
          </div>
        </div>
        <button className="btn-primary full" onClick={() => onLogin(role, user || "Você")}>
          <LogIn size={16} />
          {" "}
          Entrar
        </button>
      </div>
    </div>
  );
}
