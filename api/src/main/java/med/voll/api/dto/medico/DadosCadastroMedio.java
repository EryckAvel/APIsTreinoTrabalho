package med.voll.api.dto.medico;

import med.voll.api.dto.endereco.DadosEndereco;

public record DadosCadastroMedio(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {

}
