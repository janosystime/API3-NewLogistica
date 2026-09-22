import type { ReactNode } from "react";
import type { Session } from "../types/app";
import { useMemo, useState } from "react";
import { UserContext } from "../contexts/userContext";

type UserProviderProps = {
  children: ReactNode;
};

export default function UserProvider({ children }: UserProviderProps) {
  const [session, setSession] = useState<Session>({ userName: "", role: "operador" });
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  const contextValue = useMemo(() => ({ session, setSession, isAuthenticated, setIsAuthenticated }), [session, isAuthenticated]);

  return (
    <UserContext value={contextValue}>
      {children}
    </UserContext>
  );
}
