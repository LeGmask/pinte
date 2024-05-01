#import "../../template.typ": *

#show: project.with(
  subject: "Projet long de Technologie Objet ",
  title: "Rapport général",
  subtitle: "Itération 1 - EF02",
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
  date: "27 Avril 2024",
  toc: false,
)

= Itération 1

== Formes
On appelle Forme les objets que l'utilisateur peut ajouter à son projet dans
Pinte (rectangle, cercle, etc...)

=== User Stories réalisées
#create_user_story(
  "Créer les structures de données représentant les formes dans l'application",
  "Haute",
  "3",
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

==== Formes
Classes ajoutées :
- Classe `CanvasColor` (voir @canvascolor_collab)
- Classe `CanvasObject` abstraite sur laquelle les `CanvasObject` s'appuient (voir
  graphe d'héritage @canvasobject_inheritance)
- Classe `CanvasObjectEllipse`
- Classe `CanvasObjectRectangle`
- Classe `CanvasObjectPolygon`

==== Conversion SVG
Classes et méthodes ajoutées :
- Classe `CanvasObjectParser` qui permet de récupérer la valeur associée à un
  mot-clé dans une chaîne SVG
- Méthodes `toSVG` et `createFromSVG` ajoutées aux formes

#figure(caption: "Graphe de collaboration de " + `CanvasColor`)[
  #image("assets/canvas_color_collab_graph.png", height: 45%)
] <canvascolor_collab>

#figure(caption: "Graphe d'héritage de " + `CanvasObject`)[
  #image("assets/canvas_object_inheritance_graph.png")
] <canvasobject_inheritance>

Les méthodes `render` permettent d'obtenir un objet `Shape` affichable par
`javafx`.

==== Tests
Classes de tests ajoutées, ces tests correpondent aux classes des formes pour
tester leur comportement nominaux.

- La classe `SVGTest` a été ajouté mais ne pourra être réllement testée que lors
  des premiers render de l'application.

== Interface