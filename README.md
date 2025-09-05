Projeto Gerador de Boleto
-------------------------
Uma API REST desenvolvida em Spring Boot para gerar boletos em formato PDF, o boleto também é enviado por email se for recebido pela API e são convertidos para Base64 e retornados no JSON. 
Para o envio de email localmente, deve ser utilizada a branch `dev` pois a `main` está consumindo variáveis do servidor para configurar o envio de email.

Tecnologias
-----------
Este projeto foi construído utilizando as seguintes tecnologias:
- Java 8
- Spring Boot 2.7.18
- Maven
- React
- Springdoc OpenAPI: Para documentação da API (antigo Swagger)
- iTextPDF: Para geração do documento PDF
- ZXing: Para geração de código de barras e QR Code
- JUnit 5 & Mockito: Para testes unitários e de integração

Como Executar o Projeto
-----------------------
Para executar o projeto localmente, siga os seguintes passos:
- Clone este repositório: `git clone https://github.com/PedroBarrosoSouza/geradorBoleto-Heroku.git`
- Navegue até o diretório do projeto: `cd geradorBoleto-Heroku`
- Utilizar a branch dev: `git checkout dev`
- Execute a aplicação usando o Maven: `mvn spring-boot:run`

A aplicação estará disponível em `http://localhost:8080`

Endpoints da API
-------------------
A documentação completa da API está disponível no Swagger UI, acessível em `http://localhost:8080/swagger-ui.html`.

POST /boletos/gerar
Gera um boleto em formato PDF a partir dos dados do pagador.

URL: `http://localhost:8080/boletos/gerar`
Método: POST
Tipo de Conteúdo: application/json

Curl:
```
curl --location --request GET 'localhost:8080/boletos/gerar' \
--header 'Content-Type: application/json' \
--data-raw '{
  "nome": "Pedro Silva",
  "cpf": "12345678900",
  "email": "pedro@email.com",
  "endereco": "Rua José Bonifacio, 252 - Vila Planalto"
}'
```

Corpo da Requisição:
```JSON
{
  "nome": "Pedro Silva",
  "cpf": "12345678900",
  "email": "pedro@email.com",
  "endereco": "Rua José Bonifacio, 252 - Vila Planalto"
}
```



Resposta (200 OK):
```JSON
{
  "pdfBase64": "JVBERi0xLjQKJ...<string_base64_do_pdf>..."
}
```


Testes Automatizados
--------------------
O projeto inclui testes de unidade e de integração. Para rodar todos os testes, execute o seguinte comando no terminal: `mvn test`
