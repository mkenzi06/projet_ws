# Projet_ws Web service Football

## Membre de l'équipe
- MEBARKI Mohamed Kenzi (kenzimebarki2@gmail.com)

## Introduction

Ce projet vise à fournir plusieurs fonctionnalités permettant aux utilisateurs de créer et de gérer des équipes de football, ainsi que d'ajouter, de supprimer et de mettre à jour des joueurs au sein de ces équipes. En utilisant les services fournis, les utilisateurs peuvent interagir avec l'API externe (footballapi) pour récupérer les statistiques des joueurs, les équipes de différentes ligues et aussi les predictions de matchs de premier league (championnat anglais).
### Objectif du projet
L'objectif principal de ce projet est de fournir un service web RESTful et SOAP pour gérer les équipes et les joueurs de football. Les utilisateurs peuvent créer, supprimer et mettre à jour des équipes et des joueurs, ainsi que récupérer des informations sur les équipes et les joueurs à partir de l'API externe (footballapi) pour quelques fonctionnalites et d'autre en interagissant avec notre BD.

### Composants utilisés

Les composants utilisés dans ce projet sont les suivants :

- **JAX-RS** : JAX-RS est un framework Java pour la création de services web RESTful. Il est utilisé pour créer et exposer les endpoints RESTful de ce service web.

- **JAX-WS** : JAX-WS est un framework Java pour la création de services web SOAP. Il est utilisé pour créer et exposer les endpoints SOAP de ce service web.

- **MySQL** : MySQL est un système de gestion de base de données relationnelle. Il est utilisé pour stocker les données des équipes et des joueurs.

- **JDBC** : JDBC est une API Java pour interagir avec les bases de données relationnelles. Il est utilisé pour établir la connexion avec la base de données MySQL et exécuter des requêtes SQL.

- **API externe ([api-football.com](https://apifootball.com))** : Cette API fournit des données sur les équipes(championnat), les joueurs et leur stats durant la saison courante et les prédictions de matchs dans notre service nous avons implementes seulement les matchs de premier league.

-**java SWING** : Java Swing est une bibliothèque graphique pour Java. Elle est utilisée pour créer l'interface graphique du client.

## Description du service

### JAX-WS (SOAP)
- Ajouter une équipe
- Ajouter un joueur
- Supprimer une équipe par son nom

### JAX-RS  (RESTful)
- Supprimer un joueur par son nom (Delete) ]
- Mettre à jour les informations d'un joueur (PUT) (Param: XML)
- Récupérer les données d'un joueur par son ID  (GET) (Retour: JSON)
- Récupérer toutes les équipes des 5 grands championnats européens  (GET) (Retour: JSON)
- Récupérer les statistiques d'un joueur    (GET) (Retour: JSON)
- Prédire les résultats d'un match de premier league  (GET) (Retour: JSON)             
- Recuperer les joueurs d'une equipe (depuis notre BD) (GET) (Retour: JSON)



Les services RESTful sont exposés via l'URL `http://localhost:8080/projet.web.foot/api/`.

Exemple : Récupérer les équipes de la liga 
http://localhost:8080/projet.web.foot/api/teams/liga
Remarque : Des fonctionnalitees de POST avec le protocole REST a ete implementee aussi tel que l'ajout d'une equipe et d'un joueur.
## Description du client
Le client est développé avec une interface graphique en java en utilisant la librairie java SWING permettant à l'utilisateur d'interagir avec les services exposés par le serveur. Il peut créer des équipes, ajouter et gérer des joueurs, et obtenir des prédictions de matchs tout en passant bien evidemment par notre serveur et n'interagit jamais avec notre BD.
Exemple : Faire une requete au serveur pour recuperer les joueurs d'une equipe 
La méthode `getPlayersOfTeam` utilise `WebClient` pour envoyer une requête GET au serveur. Elle spécifie l'URL de l'API pour récupérer les joueurs d'une équipe spécifique, et configure le type de contenu accepté comme JSON (Voir annexe java json). Une fois la réponse reçue, elle est retournée sous forme de chaîne de caractères.

## Cas d'utilisation

### Mettre à jour l'équipe d'un joueur

**Description**:
Ce cas d'utilisation permet à un utilisateur de mettre à jour l'équipe d'un joueur en fournissant le nom du joueur et le nouveau nom de l'équipe.

**Acteurs**:
- Utilisateur (sur l'interface graphique du client)

**Préconditions**:
- Le joueur doit déjà exister dans la base de données.
- Le nom de la nouvelle équipe doit être valide et existante dans la base de données.

**Scénario principal**:
1. L'utilisateur fournit le nom du joueur et le nouveau nom de l'équipe.
2. Le serveur vérifie si le joueur et la nouvelle équipe existent.
3. Le serveur met à jour l'équipe du joueur avec le nouveau nom de l'équipe.
4. Le serveur confirme la mise à jour de l'équipe du joueur au client en envoyant message de confirmation.

**Postconditions**:
- L'equipe du joueur (Player) est mise à jour avec le nouveau nom de l'équipe.

**Extensions**:
- Si le joueur n'est pas trouvé, le système affiche un message d'erreur.
- Si le nom de la nouvelle équipe n'est pas valide, le système affiche un message d'erreur.

voici la requete qu'on peut effectuer sur Postman ou Insomnia pour mettre à jour l'équipe d'un joueur (avec un corps en xml bien sur): 
PUT /players/{nom_joueur}/{nouveau_nom_equipe}



# Annexe 
## Endpoints JAX-RS

1. **Supprimer un joueur par son nom (DELETE)** :
   - Endpoint : `DELETE /players/{nom_joueur}`
2. **Mettre à jour les informations d'un joueur (PUT)** :
   - Endpoint : `PUT /players/{nom_joueur}`
   - Paramètre : Body XML
3. **Récupérer les données d'un joueur par son ID (GET)** :
   - Endpoint : `GET /players/{id_player}`
   - Retour : XML
4. **Récupérer les equipes de la base de donnees** :
   - Endpoint : `GET /teams`
   - Retour : XML
5. **Récupérer toutes les équipes des 5 grands championnats européens (GET)** :
   - Endpoint : `GET /teams/{championnat}`
   - Retour : JSON
6. **Récupérer les statistiques d'un joueur (GET)** :
   - Endpoint : `GET /players/stats/{name_player}`
   - Retour : JSON
7. **Prédire les résultats d'un match de Premier League (GET)** :
   - Endpoint : `GET /predictions`
   - Retour : JSON
8. **Récupérer les joueurs d'une équipe (depuis notre BD) (GET)** :
   - Endpoint : `GET /teams/{team_name}/player`
   - Retour : JSON


### Documentation Utilisée

Vous pouvez trouver la documentation utilisée lors de l'implémentation du projet aux liens suivants :

- [Documentation JAX-RS](https://gayerie.dev/epsi-b3-javaee/javaee_web/jaxrs.html)
- [Documentation JAX-WS](https://www.jmdoudoux.fr/java/dej/chap-service-web.htm)
- [Documentation JAVA JSON](https://jenkov.com/tutorials/java-json/jackson-jsonnode.html)
- [Documentation API externe (apifootball.com)](https://apifootball.com/documentation/)
