
# 🧑‍💼 Job Portal API (Spring Boot)

A full-featured backend REST API for a Job Portal system built using Spring Boot. It includes user registration and login with JWT, role-based authorization using Spring Security, caching using Redis, job listing with pagination and dynamic filtering via JPA Specifications.

---

## 🚀 Features

- 🔐 JWT Authentication with Spring Security
- 👤 Role-based access (EMPLOYER, CANDIDATE)
- 🧠 Caching with Redis
- 📄 Pagination and Sorting
- 🔍 Dynamic search/filter using JPA Specification
- 📦 Clean code structure using Service/Repository pattern

---

## 🧱 Database Schema

### 🧑 User

| Field      | Type    | Description                      |
|------------|---------|----------------------------------|
| id         | UUID    | Primary key                      |
| username   | String  | Unique username                  |
| email      | String  | Unique email                     |
| password   | String  | Hashed password                  |
| role       | ENUM    | ADMIN / EMPLOYER / CANDIDATE     |
| created_at | DateTime| Auto timestamp                   |

---

### 💼 Job

| Field       | Type     | Description                      |
|-------------|----------|----------------------------------|
| id          | UUID     | Primary key                      |
| title       | String   | Job title                        |
| description | Text     | Job description                  |
| location    | String   | Job location                     |
| company     | String   | Company name                     |
| salary      | Decimal  | Offered salary                   |
| created_by  | UUID     | FK to User (EMPLOYER)            |
| created_at  | DateTime | Auto timestamp                   |

---

### 📄 Application

| Field        | Type     | Description                         |
|--------------|----------|-------------------------------------|
| id           | UUID     | Primary key                         |
| job_id       | UUID     | FK to Job (`@ManyToOne`)            |
| candidate_id | UUID     | FK to User (`@ManyToOne`)           |
| resume_url   | String   | Resume link                         |
| applied_at   | DateTime | Auto timestamp                      |

---

## 🔐 Authentication & Security

- **JWT** token is issued on successful login
- Endpoints are protected by Spring Security
- Roles:
  - **EMPLOYER**: Post and manage jobs
  - **CANDIDATE**: Browse/apply to jobs

---

## 🌐 API Endpoints

### 🔑 Authentication

| Method | Endpoint              | Access | Description               |
|--------|-----------------------|--------|---------------------------|
| POST   | `/user/register`  | Public | Register a new user       |
| POST   | `/user/login`     | Public | Login and get JWT         |

---

### 💼 Jobs

| Method | Endpoint           | Access     | Description                |
|--------|--------------------|------------|----------------------------|
| POST   | `/job/add`        | EMPLOYER   | Create new job             |
| GET    | `/job/all`        | Public     | Get all jobs (with filter) |
| GET    | `/job/by/{id}`   | Public     | Get job by ID              |

### 💼 Jobs

| Method | Endpoint           | Access     | Description                |
|--------|--------------------|------------|----------------------------|
| POST   | `/application/apply`        | EMPLOYER   | apply for Job             |
| GET    | `/application/view`        | Employer  | Get all application (with filter) |
| GET    | `/application/view/{id}`   | Employer    | Get application by ID              |


---

## 🧠 Caching

Use either:
- **Redis** (for distributed, production-ready caching), or

Example:
- Cache job listings (`/api/jobs`)
- Cache employer details or job details

@Cacheable(value = "jobs", key = "#id")
public Job getJobById(UUID id) { ... }

---

## 📦 Project Structure

job-portal-api/
├── controller/
├── service/
├── repository/
├── model/ (entities)
├── dto/ (request/response)
├── security/
│   ├── JWTFilter
│   ├── JWTService
│   └── SecurityConfig
├── specification/
├── config/
├── exception/
└── resources/

---

## ⚙️ Technologies Used

- Java 17
- Spring Boot 3
- Spring Security
- JWT (Java Web Token)
- Spring Data JPA + Specification API
- Redis 
- MySql
- Maven
- Lombok

---

## 📌 Future Enhancements

- [ ] Resume file upload (local / S3)
- [ ] Email verification
- [ ] Admin dashboard with stats
- [ ] Job expiry scheduler

---

## 👨‍💻 Author

Snehasish Sahoo
📧 sonu2003.sahoo@gmail.com
🌐 https://linkedin.com/in/snehasish-sahoo
