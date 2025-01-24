# Library Management System

This is a full-stack **Library Management System** project, built with:
- **Angular** for the frontend.
- **Spring Boot** for the backend (Category Management, Elements Management, and Gateway modules).
- **Docker** for containerization.

The project allows managing libraries and their books, with RESTful APIs exposed by the backend and consumed by the frontend.

---

## Table of Contents
1. [Features](#features)
2. [Tech Stack](#tech-stack)
3. [Directory Structure](#directory-structure)
4. [Prerequisites](#prerequisites)
5. [Setup Instructions](#setup-instructions)

---

## Features
- Add, update, and delete libraries.
- Manage books within libraries.
- Global API Gateway to route requests between microservices.
- Responsive Angular frontend for user interaction.
- Fully containerized with Docker and Docker Compose.

---

## Tech Stack
### Frontend:
- Angular
- TypeScript

### Backend:
- Spring Boot
    - **Category Management**: Manages libraries.
    - **Elements Management**: Manages books.
    - **Gateway**: API Gateway for routing.

### DevOps:
- Docker
- Docker Compose

---

## Directory Structure
```plaintext
library-management/
├── category-management/        # Spring Boot service for managing libraries
├── elements-management/        # Spring Boot service for managing books
├── gateway/                    # API Gateway using Spring Cloud Gateway
├── library-frontend/           # Angular-based frontend application
├── docker-compose.yml          # Docker Compose configuration
├── .gitignore                  # Ignored files for Git
└── README.md                   # Project documentation
```
## Prerequisites

Make sure you have the following installed:

- Node.js (v18 or later)
- npm (v8 or later)
- Java 17
- Docker and Docker Compose

## Setup Instructions

### Clone the Repository
git clone https://github.com/your-username/library-management.git
cd library-management

### Build and Run with Docker Compose
- docker-compose build
- docker-compose up

### Access the application:
- Frontend: http://localhost:4200
- Backend Gateway: http://localhost:8080

