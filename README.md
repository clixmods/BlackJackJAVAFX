# BlackJack JavaFX

Jeu de BlackJack sur ordinateur, développé en Java avec JavaFX, avec gestion de comptes joueurs et d'un solde enregistrés en base de données.

Projet réalisé en équipe dans le cadre de la **SAÉ 3.01** du BUT Informatique (2ᵉ année), IUT de Montpellier-Sète.

## Fonctionnalités

- **Comptes joueurs** : inscription (vérification de l'âge, de la robustesse du mot de passe et de l'unicité du login et du mail), connexion et déconnexion.
- **Mises** : sélection de la mise à l'aide de jetons (1, 20, 50, 100, 500) selon le solde disponible, et ajout d'argent sur le compte.
- **Partie** :
  - tirer, rester ou doubler ;
  - l'As compte 1 ou 11 ;
  - le croupier tire jusqu'à 17 ;
  - le blackjack est payé 3 pour 2 ;
  - la carte cachée du croupier n'est révélée qu'à son tour.
- **Interface** : français et anglais (changement à chaud), musique d'ambiance et effets sonores avec réglage du volume, pages Règles et CGU.

## Stack technique

| Domaine | Outils |
|---|---|
| Langage | Java 17 |
| Interface | JavaFX 20 (FXML) |
| Build | Maven (wrapper inclus) |
| Base de données | MariaDB via JDBC |
| Tests | JUnit 5 |

## Architecture

```
src/main/java/com/example/blackjackjavafx/
├── Metier/         Modèle du jeu : Carte, Pioche, Participant, Joueur, Jeu, Miser, Client
├── Repository/     Accès aux données : Repository<T> générique (CRUD en requêtes préparées), RepositoryClient
├── Application/
│   ├── controller/ Contrôleurs JavaFX (un par vue)
│   ├── Service/    Couche service (ClientService)
│   ├── connection/ Gestion de la session du joueur connecté
│   ├── Langage/    Internationalisation FR / EN
│   ├── lib/        Hachage (HMAC-SHA256 + pepper) et validation des mots de passe
│   └── sound/, music/
└── Vue/            SceneHandler (navigation entre les vues) et VueGenerale (header + contenu)
```

## Installation

**Prérequis** : JDK 17 ou plus récent, et une base MariaDB contenant une table `s_clients`.

Structure de la table `s_clients` :

| Colonne | Type |
|---|---|
| `login` | clé primaire |
| `mail` | |
| `dateNaissance` | `DATE` |
| `nom` | |
| `prenom` | |
| `argent` | `INT` |
| `password` | |

1. Copier le fichier de configuration et renseigner les identifiants :
   ```bash
   cp src/main/resources/config.example.properties src/main/resources/config.properties
   ```
2. Lancer l'application :
   ```bash
   ./mvnw javafx:run
   ```

Pour lancer les tests :

```bash
./mvnw test
```

Les tests des services et de l'inscription nécessitent une base de données accessible.

## Équipe

- Clément Garcia
- Yanis Bendahmane
- Elliot Barthelemy
- Quentin Laborie
- Mateo Dias
