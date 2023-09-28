# MNS-Spring-Collocation

API avec Java/Spring de gestion d'un systeme de collocation pour etudiant en erasmus.

Le projet à pour but de permettre au utilisateur de créer une collocation avec des chambres vide et les autres utilisateurs peuvent prendre une place dans la collocation. 

# Première installation :

- cloner le projet avec Intellij
- switcher sur votre branche
- Faite un `git pull origin main` pour synchro votre branche avec la version stable la plus récente 
- Installer les dépendances Maven

# Base de données 
- Lancer une instance Wamp
- Créer une base de données avec pour nom "collocation" dans phpmyadmin
- renseigner les paramètres dans le fichier si besoin `\src\main\resources\application.properties`
- Pour ouvrir la base de données dans Intellij : Onglet base de données et ajouter localhost:3306, tester la connexion et chercher la databases collocation

# Lancer l'application
- Faite un `git pull origin main` pour avoir les dernières mises à jour si cela fait longtemps que vous n'avez pas travailler sur le projet
- Avec la petite fleche verte :)

# Swagger
- Pour accéder au swagger vous pouvez passer par la route /api-docs
- Pour obtenir le yaml vous pouvez passer par la route /api-docs.yaml et il se trouve aussi à la racine du projet 