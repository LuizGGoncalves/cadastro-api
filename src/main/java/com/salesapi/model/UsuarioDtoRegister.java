package com.salesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDtoRegister implements Serializable {
    private  Long id;
    @NotNull
    private  String cpf;
    @NotNull
    private  String nome;
    @NotNull
    private  String telefone;
    @NotNull
    private  String sexo;
    @NotNull
    private  LocalDate dataDeNascimento;
    @Email
    private  String email;
    @NotNull
    private  String password;
}
