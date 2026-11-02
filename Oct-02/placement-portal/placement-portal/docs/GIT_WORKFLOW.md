# Team Git Workflow (4 members)

Every member must commit through their own feature branch and get a teammate's review on each pull request.
The codebase is split by **layer**, so each person owns whole folders and merge conflicts stay rare.

| # | Member | Branch | Owns (folders / files) | Requirements covered |
| --- | --- | --- | --- | --- |
| 1 | **Foundation & Database** | `feature/project-setup-and-database` | `pom.xml`, `application.properties`, `entity/`, `api/`, JPA data initializer, `docker-compose.yml`, `.gitignore`, README; tests `entity/*Test`, `api/*Test`, `repository/JpaRepositoryTest` | Schema integrity, JPA mappings, seed data, Maven/Java 21 setup |
| 2 | **Data Access (JPA)** | `feature/repository-layer` | `repository/` Spring Data JPA interfaces and query projections | JPA repositories, JPQL eligibility query, FR-4 cascade update, FR-5 aggregates |
| 3 | **Business Services** | `feature/service-layer` | `service/` (Student, Company, Application, Statistics), `exception/`; tests `service/*Test` | FR-1 validation, FR-3 eligibility rules, FR-4 offer logic + transactions |
| 4 | **REST API & Integration** | `feature/controller-layer` | `controller/` REST endpoints and exception advice, `api/` request/response records, REST API tests | JSON API, validation, HTTP status/error handling, Postman test coverage |

## Agree on the contracts first (15 minutes, together)

Because each layer calls the next one, fix these signatures before coding so everyone can work in parallel:

* **Member 1 -> all:** `entity/` JPA mappings and persisted enums; `api/` request and response types.
* **Member 2 -> 3:** repository method names (e.g. `StudentRepository.findByRollNo`, `CompanyRepository.findEligibleForStudent`,
  `ApplicationRepository.cancelPendingWithLowerPackage`).
* **Member 3 -> 4:** service method names (e.g. `ApplicationService.applyForDrive`, `updateInterviewStatus`) and the exception types
  mapped to API responses by `ApiExceptionHandler`.

## Rules

1. `main` is protected: **never commit directly to `main`** (GitHub: *Settings > Branches > Branch protection rule*, require a PR and 1 approval).
2. One feature branch per member; branch off the latest `main`. Work inside your own folders to avoid conflicts.
3. Small, meaningful commits in imperative mood, e.g. `Add CompanyRepository.findEligibleForStudent JOIN query`,
   `Reject applications when active backlogs exceed company limit`.
4. Open PRs early and often (at least one per major module, not only one at the end). **Someone other than the author reviews and approves.**
5. Review pairing (suggested): 1 reviews 2, 2 reviews 3, 3 reviews 4, 4 reviews 1.
6. Before opening a PR: `git fetch && git rebase origin/main` (or merge `main` in), then `mvn test`.
7. Resolve conflicts on the PR branch, never on `main`.

## Typical flow

```bash
git clone <repo-url> && cd placement-portal
git checkout -b feature/repository-layer
# ... code ...
git add src/main/java/com/university/placement/repository src/main/java/com/university/placement/entity
git commit -m "Add StudentRepository with Spring Data JPA"
git push -u origin feature/repository-layer
# open a Pull Request on GitHub -> request review -> merge after approval
git checkout main && git pull
```

## Merge order

1. `feature/project-setup-and-database` (skeleton, entities, JPA initializer) - merge first so everyone can compile
2. `feature/repository-layer`
3. `feature/service-layer`
4. `feature/controller-layer`

Then all four run the Postman endpoint examples from the README against `main` and fix issues through small follow-up PRs.

## PR review checklist

- [ ] HTTP endpoints validate request bodies and return consistent JSON errors
- [ ] Business rules live in the **service** layer, not in repositories or controllers
- [ ] New behaviour has a unit test; `mvn test` passes
- [ ] JPA schema generation and sample-data initialization are idempotent
