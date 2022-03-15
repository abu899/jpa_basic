# JAVA Persistent API(JPA) Basic Study

## 목표

1. 객체와 테이블 설계 매핑
   - 객체와 테이블을 제대로 설계하고 매핑하는 방법
   - 기본 키와 외래 키 ㅁ핑
   - 1:N, N:1, 1:1, N:M 매핑

2. JPA 내부 동작 방식 이해
   - JPA의 내부 동작 방식을 이해하고 사용
   - JPA가 어떤 SQL을 만들어 내는지 이해
   - JPA가 언제 SQL을 실행하는지 이해

### ORM

 - Object Relational Mapping(객체 관계 매핑)
 - 객체는 객체대로 설계, 관계형 데이터베이스는 관계형 데이터베이스 대로 설계
 - ORM 프레임워크가 중간에서 매핑

### Database Dialect 

JPA는 특정 데이터베이스에 종속되지 않는다

- 각각의 데이터베이스가 제공하는 SQL 문법과 함수는 다를 수 있다
  - 가변 문자: MySQL은 VARCHAR, Oracle은 VARCHAR2
  - 문자열을 자르는 함수: SQL 표준은 SUBSTRING(), Oracle은 SUBSTR()
  - 페이징: MySQL은 LIMIT , Oracle은 ROWNUM
- `Dialect`: SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능

## JPA의 구동 방식

<p align="center"><img src="/imgs/1.png" width="80%"></p>

## 주의사항

- `EntityManagerFactory`는 하나만 생성해서 어플리케이션 전체에서 공유한다
- `EntityManger`는 쓰레드간 공유가 안된다
  - 사용하고 버려야한다
- JPA의 모든 데이터는 `Transaction` 안에서 실행된다!! (매우 중요)