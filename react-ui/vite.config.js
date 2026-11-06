import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    host: '0.0.0.0',
    port: 5173,
    strictPort: true,
    proxy: {
      // Keep browser requests same-origin; Vite forwards API calls to LibraryService.
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
});