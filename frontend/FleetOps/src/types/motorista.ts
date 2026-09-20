export type Motorista = {
  id_motorista: number;
  nome_motorista: string;
  cpf_motorista: string;
  contato_motorista: string;
  status: string; // Ex: 'Disponível', 'Indisponível', 'Indesejado'
  ultimo_manifesto: string; // Formato YYYY-MM-DD
  contador_rota_sp: number;
  veiculo: {
    placa_veiculo: string;
    tipo_veiculo: string;
    subtipo_veiculo: string;
  };
  agregado: {
    nome_agregado: string;
    contato_agregado: string;
  };
};
