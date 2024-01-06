## BDD TESTS Brute Force

Pour run les tests :
`.\gradlew connectedCheck -Pcucumber`

On a une application de login simple avec une base de donnée qui contient 3 utilisateurs :
- admin@admin.com ; mdp = admin
- user@user.com ; mdp = user
- test@test.com ; mdp = test

Avec ce test on spécifie un nombre de tentatives de login faux.
Et on cherche à ne plus pouvoir cliquer sur le bouton de login après ces tentatives.