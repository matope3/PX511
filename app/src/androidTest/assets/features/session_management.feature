Feature: Gestion de Session
  En tant que testeur de sécurité
  Je veux m'assurer que la gestion de session est sécurisée
  Pour éviter les vulnérabilités liées aux sessions utilisateur

  @e2e
  @smoke
  Scenario Outline: Vérifier la sécurité de la gestion de session
    Given que je suis connecté avec mon <email> et <password>
    When je me connecte à mon compte
    Then une nouvelle session utilisateur devrait être créée
    And après la déconnexion, la session devrait être invalidée
    Examples:
      | email           | password |
      | admin@admin.com | admin123 |
      | test@test.com   | test123  |
      | test1@test.com  | test123  |


