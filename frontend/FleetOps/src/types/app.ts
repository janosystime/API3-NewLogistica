export type Role = "operador" | "gerente";
export type Theme = "dark" | "light";

export type Session = {
  role: Role;
  userName: string;
};
