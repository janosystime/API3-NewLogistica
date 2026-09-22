import type { VeiculoDTO } from "../services/importacao.service";
import type { Driver, DriverStatus } from "../types/driver";
import { useMemo, useState } from "react";
import z from "zod";
import { TODAY } from "../constants/app";
import { seedDrivers } from "../services/drivers.service";
import { veiculosParaDrivers } from "../services/importacao.service";

// Alterar para puxar serverUrl do .env
const serverUrl = "http://localhost:8080";

// eslint-disable-next-line unused-imports/no-unused-vars
const avaliacaoSchema = z.object({
  motoristaId: z.number().positive(),
  nota: z.number().min(1).max(5),
  feedback: z.string(),
});

type Avaliacao = z.infer<typeof avaliacaoSchema>;

// Estado e regras da lista de motoristas: seleção com "lock", status e avaliação
export function useDrivers(userName: string) {
  const [drivers, setDrivers] = useState<Driver[]>(seedDrivers);
  const [selectedId, setSelectedId] = useState<string | null>(null);
  const [avaliacao, setAvaliacao] = useState<Avaliacao>({ motoristaId: 0, nota: 1, feedback: "" });
  const [error, setError] = useState<string>("");

  // fila por manifesto: mais antigo primeiro
  const sortedDrivers = useMemo(
    () => [...drivers].sort((a, b) => new Date(a.manifestoData).getTime() - new Date(b.manifestoData).getTime()),
    [drivers],
  );
  const selected = drivers.find(d => d.id === selectedId) ?? null;

  function select(id: string) {
    setSelectedId(id);
    setDrivers(ds => ds.map((d) => {
      if (d.id === id)
        return d.lockedBy && d.lockedBy !== "Você" ? d : { ...d, lockedBy: "Você" };
      if (d.lockedBy === "Você")
        return { ...d, lockedBy: null };
      return d;
    }));
  }

  function closeDetail() {
    setDrivers(ds => ds.map(d => (d.id === selectedId && d.lockedBy === "Você") ? { ...d, lockedBy: null } : d));
    setSelectedId(null);
  }

  function changeStatus(id: string, status: DriverStatus) {
    setDrivers(ds => ds.map(d => d.id === id ? { ...d, status } : d));
  }

  async function sendAvaliacao() {
    if (avaliacao.motoristaId >= 0 || avaliacao.feedback === "") {
      setError("Dados Inválidos");
      return;
    }

    const responseAvaliacao = await fetch(`${serverUrl}/api/avaliacoes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(avaliacao),
    });

    if (!responseAvaliacao.ok) {
      setError("Erro na requisição");
      throw new Error(await responseAvaliacao.json());
    }

    concludeRoute();
  }

  // registra a avaliação e libera o motorista como disponível
  function concludeRoute() {
    setDrivers(ds => ds.map(d => d.id === String(avaliacao.motoristaId)
      ? { ...d, status: "disponivel", avaliacoes: [{ nota: avaliacao.nota, obs: avaliacao.feedback, data: TODAY, operador: userName }, ...d.avaliacoes] }
      : d));
  }

  function reset() {
    setDrivers(seedDrivers());
    setSelectedId(null);
  }

  function importar(veiculos: VeiculoDTO[]) {
    setDrivers(veiculosParaDrivers(veiculos));
    setSelectedId(null);
  }

  return { drivers, sortedDrivers, selected, selectedId, select, closeDetail, changeStatus, concludeRoute, reset, importar, avaliacao, setAvaliacao, sendAvaliacao, error };
}
