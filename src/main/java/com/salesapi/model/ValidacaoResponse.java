package com.salesapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidacaoResponse implements Serializable {
    private UsuarioDto usuarioDto;
    private boolean isValid;
    private List<String> error;
}
