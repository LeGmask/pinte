#import "../../template.typ": *

#show: project.with(
  subject: "Projet long de Technologie Objet ",
  title: "Manuel Utilisateur",
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

= Application
L'application peut être lancée avec la commande `./gradlew run` depuis la racine
du projet.

En l'état, l'application permet de choisir un emplacement, un nom et une taille
de document. Elle ouvre ensuite une nouvelle vue Canvas. Un bouton permet d'y
faire apparaître des cercles à l'écran en démonstration.

= Tests

Les tests unitaires peuvent être lancés avec la commande `./gradlew test` depuis
la racine du projet.