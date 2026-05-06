# Google Job Monitor

## Features

- Monitor Google Guangzhou job postings
- WeChat notifications via ServerChan
- SQLite deduplication
- Playwright scraping

## Run

### Install Playwright browsers

```bash
mvn playwright:install
```

### Build

```bash
mvn clean package
```

### Run

```bash
java -jar target/google-job-monitor-1.0.0.jar
```

## Docker

```bash
docker compose up -d --build
```
