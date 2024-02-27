#import "../template.typ": *

#show: project.with(
  subject: "Projet Long de Technologie Objet",
  title: "Fonctionnalités de l'application : Dessin Vectoriel",
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
  date: "24 Février 2024",
  subtitle: "Groupe EF02",
  toc: false,
)
#let pinte = $arrow("Pinte")$ //+ " " + emoji.beer

= Objectif général
#pinte est un éditeur de dessin vectoriel developpé en suivant le paradigme des
méthodes agiles. L'outil permettra de créer, modifier et exporter des dessins
vectoriels.

= Fonctionnalités
- Enregistrer, exporter et importer en différents formats (avec et sans perte
  d'information)
  - Enregistrer son travail au format SVG, et pouvoir y retourner ensuite
  - Exporter en SVG, JPEG, PNG, PPM ...
  - Importer un dessin non vectoriel selon différents paramètres

- Affichage du travail en cours
  - Affichage des formes créées et de celle éventuellement en cours de création
  - Interface configurable
    - Emplacements des fenêtres d'options modifiables
    - Choix du thème
    - Modification des raccourcis clavier
  - Zoom, déplacement de la fenêtre de travail
  - Axes abcisse et ordonnée avec repères

- Calques
  - Rendre le contenu d'un calque visible/invisible
  - Changer l'ordre des calques
  - Déplacer un objet d'un calque vers un autre

- Création d'objets
  - Couleur, transparence
  - Formes prédéfinies
  - Interpolation de courbes à partir de points
  - Boîte de texte
    - Choix de la police
  - Pinceaux configurables
  - Remplir zones de couleurs (intersection d'objets)

- Outil de sélection poussé qui permet différentes oppérations
  - Déplacer
  - Agrandir, Réduire
  - Rotation
  - Symétrique
  - Étirer
  - Dupliquer
  - Opacité

#pagebreak()
= Interface utilisateur

#figure(
  caption: "Vue principale, édition de document",
  image("assets/screenshot_inkscape.png"),
)

#grid(columns: 2, column-gutter: 2em, [#figure(
    caption: "Menu de gestion des calques",
    image("assets/screenshot_inkscape_layers.png"),
  )
], [
  #figure(
    caption: "Options liées à la sélection d'un objet",
    image("assets/screenshot_inkscape_selection_menu.png"),
  )
])
#pagebreak()
= Cas d'usage
== Création d'un logo
Cette application peut servir à créer un logo, en effet l'exportation au format
SVG permettra de l'agrandir sans perte de qualité. L'utilisateur créera un
nouveau document, y réalisera son logo en utilisant les outils de formes et de
sélection. Puis enregistrera son travail au format désiré.

- L'utilisateur crée une image vide, en choisissant ses dimensions.
- Dans un calque, il ajoute un cercle orange, qu'il remplit en orange.
- Il ajoute un calque par-dessus dans lequel il forme un rectangle jaune, deux
  rectangles blancs plus fin par-dessus et un arc de cercle épais sur le côté
  (pour dessiner une pinte) et plusieurs disques blancs qui se chevauchent sur le
  dessus.
- Dans une boîte de texte, il choisit un police et écrit le logo "PINTE".
- Il l'exporte au format JPG "pinte.jpg".

== Retoucher une image
Un utilisateur peut ouvrir son image à l'aide de cette application, si l'image
n'est pas un dessin vectoriel, il devra choisir comment l'application doit
interprêter son document (recréer des formes par exemple). Il pourra ensuite
réaliser les modifications souhaitées et enregistrer son travail.

== Convertir une image au format désiré
Un utilisateur peut, après avoir importé son image (SVG, PNG, JPEG), la
convertir en un autre format parmis ceux disponibles (SVG, PNG, JPEG).
- L'utilisateur importe une image qui est d'un format PNG qui construite à partir
  de formes simples (vectorisables) : l'image est vectorisée.
- Il l'exporte ensuite au format JPG.

= Points difficiles
== Importer depuis un format non vectoriel
Interprêter une image pour en créer un dessin vectoriel semble compliqué. Cela
demanderait de détecter des formes dans l'image. Pour ce faire, nous avons pensé
à utiliser des algorithmes vues en TP de statistiques.

== Affichage dynamique
L'affichage du travail constituera également une partie importante du projet. Il
s'agira de calculer les éléments présents dans la fenêtre visible en prenant
compte du zoom et de l'emplacement de cette dernière. D'autre part,
l'utilisateur devra pouvoir voir la forme qu'il est en train de dessiner avant
même qu'il l'ait finie : cela demandera une réfléxion pointue sur l'acquisition
et l'enregistrement des formes par notre programme.