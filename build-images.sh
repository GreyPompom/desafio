#!/bin/bash
docker build -t api1-image ./backend
docker build -t api2-image ./backend
docker build -t frontend-image ./frontend