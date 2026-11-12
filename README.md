# Internship Tracker

REST API (Spring Boot, JPA, H2) to track internship applications through stages (wishlist, applied, OA, interview, offer, rejected) with deadlines.

## Run it
```bash
mvn spring-boot:run
```
API at `http://localhost:8080/api/applications`.

## Endpoints
| Method | Path | Purpose |
|---|---|---|
| GET | `/api/applications?stage=OA` | List, optionally by stage |
| GET | `/api/applications/upcoming?days=14` | Deadlines in the next N days |
| POST | `/api/applications` | Create |
| PUT | `/api/applications/{id}` | Update (move stage, edit notes) |
| DELETE | `/api/applications/{id}` | Delete |

Example:
```bash
curl -X POST localhost:8080/api/applications -H 'Content-Type: application/json' \
  -d '{"company":"Acme","role":"SDE Intern","stage":"APPLIED","deadline":"2026-12-01"}'
```

## Roadmap
- [ ] Controller tests with MockMvc
- [ ] Simple HTML/JS board view
- [ ] PostgreSQL profile
- [ ] Email or console reminders for upcoming deadlines
- [ ] Data model diagram

## License
MIT
