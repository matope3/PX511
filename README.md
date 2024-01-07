## BDD TESTS Brute Force

Pour run les tests :
`.\gradlew connectedCheck -Pcucumber`

On a une application de login simple avec une base de donnée qui contient 3 utilisateurs :
- admin@admin.com ; mdp = admin
- user@user.com ; mdp = user
- test@test.com ; mdp = test

Avec ce test on spécifie un nombre de tentatives de connexion (qui échoueront toutes)
Et on cherche à ne plus pouvoir cliquer sur le bouton de login après un certain nombre de tentatives.

Avec cette application qui protège du bruteforce, on dispose de 3 tentatives de connexion avant de bloquer le bouton de login.
Même en réouvrant l'application après le bouton de login bloqué, il faudra attendre 5 minutes avant de pouvoir réessayer de se connecter.