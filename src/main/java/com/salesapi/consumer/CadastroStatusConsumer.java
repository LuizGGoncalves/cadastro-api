package com.salesapi.consumer;

import com.salesapi.model.ValidacaoResponse;
import com.salesapi.service.UsuarioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroStatusConsumer {
    @Autowired
    UsuarioService usuarioService;

    @RabbitListener(queues = "cadastroStatus")
    public void validar(ValidacaoResponse validacaoResponse){
        usuarioService.activeUsuario(validacaoResponse);
    }
}
