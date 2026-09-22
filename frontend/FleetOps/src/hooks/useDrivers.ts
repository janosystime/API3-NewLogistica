import type { VeiculoDTO } from "../services/importacao.service";
import type { Driver, DriverStatus } from "../types/driver";
import { useMemo, useState } from "react";
import { TODAY } from "../constants/app";
import { seedDrivers } from "../services/drivers.service";
import { veiculosParaDrivers } from "../services/importacao.service";

// Estado e regras da lista de motoristas: seleção com "lock", status e avaliação
export function useDrivers(userName: string) {
  const [drivers, setDrivers] = useState<Driver[]>(seedDrivers);
  const [selectedId, setSelectedId] = useState<string | null>(null);

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

  // registra a avaliação e libera o motorista como disponível
  function concludeRoute(id: string, nota: number, obs: string) {
    setDrivers(ds => ds.map(d => d.id === id
      ? { ...d, status: "disponivel", avaliacoes: [{ nota, obs, data: TODAY, operador: userName }, ...d.avaliacoes] }
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

  return { drivers, sortedDrivers, selected, selectedId, select, closeDetail, changeStatus, concludeRoute, reset, importar };
}
