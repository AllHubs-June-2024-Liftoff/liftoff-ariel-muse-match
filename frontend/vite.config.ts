import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  build: {
    outDir: 'dist', // Default output directory for production build
    emptyOutDir: true, // Clear the output directory before building
  },
  server: {
    host: '0.0.0.0', // Allows access from outside the container
    port: 5173, // Optional: Ensures consistency in the exposed port
  },
})
