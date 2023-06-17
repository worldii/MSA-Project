# 스마일게이트 개발 캠프 2022 
## 제작 기간
2023.01.01 ~ 2023.02.01
## 윈터 개발 캠프 2기 - JIKJI
Spring 을 이용한 MSA 아키텍쳐 기반 인스타 클론 프로젝트입니다.

## 간편 설명
스마일 게이트 데브 캠프에 참여하여, 대용량 트랙픽을 가진 서비스의 서버 아키텍쳐를 구현해보고자 인스타그램 아키텍쳐를 클론 코딩하였습니다.
## 상세 설명
처음 `spring boot`를 이용한 프로젝트로서 어려움이 있었던 만큼, 많은 것을 배워갔습니다. `git` 을 이용하여, 다른 사람들과 협업 개발을 해보았습니다. 대규모 트래픽을 안정적으로 처리하기 위해,  `CQRS` 패턴을 도입하였고, 쿼리를 진행하는 서버에 대해서 `NoSQL`인 `mongoDB`을 사용했다는 점에서 인상 깊었습니다. 또한 `Spring Cloud`, `eureka client` 를 이용하여 msa 환경을 구축하고, 서버간 통신에 대해 현재 인기가 많은 `Kafka`를 처음으로 사용해 봤다는 점에서 의미가 있었습니다.  또한 프론트 프레임 워크인 `react` 를 활용하였습니다.

## 주요 기능
피드, 포스트 서비스, 팔로우 서비스, 디엠 서비스, 검색 서비스, 알림 서비스, 회원 서비스 기능을 msa 설계 방법으로 구현.

## 기술 스택  및 아키텍쳐 구조
![Untitled](https://github.com/worldii/MSA-Project/assets/87687210/74b75253-b1fe-4c10-964b-ff1b9db594db)

## 기술 스택
### BackEnd
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=#007396" style="height : auto; margin-left : 10px; margin-right : 10px;"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white" style="height : auto; margin-left : 10px; margin-right : 10px;"/> <img src="https://img.shields.io/badge/JSON Web Tokens-000000?style=for-the-badge&logo=JSON Web Tokens&logoColor=white" style="height : auto; margin-left : 10px; margin-right : 10px;"/> <img src="https://img.shields.io/badge/Redis-000000?style=for-the-badge&logo=Redis&logoColor=white" style="height : auto; margin-left : 10px; margin-right : 10px;"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" style="height : auto; margin-left : 10px; margin-right : 10px;"/> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white" style="height : auto; margin-left : 10px; margin-right : 10px;"/>
  <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black">
<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=Hibernate&logoColor=white">


### FrontEnd
 <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"><img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/axios-007CE2?style=for-the-badge&logo=axios&logoColor=white" >
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white">
<img src="https://img.shields.io/badge/Styled_components-DB7093?style=for-the-badge&logo=StyledComponents&logoColor=white">


### 기타
<img src="https://img.shields.io/badge/vim-%23121011.svg?style=for-the-badge&logo=vim&logoColor=white"><img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"> <img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white"> <img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white"> <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"> <img src="https://img.shields.io/badge/S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white">


### 협업툴
<img src="https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white"><img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">


## 회고록
한편으로는 아쉬움이 많이 남습니다.각각의 서비스는 완성했지만,유저 서비스 연동이 되어있지 않은 상태입니다. 또한, 구현을 하면서도 스프링 부트를 통해 처음 프로젝트를 진행하였기 떄문에, 현재는 대규모 서비스에 대해서 성능의 요구사항에 맞는 적절한 아키텍쳐를 고려하는 능력이 부족하다고 생각하였습니다. 단순히 기술을 사용했다고 다가 아닌 점을 알기에, 대규모 서비스를 설계할 때 왜? 내가 이렇게 설계했는지에 대한 타당성을 갖는 능력을 갖추고, 이를 다시 리팩토링할 예졍입니다. 

## 참고 사항
추후 '가상 면접 사례로 배우는 대규모 시스템 설계 기초'를 읽고 리팩토링할 예정입니다.

