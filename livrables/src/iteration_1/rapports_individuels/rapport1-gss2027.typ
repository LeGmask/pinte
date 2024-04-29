#import "../../template.typ": *

#show: project.with(
  subject: "Projet Long de Technologie Objet",
  title: "Rapport Individuel - Iteration 1",
  authors: ("SABLAYROLLES Guillaume",),
  date: "28 Avril 2024",
  toc: false,
)

Durant la première itération du projet long, j'ai réalisé des Tests unitaires pour _Formes.Rectangle_  :

Structures des tests ajoutés pour _Formes_ :
- Initialisation des données de test et fonction de vérification des attributs
- Classe de test des constructeurs
- Classe de test pour la conversion SVG
- Classe de test pour le render du futur canvas
Des tests pour les cas non nominaux pourront être rajoutés par la suite.