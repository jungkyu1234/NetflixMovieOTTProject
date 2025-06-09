# 🎬 영화 프로젝트 전체 구조도

---

**📌 프로젝트 개요**  
**📦 영화 구독 및 결제 서비스 (Movie Subscription & Payment Service)**  
사용자가 **다양한 영화를 선택해 결제하고 구독할 수 있는 온라인 영화 플랫폼 서비스**입니다.  
**이 프로젝트는 Spring Boot 기반 RESTful API로, 영화 목록 조회, 영화 상세 조회, 회원가입/로그인, 결제, 결제 검증 등 핵심 기능**을 구현하여  
영화 OTT 서비스의 백엔드 시스템을 구축했습니다.  
**JWT 기반 인증, AOP 공통 로깅, Swagger 기반 API 문서화, 테스트 자동화**를 통해 확장성과 안정성을 확보한 서비스입니다.

**📌 프로젝트 목적**  
**영화 구독 및 결제 서비스 구현**  
→ 사용자가 다양한 영화를 검색, 상세 조회 후 구독 결제를 진행할 수 있도록 API 서비스 제공

**JWT 기반 인증/인가 시스템 구현**  
→ 회원가입/로그인 및 인증 토큰 발급, 보호된 엔드포인트에 대한 인증 처리

**결제 API 연동 및 결제 검증 처리**  
→ 아임포트(I'mport) API를 활용한 결제 기능 연동 및 검증 로직 구현

**AOP 기반 로깅 및 예외처리 공통화**  
→ 서비스 로직과 분리된 공통 관심사를 AOP로 처리해 코드의 일관성 및 유지보수성 향상

**API 문서화 및 테스트 자동화**  
→ Swagger로 API 스펙 자동 문서화, MockK를 통한 단위 테스트 및 통합 테스트 환경 구축

## 🏗️ **프로젝트 아키텍처 개요**  
영화 서비스의 핵심 컴포넌트 및 데이터 흐름을 한눈에 볼 수 있도록 정리했습니다.

| 계층           | 역할                                      | 예시 클래스                    |
|:---------------|:-------------------------------------------|:--------------------------------|
| 🎯 **Controller**  | 사용자 요청 처리 (웹/API)                  | **MovieController**               |
| ⚙️ **Service**     | 비즈니스 로직 담당                        | **MovieService**, **BatchService**    |
| 🗄️ **Repository**  | 데이터베이스 CRUD 작업 수행 (JPA, JdbcTemplate) | **MovieRepository**               |
| 🛢️ **Database**    | 영화 데이터 저장소 (MySQL, H2 등)          | -                            |

---

## 🔄 **외부 API 및 배치 작업 연동 흐름**  
배치 스케줄러가 주기적으로 외부 API에서 데이터를 가져와 DB에 저장하는 과정입니다.

| 컴포넌트              | 역할                                          |
|:----------------------|:---------------------------------------------|
| ⏰ **MovieBatchProcessor** | 스케줄러가 호출하는 배치 작업 클래스<br>- `@Scheduled` 어노테이션으로 매달 1회 실행 |
| 🌐 **MovieOpenApiService** | 외부 영화 API 호출 및 JSON 데이터 수신 담당        |
| 📦 **DTO 계층**           | JSON 데이터를 Java 객체로 변환 (Data Transfer Objects)<br>예: `MovieEntireResultDto`, `MovieListResultDto`, `MovieResultDto` |
| 🎬 **Movie Entity**       | DB에 저장되는 실제 영화 엔티티 객체                |

---

## 📌 **추가 팁**
- 각 계층별 **책임과 역할이 명확하게 분리**되어 있어 유지보수가 편리합니다.
- **DTO를 통한 데이터 변환**으로 API 변경에 유연하게 대응할 수 있습니다.
- **배치 작업 자동화**로 주기적인 데이터 업데이트가 가능합니다.


🔍 **흐름 요약**  
**1. 사용자 요청 → Controller → Service → Repository → Database**  

**2. 배치 작업**
   - 스케줄러가 MovieBatchProcessor 실행  
   - MovieOpenApiService를 통해 외부 API 호출  
   - JSON → DTO 변환 후  
   - Movie Entity 생성 → DB 저장  


📌 **추가 팁**  
- 각 계층별 책임과 역할이 명확하게 분리되어 유지보수가 편리합니다.  
- DTO를 통한 데이터 변환으로 API 변경에 유연하게 대응할 수 있습니다.  
- 배치 작업 자동화로 주기적 데이터 업데이트가 가능합니다.
---
> **🎬 영화 프로젝트 전체 구조도**

> **Controller**               **← 사용자 요청(웹/REST API) 받음**  
> **(MovieController 등)**  
> │  
> ▼  
> **Service**                  **← 비즈니스 로직 처리**  
> **(MovieService, BatchService)**  
> │  
> ▼  
> **Repository**               **← DB에 직접 접근(JPA, JdbcTemplate)**
> **(MovieRepository 등)**  
> │  
> ▼  
> **Database**                 **← 영화 데이터 저장소 (MySQL, H2 등)**
> 
---
> **🔄 배치 작업 & 외부 API 연동 흐름**

> **MovieBatchProcessor**       **← 스케줄러가 호출하는 배치 작업 클래스**  
> **@Scheduled 어노테이션으로 매달 1회 실행**  
> │  
> ▼  
> **MovieOpenApiService**       **← 외부 영화 API에서 데이터 조회 담당**  
> **API 호출해서 JSON 받아옴**  
> │  
> ▼  
> **DTO 계층 (Data Transfer Objects) ← JSON → 자바 객체 매핑 역할**  
> **(MovieEntireResultDto, MovieListResultDto, MovieResultDto)**  
> │  
> ▼  
> **Movie Entity             ← DB에 저장하는 엔티티 객체**
---



**📌 주요 기능**  

**📌 1️⃣ 영화 목록 및 상세 조회**  

**영화 데이터 API 연동 및 DB에 저장된 영화 데이터 조회**  
- **영화 목록 조회 API : /api/movie/{page}**  
- **영화 상세 조회 API : /api/movie/content/{movieCd}**  
- **영화 로그인 조회 API : /auth/movie/login**  
- **영화 회원가입 조회 API : /auth/movie/signup**  
- **영화 결제 조회 API : /pay/prepare_payment**  
- **영화 결제 검증 조회 API : /pay/validate_payment**  



**📌 2️⃣ 회원가입 및 로그인**   

**회원가입 API : /auth/movie/signup**  
**로그인 API : /auth/movie/login**  
**회원 정보 저장 및 JWT 토큰 발급**  

**📌 3️⃣ 영화 결제 및 검증**    

**결제 준비 API : /pay/prepare_payment**  
→ 결제 전 주문 데이터 생성 및 응답

**결제 검증 API : /pay/validate_payment**  
→ 아임포트 API로 결제 금액 및 상태 검증

**📌 4️⃣ AOP 기반 로깅 및 공통 예외처리**    

- 서비스 호출 전/후, 파라미터 및 실행시간, 예외 로그 기록

- AOP로 공통 관심사 분리하여 코드 일관성 확보

**📌 5️⃣ API 문서 자동화** 

- Swagger UI를 통해 API 목록, 파라미터, 응답 값 실시간 확인 및 테스트 가능

**📌 6️⃣ 테스트 환경 및 자동화**    

- H2 DB로 테스트 환경 구성

- MockK으로 외부 의존성 없는 단위 테스트 작성

**✅ 구현 기능 정리**      
- **영화 목록 조회 :** 페이지별 영화 데이터 반환  
- **영화 상세 조회 :** 특정 영화 코드로 상세정보 반환  
- **회원가입 :**	신규 사용자 등록  
- **로그인 :** (JWT)	JWT 토큰 발급 및 인증 처리  
- **결제 준비 :**	주문 데이터 생성 및 응답  
- **결제 검증 :**	아임포트 결제 상태 확인 및 검증  
- **AOP 로깅 :**	공통 로직 AOP 처리  
- **Swagger 문서화 :**	API 문서 자동 생성  
- **테스트 환경 구성 :**	H2 DB, MockK 단위 테스트
- **Redis 캐싱 :** 주요 데이터 캐싱으로 성능 개선  
- **Docker 환경 구성 :** 컨테이너 기반 개발/배포 환경 구축

**👨‍💻 개인 구현 기능**  
- 영화 목록/상세 API Controller 및 Service 구현  
- Spring Security + JWT 인증/인가 설정 및 토큰 발급 기능 구현  
- 아임포트 API 연동 결제 및 검증 API 개발  
- AOP 기반 공통 로깅/예외처리 모듈화  
- Swagger 설정 및 API 문서화 자동화  
- 테스트 환경(H2 + MockK) 및 테스트 코드 작성  
- Clean Architecture 설계 및 모듈화
- Redis 캐싱 적용 및 설정 구현    
- Dockerfile 및 docker-compose.yml 작성으로 개발/배포 환경 컨테이너화  

**🌱 프로젝트 성과 & 경험**  
✅ Spring Boot + Java 기반 프로젝트 설계 및 구현 경험  
✅ REST API 설계 및 JWT 인증/인가 구현 능력 향상  
✅ 아임포트 API 결제 연동 및 검증 처리 실무 경험  
✅ AOP, API 문서화, 테스트 자동화 등 실무 필수 기술 습득  
✅ 클린 아키텍처 설계와 모듈화로 유지보수성과 확장성 확보  
✅ 프로젝트 설계 → 구현 → 테스트 → 문서화 전 과정 단독 수행  

**📖 데이터베이스**  
H2 Database, MariaDB (개발/테스트용)    
JPA 기반 ORM으로 데이터 테이블과 엔티티 매핑   
회원, 영화, 결제 데이터 저장 및 조회 기능 제공  

## 프로필
**👨‍💻 프로필**
- 백엔드 개발자로서 **Spring Boot, Java, JPA, myBatis** 등을 기반으로 도메인 중심 설계와 RESTful API 개발 경험을 보유하고 있습니다.
- 실무 및 사이드 프로젝트를 통해 **API 설계, 데이터 처리, 시스템 구조 설계**를 직접 수행해왔으며, 효율적인 코드와 안정적인 서비스 운영을 목표로 개발합니다.
- 클린 코드와 테스트 자동화를 중시하며, 유지보수성과 확장성을 고려한 설계에 집중합니다.

## 기여
- 본 프로젝트는 개인 프로젝트로, 모든 코드는 직접 작성하였습니다.

## 라이선스
**📜 라이선스**
- MIT 라이선스를 준수합니다. 

## 📮 문의  
이 프로젝트에 대한 문의 사항은 언제든지 연락 주시기 바랍니다! 🚀  

- **📧 이메일 : kchs1230@naver.com**  
- **📱 깃허브 : https://github.com/jungkyu1234/NetflixMovieOTTProject**



