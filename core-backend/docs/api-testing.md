# API Testing Guide

Use this guide after the backend is running on `http://localhost:8080`.

## 1. Health Check

```bash
curl http://localhost:8080/api/health
```

Expected: `200 OK`.

## 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"operator@aegissight.local","password":"password123"}'
```

Save `accessToken` from the response.

## 3. Authenticated Request

```bash
curl http://localhost:8080/api/incidents \
  -H "Authorization: Bearer <accessToken>"
```

Expected: `200 OK`.

## 4. Invalid Token Check

```bash
curl http://localhost:8080/api/incidents \
  -H "Authorization: Bearer invalid-token"
```

Expected: `401 Unauthorized`.

## 5. Detection Flow Smoke Test

Register a camera:

```bash
curl -X POST http://localhost:8080/api/cameras \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{"id":"CAM-102","name":"Main Gate Camera","platform":"FIXED_CAMERA","locationLabel":"Main Gate","active":true,"latitude":20.2961,"longitude":85.8245}'
```

Ingest a detection:

```bash
curl -X POST http://localhost:8080/api/detections \
  -H "Authorization: Bearer <accessToken>" \
  -H "Content-Type: application/json" \
  -d '{"sourceId":"CAM-102","eventType":"PHYSICAL_DISTURBANCE","confidence":0.94,"severity":"HIGH","peopleCount":28,"vehicleCount":2,"riskScore":87,"timestamp":"2026-07-23T10:15:30Z","summary":"Possible physical disturbance detected near the main gate."}'
```

Expected: `201 Created` with `alertCreated: true`.
