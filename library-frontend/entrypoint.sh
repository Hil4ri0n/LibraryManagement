#!/bin/sh
envsubst '$API_GATEWAY' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf
exec nginx -g 'daemon off;'
