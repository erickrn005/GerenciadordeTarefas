Projeto de Gerenciamento de Tarefas com Banco de Dados PostgreSQL

Este é um projeto de gerenciamento de tarefas desenvolvido utilizando Java, JavaServer Faces (JSF), PrimeFaces e banco de dados PostgreSQL. O objetivo do projeto é permitir o cadastro, listagem, edição e exclusão de tarefas, além de oferecer a funcionalidade de marcar uma tarefa como concluída, utilizando um banco de dados para armazenar as informações.

Funcionalidades Implementadas:
O projeto possui as seguintes funcionalidades implementadas:

a) Cadastro de Tarefas: Permite cadastrar novas tarefas com as seguintes informações: título, descrição, responsável, situação, prioridade e deadline.

b) Listagem de Tarefas: Exibe uma tabela com todas as tarefas cadastradas, exibindo os campos de Id, título, descrição, responsável, situação, prioridade, deadline e ações. (editar, excluir e concluir).

c) Edição de Tarefas: Permite editar as informações de uma tarefa existente, incluindo título, descrição, responsável, situação e deadline.

d) Exclusão de Tarefas: Permite excluir uma tarefa do sistema.

e) Marcar Tarefa como Concluída: Permite marcar uma tarefa como concluída, alterando o status da tarefa para "Concluída" e fazendo com que ela saia da lista.

Instruções para Execução Local:
Para executar o projeto em um ambiente local com banco de dados PostgreSQL, siga as etapas abaixo:

Pré-requisitos:

Eclipse IDE (ou qualquer outra IDE de sua preferência) com suporte a Java e JSF.
Servidor de aplicação Java (por exemplo, Apache Tomcat 9 exatamente).
Banco de dados PostgreSQL instalado e configurado localmente.
Passos:

Clone este repositório em sua máquina local.
Abra o Eclipse e importe o projeto selecionando a opção "Importar projeto existente".
Configure o servidor de aplicação Java (por exemplo, Apache Tomcat 9) no Eclipse.
Aguarde a importação e a configuração do projeto serem concluídas.
Abra o arquivo DAOController e atualize as configurações de conexão com o banco de dados PostgreSQL de acordo com o seu ambiente.
Inicie o servidor de aplicação no Eclipse.
O sistema será carregado e criará automaticamente as tabelas necessárias no banco de dados configurado.
Abra um navegador da web e acesse o endereço (http://localhost:8080/Tarefas/) (ajuste a porta do servidor de acordo com a configuração do seu ambiente).
O sistema será carregado e você poderá começar a utilizar as funcionalidades de gerenciamento de tarefas.

Observações:
Certifique-se de que você tenha configurado corretamente todas as dependências, bibliotecas e drivers necessários para a conexão com o banco de dados PostgreSQL. Além disso, verifique se o banco de dados PostgreSQL está em execução e acessível.

Agora você está pronto para executar o projeto de gerenciamento de tarefas com banco de dados PostgreSQL localmente!
