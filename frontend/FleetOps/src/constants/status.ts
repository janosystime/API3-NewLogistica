import type { DriverStatus } from "../types/driver";

export type StatusStyle = {
  label: string;
  color: string;
  bg: string;
};

export const STATUS: Record<DriverStatus, StatusStyle> = {
  disponivel: { label: "Disponível", color: "#2FBF71", bg: "rgba(47,191,113,0.14)" },
  em_rota: { label: "Indisponível · em rota", color: "#F0A93A", bg: "rgba(240,169,58,0.14)" },
  outros: { label: "Indisponível · outros", color: "#9B6BF2", bg: "rgba(155,107,242,0.14)" },
  indesejado: { label: "Indesejado", color: "#E5484D", bg: "rgba(229,72,77,0.14)" },
};

export const STATUS_KEYS = Object.keys(STATUS) as DriverStatus[];
