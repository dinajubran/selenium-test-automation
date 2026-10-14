# Selenium Test Automation

A Java 21, Selenium, TestNG, and Maven UI automation framework using Page Objects,
parallel-safe WebDriver storage, explicit waits, and Extent HTML reports.

## Prerequisites

- JDK 21
- Chrome or Firefox installed
- Access to the target test environment

Verify Java with `java -version`. The included Maven Wrapper downloads the pinned
Maven version automatically, so a global Maven installation is not required.

## Configuration

For local development, copy `.env.example` to `.env` and supply credentials for a
non-production test account. Never commit `.env`. Environment variables and JVM
system properties take precedence over `.env` values.

| Setting | Required | Default | Purpose |
|---|---:|---|---|
| `LOGIN_URL` | Yes | — | Login page URL |
| `ADMIN_USERNAME` | Yes | — | Test account username |
| `ADMIN_PASSWORD` | Yes | — | Test account password |
| `BROWSER` | No | `chrome` | `chrome` or `firefox` |
| `HEADLESS` | No | `true` | Run without a visible browser window |
| `TIMEOUT_SECONDS` | No | `30` | Explicit-wait timeout |

PowerShell example:

```powershell
$env:BROWSER = "firefox"
$env:HEADLESS = "false"
.\mvnw.cmd clean test
```

JVM properties are also accepted:

```shell
./mvnw clean test -DBROWSER=firefox -DHEADLESS=false
```

## Running tests

Run the configured suite:

```shell
./mvnw clean test
```

Run one class or method:

```shell
./mvnw -Dtest=LoginTest test
./mvnw -Dtest=LoginTest#testLoginWithValidUsernameAndValidPassword test
```

The suite runs methods in parallel with four threads. Tests must not share mutable
data or depend on execution order.

## Results and diagnostics

- Extent report: `reports/index.html`
- Failure screenshots: `screenshots/`
- TestNG/Surefire output: `target/surefire-reports/`

GitHub Actions uploads these directories after every run, including failed runs.

## Project structure

```text
src/test/java/com/automation/
├── config/       Typed framework configuration
├── driver/       Browser creation and thread-local lifecycle
├── listeners/    Reporting and failure evidence
├── pages/        Page Objects and reusable UI interactions
├── tests/        Test classes and setup/cleanup
└── utils/        Configuration, reports, and screenshots
```

## Contribution guidelines

- Prefer stable `id`, `name`, or dedicated `data-testid` selectors over visible text.
- Keep assertions in tests; page objects should expose actions and observable state.
- Use explicit waits. Do not add fixed sleeps.
- Generate unique test data and clean up any state a test creates.
- Never run state-changing tests against production.
- Do not hide flaky behavior with unconditional retries.
- Include useful assertion messages.
- Confirm new tests can run independently and in parallel.

## Adding a test

1. Add or extend a Page Object under `pages`.
2. Pass `config.timeout()` into new Page Objects.
3. Add the test under `tests` and extend `BaseTest`.
4. Use unique data and avoid dependencies on other tests.
5. Run the test alone, then run the full suite.

## CI secrets

Configure `LOGIN_URL`, `ADMIN_USERNAME`, and `ADMIN_PASSWORD` as repository secrets.
Use a least-privileged test account and rotate its credentials regularly.
