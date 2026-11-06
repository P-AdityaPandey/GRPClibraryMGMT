# Book Library gRPC UI

A small component-based React + Vite frontend for the BookLibraryGrpcSystem project.

## Requirements
- Node.js 18 or newer
- BookService running on its configured ports
- LibraryService REST API running at `http://localhost:8080`

## Run locally
From this folder:

```bash
npm install
npm run dev
```

Open `http://localhost:5173`.

## Build check
```bash
npm run build
```

## API routes used
The UI calls the LibraryService through Vite's development proxy, so the browser uses same-origin `/api/...` URLs:
- `GET /api/library/books`
- `GET /api/library/books/:id`
- `GET /api/library/books/:id/availability`

Vite forwards `/api` to `http://localhost:8080`. If your LibraryService uses different routes, update `src/services/bookApi.js`.

## Notes
- The React app does not connect directly to BookService. The intended flow is React → LibraryService REST → BookService gRPC.
- BatchService on port 8082 is separate and is not required for the main UI.
- API error messages are displayed in the interface. If requests fail, verify LibraryService and its connection to BookService.
