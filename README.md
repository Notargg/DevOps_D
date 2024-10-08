# DevOps_D

<p> Esse Repositório foi feito para a matéria de DevOps no período 2024/1. Ofertada na UFScar ( Universidade Federal de São Carlos ) pelo Professor Delano. </p> 

 
# Integrantes
- Gabriel Lourenço de Paula Graton - 800432
- Vitor Matheus da Silva - 800260

# Aplicação

## 1. locacaobike 

![Locadora](https://github.com/user-attachments/assets/99faf0e9-bc51-43e4-8af6-a96800c16d39)

A aplicação resume em uma aplicação Web de Locação de Bicicletas, onde é possível criar e agendar locações.
- Cliente ( Admins ) conseguem gerenciar Clientes / Locadoras e Locações.
- Clientes ( Cliente ) consegue fazer novas Locações em Locadoras, além de gerencia=las.
- Locadoras conseguem gerenciar suas Locações.
- Locações seriam um acordo entre Cliente e Locadora.

Essa aplicação foi desenvolvida durante a matéria de Web1 com o Professor Alan.
Contudo foi adaptada para conseguir ser feita em Container.

- Além disso, após salvar quaisquer resultados, ele irá enviar uma mensagem para Cliente / Locadora para confirmar a alteração. ( Producer )


## 2. db

![image](https://github.com/user-attachments/assets/9ca117e7-eaa5-4403-80a9-4a0b04f5ff57)

O nosso db está usando uma imagem docker que está hospedada na porta 3306.
- Temos os Clientes Pietro e Rafa.
- Locadoras Conserta Bike RP / Conserta Bike Sanca / Oi Bike.
- Usuário Admin.
- Locações de Pietro e Conserta Bike RP em dois dias e horários diferentes.

## 3. RabbitMQ

![image](https://github.com/user-attachments/assets/d24433b8-c6ee-4f6e-9638-8fc2b9ccb318)

O RabbitMQ é um message broker, onde será o principal em gerenciar a troca de mensagens. Com ele o nosso sistema ( locacaobike ) consegue mandar mensagens que ficarão na fila para serem consumidas.
- A porta não está exposta para não precisar ( no Docker Compose ).
- Apenas o Backend / Subscriber sabem.
- Caso queira saber como está a troca de mensagem, deixe exposta 15672.

```
ports:
    - "15672:15672"

```

## 4. subscriber

![image](https://github.com/user-attachments/assets/815b49df-944c-4c70-8278-70fac3c81859) 

O subscriber é responsável por consumir as mensagens que são mandadas pelo nosso sistema e enviar elas nos email designados ( por enquanto manda para um email de autoria nosso )
- Ele fica esperando a fila do Rabbit.
- Ao chegar mensagem ele consegue consumi-las e enviar para o email designado.

# Trabalho



## T1

### Observações

- É preciso ter instalado o maven e o docker na máquina para poder rodar
- O maven pode ser instalado com
  
```
sudo apt install mvn
```
- Mas o docker é preciso seguir algum tutorial: https://aurimrv.gitbook.io/pratica-devops-com-docker

### Primeira Forma - Docker Compose Up - Build

- Primeiro é preciso buildar as imagens que serão usadas. Para isso use:
- A aplicação estará em http://localhost:80

```
./build-image
```
- Agora para descer as imagens do Docker, use:
- Caso queira remover as imagens use -r junto com o comando.
```
./compose-build-down 
```
### Segunda Forma - Docker Compose Up - Image

- Para subir a imagem é para usar:
- A aplicação estará em http://localhost:80
  
```
docker compose up
```

- Agora para descer as imagens do Docker, use:
- Caso queira remover as imagens use -r junto com o comando.

```
./compose-down 
```
## T2

- Na pasta há outro README com as informações de cada artefato do Kubernet.

### Observações

- É preciso ter instalado o minikube instalado.
- Só seguir o tutorial: https://minikube.sigs.k8s.io/docs/start/?arch=%2Fwindows%2Fx86-64%2Fstable%2F.exe+download
- Além disso, é preciso fazer uma configuração inicial:
  
```
minikube start
```
- Após esse processo use, ele irá te retornar um IP

```
minikube ip
```
- Agora acesse o o diretorio /etc/hosts e coloque esse ip com a seguinte configuração
- Colocando o seguinte: {IP_MINIKUBE}      k8s.local
```
sudo /etc/hosts
```

### Kubectl

- Para subir a imagem é para usar:

```
./minikube-up
```

- Agora para descer as imagens do minikube use:

```
./minikube-down
```

# Final

- Lembre sempre de verificar suas imagens, tanto no docker quanto no minikube
## Docker

 ```
docker image ls
```
## Minikube

 ```
minikube ssh
```
- Depois de entrar, mesmo comando.

 ```
docker image ls
```




