# ===== STAGE 1: Build Angular app =====
FROM node:20 AS build

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy all project files
COPY . .

# Build Angular app (production build)
RUN npm run build --prod

# ===== STAGE 2: Serve with NGINX =====
FROM nginx:alpine

# Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

# Copy only the built files from the browser folder
COPY --from=build /app/dist/taskhub-frontend/browser /usr/share/nginx/html

# Copy a custom nginx configuration file for Angular routing
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
