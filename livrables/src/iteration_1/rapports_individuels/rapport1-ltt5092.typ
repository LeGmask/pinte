#import "../../template.typ": *

#show: project.with(
  subject: "Projet Long de Technologie Objet",
  title: "Rapport Individuel - Iteration 1",
  authors: ("THEVENET Louis",),
  date: "26 Avril 2024",
  toc: false,
)

Durant la première itération du projet long, j'ai réalisé les User Stories _Formes_ et _Conversion SVG_ :

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

Structures de données ajoutées pour _Formes_ :
- Classe Couleur pour les formes
- Classe Forme abstraite sur laquelle les formes s'appuient
- Classe Ellipse
- Classe Rectangle
- Classe Polygon

Classes ajoutées et modifiées pour _Conversion SVG_ :
- Classe Parseur qui permet de récupérer la valeur associée à un mot-clé dans une
  chaîne SVG
- Méthodes `toSVG` et `createFromSVG` ajoutées aux formes

Des tests ont également été ajoutés pour toutes ces classes.