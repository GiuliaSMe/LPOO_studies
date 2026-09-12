# LPOO - Atividades de Consolidação de Aprendizado
 
Repositório com as atividades de consolidação da disciplina de **Linguagem de Programação Orientada a Objetos (LPOO)**, propostas pelo professor ao final de cada módulo de conteúdo.
 
Todas as atividades acontecem no mesmo cenário: um **sistema de gestão para a oficina fictícia** Oficina Box 42, que cadastra veículos, clientes e ordens de serviço. **A cada módulo, o sistema evolui** e ganha novas funcionalidades de acordo com os conceitos de orientação a objetos estudados, sempre mantendo o mesmo domínio do problema.
 
## Estrutura do repositório
 
Cada módulo possui sua própria pasta, independente das demais. Ao iniciar um novo módulo, o código do módulo anterior é duplicado e evoluído a partir dali — ou seja, **cada pasta contém um projeto Java completo e executável por si só**, representando cada etapa da evolução do sistema.
 
```
.
├── Modulo1/
├── Modulo2/
├── Modulo3/   (em breve)
└── Modulo4/   (em breve)
```
 
## Módulos
 
### Módulo 1 — Introdução a OO
Conceitos básicos de orientação a objetos: classes, objetos e métodos. Implementação inicial do sistema com as classes `Veiculo` e `OrdemServico`, cadastro simples e regras básicas de negócio (ex: aplicação de desconto).
 
### Módulo 2 — Strings, Arrays e Diagrama de Classes
Evolução do sistema para suportar múltiplas ordens de serviço e múltiplos serviços por ordem, usando arrays de objetos. Introdução da classe `Servico` e do `GerenciadorOrdens`, além de uma aplicação de console com menu interativo e buscas por nome do cliente e por placa do veículo.
 
### Módulo 3 — Herança, Polimorfismo e Encapsulamento
Reestruturação do sistema aplicando herança e polimorfismo, encapsulamento de atributos, organização em pacotes e uso de modificadores de acesso.
 
### Módulo 4 — Classes Abstratas, Interfaces e Generics
Introdução de classes abstratas e interfaces, tratamento de exceções e estruturas de dados genéricas, tornando o sistema mais robusto e flexível.
 
## Tecnologia
 
- **Linguagem:** Java