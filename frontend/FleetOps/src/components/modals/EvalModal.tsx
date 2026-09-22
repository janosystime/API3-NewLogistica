import type { Driver } from "../../types/driver";
import { CheckCircle2 } from "lucide-react";
import { useState } from "react";
import Modal from "../common/Modal";

type Props = {
  driver: Driver;
  onSubmit: (nota: number, obs: string) => void;
  onClose: () => void;
};

export default function EvalModal({ driver, onSubmit, onClose }: Props) {
  const [nota, setNota] = useState(8);
  const [obs, setObs] = useState("");
  return (
    <Modal title={`Concluir rota · ${driver.nome}`} onClose={onClose}>
      <p className="modal-copy">
        Registre a avaliação do frete antes de liberar
        {driver.nome.split(" ")[0]}
        {" "}
        como disponível.
      </p>
      <label className="eval-score modal-score">
        Nota
        <select value={nota} onChange={e => setNota(Number(e.target.value))}>
          {Array.from({ length: 11 }, (_, i) => i).map(n => <option key={n} value={n}>{n}</option>)}
        </select>
      </label>
      <textarea placeholder="Observação sobre o frete realizado..." value={obs} onChange={e => setObs(e.target.value)} />
      <div className="modal-actions">
        <button className="btn-ghost" onClick={onClose}>Cancelar</button>
        <button className="btn-primary" disabled={!obs.trim()} onClick={() => onSubmit(nota, obs)}>
          <CheckCircle2 size={15} />
          {" "}
          Concluir e liberar
        </button>
      </div>
    </Modal>
  );
}
