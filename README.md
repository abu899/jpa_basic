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

### 주의사항

- `EntityManagerFactory`는 하나만 생성해서 어플리케이션 전체에서 공유한다
- `EntityManger`는 쓰레드간 공유가 안된다
    - 사용하고 버려야한다
- JPA의 모든 데이터는 `Transaction` 안에서 실행된다!! (매우 중요)

## Persistent Context (영속성 컨텍스트)

`Entity`를 영구 저장하는 환경이라는 뜻! 엄청나게 중요하다!

- EntityManager.persist(entity)
    - DB에 저장하는게 아니라 entity를 영속성 컨텍스트에 저장한다는 뜻이다
- 사실상 영속성 컨텍스트는 논리적인 개념
- `EntityManager`를 통해서 영속성 컨텍스트에 접근할 수 있다

### Entity의 생명주기

<p align="center"><img src="/imgs/2.png" width="80%"></p>

- 비영속(new/transient)
    - 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속(managed)
  - 영속성 컨텍스트에서 관리되는 상태
  - em.persist한 후 commit되거나 find로 찾은 상태이다
- 준영속(detached)
  - 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제(removed)
  - 삭제된 상태

### 장점

- 1차 캐시
  - 영속성 컨텍스트 내부에는 캐시가 존재하여, 중복된 값은 캐시에서 가져올 수 있음
  - JPA에서 관리하는 상태
- 동일성 보장
  - 1차 캐시로 반복 가능한 읽기의 트랜잭션 격리 수준을, 데이터베이스가 아닌 어플리케이션 차원에서 공유
- 트랜잭션을 지원하는 쓰기를 지연한다
  - 트랜잭션을 커밋하는 순간 데이터베이스에 쿼리가 날아간다
- 변경 감지(Dirty Checking)
- 지연 로딩(Lazy Loading)

### flush

영속성 컨텍스트의 변경 내용을 데이터베이스에 반영하는 것

- 변경감지 실행
- 수정된 엔티티에 대한 `쓰기 지연 SQL 저장소`에 등록
- `쓰기 지연 SQL 저장소`의 쿼리를 데이터베이스에 전송한다
- 방법
  - em.flush()
  - 트랜잭션 커밋
  - JPQL 쿼리 실행
- 영속성 컨텍스트를 비우는 것이 아니다!
  - 영속성 컨텍스트의 `변경내용을 데이터베이스에 동기화` 하는것이라고 이해해야한다
- `트랜잭션이라는 작업 단위가 중요`하다
  - 영속성 컨텍스와 트랜잭션의 주기를 맞춰서 개발해야 데이터 동기화 같은 문제가 발생하지 않는다

### 준영속 상태

- 영속 상태의 엔티티가 영속성 컨텍스트에서 분리된 상태
- 업데이트 할 때나, 더티 체킹(변경감지)이 불가능해진다
- 방법
  - EntityManager.detach(Entity)
  - EntityManager.clear()
  - EntityManager.close()