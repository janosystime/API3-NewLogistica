import type { Driver } from "../types/driver";
import { TODAY } from "../constants/app";

export type VeiculoDTO = {
  placa: string;
  agregado: string;
  status: string;
  motorista: string;
  tipoVeiculo: string;
};

export type ErroLinhaCsv = {
  linha: number;
  mensagem: string;
};

export type ResultadoImportacao = {
  veiculosValidos: VeiculoDTO[];
  veiculosInvalidos: ErroLinhaCsv[];
};

function normalizeVeiculo(raw: Record<string, unknown>): VeiculoDTO {
  return {
    placa: String(raw.placa ?? raw.Placa ?? ""),
    agregado: String(raw.agregado ?? raw.Agregado ?? ""),
    status: String(raw.status ?? raw.Status ?? ""),
    motorista: String(raw.motorista ?? raw.Motorista ?? ""),
    tipoVeiculo: String(raw.tipoVeiculo ?? raw.TipoVeiculo ?? ""),
  };
}

function normalizeErro(raw: Record<string, unknown>): ErroLinhaCsv {
  return {
    linha: Number(raw.linha ?? raw.Linha ?? 0),
    mensagem: String(raw.mensagem ?? raw.Mensagem ?? ""),
  };
}

export async function importarCsv(arquivo: File): Promise<ResultadoImportacao> {
  const formData = new FormData();
  formData.append("arquivo", arquivo);

  const response = await fetch("/arquivo", { method: "POST", body: formData });

  if (!response.ok) {
    throw new Error(`Falha na importação (HTTP ${response.status}). Verifique se o arquivo é um .csv válido em UTF-8.`);
  }

  const data = await response.json();
  return {
    veiculosValidos: (data.veiculosValidos ?? []).map(normalizeVeiculo),
    veiculosInvalidos: (data.veiculosInvalidos ?? []).map(normalizeErro),
  };
}

export function veiculosParaDrivers(veiculos: VeiculoDTO[]): Driver[] {
  return veiculos.map((v, i) => ({
    id: `imp-${v.placa || i}`,
    nome: v.motorista,
    veiculo: v.tipoVeiculo,
    placa: v.placa,
    telefone: "",
    manifestoData: TODAY,
    status: v.status === "Ativo" ? "disponivel" : "outros",
    lockedBy: null,
    avaliacoes: [],
  }));
}
