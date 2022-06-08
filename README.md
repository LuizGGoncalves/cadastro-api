# Cadastro-api

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQf_JdK6frnyA7H32vTW1WN_StbS8UetUPZTtWTnSoBOrg0IZORRM36vsFol5MMIp2G0kM&usqp=CAU" alt="exemplo imagem">

>Projeto com objetivo de praticar o uso de mensageria.
>A api [cadastro-api](https://github.com/LuizGGoncalves/cadastro-api) é responsavel pelo cadastro de novos usuarios
> e envio de uma mensagem para uma fila que é comsumida pela outra aplicaçao [compliance-service](https://github.com/LuizGGoncalves/compliance-service)
> que simula um compliance que libera ou nao o cadastro do usuario enviando uma mensagem para a outra fila que por sua vez é checada pela cadastro-api
> realizando as alteraçoes no banco de dados


### Criado Utilizando
* SpringBoot
* jackson
* Jpa
* Hibernate
* MySql
* RabbitMq
* Spring Security
* Autenticaçao JWT
* Docker

## Fluxo de Funcionamento

### Cadastro.
+ Realizar um Post de cadastro do Usuario.
+ Cadastrar o usuario na base de dados.
+ Enviar um mensagem para a fila para que o cadastro seja verificado pelo complience 

### Validar Cadastro
+ Verificar a fila de Cadastros validados pelo complience
+ Realizar a alteraçao no banco de dados permitindo o login do usuario
+ Caso nao seja valido o login enviar uma mensagem para a fila de erros do Cadastro

### Verificar Dados
+ Realizar o login para receber o token Jwt.
+ Enviar o token nas demais rotas para visualizar as informaçoes dos Cadastrados.