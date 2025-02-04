# Gestion de mini réseaux sociaux MiniSocs

Binôme :
* Luka LAFAYE DE MICHEAUX
* Alexandre CHIDIAC

## Syntaxe MarkDown

La syntaxe MarkDown de ce document est compatible avec la syntaxe
GitLab, qui est documentée dans
https://docs.gitlab.com/ee/user/markdown.html

## 1. Spécification

### 1.1. Acteurs et cas d'utilisation

La **première étape** consiste à **bien comprendre le système** à
étudier. Dans le cadre de l'exercice, cela consiste à lire
attentivement l'énoncé. Cette lecture doit permettre de *délimiter les
contours du système* à réaliser. La méthode générale consiste à
retrouver les acteurs qui interagissent avec lui. Il est très
important de fixer des frontières au problème. Ensuite, nous
recherchons les fonctionnalités du système par la définition de ses
cas d'utilisation. Dans le cadre de ce module, il s'agit de rechercher
les principales fonctions attendues du système. Nous nous limitons aux
cas d'utilisation pour atteindre les premiers objectifs indiqués dans
le cahier des charges, en prenant en considération les simplifications
énoncées dans le cahier des charges.

Pour réaliser le diagramme de cas d'utilisation à partir de l'analyse
du texte :
* rechercher les acteurs, avec les potentielles relation de
  généralisation spécialisation,
* rechercher les fonctionnalités du système accessibles aux acteurs

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

Voici ci-dessous le diagramme de cas d'utilisation avec les cas
d'utilisation les plus importants (code
[source](./Diagrammes/minisocs_uml_diag_cas_utilisation.pu)).

![diagrammecasutilisation](./Diagrammes/minisocs_uml_diag_cas_utilisation.svg)

### 1.2. Priorités, préconditions et postconditions des cas d'utilisation

Les priorités des cas d'utilisation sont choisies avec les règles de
bon sens suivantes:

* pour retirer une entité du système, elle doit y être. La priorité de
l'ajout est donc supérieure ou égale à la priorité du retrait ;

* pour lister les entités d'un type donné, elles doivent y être. La
priorité de l'ajout est donc supérieure ou égale à la priorité du
listage ;

* il est *a priori* possible, c.-à-d. sans raison contraire, de
démontrer la mise en œuvre d'un sous-ensemble des fonctionnalités du
système, et plus particulièrement la prise en compte des principales
règles de gestion, sans les retraits ou les listages ;

* la possibilité de lister aide au déverminage de l'application
pendant les activités d'exécution des tests de validation.

Par conséquent, les cas d'utilisation d'ajout sont *a priori* de
priorité « HAUTE », ceux de listage de priorité « Moyenne», et ceux de
retrait de priorité « basse ».

Voici les précondition et postcondition des cas d'utilisation de
priorité HAUTE.

### Cas d'utilisation de l'administrateur

#### Ajouter un utilisateur (HAUTE)
- précondition : \
∧ pseudo de l'exécuteur bien formé  (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ pseudo de l'exécuteur est administrateur \ 
∧ pseudo bien formé (non null ∧ non vide) \
∧ nom bien formé  (non null ∧ non vide) \
∧ prénom bien formé  (non null ∧ non vide) \
∧ courriel bien formé (respectant le standard RFC822) \
∧ utilisateur avec ce pseudo inexistant
- postcondition : \
∧ utilisateur avec ce pseudo existant \
∧ le compte de l'utilisateur est actif

#### Lister les utilisateurs (moyenne)
- précondition : \
∧ pseudo de l'exécuteur bien formé  (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ pseudo de l'exécuteur est administrateur

- postcondition : \
∧ liste des utilisateurs affichée \

#### Bloquer un compte utilisateur (basse)
- précondition : \
∧ pseudo de l'exécuteur bien formé  (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ pseudo de l'exécuteur est administrateur
∧ pseudo bien formé (non null ∧ non vide) \
∧ utilisateur avec ce pseudo existe \
∧ utilisateur avec ce pseudo n'est pas déjà bloqué

- postcondition : \
∧ utilisateur avec ce pseudo est bloqué \

#### Retirer un compte utilisateur (basse)
- précondition : \
∧ pseudo de l'exécuteur bien formé (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ pseudo de l'exécuteur est administrateur
∧ pseudo bien formé (non null ∧ non vide) \
∧ utilisateur avec ce pseudo existe

- postcondition : \
∧ utilisateur avec ce pseudo inexistant \

### Cas d'utilisation de l'utilisateur

#### Désactiver son compte (moyenne)
- précondition : \
∧ pseudo de l'exécuteur bien formé (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ pseudo de l'exécuteur n'est pas administrateur \
∧ le compte n'est pas bloqué
∧ le compte est actif

- postcondition : le compte de l'exécuteur est désactivé

NB : l'opération est idempotente.

#### Activer son compte (moyenne)
- précondition : \
∧ pseudo de l'exécuteur bien formé (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ le compte n'est bloqué \
∧ le compte est désactivé

- postcondition : le compte de l'exécuteur est actif

#### Créer un réseau social (HAUTE)
- précondition : \
∧ pseudo de l'exécuteur bien formé (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ le compte n'est pas bloqué \
∧ le compte est actif \
∧ le nom du réseau social est bien formé (non null ∧ non vide) \
∧ le nom du réseau social est disponible

- postcondition : \
∧ le réseau social est créé \
∧ l'exécuteur est modérateur de ce réseau social \
∧ le réseau social est activé \
∧ l'utilisateur créateur du réseau social est membre de ce dernier

### Cas d'utilisation du modérateur

#### Modérer un message (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le réseau social existe \
∧ l'utilisateur est moderateur de ce reseau social \
∧ le message existe dans le réseau social 


- postcondition : \
∧ le message est modéré (caché, supprimé, ou marqué comme inapproprié)

#### Inviter un utilisateur sur un réseau social (HAUTE)
- précondition : \
∧ pseudo de l'exécuteur bien formé (non null ∧ non vide) \
∧ pseudo de l'exécuteur existe \
∧ le compte de l'exécuteur est actif \
∧ le nom du réseau social est bien formé (non null ∧ non vide) \
∧ le réseau social existe \
∧ l'exécuteur est modérateur du réseau social \
∧ pseudo de l'utilisateur bien formé (non null ∧ non vide)
∧ utilisateur existe \
∧ utilisateur n'est pas déjà dans le réseau social

- postcondition : \
∧ l'utilisateur est membre du réseau social

#### Fermer un réseau social (moyenne)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le réseau social existe \
∧ l'utilisateur est moderateur de ce reseau social 

- postcondition : \
∧ le réseau social est fermé \
∧ plus aucun utilisateur ne peut accéder au réseau social

#### Bloquer un membre sur son réseau social (basse) BONUS
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le réseau social existe \ 
∧ l'utilisateur est moderateur de ce reseau social \
∧ le membre existe dans le réseau social \
∧ le membre n'est pas déjà bloqué 

- postcondition : \
∧ le membre est bloqué et ne peut plus interagir dans le réseau social

#### Débloquer un membre sur son réseau social (basse) BONUS
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le réseau social existe \ 
∧ l'utilisateur est moderateur de ce reseau social \
∧ le membre existe \
∧ le membre est actuellement bloqué dans le réseau social

- postcondition : \
∧ le membre est débloqué et peut de nouveau interagir dans le réseau social

### Cas d'utilisation du membre

#### Ajouter un membre a un reseau social (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ l'utilisateur est actif \
∧ le réseau social existe et est ouvert

- postcondition : \
∧ l'utilisateur est membre du réseau social

#### Poster un message (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ l'utilisateur est membre actif du réseau social \
∧ le contenu du message est bien formé (chaine de caractère non null ∧ non vide)\
∧ le réseau social existe et est ouvert

- postcondition : \
∧ le message est publié dans le réseau social \
∧ le message est visible pour le modérateur, mais en attente de modération pour les autres membres du réseau social

#### Cacher un message (basse)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le membre est l'auteur du message \
∧ le message existe et est visible

- postcondition : \
∧ le message est caché et n'est plus visible aux membres du réseau social

#### Quitter un réseau social (basse)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ l'utilisateur est membre du réseau social

- postcondition : \
∧ l'utilisateur n'est plus membre du réseau social


## 2. Préparation des tests de validation des cas d'utilisation

### Tests administrateur

#### Ajouter un utilisateur (HAUTE)

| Décision                                                | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|---------------------------------------------------------|---|---|---|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T | T | T | T | T |
| Pseudo de l'exécuteur existe                            |   | F | T | T | T | T | T | T | T |
| Pseudo de l'exécuteur est administrateur                |   |   | F | T | T | T | T | T | T |
| Pseudo bien formé (non null ∧ non vide)                 |   |   |   | F | T | T | T | T | T |
| Nom bien formé (non null ∧ non vide)                    |   |   |   |   | F | T | T | T | T |
| Prénom bien formé (non null ∧ non vide)                 |   |   |   |   |   | F | T | T | T |
| Courriel bien formé (respectant le standard RFC822)     |   |   |   |   |   |   | F | T | T |
| Utilisateur avec ce pseudo inexistant                   |   |   |   |   |   |   |   | F | T |
|                                                         |   |   |   |   |   |   |   |   |   |
| Utilisateur avec ce pseudo existant                     | F | F | F | F | F | F | F | F | T |
| Le compte de l'utilisateur est actif                    | F | F | F | F | F | F | F | F | T |
|                                                         |   |   |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 2 | 2 | 2 | 3 | 1 | 1 |


Le jeu de test 7 comporte trois tests : non null, non vide, et adresse
courriel bien formée. On aurait pu n'en faire qu'un en considérant la
bibliothèque de validation RFC822 vérifie les deux premières
conditions.

#### Lister les utilisateurs (moyenne)

| Décision                                                | 1 | 2 | 3 | 4 |
|---------------------------------------------------------|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T |
| Pseudo de l'exécuteur existe                            |   | F | T | T |
| Pseudo de l'exécuteur est administrateur                |   |   | F | T |
|                                                         |   |   |   |   |
| Liste des utilisateurs affichée                         | F | F | F | T |
|                                                         |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 |

#### Bloquer un compte utilisateur (basse)

| Décision                                                | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|---------------------------------------------------------|---|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T | T | T |
| Pseudo de l'exécuteur existe                            |   | F | T | T | T | T | T |
| Pseudo de l'exécuteur est administrateur                |   |   | F | T | T | T | T |
| Pseudo bien formé (non null ∧ non vide)                 |   |   |   | F | T | T | T |
| Utilisateur avec ce pseudo existe                       |   |   |   |   | F | T | T |
| Utilisateur avec ce pseudo n'est pas déjà bloqué        |   |   |   |   |   | F | T |
|                                                         |   |   |   |   |   |   |   |
| Utilisateur avec ce pseudo est bloqué                   | F | F | F | F | F | F | T |
|                                                         |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 2 | 1 | 1 | 1 |


#### Retirer un compte utilisateur (basse)

| Décision                                                | 1 | 2 | 3 | 4 | 5 | 6 |
|---------------------------------------------------------|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T | T |
| Pseudo de l'exécuteur existe                            |   | F | T | T | T | T |
| Pseudo de l'exécuteur est administrateur                |   |   | F | T | T | T |
| Pseudo bien formé (non null ∧ non vide)                 |   |   |   | F | T | T |
| Utilisateur avec ce pseudo existe                       |   |   |   |   | F | T |
|                                                         |   |   |   |   |   |   |
| Utilisateur avec ce pseudo est bloqué                   | F | F | F | F | F | T |
|                                                         |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 2 | 1 | 1 |

## Tests utilisateur

#### Désactiver son compte (moyenne)

| Décision                                                | 1 | 2 | 3 | 4 | 5 | 6 |
|---------------------------------------------------------|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T | T |
| Pseudo de l'exécuteur existe                            |   | F | T | T | T | T |
| Pseudo de l'exécuteur n'est pas administrateur          |   |   | F | T | T | T |
| Le compte n'est pas bloqué                              |   |   |   | F | T | T |
| Le compte est actif                                     |   |   |   |   | F | T |
|                                                         |   |   |   |   |   |   |
| Le compte de l'exécuteur est désactivé                  | F | F | F | F | F | T |
|                                                         |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 | 1 | 1 |

#### Activer son compte (moyenne)

| Décision                                                | 1 | 2 | 3 | 4 | 5 |
|---------------------------------------------------------|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T |
| Pseudo de l'exécuteur existe                            |   | F | T | T | T |
| Le compte n'est pas bloqué                              |   |   | F | T | T |
| Le compte est désactivé                                 |   |   |   | F | T |
|                                                         |   |   |   |   |   |
| Le compte de l'exécuteur est actif                      | F | F | F | F | T |
|                                                         |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 | 1 |

#### Créer un réseau social (HAUTE)

| Décision                                                      | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|---------------------------------------------------------------|---|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide)       | F | T | T | T | T | T | T |
| Pseudo de l'exécuteur existe                                  |   | F | T | T | T | T | T |
| Le compte n'est pas bloqué                                    |   |   | F | T | T | T | T |
| Le compte est actif                                           |   |   |   | F | T | T | T |
| Le nom du réseau social est bien formé  (non null ∧ non vide) |   |   |   |   | F | T | T |
| Le nom du réseau social est disponible                        |   |   |   |   |   | F | T |
|                                                               |   |   |   |   |   |   |   |
| Le réseau social est créé                                     | F | F | F | F | F | F | T |
| L'exécuteur est modérateur de ce réseau social                | F | F | F | F | F | F | T |
| Le réseau social est activé                                   | F | F | F | F | F | F | T |
| L'utilisateur créateur du réseau social est membre            | F | F | F | F | F | F | T |
|                                                               |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                          | 2 | 1 | 1 | 1 | 2 | 1 | 1 |

## Tests modérateurs

### Cas d'utilisation du modérateur

#### Modérer un message (HAUTE)

| Décision                                                | 1 | 2 | 3 | 4 | 5 |
|---------------------------------------------------------|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T |
| Le réseau social existe                                 |   | F | T | T | T |
| L'utilisateur est moderateur de ce reseau social        |   |   | F | T | T |
| Le message existe dans le réseau social                 |   |   |   | F | T |
|                                                         |   |   |   |   |   |
| Le message est modéré                                   | F | F | F | F | T |
|                                                         |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 | 1 |

# Inviter un utilisateur sur un réseau social

#### Fermer un réseau social (moyenne)

| Décision                                                | 1 | 2 | 3 | 4 |
|---------------------------------------------------------|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T |
| Le réseau social existe                                 |   | F | T | T |
| L'utilisateur est moderateur de ce reseau social        |   |   | F | T |
|                                                         |   |   |   |   | 
| Le réseau social est fermé                              | F | F | F | T | 
| Plus aucun utilisateur ne peut accéder au réseau social | F | F | F | T | 
|                                                         |   |   |   |   | 
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 | 


#### Bloquer un membre sur son réseau social (basse) BONUS

| Décision                                                | 1 | 2 | 3 | 4 | 5 | 6 |
|---------------------------------------------------------|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T | T |
| Le réseau social existe                                 |   | F | T | T | T | T |
| L'utilisateur est moderateur de ce reseau social        |   |   | F | T | T | T |
| Le membre existe dans le réseau social                  |   |   |   | F | T | T |
| Le membre n'est pas déjà bloqué                         |   |   |   |   | F | T |
|                                                         |   |   |   |   |   |   |
| Le membre est bloqué dans le réseau social              | F | F | F | F | F | T |
|                                                         |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 | 1 | 1 |


#### Débloquer un membre sur son réseau social (basse) BONUS

| Décision                                                | 1 | 2 | 3 | 4 | 5 | 6 |
|---------------------------------------------------------|---|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T | T | T |
| Le réseau social existe                                 |   | F | T | T | T | T |
| L'utilisateur est moderateur de ce reseau social        |   |   | F | T | T | T |
| Le membre existe dans le réseau social                  |   |   |   | F | T | T |
| Le membre est actuellement bloqué dans le réseau social |   |   |   |   | F | T |
|                                                         |   |   |   |   |   |   |
| Le membre est débloqué dans le réseau social            | F | F | F | F | F | T |
|                                                         |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 | 1 | 1 |


## Tests membres

#### Ajouter un membre a un reseau social (HAUTE)

| Décision                                                | 1 | 2 | 3 | 4 |
|---------------------------------------------------------|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T |
| L'utilisateur est actif                                 |   | F | T | T |
| Le reseau social existe et est ouvert                   |   |   | F | T |
|                                                         |   |   |   |   |
| L'utilisateur est membre du reseau social               | F | F | F | T |
|                                                         |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 | 1 |

#### Poster un message (HAUTE)

| Décision                                                                | 1 | 2 | 3 | 4 | 5 |
|-------------------------------------------------------------------------|---|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide)                 | F | T | T | T | T |
| L'utilisateur est membre actif du réseau social                         |   | F | T | T | T |
| Contenu du message bien formé (non null ∧ non vide)                     |   |   | F | T | T |
| Le réseau social existe et est ouvert                                   |   |   |   | F | T |
|                                                                         |   |   |   |   |   |
| Le message est publié dans le réseau social                             | F | F | F | F | T |
| Le message est visible pour le modérateur et en attente pour les autres | F | F | F | F | T |
|                                                                         |   |   |   |   |   |
| nombre de tests dans le jeu de tests                                    | 2 | 1 | 2 | 2 | 1 |


#### Cacher un message (basse)

| Décision                                                | 1 | 2 | 3 | 4 |
|---------------------------------------------------------|---|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T | T |
| Le membre est l'auteur du message                       |   | F | T | T |
| Le message existe et est visible                        |   |   | F | T |
|                                                         |   |   |   |   |
| Le message est caché et n'est plus visible              | F | F | F | T |
|                                                         |   |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 2 | 1 |



#### Quitter un réseau social (basse)

| Décision                                                | 1 | 2 | 3 |
|---------------------------------------------------------|---|---|---|
| Pseudo de l'exécuteur bien formé  (non null ∧ non vide) | F | T | T |
| L'utilisateur est membre du réseau social               |   | F | T |
|                                                         |   |   |   |
| L'utilisateur n'est plus membre du réseau social        | F | F | T |
|                                                         |   |   |   |
| nombre de tests dans le jeu de tests                    | 2 | 1 | 1 |


# 3. Conception

## 3.1. Listes des classes candidates et de leurs attributs

Voici les listes des classes candidates et de leurs attributs:
- `MiniSocs` (mise en œuvre du patron de conception Façade) avec
  l'attribut `nom` pour le nom du système,
- `Utilisateur` avec les attributs `pseudo` pour identifier de manière
  unique un utilisateur, `nom` et `prénom`, adresse `courriel`, et
  `etatCompte` pour l'état de son compte,
- `ÉtatCompte` avec les énumérateurs `COMPTE_ACTIF` et `COMPTE_DÉSACTIVÉ`,

## 3.2. Premières opérations des classes

Les seules opérations que nous connaissons déjà sont celles
correspondant aux cas d'utilisation. Comme nous utilisons le patron de
conception Façade, toutes les opérations des cas d'utilisation sont
dans la Façade.

Donc, dans la classe `MiniSocs`, voici les premières opérations (en
ignorant celles de priorité « basse ») :
- `ajouterUtilisateur`,
- `désactiverCompte`,
- `bloquerCompte`,
- `listerUtilisateurs`.

## 3.3. Diagramme de classes

Le diagramme de classes obtenu lors d'une analyse à partir de l'énoncé
du problème est donné dans la figure qui suit. Dans ces diagrammes,
les opérations ne sont pas mentionnées parce qu'il y en aurait trop.

**Important: même dans les diagrammes de la conception détaillée, on
ne montre pas les attributs traduisant des associations.**

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

Version sans les notifications
([source](./Diagrammes/minisocs_uml_diag_classes_sans_notif.pu)).

![diagrammeclasses](./Diagrammes/minisocs_uml_diag_classes_sans_notif.svg)
([source](./Diagrammes/minisocs_uml_diag_classes_sans_notif.pu))

## 3.4. Diagrammes de séquence

Dans la suite, plusieurs versions d'un même diagramme de séquence sont proposés :
- une version dite « recommandée » **avec** les barres d'activation,
- une version dite « simplifiée » **sans** les barres d'activation.

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

#### DSUC1 : Creer un reseau social (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_create_rs.pu)).

![diagrammeséquencecreerreseausocial](./Diagrammes/minisocs_uml_diag_seq_create_rs.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_create_rs.pu))


#### DSUC2 : Ajouter un utilisateur (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.pu)).

![diagrammeséquenceajouterutilisateur](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.pu))

Version simplifiée
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur_version_simplifiee.pu)).



![diagrammeséquenceajouterutilisateursimplifié](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur_version_simplifiee.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur_version_simplifiee.pu))

#### DSUC3 : Poster un message (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_poster_message.pu)).

![diagrammeséquencepostermessage](./Diagrammes/minisocs_uml_diag_seq_poster_message.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_poster_message.pu))


# 7. Diagrammes de machine à états et invariants

Dans les diagrammes de machine à états, nous faisons le choix de faire
apparaître les états de création et de destruction. Ces états sont
transitoires, il est vrai, mais ils méritent cependant une attention
particulière.  L'état de création, en particulier, donne lieu, lors de
la réalisation dans un langage de programmation orienté objet, à
l'écriture d'une opération « constructeur » qui garantit que
tous les attributs sont initialisés correctement dès la création d'une
instance. Nous savons également qu'en Java la destruction se réalise
en « oubliant » l'objet : un mécanisme de ramasse
miettes détruit automatiquement les objets lorsqu'ils ne sont plus
référencés. Il n'en est pas de même dans tous les langages, et par
exemple en C++ qui ne possède pas de mécanisme de ramasse miettes, la
destruction des objets peut s'avérer un casse tête ardu.

Les actions provoquées par des appels en provenance d'autres objets
apparaissent sur les transitions. Nous avons gardé comme action
interne uniquement les actions correspondant à des appels que l'objet
fait seul ou fait de manière répétitive.  Les constructeurs et
destructeurs sont des exceptions (ils apparaissent en interne bien
qu'étant déclenchés par un autre objet).

## 7.1. Classe Utilisateur

### 7.1.1. Diagramme de machine à états

Diagramme ([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.pu)).

![diagrammemachineaétatsutilisateur](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.svg)
([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.pu))

### 7.1.2. Fiche de la classe

Voici tous les attributs de la classe :
```
— final String pseudonyme
— String nom
— String prenom
— String courriel
— EtatCompte etatCompte
```

### 7.1.3. Invariant

```
  pseudonyme != null ∧ !pseudonyme.isBlank()
∧ nom != null ∧ !nom.isBlank()
∧ prenom != null ∧ !prenom.isBlank()
∧ EmailValidator.getInstance().isValid(courriel)
∧ etatCompte != null
```

## 7.2. Classe Message

### 7.2.1. Diagramme de machine à états

Diagramme ([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.pu)).

![diagrammemachineaétatsmessage](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.svg)
([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.pu))

### 7.2.2. Fiche de la classe

Voici tous les attributs de la classe :
```
— String contenu
— etatMessage etatMessage
— membre : Membre (association)
— moderateur : Moderateur (composition)
— reseauSocial : ReseauSocial (composition)
```

Voici toutes les operations de la classe :
```
— Constructeur
— Destructeur

```

### 7.2.3. Invariant

```
  contenu != null ∧ !contenu.isBlank()
∧ etatMessage == "accepté" ∨ etatMessage == "verification pending" 
∨ etatMessage == "inappropriate" ∨ etatMessage == "hidden"
∨ etatMessage == "sent"
∧ membre != null
∧ moderateur != null
∧ reseauSocial != null

```

## 7.3 Classe Membre

### 7.2.1  Diagramme de machine à états

### 7.2.3 Fiche de la classe

```
— String: pseudoParticulier 
— utilisateur: Utilisateur (association) 
— reseauSocial : ReseauSocial (composition)
— 
```

### 7.3.3 Invariant de la classe

```
pseudoParticulier != null ∧ !pseudoParticulier.isBlank()
```

# 8 Préparation des tests unitaires

## 8.1. Opérations de la classe Utilisateur

### Opération constructeur OK

public Utilisateur(final String pseudonyme, final String nom, final String prenom, final String courriel)

|                                              | 1   | 2   | 3   | 4   | 5   |
|:---------------------------------------------|:----|:----|:----|:----|:----|
| pseudonyme bien formé (non null ∧ non vide)  | F   | T   | T   | T   | T   |
| nom bien formé (non null ∧ non vide)         |     | F   | T   | T   | T   |
| prénom bien formé  (non null ∧ non vide)     |     |     | F   | T   | T   |
| courriel bien formé selon le standard RFC822 |     |     |     | F   | T   |
|                                              |     |     |     |     |     |
|                                              |     |     |     |     |     |
| utilisateur (non null)                       |     |     |     |     | T   |
| pseudonyme' = pseudonyme                     |     |     |     |     | T   |
| nom' = nom                                   |     |     |     |     | T   |
| prénom' = prénom                             |     |     |     |     | T   |
| courriel' = courriel                         |     |     |     |     | T   |
| étatCompte' = actif                          |     |     |     |     | T   |
|                                              |     |     |     |     |     |
| levée d'une exception                        | oui | oui | oui | oui | non |
|                                              |     |     |     |     |     |
| nombre de tests dans le jeu de tests         | 2   | 2   | 2   | 3   | 1   |

Trois tests dans le jeu de tests 5 pour non null, puis non vide, et
enfin une chaîne de caractères qui n'est pas une adresse courriel.

### Opération désactiverCompte OK

public void desactiverCompte()

|                                      | 1   | 2   |
|:-------------------------------------|:----|:----|
| étatCompte non bloqué                | F   | T   |
|                                      |     |     |
| étatCompte' = désactivé              |     | T   |
|                                      |     |     |
| levée d'une exception                | oui | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   | 2   |

Deux tests dans le jeu de tests 2 pour l'idempotence.

### 

## 8.2. Opérations de la classe Message

### Opération constructeur OK

public Message(final String contenu, final Membre membre, final EtatMessage etat, final ReseauSocial rs)

|                                      | 1   | 2   | 3   | 4   | 5   |
|:-------------------------------------|:----|:----|:----|:----|-----|
| contenu (non null ∧ non vide)        | F   | T   | T   | T   | T   |
| membre (non null)                    |     | F   | T   | T   | T   |
| etat (non null)                      |     |     | F   | T   | T   |
| rs non null                          |     |     |     | F   | T   |
|                                      |     |     |     |     |     |
| message non null                     |     |     |     |     | T   |
| contenu' = contenu                   |     |     |     |     | T   |
| etat' = etat                         |     |     |     |     | T   |
| rs' = rs                             |     |     |     |     | T   |
| membre' = membre                     |     |     |     |     | T   |
|                                      |     |     |     |     |     |
| levée d'une exception                | oui | oui | oui | oui | non |
|                                      |     |     |     |     |     |
| nombre de tests dans le jeu de tests | 2   | 1   | 1   | 1   | 1   |


### Opération modifierStatutMessage OK

public void modifierStatutMessage(final EtatMessage etat)

|                                      | 1   | 2   |
|:-------------------------------------|:----|-----|
| etat (non null)                      | F   | T   |
|                                      |     |     |
| etat' = etat                         |     | T   |
|                                      |     |     |
| levée d'une exception                | oui | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   |     |
	
### Opération envoyerMessage OK

|                                      | 1   | 2   |
|:-------------------------------------|:----|-----|
| message membre modérateur            | F   | T   |
|                                      |     |     |
| etat' = ACCEPTE                      | F   | T   |
|                                      |     |     |
| levée d'une exception                | non | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   | 1   |


## 8.3. Opérations de la classe Membre

### Opération constructeur OK

public Membre(final String pseudoParticulier, final Utilisateur utilisateur, final ReseauSocial rs)

|                                                    | 1   | 2   | 3   | 4   |
|:---------------------------------------------------|:----|:----|:----|:----|
| pseudoParticulier bien formé (non null ∧ non vide) | F   | T   | T   | T   |
| utilisateur (non null)                             |     | F   | T   | T   |
| rs (non null)                                      |     |     | F   | T   |
|                                                    |     |     |     |     |
| membre not null                                    |     |     |     |     |
| pseudoParticulier' = pseudoParticulier             |     |     |     |     |
| utilisateur' = utilisateur                         |     |     |     |     |
| rs' = rs                                           |     |     |     |     |
|                                                    |     |     |     |     |
| levée d'une exception                              | oui | oui | oui | non |
|                                                    |     |     |     |     |
| nombre de tests dans le jeu de tests               | 2   | 1   | 1   | 1   |

### Opéation modérer OK

public void moderer(final Message m, EtatMessage etat)

|                                      | 1   | 2   | 3   | 4   |
|:-------------------------------------|:----|:----|:----|:----|
| m (non null)                         | F   | T   | T   | T   |
| etat (non null)                      |     | F   | T   | T   |
| exécutant de type Modérateur         |     |     | F   | T   |
|                                      |     |     |     |     |
| etat non null                        |     |     |     | T   |
| etat' = etat                         |     |     |     | T   |
|                                      |     |     |     |     |
| levée d'une exception                | oui | oui | oui | non |
|                                      |     |     |     |     |
| nombre de tests dans le jeu de tests | 1   | 1   | 1   | 1   |

## Opérations de la classe Reseau Social

### Opération constructeur OK

public ReseauSocial(final String nom, boolean ouvert)

|                                      | 1   | 2   | 3   |
|:-------------------------------------|:----|:----|:----|
| nom (non null  ∧ non vide)           | F   | T   | T   |
| ouvert                               |     | F   | T   |
|                                      |     |     |     |
| rs (non null)                        |     |     |     |
| nom' = nom                           |     | T   | T   |
| ouvert' = ouvert                     |     | T   | T   |
| membres' (non null)                  |     | T   | T   |
| messages' (non null)                 |     | T   | T   |
|                                      |     |     |     |
| levée d'une exception                | oui | non | non |
|                                      |     |     |     |
| nombre de tests dans le jeu de tests | 2   | 1   | 1   |

### Opération getMembrefromUtilisateur OK

public Membre getMembrefromUtilisateur(Utilisateur u)

|                                           | 1   | 2   | 3   |
|:------------------------------------------|:----|:----|:----|
| u non null                                | F   | T   | T   |
| utilisateur associé à un membre sur le rs |     | F   | T   |
|                                           |     |     |     |
|                                           |     |     |     |
| renvoie null                              |     | T   | F   |
| renvoie membre                            |     |     | T   |
|                                           |     |     |     |
| levée d'une exception                     | oui | non | non |
|                                           |     |     |     |
| nombre de tests dans le jeu de tests      | 1   | 1   | 1   |

// Pb : deux membres peuvent avoir le mm rs

### Opération getMessageFromId

public Message getMessageFromId(double id)

|                                      | 1   | 2   | 3   |
|:-------------------------------------|:----|:----|:----|
| id (non null)                        | F   | T   | T   |
| message avec id existe sur le rs     |     | F   | T   |
|                                      |     |     |     |
|                                      |     |     |     |
| renvoie null                         |     | T   | F   |
| renvoie message                      |     |     | T   |
|                                      |     |     |     |
| levée d'une exception                | oui | non | non |
|                                      |     |     |     |
| nombre de tests dans le jeu de tests | 1   | 1   | 1   |

On vérifie aussi que l'id est incrémenté de 1 à chaque nouveau message... OK

### Opération ajouterMembre

public ajouterMembre(Utilisateur u, String pseudoParticulier, boolean mod)

|                                         | 1   | 2   | 3   | 4   | 5   |
|                                         |     |     |     |     |     |
|:----------------------------------------|:----|:----|:----|:----|-----|
| utilisateur (non null)                  | F   | T   | T   | T   | T   |
| pseudoParticulier (non null ∧ non vide) |     | F   | T   | T   | T   |
| pseudoParticulier disponible            |     |     | F   | T   | T   |
| mod                                     |     |     |     | F   | T   |
|                                         |     |     |     |     |     |
| membre non null                         |     |     |     | T   | T   |
| membre dans rs                          |     |     |     | T   | T   |
| membre est modérateur                   |     |     |     | F   | T   |
|                                         |     |     |     |     |     |
| levée d'une exception                   | oui | oui | oui | non | non |
|                                         |     |     |     |     |     |
| nombre de tests dans le jeu de tests    | 1   | 2   | 1   | 1   | 1   |

### Opération ajouterMessage

public Message ajouterMessage(String contenu, Membre m)

|                                      | 1   | 2   | 3   | 4   |
|                                      |     |     |     |     |
|:-------------------------------------|:----|:----|:----|-----|
| contenu (non null ∧ non vide)        | F   | T   | T   | T   |
| membre non null                      |     | F   | T   | T   |
| membre modérateur                    |     |     | F   | T   |
|                                      |     |     |     |     |
| message' non null                    |     |     | T   | T   |
| rs contient message'                 |     |     | T   | T   |
| contenu' = contenu                   |     |     | T   | T   |
| etat' = SENT                         |     |     | F   | T   |
| etat' = VERIFICATION_PENDING         |     |     | T   | F   |
|                                      |     |     |     |     |
| levée d'une exception                | oui | oui | non | non |
|                                      |     |     |     |     |
| nombre de tests dans le jeu de tests | 2   | 1   | 1   | 1   |


## Tests validation

### UC1 : Créer un réseau social

public void creerReseauSocial(final String pseudoExec, final String nomReseau, final boolean ouvert, final String pseudoParticulier)

|                                                                                 | 1   | 2   | 3   | 4   | 5   | 6   |
|:--------------------------------------------------------------------------------|:----|:----|:----|-----|-----|-----|
| pseudoExec  (non null ∧ non vide)                                               | F   | T   | T   | T   | T   | T   |
| Compte exec actif                                                               |     | F   | T   | T   | T   | T   |
| nomReseau  (non null ∧ non vide)                                                |     |     | F   | T   | T   | T   |
| pseudoParticulier  (non null ∧ non vide)                                        |     |     |     | F   | T   | T   |
| rs n'existe pas                                                                 |     |     |     |     | F   | T   |
|                                                                                 |     |     |     |     |     |     |
| rs non null                                                                     |     |     |     |     |     | T   |
| nomReseau' = nomReseau                                                          |     |     |     |     |     | T   |
| ouvert' = ouvert                                                                |     |     |     |     |     | T   |
| pseudoParticulier' = pseudoParticulier                                          |     |     |     |     |     | T   |
| rs contient moderateur nommé pseudoParticlier associé à utilisateur  pseudoExec |     |     |     |     |     | T   |
|                                                                                 |     |     |     |     |     |     |
| Lancement d'une exception                                                       | oui | oui | oui | oui | oui | non |
|                                                                                 |     |     |     |     |     |     |
| Nombre de tests dans le jeu de tests                                            | 2   | 1   | 2   | 2   | 1   | 1   |

Nous avons aussi testé avec ouvert = false... OK

### UC2 : Ajouter un membre à un réseau social

public Membre ajouterMembreRS(final String pseudo, final String nomReseau, final String pseudoParticulier, final boolean mod) 

|                                           | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   |
|:------------------------------------------|:----|:----|:----|-----|-----|-----|-----|-----|
| pseudo  (non null ∧ non vide)             | F   | T   | T   | T   | T   | T   | T   | T   |
| nomReseau  (non null ∧ non vide)          |     | F   | T   | T   | T   | T   | T   | T   |
| pseudoParticulier  (non null ∧ non vide)  |     |     | F   | T   | T   | T   | T   | T   |
| Compte exec actif                         |     |     |     | F   | T   | T   | T   | T   |
| rs non null                               |     |     |     |     | F   | T   | T   | T   |
| rs ouvert                                 |     |     |     |     |     | F   | T   | T   |
| l'utilisateur n'a pas de membre sur le rs |     |     |     |     |     |     | F   | T   |
|                                           |     |     |     |     |     |     |     |     |
| pseudoParticulier' = pseudoParticulier    |     |     |     |     |     |     |     | T   |
| rs contient membre pseudoParticlier       |     |     |     |     |     |     |     | T   |
| mod' = mod                                |     |     |     |     |     |     |     | T   |
|                                           |     |     |     |     |     |     |     |     |
| Lancement d'une exception                 | oui | oui | oui | oui | oui | oui | oui | non |
|                                           |     |     |     |     |     |     |     |     |
| Nombre de tests dans le jeu de tests      | 2   | 2   | 2   | 1   | 1   | 1   | 1   | 1   |

### UC3 : Poster un message

public void posterMessageRS(final String pseudo, final String contenu, final String nomReseau) 

|                                                | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
|:-----------------------------------------------|:----|:----|:----|-----|-----|-----|-----|-----|-----|
| pseudo  (non null ∧ non vide)                  | F   | T   | T   | T   | T   | T   | T   | T   | T   |
| contenu  (non null ∧ non vide)                 |     | F   | T   | T   | T   | T   | T   | T   | T   |
| nomReseau  (non null ∧ non vide)               |     |     | F   | T   | T   | T   | T   | T   | T   |
| Compte exec actif                              |     |     |     | F   | T   | T   | T   | T   | T   |
| rs non null                                    |     |     |     |     | F   | T   | T   | T   | T   |
| rs ouvert                                      |     |     |     |     |     | F   | T   | T   | T   |
| utilisateur pseudo possède un membre sur le rs |     |     |     |     |     |     | F   | T   | T   |
| membre possédé est modérateur sur le rs        |     |     |     |     |     |     |     | F   | T   |
|                                                |     |     |     |     |     |     |     |     |     |
| message non null                               |     |     |     |     |     |     |     | T   | T   |
| message posté dans rs                          |     |     |     |     |     |     |     | T   | T   |
| contenu' = contenu                             |     |     |     |     |     |     |     | T   | T   |
| etat' = SENT                                   |     |     |     |     |     |     |     | F   | T   |
| etat' = VERIFICATION_PENDING                   |     |     |     |     |     |     |     | T   | F   |
|                                                |     |     |     |     |     |     |     |     |     |
| Lancement d'une exception                      | oui | oui | oui | oui | oui | oui | oui | non | non |
|                                                |     |     |     |     |     |     |     |     |     |
| Nombre de tests dans le jeu de tests           | 2   | 2   | 2   | 1   | 1   | 1   | 1   | 1   | 1   |

### UC4 : Modérer un message

public void modererMessage(final String pseudo, final double id, final String nomReseau, final EtatMessage etat)

|                                                | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  |
|:-----------------------------------------------|:----|:----|:----|-----|-----|-----|-----|-----|-----|-----|
| pseudo  (non null ∧ non vide)                  | F   | T   | T   | T   | T   | T   | T   | T   | T   | T   |
| id positif                                     |     | F   | T   | T   | T   | T   | T   | T   | T   | T   |
| nomReseau  (non null ∧ non vide)               |     |     | F   | T   | T   | T   | T   | T   | T   | T   |
| Compte exec actif                              |     |     |     | F   | T   | T   | T   | T   | T   | T   |
| rs non null                                    |     |     |     |     | F   | T   | T   | T   | T   | T   |
| rs ouvert                                      |     |     |     |     |     | F   | T   | T   | T   | T   |
| message avec id existe                         |     |     |     |     |     |     | F   | T   | T   | T   |
| utilisateur pseudo possède un membre sur le rs |     |     |     |     |     |     |     | F   | T   | T   |
| membre possédé est modérateur sur le rs        |     |     |     |     |     |     |     |     | F   | T   |
|                                                |     |     |     |     |     |     |     |     |     |     |
| message non null                               |     |     |     |     |     |     |     |     |     | T   |
| message reste posté dans rs                    |     |     |     |     |     |     |     |     |     | T   |
| etat' = etat                                   |     |     |     |     |     |     |     |     |     | T   |
|                                                |     |     |     |     |     |     |     |     |     |     |
| Lancement d'une exception                      | oui | oui | oui | oui | oui | oui | oui | oui | oui | non |
|                                                |     |     |     |     |     |     |     |     |     |     |
| Nombre de tests dans le jeu de tests           | 2   | 1   | 2   | 1   | 1   | 1   | 1   | 1   | 1   | 1   |


### UC5 : Ajouter un utilisateur à MiniSocs

public Utilisateur ajouterUtilisateur(final String pseudo, final String nom, final String prenom, final String courriel)

|                                                     | 1   | 2   | 3   | 4   | 5   |
|:----------------------------------------------------|:----|:----|:----|-----|-----|
| pseudo  (non null ∧ non vide)                       | F   | T   | T   | T   | T   |
| nom  (non null ∧ non vide)                          |     | F   | T   | T   | T   |
| prenom  (non null ∧ non vide)                       |     |     | F   | T   | T   |
| Courriel bien formé (respectant le standard RFC822) |     |     |     | F   | T   |
|                                                     |     |     |     |     |     |
| utilisateur créé non null                           |     |     |     |     |     |
| pseudo' = pseudo                                    |     |     |     |     | T   |
| nom' = nom                                          |     |     |     |     | T   |
| prenom' = prenom                                    |     |     |     |     | T   |
| courriel'= courriel                                 |     |     |     |     | T   |
| minisocs contient utilisateur                       |     |     |     |     | T   |
|                                                     |     |     |     |     |     |
| Lancement d'une exception                           | oui | oui | oui | oui | non |
|                                                     |     |     |     |     |     |
| Nombre de tests dans le jeu de tests                | 2   | 2   | 2   | 3   | 1   |

---
FIN DU DOCUMENT
