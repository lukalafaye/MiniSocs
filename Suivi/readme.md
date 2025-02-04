Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part, 

 
---
# Suivi du lun. 12 févr. 2024 21:23:58
Sophie Chabridon
 
Bon démarrage.

Pour prendre de bonnes habitudes, merci d'indiquer lorsque vous prenez une remarque en compte.

* Pour les cas d'utilisation, revoir l'héritage. Un membre d'un réseau social est également un utilisateur...

[X] DIAGUC-06-Pb-généralisation-spécialisation-acteur

* Le cas d'utilisation "rejoindre un réseau social" n'est pas proposé directement aux utilisateurs. Cela doit passer par le modérateur qui peut ajouter un utilisateur à un réseau

[X] DIAGUC-02-Compréhension-étude-de-cas

* Revoir les pré- et post-conditions, pour tous les cas d'usage obligatoires. 

Par exemple, pour "créer un réseau social", dans les post-conditions, il faut prévoir d'activer le réseau social, et d'ajouter l'utilisateur qui vient de le créer également comme membre.

Pour "poster un message", ne pas oublier de vérifier que le réseau social existe et est ouvert. Il y a également plusieurs post-conditions à ajouter telles que le message est visible pour le modérateur, mais en attente de modération pour les autres membres du réseau social...

[X] PREPOSTCOND-01-Pré-post-condition-manquante

[X] PREPOSTCOND-05-Chaîne-de-caractères

[X] PREPOSTCOND-06-Précondition-postcondition-incomplète

* Il faudra ensuite revoir les tables de décision selon les pré- et post-conditions ajoutées/modifiées :

[X] TABLEDECTV-07-MAJ-précondition-postcondition

 
 
---
# Suivi du mar. 05 mars 2024 22:19:06
Elisabeth Brunet

Bon travail. Voici des remarques qui devraient vous aider à affiner
votre modélisation avant de passer à l'implémentation. 


Diagramme de classes
[X]Quelle différene entre débloqué et actif?

Le blocage/déblocage sont gérés par l'administrateur / modérateur tandis que l'activation/désactivation sont gérés par l'utilisateur.

[X] Participation ne doit pas faire apparaître d'attribut pseudonyme
car il y a une association avec Utilisateur. D'ailleurs, depuis
Participation, cela va vous demander du travail de récuperer
l'Utilsateur depuis son pseudo. Pourquoi ne pas avoir opter pour une
ref sur l'Utilsiateur?

Lien bidirectionnel entre participation et utilisateur (pour récupérérer l'utilisateur à partir d'un message.

Supprimé pseudo_particulier...

[X] le fait qu'un message puisse juste être accepté ou non grâce à un
booléen n'est pas suffisant. Vous auriez dû vous en rendre compte en
faisant le diagramme de machine à états de Message.

J'ai rajouté les attributs manquants.

[X]Même remarque sur le champ pseudoParticulier de Message. Si
association, cela ne doit pas apparaître sur le diagramme.

Supprimé pseudo_particulier...

[X] Un réseau social doit pouvoir etre fermé par un modérateur.

Rajout d'un booléen attribut de la classe R.S.

[X] vous avez deux associations entre Participation et Message qui
devrait etre unique; idem entre Participation et ReseauSocial.


Diagrammes de séquence
Cas d'utilisation « créer un réseau social »
[]u n'est pas un objet Utilsiateur mais un acteur utilsiateur externe
au système. Il faut le voir comme un humain qui utilise votre
programme. (Vous devriez le nommer autrement ). C'est pour cela qu'on
utilise le bonhomme! 
[] du coup, il faut donner le pseudo de l'utilisateur qui demande la
création du réseau en paramètre du cas d'utilisation et récupérer
l'Utilsiateur instancié u, vérifier s'il existe et est actif. 
[] Il n'y a pas de classe compteParticipation dans votre diagramme de
classes.
[] En plus de créer le reseau, il faut creer une Participation ayant
un rôle de modérateur à ajouter au réseau et à l'utilsiateur initial.

Cas d'utilisation « ajouter un membre à un réseau social »
[]manquant


Cas d'utilisation « poster un message »
[]meme remarque. L'utiliateur u n'est pas une instance d'un objet
Utilisateur mais un acteur du système. A revoir donc.


Raffinement du diagramme de classes

Fiche de la classe « Message »
[X] comment encodez-vous les différents états du Message avec seulement
un booléen "accpeté"? il vous faut une énumération.

Fait.

[X] Un attribut pseudoParticulier vous est-il vraiment utile?

Pas nécessaire mais certains utilisateurs veulent un pseudo différent pour chaque réseau social...

[X] Pareil pour Participation et pourquoi avoir les deux?
Supression de pseudonyme (redondance).

[X] Pareil pour reseauSocial. La navigabilité depuis Message est-elle utile?
Il n'y a pas de navigabilité depuis message?


Diagramme de machine à états et invariant
Diagramme de machine à états de la classe « Message »
[X] Un message puet aussi passer de l'état inappropirte et hidden vers
en destruction
Implémenté.


Invariant de la classe « Message »
[] a revoir en fonction des modifications que vous aurez apportées à
vos attributs


4. Préparation des tests unitaires

Table de décision des tests unitaires de la méthode Message::constructeur
[] quand on crée un message, il ne devrait pas y avoir de
pré-condition sur le fait que le message soit accepté ou non car c'est
le constructeur qui initialise cet attribut. Cela ne dervait donc pas
engendré de test. 

[]A revoir



 
---
# Suivi du jeu. 04 avril 2024 10:52:20
Sophie Chabridon
 
Bon avancement sur les tests, continuez ainsi. Quelques points à améliorer, notamment sur la cohérence entre la conception et le code.	

[] STYLECHECK-03-pb-checkstyle
	à vérifier sur les classes principales
	
[] IDIOM-02-Méthode-toString-manquante
	cela concerne la classe ReseauSocial

[] STYLECHECK-02-javadoc-manquant

[] JAVA-07-Cohérence-avec-diagramme-de-classes
	- Le diagramme de classes prévoit une association entre Moderateur et Message, mais cela n'est pas traduit dans le code. Soit vous devez modifier le code, soit le diagramme de classes.
	
	- Les associations entre Utilisateur et les classes Membre et ReseauSocial ne sont pas traduites dans le code. Même remarque que précédemment.
	
	- Un attribut "moderateurs" existe dans le code de la classe ReseauSocial mais pas dans le diagramme de classes.
