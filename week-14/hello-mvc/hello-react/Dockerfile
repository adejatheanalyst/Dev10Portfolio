FROM node:18-alpine AS builder
WORKDIR /home/app
COPY . .
RUN npm ci && npm cache clean --force
RUN REACT_APP_MESSAGE="this is a production build" npm run build

# NEW CODE
# 1. Nginx & Alpine
FROM nginx:1.25-alpine
# 2. replace nginx's default config with our nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf
# 3. copy build output from builder to the default static file location
COPY --from=builder /home/app/dist /usr/share/nginx/html

# NEW CODE
# 1. Copy the initialization script to the Nginx init scripts directory.
COPY 85-set-environment-vars.sh /docker-entrypoint.d
# 2. Make it executable.
RUN chmod +x /docker-entrypoint.d/85-set-environment-vars.sh

EXPOSE 80
# 4. start nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]