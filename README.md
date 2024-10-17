# Bootcamp Project - Spring Boot 3.3.4

## Prerequisites

Ensure the following are installed on your system:

- **Java**: OpenJDK 17 or later
- **Maven**: 3.9.8 or later
- **MySQL**: Running instance of MySQL database
- **IntelliJ IDEA** (optional for development)

## Environment Configuration

This application uses environment variables for configuration. Set the following variables before running the application:


### Required Environment Variables

- `DB_HOST`: MySQL host (e.g., `localhost`)
- `DB_PORT`: MySQL port (default is `3306`)
- `DB_NAME`: Name of the MySQL database
- `DB_USER`: MySQL username
- `DB_PASSWORD`: MySQL password
- `MODE`: DEV / PROD

These variables can be added to a `.env` file or set as environment variables on your OS.

## Running the Application

### 1. Using Windows PowerShell

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="your_db_name"
$env:DB_USER="root"
$env:DB_PASSWORD="yourpassword"
$env:MODE="dev"
mvn spring-boot:run
```
### 2. Using Linux / MacOS

```bash
export DB_HOST="localhost"
export DB_PORT="3306"
export DB_NAME="your_db_name"
export DB_USER="root"
export DB_PASSWORD="yourpassword"
export MODE="dev"
mvn spring-boot:run
```

### 3. Using IntelliJ IDEA

To run the application via IntelliJ IDEA:

1. Open the project in **IntelliJ IDEA**.
2. Ensure the project SDK is set to **JDK 17**:
   - Go to **File > Project Structure > Project Settings > Project**.
   - Set **Project SDK** to **JDK 17**.
3. Right-click the main class (`BootcampProjectApplication.java`) and select **Run 'BootcampProjectApplication'**.
4. To add environment variables in IntelliJ:
   - Go to **Run > Edit Configurations**.
   - Add the environment variables (`DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`, `MODE`, etc.) under the **Environment Variables** section.

By following these steps, you can successfully run the Spring Boot project in IntelliJ IDEA with the necessary environment variables.
