## 개발 환경
* OS: macOS Monterey 12.3.1 <br>
* Language: Java 8 <br>
* Framework: Spring Boot 2.6.7 <br>
* Database: H2 2.1.212 <br>
* IDE: IntelliJ IDEA 2021.2.2 (Ultimate Edition) <br>

## DB 설치 안내
1. https://www.h2database.com/html/download-archive.html
   <br>
   사이트로 이동해 2.1.212 버전의 Installer 파일을 다운받습니다. (버전을 반드시 맞추어야 합니다.)
   윈도우는 Windows Installer, 맥은 Platform-Independent ZIP 을 사용합니다.
   <br><br>
2. 설치 참고 사이트 <br>
   https://atoz-develop.tistory.com/entry/H2-Database-%EC%84%A4%EC%B9%98-%EC%84%9C%EB%B2%84-%EC%8B%A4%ED%96%89-%EC%A0%91%EC%86%8D-%EB%B0%A9%EB%B2%95
   <br><br>
3. application.yml 파일의 Datasource 정보대로 접속해주세요.
4. 터미널에서 h2를 실행 후 콘솔 화면이 켜지지 않으면 URI 의 IP 부분을 localhost 로 변경해주세요.

## API 로컬 실행 방법
1. 위의 가이드대로 H2 데이터베이스(Server)에 접속합니다. (Port: 8082)
2. 터미널을 열고 메일로 송부드린 ```hdj-assignment-0.0.1-SNAPSHOT``` 파일이 포함된 폴더 위치로 이동합니다.
3. ```java -jar hdj-assignment-0.0.1-SNAPSHOT``` 명령어를 통해 실행합니다.
4. 애플리케이션이 실행되는 동시에 더미 데이터가 DB로 쌓입니다.
5. Init DB Insertion 이 끝난 뒤 Postman 을 통해 API 를 호출해보시면 됩니다.
6. 그래도 실행이 되지 않는다면, MacOS 의 경우 백그라운드로 실행중인 brew services 들을 모두 종료한 후 다시 시도해주세요.

## API 명세서
1. 환자 등록 API
   * https://2dam0.notion.site/1-API-0fd1e801103546efb134ade184667ce6
2. 환자 정보 수정 API
   * https://2dam0.notion.site/2-API-1468734328d042528a53bb2c84ac6a75
3. 환자 삭제 API
   * https://2dam0.notion.site/3-API-20b02da971474ab0868ee17e6b25e31e
4. 한 환자의 상세 정보 조회 API
   * https://2dam0.notion.site/4-1-API-4b9a6ff2654b47648eb323aab62e479d
5. 환자 목록 조회 API
   * https://2dam0.notion.site/5-API-6357c7bd5e2f40baaaafe6034d108c32
---
6. (추가) 환자 방문 접수 API
   * https://2dam0.notion.site/6-API-0ddbdd548973496a9502ad2df9f547ee
7. (추가) 환자 방문 정보 변경 API
   * https://2dam0.notion.site/7-API-4993dfa8dceb4d3f8fb80cceb27dff62
   
<br><br>
### 개발자 소개
* 이름: 이담영 <br>
* 이메일: yidamyeong@gmail.com <br>

<br>

### 감사합니다.
