# springboot-reddit-backend

목표  : 텍스트 위주의 SNS 사이트를 클론해보고 싶었다. 여러 사이트를 돌아보다가 구현 참고 자료도 많고, 아직까지도 활발하게 사용자가 있는 레딧을 클론해보기로 결정했다.

구현 기술 :

기술 사용 이유

- react : 프론트를 구현할 때 가장 쉬운 방법이라고 생각해 사용

- bootstrap : 디자인엔 재능이 없어 css를 아주 잘 해주는 기술이라 사용

- mariaDB : mysql과 호환성이 좋으면서 무료인 mariaDB를 예전부터 프로젝트에서 사용해보고 싶었다.

- java spring boot : 순전히 java spring을 선택한 이유는 JD에서 가장 많이 요구하는 웹 개발 프레임워크이기 때문이다. 도대체 자바 스프링이 어떻길래 그렇게 인기가 좋은 건지 경험해보고 싶었다. spring을 좀 더 용이하게 쓰게 해주는 spring boot로 환경 설정과 코딩 시간을 단축했다.

- JPA with Hibernate : 직접 sql 구문을 하나하나 매핑하지 않아도 DB를 쉽게 다룰 수 있어 사용했다. 우아한 형제들 같은 대규모 서비스에서도 JPA를 사용한다고 해서 궁금하기도 했다. 앞으로 공부가 더 많이 필요하다.

기능 목록 :

- 회원 가입

회원 가입 신청 후 본인 확인 이메일을 보내 이메일 인증 완료 시에 회원 가입 성공

- 로그인

Spring Security + JWT 로그인

세션과 쿠키 기반의 로그인보다 보안이 좋은 jwt 로그인을 구현하고 싶었다.

나중에는 OAuth2도 추가하고 싶다.

- 게시판 (Subreddit 카테고리)

 게시판 생성,  삭제 (수정 예정)

- 게시글 (Post)

게시글 생성, 삭제, 수정

- 댓글 (Comment)

댓글 생성, (수정과 삭제는 예정)

댓글 달리면 메일로 댓글 달렸다고 알림

- 게시글 투표(Vote)

투표 Up & Down 기능 구현

- 회원 활동 요약

"/user-profile/:id"

