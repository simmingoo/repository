1. 프로젝트 실행 방법
    1.1. vue 빌드
     - npm 사용을 위하여 node.js 설치
     - node.js 설치 후 터미널을 이용하여 프로젝트 폴더 ex)10bagger-listed-front/src/main/resources/10bagger 로 이동
     - npm install 을 실행 하여 필요 모듈 설치
     - 동일 폴더에서 npm run build를 실행하여 vue 빌드 ( resource 폴더 내 static / templates 에 빌드된 파일 생성)

    1.2. Spring Boot 프로젝트 실행
     - TenBaggerListed 클래스를 실행하여 스프링 부트 프로젝트 실행

    1.3. 확인
     - http://localhost:8888 (application.properties 폴더 내 server.port 로 설정됨) 으로 접속 하여 페이지 접상 접속 여부 확인

2. 권고 사항
    2.1. 형상 관리
     - 개발 전 또는 소스 커밋 전 remote 서버의 변경 사항이 있는지 먼저 확인 후 업로드
    2.2. api 호출
     - api 서버를 직접 통신 하지 않고 Front의 Sprint Boot 내 RestTemplate 사용하여 api 서버를 호출 하는 것을 권고

