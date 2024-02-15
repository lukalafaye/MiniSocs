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

* Le cas d'utilisation "rejoindre un réseau social" n'est pas proposé directement aux utilisateurs. Cela doit passer par le modérateur qui peut ajouter un utilisateur à un réseau social.

[X] DIAGUC-02-Compréhension-étude-de-cas

* Revoir les pré- et post-conditions, pour tous les cas d'usage obligatoires. 

Par exemple, pour "créer un réseau social", dans les post-conditions, il faut prévoir d'activer le réseau social, et d'ajouter l'utilisateur qui vient de le créer également comme membre.

Pour "poster un message", ne pas oublier de vérifier que le réseau social existe et est ouvert. Il y a également plusieurs post-conditions à ajouter telles que le message est visible pour le modérateur, mais en attente de modération pour les autres membres du réseau social...

[X] PREPOSTCOND-01-Pré-post-condition-manquante

[X] PREPOSTCOND-05-Chaîne-de-caractères

[X] PREPOSTCOND-06-Précondition-postcondition-incomplète

* Il faudra ensuite revoir les tables de décision selon les pré- et post-conditions ajoutées/modifiées :

[X] TABLEDECTV-07-MAJ-précondition-postcondition

 
