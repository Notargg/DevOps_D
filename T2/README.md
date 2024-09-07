# Explicações do T2

Aqui ireimos dar uma breve explicação do que é cada parte da aplicação de forma breve


## DB

### Configmap

- Este arquivo é um ConfigMap no Kubernetes. Ele define um valor chamado database_url que especifica a URL de conexão para um banco de dados MySQL chamado "LocacaoBike", 

### Deployment

- Este arquivo define um Deployment no Kubernetes. Ele cria um único pod (não especificado o número de réplicas) com a imagem do MySQL, expondo a porta 3306 do MySQL. A senha do usuário root do MySQL é obtida de um Secret e utiliza a estratégia de atualização do tipo Recreate.


### Persistent Volume

- Vai ser responśavel por garantir que as mudanças feitas sejam salvas. Dessa forma caso a aplicação caia ele mantenha o BD.

#### PV

- Este arquivo define um PersistentVolume (PV) no Kubernete. Ele fornece 2 GiB de armazenamento local e está configurado para ser acessado com o modo ReadWriteOnce. O volume é armazenado no caminho local /mnt/data/locacaobike no host, e o diretório será criado se não existir. A classe de armazenamento é configurada manualmente.


#### PVC

- Este arquivo define um PersistentVolumeClaim (PVC) no Kubernetes Ele solicita 2 GiB de armazenamento com o modo de acesso ReadWriteOnce . O PVC usa a classe de armazenamento manual e é utilizado para vincular um PersistentVolume, garantindo que o volume solicitado seja fornecido à aplicação.


### Secret

- Este arquivo define um Secret no Kubernetes chamado, que armazena credenciais de autenticação básica. Ele contém dois dados codificados, o nome de usuário e a senha . Esses dados são usados, por exemplo, para acessar o banco de dados MySQL.


### Service

- Este arquivo define um Service no Kubernetes chamado locacaobike-db, que expõe o banco de dados MySQL. Ele mapeia a porta 3306 do serviço para a porta 3306 dos pods-alvo. O Service direciona o tráfego para os pods correspondentes, permitindo a comunicação interna entre serviços dentro do cluster.


## LocacaoBike

### Configmap

- Este arquivo é um ConfigMap no Kubernetes. Ele define as variáveis de ambiente relacionadas ao RabbitMQ, que será importante para o uso do servidor RabbitMQ.

### Deployment

- Este arquivo define um Deployment no Kubernetes para o aplicativo LocacaoBike. Ele cria um pod - com uma única replica - que roda a imagem notargg/locacaobike:latest, expõe a porta 80. As credenciais são obtidas de um Secret chamado notargg-secret,.

### Service 

- Este arquivo define um Service no Kubernetes chamado locacaobike, que expõe a aplicação LocacaoBike. Ele mapeia a porta 80 do serviço para a porta 80 dos pods-alvo. O Service direciona o tráfego para os pods correspondentes, permitindo a comunicação interna entre serviços dentro do cluster.

## RabbitMQ

- Este arquivo define um Deployment no Kubernetes para o serviço RabbitMQ. Ele cria um pod com um container que expõe duas portas: 5672 (porta padrão para comunicação do RabbitMQ) e 15672 (porta para a interface de gerenciamento do RabbitMQ)

As credenciais são obtidas de um Secret chamado notargg-secret, que contém as chaves rabbitmq-username e rabbitmq-password