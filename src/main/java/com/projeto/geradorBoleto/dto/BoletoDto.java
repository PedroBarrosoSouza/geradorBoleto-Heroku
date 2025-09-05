package com.projeto.geradorBoleto.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "Dados necessários para gerar um boleto")
public class BoletoDto {

    @Schema(description = "Nome completo do pagador", example = "Pedro Silva", required = true)
    @NotBlank(message = "O nome não pode ser nulo ou vazio.")
    private String nome;

    @Schema(description = "CPFdo pagador", example = "12345678900", required = true)
    @NotBlank(message = "O cpf não pode ser nulo ou vazio.")
    private String cpf;

    @Schema(description = "Valor do boleto (deve ser enviado sem virgula, será dividido por 100)", example = "9999" ,required = true)
    @NotNull(message = "O valor não pode ser nulo ou vazio.")
    private Integer valor;

    @Schema(description = "E-mail do pagador", example = "pedro@email.com", required = false)
    @Email(message = "O e-mail deve ter um formato válido.")
    private String email;

    @Schema(description = "Endereço do pagador", example = "Rua José Bonifacio, 252 - Vila Planalto", required = true)
    @NotBlank(message = "O endereço não pode ser nulo ou vazio.")
    private String endereco;

    public BoletoDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public @NotNull(message = "O valor não pode ser nulo ou vazio.") Integer getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "O valor não pode ser nulo ou vazio.") Integer valor) {
        this.valor = valor;
    }
}
