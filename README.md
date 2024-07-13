# DevOps_D
 Esse Repositório foi feito para a matéria de DevOps no período 2024/1. 
 Ofertada na UFScar ( Universidade Federal de São Carlos ) pelo Professor Delano.

 
# Integrantes
- Gabriel Lourenço de Paula Graton - 800432
- Vitor Matheus da Silva - 800260

# Aplicação

## 1. locacaobike 

A aplicação resume em uma aplicação Web de Locação de Bicicletas, onde é possível criar e agendar locações.
- Cliente ( Admins ) conseguem gerenciar Clientes / Locadoras e Locações.
- Clientes ( Cliente ) consegue fazer novas Locações em Locadoras, além de gerencia=las.
- Locadoras conseguem gerenciar suas Locações.
- Locações seriam um acordo entre Cliente e Locadora.

Essa aplicação foi desenvolvida durante a matéria de Web1 com o Professor Alan.
Contudo foi adaptada para conseguir ser feita em Container.

- Além disso, após salvar quaisquer resultados, ele irá enviar uma mensagem para Cliente / Locadora para confirmar a alteração. ( Producer )


## 2. db

O nosso db está usando uma imagem docker que está hospedada na porta 3306.
- Temos os Clientes Pietro e Rafa.
- Locadoras Conserta Bike RP / Conserta Bike Sanca / Oi Bike.
- Usuário Admin.
- Locações de Pietro e Conserta Bike RP em dois dias e horários diferentes.

## 3. rabbit

O rabbit é o responsável por gerenciar essa troca de mensagens, principalmente pela condição de querrys

## 4. subscriber

será responsável para consumir as mensagens enviadas pela aplicação durante essas operações de salvamento.

# Como subir ?

## Observações

- É preciso ter instalado o maven e o docker na máquina para poder rodar
- O maven pode ser instalado com
  
```
sudo apt install mvn
```
- Mas o docker é preciso seguir algum tutorial: https://aurimrv.gitbook.io/pratica-devops-com-docker

## Processo para subir: docker compose

- Primeiramente é preciso entrar na pasta de locacaobike e fazer o build

```
cd locacaobike
mvn clean package
cd ..
```

- Com isso finalizado, entrar em subscriber e fazer o build

```
cd subscriber
mvn clean package
cd ..
```

- Para finalizar, apenas

```
docker compose up
```





