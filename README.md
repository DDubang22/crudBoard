# 게시판 만들기 미니 프로젝트

## 프로젝트 소개
스프링 부트를 사용하여 세션을 이용한 회원가입이 있고 Create, Read, Update,Delete (CRUD) 기능을 가진 게시판을 구현하는 프로젝트입니다.

## 프로젝트 기간
2024.11.25 ~ 2024.11.27

## 참여한 사람
[@DDubang@2](https://github.com/DDubang22)

## 사용 기술
- Spring Boot 3.4.0
- JDK 17
- Mysql 8.1.0
- myBatis
- thymeleaf
- lombok
- BootStrap5
- HTMl
- CSS

## 개발환경
- Mac
- Intellij
- Git

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


