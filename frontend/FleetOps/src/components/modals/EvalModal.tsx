import type { SubmitEventHandler } from "react";
import type { Driver } from "../../types/driver";
import { CheckCircle2 } from "lucide-react";
import { useEffect } from "react";
import { useDrivers } from "../../hooks/useDrivers";
import useUsuario from "../../hooks/useUsuario";
import Modal from "../common/Modal";

type Props = {
  driver: Driver;
  onClose: () => void;
};

export default function EvalModal({ driver, onClose }: Props) {
  const { session } = useUsuario();
  const { avaliacao, setAvaliacao, sendAvaliacao } = useDrivers(session?.userName);

  const handleSubmit: SubmitEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault();
    sendAvaliacao();
  };

  useEffect(() => {
    setAvaliacao(prev => ({ ...prev, motoristaId: 42 }));
  }, [driver.id, setAvaliacao]);

  return (
    <Modal title={`Concluir rota · ${driver.nome}`} onClose={onClose}>
      <form className="flex flex-col gap-2" onSubmit={handleSubmit}>
        <p className="modal-copy">
          Registre a avaliação do frete antes de liberar
          {" "}
          {driver.nome.split(" ")[0]}
          {" "}
          como disponível.
        </p>
        <label className="eval-score modal-score">
          Nota:
          <select required className="ml-2" value={avaliacao.nota} onChange={e => setAvaliacao(prev => ({ ...prev, nota: Number(e.target.value) }))}>
            {Array.from({ length: 11 }, (_, i) => i).map(n => <option key={n} value={n}>{n}</option>)}
          </select>
        </label>
        <textarea required placeholder="Observação sobre o frete realizado..." value={avaliacao.feedback} onChange={e => setAvaliacao(prev => ({ ...prev, feedback: e.target.value.trim() }))} />
        <div className="modal-actions">
          <button className="btn-ghost" onClick={onClose}>Cancelar</button>
          <button type="submit" className="btn-primary" disabled={!avaliacao.feedback.trim()}>
            <CheckCircle2 size={15} />
            {" "}
            Concluir e liberar
          </button>
        </div>
      </form>
    </Modal>
  );
}
