# Civilian Registry Client

An interactive command-line client for the Civilian Registry REST API. It lets users manage civilian records from a terminal through a simple numbered menu.

## Features

- Add a civilian
- View all civilians
- Search using one or more fields
- Update a civilian's address and tax identification number
- Delete a civilian by ID
- Validate basic input formats before sending requests
- Serialize and deserialize JSON with Jackson

## Technologies

- Java 17
- Maven
- Java HTTP Client
- Jackson Databind 2.22.2

## Project structure

```text
src/main/java/gr/gnoome/
├── Menu.java          # Interactive command-line interface
├── HTTP_Handler.java  # HTTP requests and JSON handling
└── Person.java        # Civilian data model
```

## Prerequisites

- JDK 17 or newer
- Maven 3.8+
- The companion Civilian Registry REST API running locally

By default, the client connects to:

```text
http://localhost:8080/Civilian_REST/api/Civilians
```

Start the REST API before running the client. If the server address or deployed context changes, update the `URL` value in `HTTP_Handler.java`.

## Build

Download the dependencies and compile the project:

```bash
mvn clean compile dependency:copy-dependencies
```

## Run

### From an IDE

Import the project as a Maven project and run the `gr.gnoome.Menu` main class.

### From PowerShell

After building the project, run:

```powershell
java -cp "target/classes;target/dependency/*" gr.gnoome.Menu
```

### From macOS or Linux

After building the project, run:

```bash
java -cp "target/classes:target/dependency/*" gr.gnoome.Menu
```

## Menu

```text
1. Add a new civilian
2. View all civilians
3. Update a civilian
4. Delete a civilian
5. Search for a civilian
6. Exit
```

The menu is displayed again after each operation.

## Civilian fields

| Field | Input rule |
|---|---|
| ID | Exactly 8 characters |
| Name | Required |
| Surname | Required |
| Gender | `M` or `F` |
| Birthdate | `dd-mm-yyyy` |
| Address | Optional |
| Tax number | Optional; exactly 9 digits when supplied |

## Available operations

### Add

Collects all required civilian details, converts them to JSON, and sends a `POST` request to the API.

### View all

Sends a `GET` request and prints every returned civilian record.

### Search

Prompts for any combination of ID, name, surname, gender, birthdate, address, and tax number. Supplied fields are URL-encoded and sent as query parameters to the API's `/search` endpoint.

### Update

Updates the address, tax number, or both for the specified civilian ID using a `PATCH` request.

### Delete

Deletes the civilian with the specified ID using a `DELETE` request.

## Troubleshooting

- **Connection refused:** confirm that the REST API is running on port `8080` and deployed under the `Civilian_REST` context.
- **Unexpected JSON error:** the client expects the view and search endpoints to return a JSON array.
- **Database unavailable:** verify that MySQL is running and that the REST API's database settings are correct.
- **Different server URL:** change the `URL` constant in `HTTP_Handler.java` to match the deployed API.

## Related project

This client is designed to work with the companion `Civilian_registry_rest` project.

## License

No license has been specified for this project.
