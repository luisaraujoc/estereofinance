# estrutura de pastas

```
app/
└── src/
    └── main/
        ├── java/com/coutinho/estereofinance/
        │   ├── data/
        │   │   ├── local/
        │   │   │   ├── dao/
        │   │   │   │   ├── UserDao.kt
        │   │   │   │   ├── CategoryDao.kt
        │   │   │   │   └── MovimentDao.kt
        │   │   │   └── db/
        │   │   │       └── AppDatabase.kt
        │   │   └── model/
        │   │       ├── User.kt
        │   │       ├── Category.kt
        │   │       └── Moviment.kt
        │   ├── repository/
        │   │   ├── UserRepository.kt
        │   │   ├── CategoryRepository.kt
        │   │   └── MovimentRepository.kt
        │   ├── ui/
        │   │   ├── theme/                # Cores, tipografia etc.
        │   │   ├── screens/
        │   │   │   ├── auth/             # Login, cadastro
        │   │   │   │   ├── LoginScreen.kt
        │   │   │   │   └── SignUpScreen.kt
        │   │   │   ├── home/             # Dashboard principal
        │   │   │   │   ├── HomeScreen.kt
        │   │   │   │   └── components/
        │   │   │   ├── moviment/
        │   │   │   │   ├── AddMovimentSheet.kt
        │   │   │   │   ├── MovimentList.kt
        │   │   │   ├── category/
        │   │   │   │   ├── AddCategorySheet.kt
        │   │   │   │   └── CategoryList.kt
        │   ├── viewmodel/
        │   │   ├── UserViewModel.kt
        │   │   ├── CategoryViewModel.kt
        │   │   └── MovimentViewModel.kt
        │   └── navigation/
        │       └── AppNavHost.kt
        └── res/
            ├── drawable/
            ├── values/ 
            │   ├── colors.xml
            │   └── strings.xml
            └── ...
```