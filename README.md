# Java Todo List

A small **JavaFX** desktop application for managing your daily tasks, backed by a
**MySQL** database. Sign up, log in, and keep track of the things you need to do.

## Features

- 👤 User sign up and login (passwords are hashed before they are stored)
- ✅ Create tasks with a name and an optional description
- 📋 View all of your tasks, newest first
- 🗄️ Data persisted in MySQL, schema created automatically on first run

## Tech stack

- Java 21+
- JavaFX 25 (UI / FXML)
- MySQL 8 (via Docker)
- Maven (build & run, with a bundled wrapper — no local Maven install needed)

## Project layout

```
src/main/java
├── app/Main.java            # Application entry point
├── controller/              # JavaFX controllers (login, sign up, tasks)
├── service/                 # Business logic (user & task services)
├── database/                # Connection handling and SQL
├── model/                   # User & Task data classes
├── session/                 # Holds the logged-in user
└── animation/               # Small UI animation helpers
src/main/resources
├── view/                    # FXML screens
└── assets/                  # Images
```

## Prerequisites

- **JDK 21 or newer** (`java -version`)
- **Docker** (to run MySQL) — or your own MySQL 8 instance

## Getting started

### 1. Start the database

```bash
docker compose up -d
```

This starts MySQL 8 on port **4300** with database `todolist`
(user `root`, password `root`). The application creates the tables for you on
its first launch.

### 2. Run the application

Use the Maven wrapper — it downloads everything it needs (including the correct
JavaFX libraries for your OS) automatically:

```bash
./mvnw javafx:run          # macOS / Linux
mvnw.cmd javafx:run        # Windows
```

That's it — the login window opens. Click **Sign Up** to create an account,
then log in and start adding tasks.

### 3. Stop the database

```bash
docker compose down        # keep your data
docker compose down -v     # also delete stored data
```

## Configuration

Database settings live in
[`src/main/java/database/Config.java`](src/main/java/database/Config.java).
If you use a different host, port, or credentials, update them there:

| Setting | Default     |
|---------|-------------|
| Host    | `127.0.0.1` |
| Port    | `4300`      |
| User    | `root`      |
| Password| `root`      |
| Database| `todolist`  |

## Useful commands

```bash
./mvnw clean compile       # compile the project
./mvnw javafx:run          # run the app
docker compose logs -f     # follow the database logs
```

## Contributing

Contributions are welcome! Feel free to open an issue or a pull request.
