# Placement Portal REST API Guide

This guide documents the HTTP API and provides JSON examples for testing it with Postman.

## 1. Start the application

1. Start MySQL and configure the connection in `src/main/resources/application.properties`.
2. From the project root, run:

   ```bash
   mvn spring-boot:run
   ```

3. Use `http://localhost:8080` as the base URL. For convenience, create a Postman environment
   variable named `baseUrl` with value `http://localhost:8080`.
4. For requests with JSON bodies, set `Content-Type` to `application/json`.

On an empty database, a Spring Data JPA initializer loads sample students, companies, and
applications at startup. The examples below use new resource names where a request creates data,
so they can be run independently of the sample records.

## 2. Endpoint summary

| Method | Path | Purpose | Success |
| --- | --- | --- | --- |
| `POST` | `/api/students` | Register a student | `201 Created` |
| `GET` | `/api/students` | List students | `200 OK` |
| `GET` | `/api/students/{rollNo}` | Get one student | `200 OK` |
| `POST` | `/api/students/{rollNo}/opt-out` | Opt a student out of placements | `200 OK` |
| `POST` | `/api/companies` | Register a company drive | `201 Created` |
| `GET` | `/api/companies` | List companies | `200 OK` |
| `GET` | `/api/companies/{companyId}` | Get one company | `200 OK` |
| `GET` | `/api/companies/eligible?rollNo={rollNo}` | List drives for which a student is eligible | `200 OK` |
| `POST` | `/api/applications` | Apply a student to a drive | `201 Created` |
| `GET` | `/api/applications` | List all applications | `200 OK` |
| `GET` | `/api/applications?rollNo={rollNo}` | Filter applications by student | `200 OK` |
| `GET` | `/api/applications?companyId={companyId}` | Filter applications by company | `200 OK` |
| `GET` | `/api/applications?rollNo={rollNo}&companyId={companyId}` | Filter by both values | `200 OK` |
| `GET` | `/api/applications/{appId}` | Get application details | `200 OK` |
| `PATCH` | `/api/applications/{appId}/status` | Change an application's interview status | `200 OK` |
| `GET` | `/api/statistics` | Get overall placement statistics | `200 OK` |
| `GET` | `/api/statistics/drives` | Get applicant and offer counts by drive | `200 OK` |

Use the Postman request URL format `{{baseUrl}}/api/...` after creating the `baseUrl` environment
variable. URL path values and query parameters are substituted by Postman.

## 3. Student endpoints

### Register a student

```http
POST {{baseUrl}}/api/students
Content-Type: application/json
```

```json
{
  "rollNo": "API26001",
  "name": "Taylor Student",
  "cgpa": 8.75,
  "activeBacklogs": 0
}
```

The roll number is trimmed and converted to uppercase. A new student starts with status `UNPLACED`.
`rollNo`, `name`, `cgpa`, and `activeBacklogs` are required. The name must contain non-whitespace
characters; roll number is at most 30 characters and name at most 100. CGPA must be between `0.00`
and `10.00` with no more than two decimal places. Backlogs cannot be negative.

Example `201 Created` response:

```json
{
  "rollNo": "API26001",
  "name": "Taylor Student",
  "cgpa": 8.75,
  "activeBacklogs": 0,
  "placementStatus": "UNPLACED"
}
```

### List students

```http
GET {{baseUrl}}/api/students
```

Returns an array of student objects using the same fields as the registration response.

### Get a student

```http
GET {{baseUrl}}/api/students/API26001
```

Roll numbers are case-insensitive after normalization.

### Opt out of placements

```http
POST {{baseUrl}}/api/students/API26001/opt-out
```

Returns the student with `placementStatus` set to `OPTED_OUT`. A student who is already placed or
has already opted out cannot opt out again.

## 4. Company and drive endpoints

### Register a company drive

```http
POST {{baseUrl}}/api/companies
Content-Type: application/json
```

```json
{
  "name": "API Example Company",
  "minCgpa": 7.50,
  "maxBacklogs": 0,
  "packageLpa": 12.00
}
```

`name`, `minCgpa`, `maxBacklogs`, and `packageLpa` are required. Company name must not be blank and
must be at most 150 characters. Minimum CGPA is `0.00`–`10.00`; package is greater than zero and at
most `999.99` LPA; both may have at most two decimal places. Backlogs cannot be negative. Company
names are unique ignoring case.

Example `201 Created` response:

```json
{
  "companyId": 10,
  "name": "API Example Company",
  "minCgpa": 7.50,
  "maxBacklogs": 0,
  "packageLpa": 12.00
}
```

Use the returned `companyId` in the application request; the example ID above is illustrative.

### List companies

```http
GET {{baseUrl}}/api/companies
```

### Get a company

```http
GET {{baseUrl}}/api/companies/10
```

### Find eligible drives for a student

```http
GET {{baseUrl}}/api/companies/eligible?rollNo=API26001
```

A student is eligible when their CGPA is at least the drive minimum and their active backlogs do not
exceed the drive maximum. An unknown roll number returns `404 Not Found`.

## 5. Application endpoints

### Apply to a drive

The student and company must already exist. The student must be `UNPLACED`, meet the drive's CGPA
and backlog criteria, and must not already have an application to that company.

```http
POST {{baseUrl}}/api/applications
Content-Type: application/json
```

```json
{
  "rollNo": "API26001",
  "companyId": 10
}
```

Example `201 Created` response:

```json
{
  "appId": 25,
  "rollNo": "API26001",
  "companyId": 10,
  "interviewStatus": "APPLIED"
}
```

The returned `appId` is generated by the database and is illustrative.

### List and filter applications

```http
GET {{baseUrl}}/api/applications
GET {{baseUrl}}/api/applications?rollNo=API26001
GET {{baseUrl}}/api/applications?companyId=10
GET {{baseUrl}}/api/applications?rollNo=API26001&companyId=10
```

Returns an array of application detail objects:

```json
[
  {
    "appId": 25,
    "rollNo": "API26001",
    "studentName": "Taylor Student",
    "cgpa": 8.75,
    "companyId": 10,
    "companyName": "API Example Company",
    "packageLpa": 12.00,
    "interviewStatus": "APPLIED"
  }
]
```

If both query parameters are supplied, the result must match both. Filtering by a nonexistent
student or company returns `404 Not Found`.

### Get application details

```http
GET {{baseUrl}}/api/applications/25
```

Returns one application detail object using the same shape shown above.

### Update interview status

```http
PATCH {{baseUrl}}/api/applications/25/status
Content-Type: application/json
```

```json
{
  "status": "SHORTLISTED"
}
```

Valid status values and transitions:

| Current | Allowed next statuses |
| --- | --- |
| `APPLIED` | `SHORTLISTED`, `REJECTED` |
| `SHORTLISTED` | `INTERVIEWING`, `REJECTED` |
| `INTERVIEWING` | `OFFERED`, `REJECTED` |
| `OFFERED` | No transitions (final) |
| `REJECTED` | No transitions (final) |

When setting status to `OFFERED`, the service marks the student `PLACED` (or upgrades an already
placed student only when this offer has a strictly higher package) and rejects that student's
pending applications for drives with a strictly lower package.

The response describes the result:

```json
{
  "application": {
    "appId": 25,
    "rollNo": "API26001",
    "companyId": 10,
    "interviewStatus": "SHORTLISTED"
  },
  "studentMarkedPlaced": false,
  "cancelledApplications": 0
}
```

For a non-offer transition, `studentMarkedPlaced` is `false` and `cancelledApplications` is `0`.
When an offer is released, the values reflect the placement update and lower-package cascade.

## 6. Statistics endpoints

### Overall placement statistics

```http
GET {{baseUrl}}/api/statistics
```

Example response:

```json
{
  "totalStudents": 15,
  "placedStudents": 4,
  "totalOffers": 4,
  "averagePackageLpa": 18.25,
  "highestPackageLpa": 42.00,
  "placementPercentage": 26.67
}
```

Numbers depend on the data in the database; the example is illustrative.

### Company drive summaries

```http
GET {{baseUrl}}/api/statistics/drives
```

Example response:

```json
[
  {
    "companyName": "API Example Company",
    "packageLpa": 12.00,
    "applicants": 1,
    "offers": 0
  }
]
```

## 7. Error responses

Errors are JSON objects with this shape:

```json
{
  "timestamp": "2026-11-02T07:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "cgpa: must be less than or equal to 10.00"
}
```

| HTTP status | Meaning / common causes |
| --- | --- |
| `400 Bad Request` | Invalid field values, malformed/missing JSON, invalid status enum, or other business validation errors |
| `404 Not Found` | Requested student, company, or application does not exist |
| `409 Conflict` | Duplicate student/company/application, eligibility rule failure, illegal status transition, or database uniqueness conflict |

## 8. Suggested Postman test sequence

1. Create a Postman environment with `baseUrl = http://localhost:8080`.
2. Register student `API26001` using the student request above.
3. Register `API Example Company` using the company request above. Copy the actual `companyId` from
   the response.
4. Check eligibility with `GET /api/companies/eligible?rollNo=API26001`.
5. Apply using the actual company ID; copy the returned `appId`.
6. Advance that application's status by sending PATCH requests in order:
   `SHORTLISTED`, `INTERVIEWING`, then `OFFERED`.
7. Fetch the application, student, and statistics endpoints to see the updated results.

Repeat with a distinct roll number and company name if you want to test registration again.
