# Lab 10 — Reserva de Assentos de Ônibus (Padrão Observer + MVC)

Atividade de laboratório da disciplina de **Métodos Avançados de Programação (MAP)**.

## Sobre o projeto

Sistema de reserva de assentos de ônibus que combina a arquitetura **MVC** com o padrão **Observer**, usando o modelo de eventos do Java (`EventObject` / listeners):

- **Model** — `Onibus` mantém o estado dos assentos e notifica os listeners registrados a cada mudança; `AssentoEvent` carrega o número do assento e o novo status; `AssentoListener` é a interface dos observadores.
- **View** — `PainelCentral` e `Quiosque` observam o ônibus e exibem as atualizações em tempo real.
- **Controller** — `ControleOnibus` expõe as operações `reservarAssento(n)` e `comprarAssento(n)`.

Quando um assento muda de status (Disponível → Reservado → Indisponível), todas as views registradas são notificadas automaticamente.

## Tecnologias

- Java
- Maven
- JUnit 5 (testes unitários)

## Como executar

```bash
mvn compile exec:java -Dexec.mainClass="Main.Main"
```

Ou abra o projeto em uma IDE (IntelliJ, Eclipse) e execute a classe `Main.Main`.

## Testes

```bash
mvn test
```

Os testes cobrem o modelo, o controlador, os eventos e a notificação das views.
