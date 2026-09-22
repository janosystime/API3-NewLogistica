import type { Role, Session, Theme } from "../types/app";
import { useState } from "react";

import Shell from "../components/layout/Shell";
import LoginPage from "../pages/LoginPage";
import PainelPage from "../pages/PainelPage";
import "../styles/painel/index.css";

// Roteamento simples por estado (login ⇄ painel).
// Ao adotar react-router, cada página vira uma <Route> e `session` vai para um contexto.
export default function AppRoutes() {
  const [session, setSession] = useState<Session | null>(null);
  const [theme, setTheme] = useState<Theme>("dark");

  function handleLogin(role: Role, userName: string) {
    setSession({ role, userName });
  }

  return (
    <Shell theme={theme}>
      {session
        ? (
            <PainelPage
              role={session.role}
              userName={session.userName}
              theme={theme}
              onToggleTheme={() => setTheme(t => (t === "dark" ? "light" : "dark"))}
              onLogout={() => setSession(null)}
            />
          )
        : (
            <LoginPage theme={theme} onLogin={handleLogin} />
          )}
    </Shell>
  );
}
