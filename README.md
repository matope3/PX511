## BDD TESTS SQL Injection

Pour run les tests :
`.\gradlew connectedCheck -Pcucumber`

On a une application de login simple avec une base de donnée qui contient 3 utilisateurs :
- admin@admin.com ; mdp = admin
- user@user.com ; mdp = user
- test@test.com ; mdp = test

On test normalement la page de login puis on essaye une injection SQL :
- test@gmail.com' or 1=1 -- - ; mdp = shioghdiogher (random)

Dans le cas ou ce test valide le login alors il y a injection SQL et le test fail

Pour contrer l'injection SQL on peut faire en sorte d'utiliser des requêtes préparées (données et commandes séparées) :
- `MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password})`

Dans le cas contraire on est exposé aux injections SQL :
- `MyDB.rawQuery("Select * from users where username = '" + username + "'", null)`