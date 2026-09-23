import type { Driver } from "../types/driver";
import { TODAY } from "../constants/app";
import { STATUS_KEYS } from "../constants/status";

const VEHICLES = ["Truck 3/4", "Toco", "Truck", "Carreta", "Bitrem", "VUC"];
const FIRST = ["Marcos", "Eliane", "Josué", "Patrícia", "Rogério", "Tainá", "Nilson", "Cássia", "Wagner", "Iracema", "Deivid", "Solange", "Adilson", "Marlene"];
const LAST = ["Vieira", "Souza", "Bittencourt", "Ramalho", "Prado", "Correia", "Nogueira", "Farias", "Assunção", "Bezerra"];

// Dados mock. Quando houver backend, troque por uma chamada HTTP mantendo a assinatura.
export function seedDrivers(): Driver[] {
  const today = new Date(TODAY);
  return FIRST.map((f, i) => {
    const daysAgo = 2 + i * 3 + (i % 4);
    const manifestDate = new Date(today);
    manifestDate.setDate(manifestDate.getDate() - daysAgo);
    return {
      id: `mot-${i + 1}`,
      nome: `${f} ${LAST[i % LAST.length]}`,
      veiculo: VEHICLES[i % VEHICLES.length],
      placa: `${["ABC", "JVQ", "RTL", "PMK"][i % 4]}-${1000 + i * 37}`,
      telefone: `(12) 9${8000 + i * 111}-${4000 + i * 17}`,
      manifestoData: manifestDate.toISOString().slice(0, 10),
      status: STATUS_KEYS[i % STATUS_KEYS.length],
      lockedBy: i === 5 ? "Operador Renan" : null,
      avaliacoes: i % 2 === 0
        ? [
            { nota: 8, obs: "Pontual, sem avarias na carga.", data: "2026-08-14", operador: "Operador Bia" },
            { nota: 6, obs: "Atraso de 40min no carregamento.", data: "2026-07-30", operador: "Operador Caio" },
          ]
        : [
            { nota: 9, obs: "Excelente comunicação durante a rota.", data: "2026-08-20", operador: "Operador Renan" },
          ],
    };
  });
}
