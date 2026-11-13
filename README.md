# LPOO - Atividades de Consolidação de Aprendizado
 
Repositório com as atividades de consolidação da disciplina de **Linguagem de Programação Orientada a Objetos (LPOO)**, propostas pelo professor ao final de cada módulo de conteúdo.
 
Todas as atividades acontecem no mesmo cenário: um **sistema de gestão para a oficina fictícia** Oficina Box 42, que cadastra veículos, clientes e ordens de serviço. **A cada módulo, o sistema evolui** e ganha novas funcionalidades de acordo com os conceitos de orientação a objetos estudados, sempre mantendo o mesmo domínio do problema.
 
## Estrutura do repositório

Cada módulo possui sua própria pasta, independente das demais. Ao iniciar um novo módulo, o código do módulo anterior é duplicado e evoluído a partir dali, ou seja, cada pasta contém um projeto Java completo e executável por si só, representando um "snapshot" da evolução do sistema.

```
.
├── Modulo1/
├── Modulo2/
├── Modulo3/
└── Modulo4/
```

Dentro de cada módulo, o código-fonte fica separado dos arquivos compilados:

```
ModuloX/
├── src/    <- arquivos .java
└── bin/    <- arquivos .class gerados na compilação (ignorado pelo git)
```

A partir do Módulo 4, o código dentro de `src/` é organizado em pacotes por responsabilidade:

```
src/
├── ui/              <- Main e interação com o console
├── modelo/          <- Veiculo e OrdemServico
├── servicos/        <- Servico (abstrata) e suas subclasses
├── gerenciadores/   <- GerenciadorOrdens
├── interfaces/      <- Descontavel e Buscavel
└── excecoes/        <- exceções personalizadas
```

## Como compilar e rodar

Dentro da pasta do módulo desejado:

**Módulos 1 a 3** (sem pacotes):

```bash
javac -d bin src/*.java
java -cp bin Main
```

**Módulo 4** (com pacotes):

```bash
# No Powershell
javac -d bin -sourcepath src src/ui/Main.java
java -cp bin ui.Main
```

O `javac` organiza os `.class` gerados dentro de `bin/`, espelhando os pacotes, e o `java` executa a classe principal a partir deles.

## Módulos
 
### Módulo 1 — Introdução a OO
Conceitos básicos de orientação a objetos: classes, objetos e métodos. Implementação inicial do sistema com as classes `Veiculo` e `OrdemServico`, cadastro simples e regras básicas de negócio (ex: aplicação de desconto).
 
### Módulo 2 — Strings, Arrays e Diagrama de Classes
Evolução do sistema para suportar múltiplas ordens de serviço e múltiplos serviços por ordem, usando arrays de objetos. Introdução da classe `Servico` e do `GerenciadorOrdens`, além de uma aplicação de console com menu interativo e buscas por nome do cliente e por placa do veículo.
 
### Módulo 3 — Herança, Polimorfismo e Encapsulamento
Encapsulamento de todos os atributos (private + getters/setters) e criação de uma hierarquia de serviços: `Servico` passa a ser a classe base, especializada em `ServicoComPecas`, `ServicoPorHora` e `ServicoDiagnostico`, cada uma com sua própria regra de cálculo de preço via sobrescrita de `getPrecoFinal()`. O array de serviços na `OrdemServico` é declarado como `Servico[]` mas armazena as diferentes subclasses, e o cálculo do total explora polimorfismo puro.
 
### Módulo 4 — Classes Abstratas, Interfaces, Exceções e Generics
Consolidação final do sistema: `Servico` passa a ser uma classe abstrata, as interfaces `Descontavel` (aplicação de desconto na ordem) e `Buscavel` (busca no gerenciador) definem contratos entre as classes, e exceções personalizadas (`ValorInvalidoException`, `OrdemVaziaException`, `CapacidadeExcedidaException`, `OrdemFinalizadaException`) tornam as validações explícitas e mantêm a aplicação de console utilizável mesmo diante de erros. O código é reorganizado em pacotes, separando regras de negócio da interação com o usuário.
 
## Tecnologia
 
- **Linguagem:** Java