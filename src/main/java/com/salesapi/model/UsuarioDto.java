package com.salesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("cpf")
    @NotNull
    private String cpf;
    @JsonProperty("nome")
    @NotNull
    private String nome;
    @JsonProperty("telefone")
    @NotNull
    private String telefone;
    @JsonProperty("sexo")
    @NotNull
    private String sexo;
    @JsonProperty("dataDeNascimento")
    @NotNull
    private LocalDate dataDeNascimento;
    @JsonProperty("email")
    @Email
    private String email;
}
