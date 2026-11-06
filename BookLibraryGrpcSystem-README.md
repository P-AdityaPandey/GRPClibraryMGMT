# BookLibraryGrpcSystem

A learning-oriented microservices demo that shows how a React frontend
can call a Spring Boot REST gateway, which then calls a separate
BookService over gRPC. It also includes an independent Spring Batch
module for importing book records from CSV into an H2 database.

## Contents

-   [Overview](#overview)
-   [Architecture and request flow](#architecture-and-request-flow)
-   [Technology stack](#technology-stack)
-   [Prerequisites](#prerequisites)
-   [Project structure](#project-structure)
-   [Ports and URLs](#ports-and-urls)
-   [Run the complete application](#run-the-complete-application)
-   [Frontend features](#frontend-features)
-   [REST API reference](#rest-api-reference)
-   [Run the Spring Batch import](#run-the-spring-batch-import)
-   [Build and test](#build-and-test)
-   [Configuration](#configuration)
-   [Troubleshooting](#troubleshooting)
-   [Current demo limitations](#current-demo-limitations)

## Overview

The project contains four independently runnable modules:

1.  **BookService** --- a Spring Boot application that starts a gRPC
    server and serves sample book records.
2.  **LibraryService** --- a Spring Boot REST API and gRPC client. It
    exposes HTTP endpoints for the React app and calls BookService over
    gRPC.
3.  **BatchService** --- a Spring Batch application that reads
    `books.csv` and writes records to its own H2 in-memory database.
4.  **react-ui** --- a component-based React + Vite frontend for listing
    books, looking up a book by ID, and checking availability.

The main user-facing request flow is:

``` text
Browser
  |
  | HTTP / JSON
  v
React UI :5173
  |
  | /api/... (Vite development proxy)
  v
LibraryService :8080
  |
  | gRPC
  v
BookService gRPC :9090
```

BookService also runs a Spring Boot HTTP server on port `8081`, but the
main frontend flow uses the gRPC listener on port `9090` through
LibraryService. BatchService is separate and is not required to use the
main UI.

## Architecture and request flow

### Browse all books

1.  The React UI requests `GET /api/library/books`.
2.  Vite forwards `/api/...` requests to `http://localhost:8080` during
    development.
3.  LibraryService calls BookService's `ListBooks` gRPC method.
4.  LibraryService returns the book list as JSON to the browser.

### Get one book or check availability

The React UI requests one of these routes:

-   `GET /api/library/books/{id}`
-   `GET /api/library/books/{id}/availability`

LibraryService validates that the ID is positive, calls BookService over
gRPC, and returns the result.

### Import CSV records

BatchService runs a separate Spring Batch job using
`BatchService/src/main/resources/books.csv`. It writes records to the
`book_record` table in its own H2 in-memory database. This batch
database is not automatically shared with BookService's in-memory sample
data.

## Technology stack

-   Java (the backend POM files currently target Java 17; BatchService
    targets Java 21)
-   Spring Boot 3.5.6
-   Spring Web / REST
-   gRPC and Protocol Buffers
-   Spring Batch
-   Spring Data JPA
-   H2 in-memory database for BatchService
-   React 18
-   Vite 5
-   Maven

> **Java version note:** BookService and LibraryService set Java 17 in
> their Maven configuration. BatchService sets Java 21. Install Java 21
> to run all modules together, or align the Maven compiler settings if
> you want to use a single Java version consistently.

## Prerequisites

Install the following:

-   JDK 21 (recommended for running all modules as currently configured)
-   Apache Maven 3.9 or later
-   Node.js 18 or later, with npm
-   Git (optional, for cloning and version control)

Verify the tools in PowerShell:

``` powershell
java -version
mvn -version
node --version
npm --version
```

## Project structure

``` text
BookLibraryGrpcSystem/
├── BookService/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/example/bookservice/
│       ├── main/proto/book.proto
│       └── main/resources/application.properties
├── LibraryService/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/example/libraryservice/
│       ├── main/proto/book.proto
│       └── main/resources/application.properties
├── BatchService/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/example/batchservice/
│       └── main/resources/books.csv
├── react-ui/
│   ├── package.json
│   ├── vite.config.js
│   └── src/
│       ├── components/
│       ├── services/
│       ├── App.jsx
│       ├── App.css
│       └── styles.css
├── HighLevelDesign.md
├── LowLevelDesign.md
├── batchsetup.md
├── testing.md
└── README.md
```

## Ports and URLs

  ----------------------------------------------------------------------------------------------
  Module / service                              Port URL / purpose
  --------------------- ---------------------------- -------------------------------------------
  React UI                                    `5173` `http://localhost:5173`

  LibraryService REST                         `8080` `http://localhost:8080/api/library/books`
  API                                                

  BookService HTTP                            `8081` Spring Boot HTTP port; not the gRPC port
  server                                             

  BookService gRPC                            `9090` Used by LibraryService's gRPC client
  server                                             

  BatchService REST API                       `8082` `http://localhost:8082/api/batch/run`

  BatchService H2                             `8082` `http://localhost:8082/h2-console`
  console                                            
  ----------------------------------------------------------------------------------------------

## Run the complete application

Run each service in a separate terminal. Keep the terminals open while
testing.

### 1. Start BookService

From the repository root:

``` powershell
cd BookService
mvn spring-boot:run
```

Wait for a log message similar to:

``` text
Book Service gRPC server started on port 9090
```

BookService starts its HTTP server on `8081` and its gRPC server on
`9090`. LibraryService needs the gRPC listener, so ensure port `9090` is
available.

### 2. Start LibraryService

Open a second terminal at the repository root:

``` powershell
cd LibraryService
mvn spring-boot:run
```

LibraryService runs on port `8080` and is configured to connect to
`localhost:9090`.

### 3. Start BatchService (optional)

Open a third terminal at the repository root:

``` powershell
cd BatchService
mvn spring-boot:run
```

BatchService runs on port `8082`. It is independent of the main UI and
can be skipped if you only want to browse books.

### 4. Start React UI

Open a fourth terminal at the repository root:

``` powershell
cd react-ui
npm install
npm run dev
```

Open the Vite URL:

**http://localhost:5173**

If Vite reports that port `5173` is already in use, stop the other
frontend process or check the port configuration.

### Recommended startup order

1.  BookService
2.  LibraryService
3.  BatchService (only if testing batch import)
4.  React UI

The frontend may start even when the backend is down, but API actions
will fail until LibraryService and BookService are both available.

## Frontend features

The React UI is split into reusable JSX components and CSS files. It
includes:

-   Responsive dashboard layout
-   List all books
-   Get a book by ID
-   Check availability by book ID
-   Book collection table
-   Selected API response panel, including raw JSON
-   Loading, success, empty, and error states
-   API request helper in `src/services/bookApi.js`
-   Vite proxy for forwarding `/api` requests to LibraryService on port
    `8080` (when using the supplied frontend package)

The frontend does not call BookService directly. It calls
LibraryService, which communicates with BookService over gRPC.

## REST API reference

Base URL: `http://localhost:8080`

### List all books

``` http
GET /api/library/books
```

PowerShell:

``` powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/library/books"
```

Expected response: a JSON array of book records.

### Get a book by ID

``` http
GET /api/library/books/1
```

PowerShell:

``` powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/library/books/1"
```

### Check book availability

``` http
GET /api/library/books/1/availability
```

PowerShell:

``` powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/library/books/1/availability"
```

### Invalid or unknown IDs

Book IDs must be positive integers. An unknown ID should return a
not-found response; an invalid ID should return an error response.

### Sample books

BookService currently initializes sample data in memory:

    ID Title                          Author                        Available
  ---- ------------------------------ ----------------------------- -----------
     1 Clean Code                     Robert C. Martin              Yes
     2 Effective Java                 Joshua Bloch                  Yes
     3 Head First Java                Kathy Sierra and Bert Bates   No
     4 Spring in Action               Craig Walls                   Yes
     5 Java: The Complete Reference   Herbert Schildt               Yes

This data is recreated when BookService restarts; it is not persisted to
a relational database in the current demo.

## Run the Spring Batch import

BatchService reads the sample file at:

``` text
BatchService/src/main/resources/books.csv
```

It stores imported records in its own H2 in-memory database.

### Trigger the job

Start BatchService first, then run this command in PowerShell:

``` powershell
Invoke-RestMethod `
  -Method Post `
  -Uri "http://localhost:8082/api/batch/run"
```

The endpoint returns job information such as the job name, status, and
exit code. The exact status depends on the job execution.

You can also use Insomnia or Postman:

-   Method: `POST`
-   URL: `http://localhost:8082/api/batch/run`
-   Body: none

### Open the H2 console

Visit:

**http://localhost:8082/h2-console**

Use the following connection settings:

  Setting     Value
  ----------- -----------------------
  JDBC URL    `jdbc:h2:mem:batchdb`
  User Name   `sa`
  Password    Leave blank

The H2 database is in memory. Its contents are lost when BatchService
stops. The current batch import is a separate demonstration and does not
automatically update BookService's in-memory records.

## Build and test

Run backend checks from each service directory.

### BookService

``` powershell
cd BookService
mvn clean test
mvn package
```

### LibraryService

``` powershell
cd LibraryService
mvn clean test
mvn package
```

### BatchService

``` powershell
cd BatchService
mvn clean test
mvn package
```

### React UI

``` powershell
cd react-ui
npm install
npm run build
```

Vite writes production assets to `react-ui/dist/`. To preview the
production build locally:

``` powershell
npm run preview
```

Run each command in its module directory. Maven commands may download
dependencies on the first run.

## Configuration

### BookService

File: `BookService/src/main/resources/application.properties`

-   HTTP port: `8081`
-   gRPC server port: `9090` (configured in `GrpcServerLifecycle.java`)

### LibraryService

File: `LibraryService/src/main/resources/application.properties`

-   REST port: `8080`
-   gRPC target: `localhost:9090`

If you change the BookService gRPC port, update both
`GrpcServerLifecycle.java` and LibraryService's `grpc.client.target`
setting.

### BatchService

File: `BatchService/src/main/resources/application.properties`

-   REST port: `8082`
-   H2 JDBC URL: `jdbc:h2:mem:batchdb;DB_CLOSE_DELAY=-1`
-   H2 console: `/h2-console`
-   Spring Batch auto-start is disabled so the job runs only when
    triggered through the API.

### React UI

File: `react-ui/vite.config.js`

The development server runs on port `5173`. The UI's API helper uses
`/api/...` paths. For local development, ensure the Vite config proxies
`/api` to `http://localhost:8080`; if the proxy is missing in your local
checkout, add this configuration to the Vite `server` section:

``` javascript
proxy: {
  '/api': {
    target: 'http://localhost:8080',
    changeOrigin: true
  }
}
```

Restart Vite after changing its configuration.

## Troubleshooting

### Frontend loads but displays "Cannot reach LibraryService"

1.  Confirm LibraryService is running on `8080`.
2.  Open `http://localhost:8080/api/library/books` directly.
3.  If the direct request fails, read the LibraryService terminal logs.
4.  If the direct request succeeds but the frontend fails, confirm Vite
    proxies `/api` to `http://localhost:8080` and restart Vite.

### LibraryService cannot connect to BookService

1.  Confirm BookService started successfully.
2.  Check its log for the gRPC server on port `9090`.
3.  Confirm LibraryService's `grpc.client.target=localhost:9090`
    setting.
4.  Check that no other process is using port `9090`.

### Port is already in use

On Windows PowerShell, find the process listening on a port:

``` powershell
netstat -ano | findstr :8080
```

Replace `8080` with the affected port. To stop a process, verify its PID
first, then use Task Manager or `Stop-Process` only if you are sure it
is the correct process.

### Maven command is not recognized

Install Maven and add its `bin` directory to `PATH`, then open a new
terminal and run:

``` powershell
mvn -version
```

### Java version mismatch

Check the Java version Maven actually uses:

``` powershell
java -version
mvn -version
```

BookService and LibraryService currently target Java 17, while
BatchService targets Java 21. Keep this difference in mind if building
modules with different JDK installations.

### Batch import fails

1.  Confirm BatchService is running on port `8082`.
2.  Check that `books.csv` is present in `src/main/resources`.
3.  Review the BatchService logs for CSV parsing, schema, or job errors.
4.  Open the H2 console using the JDBC URL shown above.
5.  Remember that each restart creates a fresh in-memory database.

## Current demo limitations

-   BookService stores its sample books in an in-memory map; there is no
    persistent database for the main book catalogue.
-   BatchService uses a separate H2 database and is not wired to update
    BookService's sample data.
-   The application is intended as a local learning/demo project, not a
    production deployment.
-   Authentication, authorization, production secrets management,
    persistent storage, and deployment orchestration are not configured
    here.
-   API route and port changes must be kept consistent across the
    frontend proxy and backend configuration.

## Suggested next improvements

1.  Add automated integration tests covering the full React → REST →
    gRPC flow.
2.  Add persistent storage to BookService.
3.  Connect batch imports to the shared book persistence layer, with
    duplicate handling and validation.
4.  Add job status and import summary endpoints.
5.  Add Docker Compose for one-command local startup.
6.  Add CI using GitHub Actions to build and test each module.

## License

Add your chosen license here before distributing the project publicly.
