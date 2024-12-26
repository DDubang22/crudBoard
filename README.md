# 게시판 만들기 미니 프로젝트

## 프로젝트 소개
스프링 부트를 사용하여 세션을 이용한 회원가입이 있고 Create, Read, Update,Delete (CRUD) 기능을 가진 게시판을 구현하는 프로젝트입니다.

## 프로젝트 목적
스프링 부트를 강의를 통해서 학습했고 DB, DB접근법에 대해서도 학습을 했습니다. 머리에 남지도 않고 강의를 통해 학습했던 내용을 프로젝트로 만드는 것이 목표입니다.
서버사이드 언어를 활용하여 JSP가 아닌 Thymeleaf를 사용해서 토이 프로젝트 하는 것으로 목적을 잡았습니다.

## 프로젝트 회고
스프링 배운것을 처음으로 학습해보는 과정이었습니다. 쉽지 않았습니다, 아무것도 없는 백지상태에서 시작하려고 하려고 하니 어떻게 해야될지 고민을 했습니다
처음에는 A4용지에 기본적으로 게시판이 기본적으로 어떻게 구성되어있고 작동하는지 검색을 통해 알아봤습니다.
<br>
그래서 초반에는 어떻게든 프로젝트를 완수하는것이 목표이기에 초안을 잡고 심플하게 레이아웃을 잡고 기능도 기본적인 CRUD + 로그인으로 구상했습니다.
아는 지식도 막상 프로젝트에 녹일려고 하니깐 뭔가 뜬 구름? 잡는 느낌도 들었습니다. 다시 검색을 통해서 복습하고 이해하고 적용시켰습니다.
<br>
ChatGPT도 활용을 했습니다 스택오버플로우을 대신해 줄 AI라고 생각했고 만드는 기간 중에 도움을 많이 받았습니다.
단순히 복붙을 하지않고(__오류도 많았기에__) 필요한 부분만 발췌해서 코드에 적용시켰고 그 과정중에서 반드시 이해하고 넘어갈려고 노력했습니다.
<br>
앞으로는 JPA, Qdsl을 다음 프로젝트에 쓸 에정이고 배포에 관한 것도 AWS, Docker 등 학습할 예정입니다.
<br>
SSR 언어가 아닌 CSR 언어랑 소통하기 위해서는 RestAPI에 대해서도 알아야 된다는 생각을 들었습니다.
또한 여러가지 아키텍처에 공부할 예정입니다.

## 프로젝트 기간
2024.11.25 ~ 2024.12.02, 2024.12.26(배포)

## 참여한 사람
[@DDubang@2](https://github.com/DDubang22)

## 사용 기술
- Spring Boot 3.4.0
- JDK 17
- Mysql 8.0.40
- MyBatis
- Thymeleaf
- Thymeleaf-layout
- Lombok
- BootStrap5
- HTML
- CSS

## 개발환경
- Mac
- Intellij
- Git

## 배포 환경
- AWS EC2
- AWS RDS
- linux server
  
## 모니터링 및 로깅
- CloudWatch

## 구상
로그인을 세션을 통하여 구현할 것이고 게시판의 기본적인 기능을 포함하며 만들어 볼예정입니다.

## ERD
![crudERD](https://github.com/user-attachments/assets/2f8e3698-210a-46c7-8f4f-2e5c9e78039d)


## 구현

### 화면
![screen](https://github.com/user-attachments/assets/6f7be8f3-14ce-4e94-9401-3bdd053c87c6)
전체적인 화면 구성은 심플하게 구성했습니다. 좌측 상단에 메인, 자유게시판으로 이동할 수 있는 하이퍼링크로 만들었습니다. 우측 상단에는 로그인, 계정생성 버튼으로 구성했습니다.
Content(중앙)에 레이아웃이 변경되도록 했습니다.

### 계정 생성
![screen](https://github.com/user-attachments/assets/5b93bf8e-7f96-47c4-b096-e92744af1c0b)
아이디, 비밀번호, 닉네임, 생일, 이메일 항목으로 구성했습니다. 생일은 <input/> 타입인 Date 형식으로 설정했습니다. 회원가입을 할 때 계정생성시간(userCreatedDate)을 추가해서 언제 만들었는지 확인가능합니다.

## 유효성 검사
<img src="https://github.com/user-attachments/assets/ed98d023-4aa5-4544-9a87-ade5e754bf63" height=45% width=45% /><br>
<input/> 기능 중에 required의 기능을 통해 유저단에서 검증할 수 있습니다.

## 튜플 생성
<img src="https://github.com/user-attachments/assets/1075482d-b805-4778-afc3-80b51064efbf" height=45% width=45% /><br>
DB에 값이 insert되는 것을 확인할 수 있습니다.

## 로그인
<img src="https://github.com/user-attachments/assets/62a29ed5-8bbd-49eb-a05a-2818f3f73cf8" height=45% width=45% /><br>
로그인할 때 사용자 편의성을 위해 클라이언트단에서 검증을 하지 않고 서버단에서 유효성 검사를 했습니다. 아이디나 비밀번호가 입력되지 않았을 때 경고성 메시지를 추가하도록 했습니다.
아이디 비밀번호가 입력되면 Mybatis(DB통신)를 통해 비밀번호가 DB에 있는 값이랑 일치하는지 검증하도록 했습니다. 일치하지 않으면 경고메시지가 등장합니다.
<br>
화면을 넘어가도 로그인을 유지하기 위해 세션을 통해 유지되도록 구현했습니다.

## 로그인 화면
![screen](https://github.com/user-attachments/assets/97347fd9-3c8c-4b04-bfed-7b1d1d7874a5)
로그인을 하면 닉네임을 우측상단에 표시됩니다. 또 세션유무에 따라 버튼들이 다르게 보일 수 있도로 구현했습니다. 로그아웃 버튼과 마이페이지로 버튼이 있습니다.

## 마이페이지 
<img src="https://github.com/user-attachments/assets/98c200ba-6284-4e7a-a4da-130a27b20e1c" height=45% width=45% /><br>
기본적으로 DB에서 값을 불러와 비밀번호를 제외한 항목을 표시했습니다. ID는 변경하지 못하고 닉네임, 생일, 비밀번호, 이메일은 변경할 수 있도록 했습니다.<br>
비밀번호는 기존 비밀번호와 새로운 비밀번호를 구분하여 입력할 수 있도록 구현했습니다.<br>

## 마이페이지 - 비밀번호 검증
<img src="https://github.com/user-attachments/assets/560e4ef9-dfbe-44b6-a4df-e6985870e207" height=45% width=45% /><br>
기존 비밀번호가 틀리면 "기존 비밀번호가 틀렸습니다" 메시지를 추가하도록 했습니다.

<img src="https://github.com/user-attachments/assets/35b877d1-21a3-4940-ac66-98d84042664e" height=45% width=45% /><br>
기존 비밀번호는 일치하고 새로운 비밀번호가 기존 비밀번호랑 일치하면 회원 수정할 수 없도록 구현했습니다. 기존 비밀번호와 새로운 비밀번호가 달라야 정상적으로 회원을 수정할 수 있습니다.


## 게시글 조회
<img width="1790" alt="스크린샷 2024-12-02 오후 4 48 03" src="https://github.com/user-attachments/assets/d314e51a-e663-4843-8983-9b3c8863f16e">
게시글을 번호 순서대로 확인할 수 있습니다. 제목을 클릭해서 게시글 상세보기로 넘어갈 수 있습니다.

## 페이징 처리
![Dec-02-2024 16-55-22](https://github.com/user-attachments/assets/9cd38df3-783e-4197-a9f2-675a84d7c36c)
페이징 처리를 해서 한번에 10개씩 게시글을 확인 가능합니다. 하단에 있는 페이지네이션 바는 데이터 개수에 따라 동적으로 변합니다.

## 게시글 작성
<img src="https://github.com/user-attachments/assets/8573492b-f5a5-4c19-a48c-119b655941b8" height=100% width=100% /><br>
제목과 글 내용, 작성자로 표시됩니다. 작성자는 세션에 있는 데이터를 활용하여 작성자에 표시된다.

## 게시글 편집
<img width="1119" alt="스크린샷 2024-12-02 오후 5 31 56" src="https://github.com/user-attachments/assets/49a1545c-3064-4142-8f52-4208cb05bfce">
게시글 편집할때 제목은 파란색으로 처리해서 수정할 수있게 직관적으로 처리했습니다.

## 게시글 삭제 - 작성자랑 로그인 회원이 다를 경우

![Dec-02-2024 17-43-03](https://github.com/user-attachments/assets/b0b9c7ea-d207-4624-ae6a-9a50c014059a)
현재 로그인한 회원과 작성자가 다를 경우 삭제가 되지 않게 구현했습니다. 세션에 있는 회원 데이터와 DB에 있는 작성자와 비교를 합니다

## 게시글 삭제 - 작성자랑 로그인 회원이 같을 경우

![Dec-02-2024 17-44-12](https://github.com/user-attachments/assets/6fc28a91-2cfe-4a03-80a6-2ac7c0110ef8)
로그인한 회원과 작성자가 같을 경우는 성공적으로 삭제가 됩니다.

## 서버 시간대
### nohup java -Duser.timezone=Asia/Seoul -jar [jarFileName].jar &
nohup 명령어를 이용해 백그라운드에서 작동하게 했습니다. Mysql 서버시간대, Linux 서버 시간대는 __KST 기준__ 정상 작동했지만, 앱 내에서는 정상 작동하지 않았습니다. -Duser.timezone=Asia/Seoul 옵션을 통해 애플리케이션 시간대를 설정했습니다.
### nohup.out
nohup.out 파일을 통해 로그를 확인할 수 있습니다

<br>
<br>

--- 

## 개선점

서블릿 필터를 통해 미래에 필요한 페이지도 whiteLabelPage가 뜨지 않게 처리<br>
회원가입 아이디 중복 검사<br>
아이디 찾기 및 비밀번호 찾기<br>
소셜로그인 구현(필수는 아니지만 추후에 다른 프로젝트에 할 예정)<br>
게시글 검색 기능(제목 특정 문구를 통해 검색하기) <br>
게시글 좋아요 구현<br>
게시글 조회수 구현<br>
파일 첨부 기능<br>

