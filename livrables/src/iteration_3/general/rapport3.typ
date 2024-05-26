#import "../../template.typ": *

#show: project.with(
  subject: "Projet long de Technologie Objet ",
  title: "Rapport général",
  subtitle: "EF02",
  authors: (
    "ARRIX-POUGET Baptiste",
    "DEMAZURE Clement",
    "DREUMONT Evann",
    "GIULIANI Astrid",
    "GUTIERREZ Tom",
    "LAGRANGE Angel",
    "SABLAYROLLES Guillaume",
    "THEVENET Louis",
  ),
  teachers: (),
  date: "2024",
  toc: false,
)
#outline(indent: true)

#pagebreak()

= Introduction

Dans le cadre de notre projet long de TOB, nous avons choisi de développer un
éditeur de dessin vectoriel nommé #strong("Pinte") (référence à Paint). Ce choix
a été motivé par la difficulté de la tâche et par la variété d'outils mobilisés.
Les dessins vectoriels désignent une forme de fichiers décrits par des formes,
leurs dimensions et positions, et offrent une flexibilité et une qualité bien
particulière, permettant notamment des mises à l'échelle sans perte de qualité
et une facilité de modification qui ne sont pas possibles avec des images
normales, pratique pour faire des logos par exemple.

== Objectifs du Projet

=== Objectif Général

L'objectif principal de #strong("Pinte") est de fournir une solution complète
pour la création, la modification et l'exportation de dessins vectoriels. En
utilisant les principes des méthodes agiles, nous visons à développer un outil
qui soit à la fois intuitif et puissant, permettant aux utilisateurs de réaliser
des projets graphiques de qualité avec facilité.

=== Caractéristiques Principales Attendues

- #strong("Choix d'Outils Complet") : Permettre à l'utilisateur de pouvoir
  utiliser tout les outils connus pour pouvoir dessiner sur un logiciel type Paint
  (sceau, sélection, construire un carré, tracer une droite, etc..).
- #strong("Facilité d'Utilisation") : Concevoir une interface utilisateur
  intuitive et ergonomique, permettant même aux novices de créer et de modifier
  des dessins vectoriels sans courbe d'apprentissage abrupte.
- #strong("Flexibilité et Personnalisation") : Offrir des options de
  personnalisation pour les raccourcis clavier et l'apparence générale de
  l'application.
- #strong("Compatibilité") : Assurer la compatibilité avec les formats de fichiers
  graphiques les plus courants, permettant l'importation et l'exportation en SVG,
  JPEG, PNG, etc.
- #strong("Performance") : Garantir une performance fluide et réactive.

== Motivations et Contexte

La motivation derrière le développement de #strong("Pinte") nous viens des
défauts principaux dans les éditeurs de dessin vectoriel existants. Bien que des
outils comme Adobe Illustrator et Inkscape soient extrêmement puissants, ils
peuvent être intimidants pour les nouveaux utilisateurs en raison de leur
complexité.. De plus, ces outils sont souvent coûteux, nécessitent des
abonnements ou demande une machine puissante pour les faire tourner, ce qui peut
représenter un obstacle pour les étudiants, les amateurs et les petites
entreprises.

Nous avons donc décidé de créer un outil open-source qui comblerait ces lacunes,
en mettant l'accent sur l'accessibilité et la facilité d'utilisation, sans
sacrifier la puissance et la flexibilité attendues d'un éditeur vectoriel
professionnel.

== Méthodologie Agile

Pour mener à bien ce projet, nous avons adopté une approche agile, caractérisée
par des cycles de développement itératifs. Cette méthodologie nous permet de
rester flexibles et réactifs aux retours de notre encadrant de cours, d'intégrer
des améliorations en continu et de s'assurer que le produit final soit
qualitatif.

#pagebreak()
=== Phases de Développement

1. #strong("Phase de Planification") : Les premiers cours de méthodes agiles nous
  ont permis plusieurs choses : identification des besoins, définition des
  fonctionnalités principales et secondaires, et établissement du calendrier de
  développement.
2. #strong("Phase de Conception") : Suite à la première phase, nous devions
  élaborer des maquettes de l'interface utilisateur et concevoir des architectures
  logicielles et des bases de données.
3. #strong("Phase de Développement") : Ensuite, le plus important :
  l'implémentation des fonctionnalités de base, suivi des sprints et des
  révisions, et réalisation des tests unitaires et d'intégration.
4. #strong("Phase de Test") : Validation de la stabilité et de la performance de
  l'application, corrections des bugs et ajustements des fonctionnalités selon les
  retours des tests utilisateurs.

=== Outils et Technologies

Pour le développement en JAVA de #strong("Pinte"), nous avons choisi de
travailler avec des outils nous permettant l'agilité de notre projet :
#linebreak()
#linebreak()
- #strong("API JavaFX") : Cette API nous permet de gérer l'affichage de notre
  application. Nous avions le choix entre celle vue en cours (SWING) ou JavaFX
  mais par raison d'efficacité, de fonctionnalités et du fait qu'elle est plus
  récente nous avons choisi JavaFX.
- #strong("GITHUB") : Pour la gestion des versions et la collaboration en équipe,
  la gestion temporelle des différentes itérations et la gestion des différentes
  features à développer.
#linebreak()

#figure(caption: "Gestion des itérations avec GitHub")[
  #image("assets/git iteration.png", height: 14%)
]
#linebreak()

- #strong("DISCORD") : Pour pouvoir communiquer ensemble sur l'avancée de chacun
  et garder une bonne cohésion d'équipe.

=== Mises au Point hebdomadaires

Nous avons mis en place des réunions hebdomadaires pour discuter des avancées,
des obstacles rencontrés et des prochaines étapes. Chaque TD de méthodes agiles
nous permettaient aussi de faire le point avec notre encadrant afin de
déterminer si nous prenions du retard ou non.

#pagebreak()




= Architecture
L'application est découpée en plusieurs morceaux :


- Le `Canvas` qui est le point central de l'application, il permet notamment
    - de contenir les objets dessinés par l'utilisateur
    - contenir les paramètres du projet actuel
    - contenir l'état de l'éditeur de dessin (presse-papier, couleurs, taille de police, ...)
    - fournir de quoi afficher les objets

- La classe `CanvasObject`
    - classe abstraite sur laquelle s'appuient les différents objets du canvas
    - permet d'avoir une interface commune pour les différentes classes filles
    - fournit des méthodes pour manipuler les objets (déplacer, supprimer, ...)
    - sous-classes : `CanvasPolygon`, `CanvasTextField`, `CanvasEllipse`,  ...

- La classe `State`
    - classe abstraite sur laquelle s'appuient les classes `AddEllipseState`, `AddRectangleState`, `SelectionState`, `TranslateState`, ...
    - sert à indiquer l'état de l'application
    - fournit la méthode d'ajout propre à chaque chaque forme (poser les points de polygones, tracer une droite, ...)
    - fournit des méthodes pour manipuler les objets (déplacer, supprimer, ...)

- Classes `Main`, `Menu`, etc
    - classes qui servent à créer les éléments graphiques de l'application
    - notamment la classe `ContextualMenu` qui fait le lien entre l'interface et les objets du canvas
    - la classe `Save` sert à sauvegarder le projet actuel
    - la classe `Export` sert à exporter au format PNG

- La classe `CanvasObjectParser`
    - classe utile à la lecture des fichiers SVG
    - permet d'instancier les objets en fonction du type de balise SVG rencontrée



= User Stories
Cette partie indique toutes les user stories, l'itération dans lesquelles elles
ont été réalisées, leur priorité ainsi que leur état d'avancement.

#figure(caption: "User stories réalisées ou non dans l'itération 1")[
  #image("assets/iter1.PNG")
]
#figure(caption: "User stories réalisées ou non dans l'itération 2")[
  #image("assets/iter2.PNG")
]
#figure(caption: "User stories réalisées ou non dans l'itération 3")[
  #image("assets/iter3.PNG")
]

#figure(caption: "User stories non traitées")[
  #image("assets/iterno.png", height: 11.7%)
]

#pagebreak()

= Déroulement
Dans cette partie, nous décrirons itération par itération les différentes User Stories implantées et les choix de conception effectués.

Chaque membre du groupe a pioché parmi les User Stories prêtes à être réalisées du backlog.

== Itération 1

La première itération avait pour objectif de
mettre en place l'architecture du projet et l'affichage simple de notre
application avec des fonctionnalités primaires telle que afficher des formes
etc... Les US suivantes sont combinées avec l'itération 0 qui a permis la mise
en place des outils nécessaires pour le développement de Pinte.

=== User Stories réalisées
#create_user_story(
  "Créer les structures de données représentant les formes dans l'application",
  "Haute",
  "2",
  "Programmeur",
  "Avoir accès à des formes",
  "Pouvoir développer les outils de création et édition de formes durant la prochaine itération",
)

#create_user_story(
  "Permettre la conversion entre le format SVG et les formes de l'application",
  "Haute",
  "3",
  "Programmeur",
  "Pouvoir convertir les formes au et depuis le format SVG",
  "Pouvoir développer la sauvegarde d'un projet utilisateur vers un fichier durant la prochaine itération",
)
#create_user_story(
  "Afficher le projet actuel",
  "Très Haute",
  "4",
  "Programmeur",
  "Pouvoir afficher mon projet",
  "Pouvoir tester les différentes features",
)
#create_user_story(
  "Ajouter une forme générique",
  "Moyenne",
  "3",
  "Utilisateur",
  "Pouvoir générer une forme générique sur mon application",
  "Pouvoir commencer à créer un design",
)

=== Code réalisé
==== Formes

Classe `CanvasObject` abstraite implantée par les classes d'objets spécifiques :
rectangle, ellipse et polygon Ainsi nous pouvons générer différentes formes et
les afficher sur un canva. D'autres formes ont été ajoutées par la suite.

==== Conversion SVG
Une classe `CanvasObjectParser` nous permet de récupérer des valeurs de clé dans
un SVG et des méthodes `toSVG` et `createFromSVG` pour passer d'une forme de
notre canva à un SVG et inversement.

=== Graphes UML


#figure(caption: "Graphe d'héritage de " + `CanvasObject`)[
  #image("assets/canvasobject_heritage.svg", width: 100%)
] <canvasobject_inheritance>

#figure(caption: "Graphe de collaboration " + `CanvasColor`)[
  #image("assets/canvas_color_collab_graph.png", width: 30%)
] <canvascolor_inheritance>


=== Rendu
#linebreak()
#figure(caption: "Rendu de la 1ère itération")[
  #image("assets/renduiter1.png", height: 40%)
]

Le bouton "click me" ici permet d'afficher une forme aléatoire.

== Itération 2
Durant cette itération, l'attention s'est portée sur la gestion des fichiers, afin de pouvoir sauvegarder et rouvrir des fichiers SVG. Mais également pouvoir poser des formes à l'endroit voulu.
Un outil de sélection a également été développé.

=== User Stories réalisées
#create_user_story(
  "Créer un nouveau projet",
  "Très Haute",
  "4",
  "Utilisateur",
  "Pouvoir créer un nouveau projet",
  "Commencer un nouveau design et pouvoir potentiellement le sauvegarder après",
)

#create_user_story(
  "Enregister un projet",
  "Moyenne",
  "3",
  "Utilisateur",
  "Pouvoir enregistrer mon design en cours",
  "Pouvoir sauvegarder mon travail et le continuer plus tard",
)

#create_user_story(
  "Sélection basique",
  "Très Haute",
  "2",
  "Utilisateur",
  "Pouvoir sélectionner une forme",
  "Pouvoir manipuler le dessin et le modifier",
)

#create_user_story(
  "Dessiner des formes basiques",
  "Très Haute",
  "3",
  "Utilisateur",
  "Pouvoir créer des formes simple, carré, rond, etc...",
  "Pouvoir commencer à créer un design de toute pièce",
)

=== Code réalisé
==== `States`
Des classes ont été créées pour gérer les différentes états de l'application, création de forme, sélection, etc.

==== Menu
Pour pouvoir choisir quellles formes nous voulons ajouter, il nous à fallu créer un
menu qui répertorie les formes et les outils disponibles.

=== Graphe UML
#grid(
  columns: 2,
  column-gutter: 2em,
  [Voici le graphe pour mettre un exemple sur la relation entre les states et le
  canva. #linebreak()

  Dans cette classe on y voit les méthodes :
  - `registerP1()`
  - `registerP2()`
  (Qui servent à enregistrer deux points de l'ellipse pour définir son centre et
  ses rayons verticaux et horizontaux.)

  Et un lien d'agrégation avec la classe abstraite CanvasObject. ],
  grid.cell(colspan: 1, image("assets/umlstates.png", width: 70%)),
)
=== Rendu
#linebreak()
#figure(caption: "Rendu de la 2ème itération")[
  #image("assets/renduiter2.png", height: 40%)
]

L'interface n'est pas parfait mais on y voit les boutons outils permettant
d'ajouter différentes formes et de pouvoir sélectionner celle que l'on souhaite.

== Itération 3
La troisième itération avait pour objectif de rendre Pinte plus interactif,
avoir une meilleure interface, et pouvoir utiliser les outils complets de ce que
peut faire un outil de dessin vectoriel (remplir une forme avec une couleur,
créer des segments, des polygones, copier coller, enregistrer un projet,
etc...). L'interface finale est restée cependant rudimentaire mais
fonctionnelle.

=== User Stories réalisées
#create_user_story(
  "Sélection un groupe d'objets",
  "Haute",
  "3",
  "Utilisateur",
  "Pouvoir sélectionner plusieurs formes à la fois",
  "Leur appliquer les opérations proposées par l'application de manière ponctuelle",
)

#create_user_story(
  "Grouper une sélection",
  "Haute",
  "3",
  "Utilisateur",
  "Pouvoir grouper des formes ensemble",
  "Leur appliquer les opérations proposées par l'application plusieurs fois sans les resélectionner",
)

#create_user_story(
  "Créer et ajouter un vecteur à partir de deux clics",
  "Hautes",
  "3",
  "Utilisateur",
  "Pouvoir dessiner des lignes",
  "Pouvoir dessiner simplement",
)

#create_user_story(
  "Copier/Couper/Coller",
  "Haute",
  "4",
  "Utilisateur",
  "Pouvoir copier et coller une ou plusieurs formes",
  "Répéter des formes",
)

#create_user_story(
  "Améliorer l'interface",
  "Haute",
  "2",
  "Utilisateur",
  "Pouvoir avoir une interface plus intuitive et jolie",
  "Simplifier l'utilisation et améliorer l'ergonomie de l'outil",
)

#create_user_story(
  "Exporter un projet dans un format d'image choisi",
  "Moyenne",
  "13",
  "Utilisateur",
  "Pouvoir exporter dans un autre format",
  "Obtenir une image dans le format voulu",
)

#create_user_story(
  "Ouvrir un projet existant",
  "Moyenne",
  "3",
  "Utilisateur",
  "Pouvoir charger un projet existant",
  "Continuer son développement",
)

#create_user_story(
  "Déplacer la sélection",
  "Haute",
  "3",
  "Utilisateur",
  "Pouvoir déplacer une ou plusieurs formes créées",
  "Corriger une erreur ou déplacer des formes pour les placer ailleurs",
)

#create_user_story(
  "Gérer l'opacité",
  "Moyenne",
  "2",
  "Utilisateur",
  "Pouvoir choisir l'opacité d'une forme",
  "Réaliser des formes plus ou moins opaques",
)

#create_user_story(
  "Créer des polygones",
  "Moyenne",
  "2",
  "Utilisateur",
  "Pouvoir créer des polygones",
  "Dessiner des formes plus complexes",
)

#create_user_story(
  "Importer et convertir en SVG depuis un autre format d'image",
  "Faible",
  "20",
  "Utilisateur",
  "Pouvoir importer un autre format que SVG",
  "Le modifier sous forme SVG",
)

#create_user_story(
  "Remplir une formes avec une couleur",
  "Basse",
  "3",
  "Utilisateur",
  "Pouvoir gérer la couleur d'une forme",
  "Modifier la couleur du contour et de remplissage",
)

#create_user_story(
  "Ajouter à la sélection actuelle à l'aide du bouton ctrl",
  "Basse",
  "5",
  "Utilisateur",
  "Pouvoir ajouter une forme à la sélection",
  "Grouper ou appliquer des modifications",
)

=== Code réalisé

==== Menu contextuel

Ajout d'un menu contextuel `CanvasContextualMenu` lors du clic
droit pour pouvoir appliquer des opérations sur la sélection actuelle.

==== Coloration

Choisir la couleur à utiliser pour créer la nouvelle forme, remplir ou changer
le contour d'une forme.

==== Sauvegarde

Enregistrer le projet pour pouvoir le continuer ultérieurement et pouvoir
l'exporter dans un format d'image souhaité.

=== Graphe UML
#grid(
  columns: 2,
  column-gutter: 2em,
  [Voici le diagramme de classe de `CanvasContextualMenu` : #linebreak()

  La méthode #strong("shapeContextualMenu()") permet d'obtenir les actions à
  performer sur le canvas selon les touches de souris et clavier appuyées. ],
  grid.cell(colspan: 1, image("assets/contectualmenu.PNG", width: 70%)),
)
=== Rendu
#linebreak()
#figure(caption: "Rendu de la 3e itération")[
  #image("assets/image.png", height: 40%)
]
#pagebreak()

= Application
L'application peut être lancée avec la commande `./gradlew run` depuis la racine
du projet.

En l'état, l'application permet de choisir un emplacement, un nom et une taille
de document. Elle ouvre ensuite une nouvelle vue Canvas. Un bouton permet d'y
faire apparaître des cercles à l'écran en démonstration.

= Tests

Les tests unitaires peuvent être lancés avec la commande `./gradlew test` depuis
la racine du projet.
