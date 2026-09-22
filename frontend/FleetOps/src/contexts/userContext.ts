import type { Dispatch, SetStateAction } from "react";
import type { Session } from "../types/app";
import { createContext } from "react";

export type UserContextProps = {
  session: Session;
  setSession: Dispatch<SetStateAction<Session>>;
  isAuthenticated: boolean;
  setIsAuthenticated: Dispatch<SetStateAction<boolean>>;
};

export const UserContext = createContext<UserContextProps | null>(null);
