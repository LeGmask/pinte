#import "../../template.typ": *

#show: project.with(
  subject: "Projet Long de Technologie Objet",
  title: "Rapport Individuel - Iteration 2",
  authors: ("THEVENET Louis",),
  date: "13 Mai 2024",
  toc: false,
)

Durant la seconde itération du projet long, j'ai réalisé les User Stories _Sélection basique_ et _Menu Contextuel_ :

#create_user_story(
  "Sélection basique",
  "Moyenne",
  "3",
  "Utilisateur",
  "Pouvoir sélectionner les formes existantes et avoir un retour visuel sur la sélection",
  "Pouvoir appliquer des effets et opérations sur les formes sélectionnées",
)

#create_user_story(
  "Menu contextuel",
  "Haute",
  "3",
  "Utilisateur",
  "Avoir accès à un menu contextuel lorsque l'utilisateur clique sur le canvas",
  "Avoir accès à des opérations en fonction du contexte actuel (clic sur une forme, sur le canvas, ...)",
)
