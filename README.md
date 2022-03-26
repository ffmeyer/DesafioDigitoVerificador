
# Desfio procesos seletivo techgraph para analista de teste.

Testes unitarios em java para validar geração de digito verificador.

## Melhores Praticas incluidas

- Independencia de cada cenário de teste.
- Geração de relatorio adicional no formato xunit

#### Testes de unidade 
- validar Dados de Entrada NULL
- validar Dados de Entrada Inexistente
- validar Dados de Entrada ExtensaoInvalida
- validar Dados de Entrada Alfanumerica
- validar Arquivo Vazio
- validar Quantidade de matriculas geradas
- validar Geração Digito Verificador

#### Testes manuais
- Uso invalido da UI - Gerar arquivo inválido
- Uso invalido da UI - Caminho inválido do arquivo 
- Uso invalido da UI - Cxtensão invalida / formato invalido
- Teste estutural Arquivo ok, matricula invalida
- Teste estutural Arquivo vazio
- Teste estutural Arquivo valido, apenas 1 item
- Teste estutural Arquivo valido, apenas 2 ou mais itens

## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  Para uso em desenvolvimento
  mvn tests
```

## Dependencias externas

- [Junit](https://mvnrepository.com/artifact/junit/junit/4.12)
- [Maven](https://maven.apache.org/)

## Autores

- [Felipe Meyer](https://www.github.com/ffmeyer)
