# 🎬 영화 프로젝트 전체 구조도 (개념도)

---

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
1. 사용자 요청 → Controller → Service → Repository → Database  
2. 배치 작업  
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


