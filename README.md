# 스프링부트 JPA - 주소 API 활용해서 회원가입하기

1. 의존성
- devtools
- spring web (mvc)
- mustache
- lombok
- jpa
- mariadb
- security
- validation


2. DB설정
```
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```

3. 모델링
```
USER
id
username
password
email
addr
```

4. 기능
- 회원가입 (Spring Security 사용)
- 로그인
- 회원탈퇴
- 회원정보수정
- 도로명주소 검색

5. API
https://www.juso.go.kr/openIndexPage.do