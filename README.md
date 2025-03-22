# Gold Price Service

## Descrição

Este projeto fornece serviços para obter o preço do ouro em dólares americanos (USD) e convertê-lo para reais (BRL) utilizando a taxa de câmbio USD/BRL. Ele utiliza duas APIs externas:

- **GoldAPI** para obter o preço do ouro por onça troy.
- **ExchangeRate-API** para obter a taxa de conversão entre USD e BRL.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **JSON Processing (org.json)**
- **SLF4J (Simple Logging Facade for Java)**
- **APIs externas**

## Como Funciona

O serviço principal da aplicação está na classe `GoldPriceService`, que contém os seguintes métodos:

1. `getGoldPriceInUSD()`: Obtém o preço do ouro em dólares americanos utilizando a GoldAPI.
2. `getUSDBRLExchangeRate()`: Obtém a taxa de câmbio USD para BRL utilizando a ExchangeRate-API.
3. `getGoldPriceInBRL()`: Converte o preço do ouro de USD para BRL utilizando a taxa de câmbio obtida.

## Configuração

Antes de executar o projeto, você precisa configurar suas chaves de API no arquivo `application.properties`:

```properties
# Chave da API GoldAPI
goldapi.api.key=SEU_TOKEN_AQUI

# Chave da API ExchangeRate-API
exchangerate.api.key=SEU_TOKEN_AQUI
```

## Como Executar

1. Clone este repositório:
   ```sh
   git clone https://github.com/Wil-JC-Pimenta/gold-price.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd gold-price
   ```
3. Configure suas credenciais no `application.properties`.
4. Compile e execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

## Exemplo de Uso

O serviço pode ser consumido chamando diretamente os métodos na classe `GoldPriceService`. Por exemplo:

```java
@Autowired
private GoldPriceService goldPriceService;

double goldPriceInBRL = goldPriceService.getGoldPriceInBRL();
System.out.println("Preço do ouro em BRL: " + goldPriceInBRL);
```

## Logs

O projeto usa SLF4J para logging. Durante a execução, serão registrados logs informando os códigos de resposta das APIs e os dados recebidos.

## Contribuição

Sinta-se à vontade para contribuir com melhorias, reportar bugs ou sugerir novas funcionalidades.

## Licença

Este projeto está licenciado sob a MIT License.

```
MIT License

Copyright (c) 2024

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTH
