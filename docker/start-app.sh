#!/bin/sh

docker build -t app/bancodigital .

docker-compose -f docker-compose.yml up