# Password Validator API

## Descrição

Esta aplicação expõe uma API para validar senhas conforme os seguintes critérios:
- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial (!@#$%^&*()-+)
- Não possuir caracteres repetidos

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- JUnit 5
- Lambda AWS

## Como Executar

1. Clone o repositório:
   ```bash
      git clone https://github.com/tarikwesley/password-validator.git
      cd password-validator
   ```
2. Construir o projeto
   ```bash
   ./mvnw clean package
   ```
3. Testar a aplicação localmente
   ```bash
      java -jar target/password-validator-1.0.0.jar
   ```