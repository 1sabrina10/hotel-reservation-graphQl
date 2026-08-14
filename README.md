# Système de Réservation d'Hôtels — Architecture GraphQL

## 1. Prérequis

- Java 17
- Maven
- Navigateur Web (pour consulter l'interface du comparateur)
- Environnement conseillé : Linux ou Windows
- IDE recommandé : IntelliJ IDEA

## 4. Lancement du projet

### a) Importer les projets

Importer hotel1, hotel2, agenceRest1, agenceRest2, comparateur.

### b) Lancer les serveurs hotels

- Hôtel 1 (port 8080) - GraphQL endpoint : `http://localhost:8080/graphql`
- Hôtel 2 (port 9090) - GraphQL endpoint : `http://localhost:9090/graphql`

### c) Lancer les Agences

- Démarrer le projet agenceRest1 (port 8081) - GraphQL endpoint : `http://localhost:8081/graphql`
- Démarrer le projet agenceRest2 (port 8082) - GraphQL endpoint : `http://localhost:8082/graphql`


### d) Lancer le Comparateur (interface web)

Démarrer le projet comparateur (port 8083)
- GraphQL endpoint : `http://localhost:8083/graphql`

Consulter l'interface web via un navigateur en tapant :
`http://localhost:8083/comparateur`



### Identifiants disponibles (chargés en base de données)

| Agence | Email | Mot de passe |
|--------|-------|--------------|
| Agence 1 | agence1@gmail.com | agence1 |
| Agence 2 | agence2@gmail.com | agence2 |

### Villes gérées par les hôtels
- Paris

### Conventions (calcul prix avec réduction)

| Agence | Hotel 1 Paradis | Hotel 2 Champs |
|--------|----------------|----------------|
| Agence1 | 15%           | 10%            |
| Agence2 | pas de convention|  20%        |

## 11. Auteur

Sabrina MOUFOK 



.





