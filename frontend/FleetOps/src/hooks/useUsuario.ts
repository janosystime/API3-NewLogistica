import { use } from "react";
import { UserContext } from "../contexts/userContext";

export default function useUsuario() {
  const userContext = use(UserContext);

  if (!userContext)
    throw new Error("useUsuario deve ser usado dentro de um UserProvider");

  return userContext;
}
