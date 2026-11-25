# Student Project (JDBC)

A console app for managing a small university database (students, addresses and
departments) with plain JDBC and MySQL, plus a set of JDBC example programs.
Built as a group project during the Cognixia JUMP program.

## Layout

- `JDBC/` – the current project (Maven build)
  - `src/.../connection` – connection managers and `DatabaseConfig`
  - `src/.../dao` – `Department` DAO
  - `src/.../project` – `Student` and `Address` DAOs and the `MainMenu` console app
  - `src/.../examples` – standalone JDBC examples (Statement, PreparedStatement, ResultSet, metadata)
  - `test/` – JUnit 5 tests (DAO tests use an in-memory H2 database, no MySQL needed)
- `src/` – an older copy of the code kept for history

## Tech

Java 17, JDBC, MySQL (mysql-connector-j), Maven, JUnit 5, H2 (tests only), GitHub Actions.

## Database settings

No credentials are stored in the code. Each value is read from, in order:

1. Environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
2. `JDBC/resources/config.properties` (git-ignored; copy `config.properties.example`)
3. Defaults: `jdbc:mysql://localhost:3306/university`, user `root`, empty password

## Build and test

```bash
cd JDBC
mvn -B verify
```

## Run

You need a MySQL `university` database with `student`, `address` and `department` tables.

```bash
cd JDBC
export DB_USERNAME=root DB_PASSWORD=yourpassword
mvn -q compile dependency:copy-dependencies -DincludeScope=runtime
java -cp target/classes:target/dependency/* com.cognixia.jump.jdbc.project.MainMenu
```

Run from the `JDBC` folder so `resources/config.properties` is found.
