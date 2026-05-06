# Google Job Monitor

## 功能

- 监控 Google 广州职位
- 微信通知（Server酱）
- SQLite 去重
- Playwright 抓取

## 运行

### 安装 Playwright 浏览器

```bash
mvn playwright:install
```

### 打包

```bash
mvn clean package
```

### 运行

```bash
java -jar target/google-job-monitor-1.0.0.jar
```

## Docker

```bash
docker compose up -d --build
```
