# 💳 Processador de Pagamentos e Operadora de Cartão de Crédito - API REST

> API REST desenvolvida em **Java 17+** e **Spring Boot 3** para simular o cadastro de clientes, análise automática de crédito e emissão de cartões de crédito.

---

## 💻 Sobre o Projeto

O **Processador de Pagamentos** é uma aplicação backend robusta que simula a inteligência de negócio de uma operadora de cartão de crédito. Ao cadastrar um novo cliente, o sistema analisa automaticamente o perfil de idade e faixa salarial para conceder um limite de crédito personalizado, além de gerar o número do cartão (Bandeira Visa), código de segurança (CVV) e data de expiração.

### 🌟 Destaques do Projeto:
* **Concessão de Limite Inteligente:** Lógica de negócio que calcula limites de R$ 1.000,00 a R$ 15.000,00 com base em perfil de idade e renda mensal.
* **Segurança de Dados:** Validações rigorosas para impedir duplicidade de CPF e E-mail.
* **Arquitetura Desacoplada:** Uso de DTOs com **Java Records** para isolar as entidades da base de dados do modelo de entrada/saída da API.
* **Mapeamento de Alta Performance:** Integração com **MapStruct** para conversão automática entre DTOs e Entities.
* **Tratamento de Exceções Customizado:** Exceções não verificadas (`ConflictException`, `ResourceNotFoundException`) para retornos HTTP claros e elegantes.
* **Fluxo Profissional Git Flow:** Organização de branches via `feature/*`, `develop` e `master`.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3**
    * Spring Data JPA
    * Spring Web
* **PostgreSQL** (Banco de Dados Relacional)
* **MapStruct** (Mapeamento de Objetos)
* **Lombok** (Produtividade e redução de código boilerplate)
* **Gradle** (Gerenciamento de Dependências)
* **Postman** (Testes de Requisições HTTP)

---

## 📐 Estrutura de Pacotes

```text
com.marcos.operadora_cartao_credito/
├── api/
│   └── controller/          # Endpoints HTTP da aplicação
├── business/
│   ├── dto/                 # Data Transfer Objects (Records: in/out)
│   ├── mapper/              # Interfaces do MapStruct para conversão de objetos
│   ├── GeraDadosCartaoService.java  # Regra de geração de cartão e cálculo de limite
│   └── ClienteService.java  # Regras de negócio do cliente e persistência
└── infrastructure/
    ├── entity/              # Entidades JPA (Cliente, Cartao, Endereco)
    ├── exception/           # Exceções personalizadas de negócio
    └── repository/          # Interfaces do Spring Data JPA
``` 
---
## 🚀 Endpoints da API
1. Solicitar Cartão / Cadastrar Cliente
- HTTP Method: POST
- URL: /cliente
- Body (JSON):

```text
{
  "nome": "Marcos Araújo",
  "email": "marcos@email.com",
  "idade": 28,
  "cpf": "12345678900",
  "rendaMensal": 5000.0,
  "dataVencimentoFatura": "10",
  "endereco": {
    "rua": "Rua das Flores",
    "numero": 100,
    "complemento": "Apto 101",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01234567"
  }
}
``` 
---
- Resposta de Sucesso (200 OK):
```text
{
  "id": 1,
  "nome": "Marcos Araújo",
  "email": "marcos@email.com",
  "cpf": "12345678900",
  "idade": 28,
  "rendaMensal": 5000.0,
  "cartao": {
    "numero": "4000762675896256",
    "dataExpiracao": "2029-08-01",
    "cvv": "042",
    "limiteCartao": 5000.0,
    "limiteCartaoDisponivel": 5000.0,
    "dataVencimentoFatura": 10
  }
}
``` 
---
2. Buscar Cliente por CPF
- HTTP Method: GET
- URL: /cliente?cpf=12345678900
- Resposta de Sucesso (200 OK): Retorna as informações do cliente e o cartão emitido.
  
## 👤 Autor
  Desenvolvido por Marcos Araújo.
