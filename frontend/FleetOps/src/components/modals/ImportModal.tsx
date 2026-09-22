import type { ResultadoImportacao } from "../../services/importacao.service";
import { useRef, useState } from "react";
import { importarCsv } from "../../services/importacao.service";
import Modal from "../common/Modal";

type EstadoImportacao = "selecao" | "enviando" | "sucesso" | "parcial" | "erro";

type Props = {
  onClose: () => void;
  onImport: (resultado: ResultadoImportacao) => void;
};

export default function ImportModal({ onClose, onImport }: Props) {
  const [estado, setEstado] = useState<EstadoImportacao>("selecao");
  const [arquivo, setArquivo] = useState<File | null>(null);
  const [resultado, setResultado] = useState<ResultadoImportacao | null>(null);
  const [erro, setErro] = useState<string | null>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  const enviando = estado === "enviando";

  async function handleImport() {
    if (!arquivo || enviando)
      return;
    setEstado("enviando");
    setErro(null);

    try {
      const res = await importarCsv(arquivo);
      setResultado(res);
      onImport(res);

      if (res.veiculosInvalidos.length === 0) {
        setEstado("sucesso");
      }
      else if (res.veiculosValidos.length > 0) {
        setEstado("parcial");
      }
      else {
        setEstado("erro");
      }
    }
    catch (e) {
      setErro(e instanceof Error ? e.message : "Erro inesperado ao importar o arquivo.");
      setEstado("erro");
    }
  }

  function handleVoltarSelecao() {
    setEstado("selecao");
    setResultado(null);
    setErro(null);
  }

  return (
    <Modal title="Importar cadastro mensal" onClose={onClose}>
      <p className="modal-copy">
        Envie o .csv de motoristas/veículos ou o manifesto do mês para atualizar a base.
        Essa importação costuma ser feita apenas 1x por mês — não é necessária a cada acesso.
      </p>

      {(estado === "selecao" || enviando) && (
        <>
          <input
            ref={inputRef}
            type="file"
            accept=".csv"
            hidden
            onChange={e => setArquivo(e.target.files?.[0] ?? null)}
          />
          <button className="btn-primary full" onClick={() => inputRef.current?.click()} disabled={enviando}>
            {arquivo ? arquivo.name : "Selecionar arquivo .csv"}
          </button>
          <button className="btn-primary full" onClick={handleImport} disabled={!arquivo || enviando}>
            {enviando ? "Importando..." : "Importar"}
          </button>
          <span className="import-hint">colunas esperadas: Placa, Agregado, Status, Motorista, TipoVeiculo</span>
        </>
      )}

      {estado === "sucesso" && resultado && (
        <>
          <p className="import-result import-success">
            Importação concluída com sucesso:
            {" "}
            {resultado.veiculosValidos.length}
            {" "}
            registro(s) importado(s).
          </p>
          <button className="btn-primary full" onClick={onClose}>Fechar</button>
        </>
      )}

      {estado === "parcial" && resultado && (
        <>
          <p className="import-result import-partial">
            Importação parcial:
            {" "}
            {resultado.veiculosValidos.length}
            {" "}
            registro(s) importado(s) e
            {" "}
            {resultado.veiculosInvalidos.length}
            {" "}
            linha(s) rejeitada(s).
          </p>
          <ul className="import-errors">
            {resultado.veiculosInvalidos.map(inv => (
              <li key={inv.linha}>
                <strong>
                  Linha
                  {inv.linha}
                  :
                </strong>
                {" "}
                {inv.mensagem}
              </li>
            ))}
          </ul>
          <button className="btn-primary full" onClick={onClose}>Fechar</button>
        </>
      )}

      {estado === "erro" && (
        <>
          <p className="import-result import-error">
            {erro ?? "Nenhum registro válido foi importado. Corrija as linhas abaixo e tente novamente."}
          </p>
          {resultado && resultado.veiculosInvalidos.length > 0 && (
            <ul className="import-errors">
              {resultado.veiculosInvalidos.map(inv => (
                <li key={inv.linha}>
                  <strong>
                    Linha
                    {inv.linha}
                    :
                  </strong>
                  {" "}
                  {inv.mensagem}
                </li>
              ))}
            </ul>
          )}
          <div className="modal-actions">
            <button className="btn-primary" onClick={handleVoltarSelecao}>Tentar novamente</button>
            <button className="btn-primary" onClick={onClose}>Fechar</button>
          </div>
        </>
      )}
    </Modal>
  );
}
