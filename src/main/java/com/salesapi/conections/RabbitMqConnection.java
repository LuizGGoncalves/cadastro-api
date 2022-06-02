package com.salesapi.conections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConnection {
    private static final String NOME_EXCHANGE = "amq.direct";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    private void adiciona(){
        var filaCadastrado = new Queue("cadastro",true,false,false);
        var ligacaoCadastro = new Binding(filaCadastrado.getName(),
                Binding.DestinationType.QUEUE, NOME_EXCHANGE, filaCadastrado.getName(),null);
        amqpAdmin.declareQueue(filaCadastrado);
        amqpAdmin.declareBinding(ligacaoCadastro);
    }

}
