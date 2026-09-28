Markdown
#  Lava-Jato - Sistema de Gestão de Ordens e Atendimento

Sistema desktop desenvolvido em Java (Swing) para controle operacional de lava-rápido e estética automotiva, incluindo cadastro de clientes/veículos, seleção múltipla de serviços com cálculo dinâmico de valores, registro de forma de pagamento e geração diária de relatórios de atendimento.

---

## 📌 Sobre o Projeto

Este projeto foi desenvolvido com foco em aplicar conceitos fundamentais de desenvolvimento de software em Java: Programação Orientada a Objetos (POO), interfaces gráficas com Java Swing, manipulação de arquivos com persistência diária em disco e separação de responsabilidades em camadas.

O sistema atende à rotina operacional de um lava-jato: o atendente realiza o cadastro do veículo e do cliente, seleciona um ou mais serviços através de checkboxes (com atualização em tempo real do total), escolhe a forma de pagamento e finaliza a ordem. Ao concluir, o atendimento é numerado sequencialmente e registrado em um arquivo diário consolidado (`historico YYYY-MM-DD.txt`).

---

## 🚀 Funcionalidades

- **Cadastro de Veículo e Cliente:** Registro completo contendo Nome do Cliente, Placa, Modelo, Marca, Cor e Telefone.
- **Log Visual em Tela:** Exibição imediata dos veículos cadastrados em área de texto (`JTextArea`) e alimentação dinâmica da lista de seleção (`JComboBox`).
- **Seleção Múltipla de Serviços:** Escolha combinada de serviços com cálculo e atualização automática do valor total na interface:
    - Lava Completo: R$ 80,00
    - Aspiração: R$ 20,00
    - Polimento: R$ 150,00
    - Higienização Interna: R$ 90,00
    - Lava Rápido: R$ 50,00
- **Formas de Pagamento:** Seleção do método de quitação por meio de `JComboBox`.
- **Relatório Diário Automatizado:** Geração e persistência do histórico em arquivo de texto formatado por data (`historico YYYY-MM-DD.txt`), com contagem sequencial automática de atendimentos do dia (`ATENDIMENTO #1`, `ATENDIMENTO #2`, etc.).
- **Limpeza Rápida:** Botão para resetar formulários e seleções de serviços após a finalização ou cancelamento.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
| :--- | :--- |
| **Java (JDK 17+)** | Linguagem principal da aplicação (Java SE) |
| **Java Swing / AWT** | Construção da interface gráfica e manipulação de eventos |
| **java.io / java.nio** | Manipulação de arquivos, escrita com `BufferedWriter` e contagem com `Files.lines` |
| **IntelliJ IDEA** | IDE de desenvolvimento e construção visual com GUI Designer (`.form`) |
| **Git / GitHub** | Controle de versão e hospedagem do código |

---

## 📂 Estrutura do Projeto

O código foi estruturado aplicando separação de responsabilidades para manter a camada visual desacoplada das regras e da persistência:


src/
└── lavaJato/
    ├── Veiculo.java          # Modelo de dados (dados do cliente e veículo)
    ├── Servico.java          # Enum centralizando tabela de serviços e preços
    ├── HistoricoService.java # Serviço responsável pela lógica de I/O e log diário
    ├── LavaJato.java         # Controlador da interface e tratamento de eventos
    └── LavaJato.form         # Layout visual das telas (IntelliJ GUI Designer)
    


---

## 🧠 Conceitos e Práticas Aplicadas
Separação de Responsabilidades: A camada gráfica (LavaJato.java) não grava arquivos diretamente nem calcula preços com valores fixos perdidos no código; ela delega a gravação para HistoricoService e consulta os valores em Servico.

Uso de Enums: Centralização da tabela de preços e nomes de serviços no enum Servico, eliminando números mágicos (magic numbers) e facilitando reajustes de preços no futuro.

Manipulação de Arquivos e Streams (java.nio): Utilização de Files.lines() para contar atendimentos existentes no dia e BufferedWriter com append mode para não sobrescrever relatórios anteriores.

Tratamento de Exceções: Captura e tratamento de IOException com alertas visuais amigáveis via JOptionPane.

---

## ⚙️ Como Executar o Projeto
Pré-requisitos
Java JDK (versão 17 ou superior) instalado.

IntelliJ IDEA (recomendado para a renderização automática dos componentes criados no GUI Designer .form).

Passo a Passo
Clone o repositório:

Bash
git clone [https://github.com/anaaximenes/lava-jato-swing.git](https://github.com/anaaximenes/lava-jato-swing.git)
Abra o projeto no IntelliJ IDEA:

Abra a pasta lava-jato-swing diretamente no IntelliJ.

Aguarde o IntelliJ indexar e compilar as dependências do projeto.

Execute a aplicação:

Navegue até src/lavaJato/LavaJato.java.

Clique com o botão direito e selecione Run 'LavaJato.main()'.

⚠️ Atenção: Como o layout visual foi desenhado com o GUI Designer do IntelliJ (.form), a execução via linha de comando pura (javac/java) pode exigir compilação prévia das classes de formulário pelo plugin do Swing. A execução pela IDE é a mais recomendada.

--- 

## 💻 Exemplo de Uso e Saída
Trecho do log diário gerado (historico 2026-09-28.txt):
Plaintext
==================================================================
          RELATÓRIOS DIÁRIOS DE ATENDIMENTO - 2026-09-28
==================================================================
___________________________________________________________________
ATENDIMENTO #1 | Hora: 14:35:10
Veículo: ABC1234 | Onix - João Silva
Serviços: Lava Completo: R$ 80,00, Aspiração: R$ 20,00
Pagamento: Cartão de Crédito
Total: R$ 100,00
___________________________________________________________________
ATENDIMENTO #2 | Hora: 15:10:42
Veículo: XYZ9876 | Corolla - Maria Souza
Serviços: Polimento: R$ 150,00
Pagamento: Pix
Total: R$ 150,00

---

## 🔧 Melhorias Futuras
[ ] Validação visual de campos vazios antes de registrar o veículo.

[ ] Implementação de máscaras de entrada (ex: placa no formato Mercosul e telefone).

[ ] Migração da persistência de .txt para banco de dados relacional (ex: SQLite ou MySQL).

[ ] Exportação de relatórios em formato .pdf ou planilhas .xlsx.

[ ] Testes unitários com JUnit para as classes Servico e HistoricoService.

## 👩‍💻 Autora
Desenvolvido por Ana Beatriz Ximenes Amaral

GitHub: @anaaximenes
Linkedin: https://www.linkedin.com/in/ana-beatriz-ximenes-amaral-101323247/
