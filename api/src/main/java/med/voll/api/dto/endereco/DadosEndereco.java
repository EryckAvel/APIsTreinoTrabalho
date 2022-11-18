package med.voll.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @Pattern(regexp = "\\d{8}")
        @NotBlank
        String cep,
        @NotBlank
        String uf,
        String complemento,
        String numero,
        @NotBlank
        String cidade) {

}
