
# sbb2b
### Bash .sh scripts description
* runenv: **_Run selenoid & selenoid-UI _**
* runpull: **_get all docker images_**

### Run tests
#### With Docker
 * Open a terminal or command prompt
 * Go to **{ACCEPTANCE_SRC}**
 * Execute **sh runpull**(It will download all necessary docker images).
 * Execute **sh runenv.sh**(It will start selenium grid (selenoid)).
 * Execute **docker-compose up -d test (It will run all your tests).
 * Allure tests report is available by link **http://127.0.0.1:8082/**
 * Video record is available at directory **{ACCEPTANCE_SRC}/video**
 * Selenoid-UI is available by link **http://127.0.0.1:8081/**
 
#### Selenoid configuration
* Open File **docker-compose.yml**: **_images: selenoid, selenoid-ui, maven (tests), nginx (allure report)_**
* Open File **browsers.json**: chrome, firefox.
#### Tests configuration
* Open a terminal or command prompt
* Go to **{ACCEPTANCE_SRC}/src/main/resources/env/**
* Open File **dev.properties** && see the env props