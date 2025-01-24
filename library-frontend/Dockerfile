FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN npx ngcc --properties es2023 browser module main --first-only --create-ivy-entry-points

COPY . .
RUN npm run build

FROM nginx:stable
COPY --from=build /app/dist/library-frontend/browser /usr/share/nginx/html
COPY nginx.conf.template /etc/nginx/conf.d/default.conf

#
COPY nginx.conf.template /etc/nginx/nginx.conf.template
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

EXPOSE 80

