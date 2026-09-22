export type DriverStatus = "disponivel" | "em_rota" | "outros" | "indesejado";
export type StatusFilter = DriverStatus | "all";

export type Evaluation = {
  nota: number;
  obs: string;
  data: string;
  operador: string;
};

export type Driver = {
  id: string;
  nome: string;
  veiculo: string;
  placa: string;
  telefone: string;
  manifestoData: string; // ISO yyyy-mm-dd
  status: DriverStatus;
  lockedBy: string | null;
  avaliacoes: Evaluation[];
};
