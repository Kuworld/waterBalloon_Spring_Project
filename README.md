<h1>WaterBalloon</h1>


<p align="center">
<img src="https://github.com/user-attachments/assets/2e17c8fd-a5f7-4e5d-9b05-02631df7c94c" with="300" height="300"/>
</p>

<h2> WarterBalloon Web Page </h2>

> 프로젝트 기간 : 2024.06 ~ 2024.07

<h3> 프로젝트 소개 </h3>

     휴대폰 어플리케이션을 웹 페이지로 만들어 보자는 생각에서 
     프로젝트를 시작하였습니다.
     
     사용자가 원하는 상품을 검색할 때 여러 유사 상품이 함께 
     검색되어 원하는 상품을 다시 찾아야 하는 번거로움을 해결
     하고자 시작되었습니다. 최대한 간편하게 원하는 상품을 
     찾을 수 있는 쇼핑몰을 개발하는 것이 목표입니다.

<h3> 목  표 </h3>

    간결한 디자인: 가독성을 높이고 직관적인 내비게이션 디자인을 통해 검색 편의성을 확보하고자 했습니다.

    상품 등록: 상품 옵션을 다양하게 하지 않고 카테고리를 간결히 하였습니다.

    간단한 검색: 최대한 간단한 동작으로 원하는 상품을 찾을수 있게 하였습니다.

## 시스템 구조도
> <img src="https://github.com/user-attachments/assets/2a2d2802-13cf-4b97-b8be-b3663fd5a356" width=900>
>
> * 퍼블릭페이지 : 로그인 없는 공개 페이지로, 누구나 접근할 수 있습니다.
>
> * 사용자페이지 : 사용자 전용 페이지로, 로그인 후 접근 가능하며, 개인화된 기능 제공합니다.
>
> * 관리자페이지 : 관리자가 시스템을 운영하고 관리할 수 있는 페이지, 사용자와 데이터 관리를 위한 기능을 포함합니다.





## 데이터 베이스

> ![image](https://github.com/user-attachments/assets/0677d374-1d52-46e3-8525-d5dc18c91b00)




## 🕹️ 주요기능
> 1. 상세보기 및 카테고리
>     * 카테고리별 상품 검색 가능합니다
>     * <img src="https://github.com/user-attachments/assets/bc1df4f1-1081-490d-bfc9-90b25cf17060" width="300"> 
>     
>     * 개별 상품의 상세 정보 페이지 제공(상품이미지, 설명, 가격 등)
>     * <img src="https://github.com/user-attachments/assets/6be6b85a-3435-4e1d-9c9d-7f2ec73b5b00" width="300">

> 2. 장바구니 기능
>     * 사용자들이 장바구니에 상품을 담을 수 있습니다.
>     * <img src="https://github.com/user-attachments/assets/0e287401-c006-43b9-912c-885d9bf5b549" width="300"> 
>   
>     * 장바구니에 담긴 상품의 총 금액 자동계산
>   
> 3. 사용자 인증, 권한 관리, 세션관리
>     * Spring Security를 사용해 사용자 로그인/ 회원가입 기능 구현
>     * BCryptPasswordEncoder로 비밀번호 해시화
>     * 중복 로그인 방지를 위해 한 계정당 하나의 세션만 허용
>     * 권한에 따라 일반 사용자와 관리자 역할 구분 (관리자는 상품 등록, 관리 가능, 회원 관리 가능)
>     * <img src="https://github.com/user-attachments/assets/9da24113-1e57-4af4-8d6b-408e500f9eac" width="300">
>     * <img src="https://github.com/user-attachments/assets/2dd756a8-96a2-4e6f-a1be-4dd91100a595" width="300">
>     * <img src="https://github.com/user-attachments/assets/2242dcc2-cd61-438b-9b16-0cfaeeab3e42" width="300">
> 4. 문의 및 답변 기능
>     * 사용자는 상품에 대한 문의를 남길 수 있으며 관리자는 답변을 달 수 있습니다
>     * 문의 등록 시간과 답변 시간이 표시됩니다다
>     * <img src="https://github.com/user-attachments/assets/0c31724c-0f1b-4629-ad29-9904566f9beb" width="300">

### 특징 
> ```HTML
>    <!-- Header fragment -->
>    <th:block layout:fragment="css"></th:block>
>    <th:block th:replace="~{fragment/header :: headerFragment}"></th:block>
>     
>    <!-- Content fragment -->
>    <th:block layout:fragment="content"></th:block>
>    <th:block th:replace="~{fragment/nav :: navFragment}"></th:block>
>    
>    <!-- Footer fragment -->
>    <th:block th:replace="~{fragment/footer :: footerFragment}"></th:block>
> ```
>  * Thymeleaf의 fragment 기능을 활용하여 사이트의 공통 레이아웃을 효율적으로 관리하였습니다. fragment를 사용하여 헤더, 푸터, 네비게이션 바와 같은 공통 요소를 독립적인 템플릿으로 분리하고, 각 페이지에서 이를 불러와 사용하는 방식으로 개발했습니다.
>    >
>    > *  공통 요소를 한 번만 정의하여 코드의 중복을 최소화 하였고, 공통 요소가 변경될 경우, 하나의 fragment 파일만 수정하면 모든 페이지에 적용 되도록 하여 유지보수가 용이하도록 노력하였습니다.
>    >
>    > *  일반 사용자와 관리자의 레이아웃을 나누어 관리자 로그인 시 관리자 전용 메뉴가 나타나도록 하였습니다.


> ```HTML
> <div sec:authorize="isAuthenticated() and !hasRole('ROLE_ADMIN')"><!-- 로그인 한 후 -->
>       <span th:text="${#authentication.principal.username} + ' 님'"></span>
>       &nbsp; | &nbsp; <a th:href="@{/user/cart}">장바구니</a>
>       &nbsp; | &nbsp; <a th:href="@{/user/mypage2}">마이페이지</a>
>       &nbsp; | &nbsp; <a th:href="@{/user/logout}">로그아웃</a>
> </div>
>                
> <div sec:authorize="hasRole('ROLE_ADMIN')"><!--admin 로그인 한 후 -->
>       <span th:text="${#authentication.principal.username} + ' 님'"></span>
>       &nbsp; | &nbsp; <a th:href="@{/}"> 홈 </a>
>       &nbsp; | &nbsp; <a th:href="@{/user/logout}">로그아웃</a>
> </div>
>                
> <div sec:authorize="isAnonymous()"><!-- 로그인 하기 전 -->
>    <a th:href="@{/user/login}">로그인</a>
>    &nbsp; | &nbsp; <a th:href="@{/user/join}">회원가입</a>
> </div>
> ```
> * 프로젝트에서 Thymeleaf와 Spring Security(sec:authorize)를 이용하여 사용자 인증 및 권한에 따라 동적으로 메뉴를 구성하는 부분을 구현하였습니다.
> 
>     > * 로그인 전: 비회원에게는 로그인 및 회원가입 버튼만 노출하여, 가입을 유도하고 불필요한 메뉴는 숨겼습니다.
>     >      > ![image](https://github.com/user-attachments/assets/f67489bd-22da-49a3-acee-31630b563859)
>     >  
>     >
>     >
>     > * 로그인 후 (일반 사용자): 로그인한 사용자에게는 장바구니, 마이페이지, 로그아웃 메뉴를 제공하여 개인화된 서비스를 이용할 수 있도록 했습니다.
>     >
>     >      > ![image](https://github.com/user-attachments/assets/e841fa6c-badd-497b-940e-5098d4f4e321)
>     >
>     >
>     > * 로그인 후 (관리자): 관리자는 일반 사용자와 다른 메뉴를 표시하여, 관리 기능에 쉽게 접근할 수 있도록 했습니다.
>     >      > ![image](https://github.com/user-attachments/assets/e6f4d3c0-addd-40b0-b6a6-f8f161ff9fac)




## 🤔 추후 개선 사항
> * 결제 시스템 : 현재 결제기능을 구현하지 못 하였습니다. 카카오페이 등  국내 PG사 연동 기능 추가계획이 있습니다.

> * 마이페이지 : 결제기능을 추가 함과 동시에 결제내역이 조회 가능하도록 결제내역 조회 기능 추가계획이 있습니다. 추가로 개인정보 수정 페이지 UX/UI 디자인 개선 계획이 있습니다.

> * 관리자 페이지 : 문의사항, 리뷰관리 기능 추가 계획이 있고, 회원관리, 상품관리 페이지 개선 계획이 있습니다.

     

     

 



<h3> Stack 🚀 </h3>



<h4>Environment</h4>

<img src="https://img.shields.io/badge/STS4-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">

<h4> Development </h4>

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

<h3> 화면구성 📺 </h3>

<h2> User </h2>

|메인페이지|상품상세 페이지|
|-|---|
|![image](https://github.com/user-attachments/assets/3e921ee9-4bbd-45bb-be3f-ed9ff5647809)|![image](https://github.com/user-attachments/assets/94714db7-fa19-472a-a8a1-61452585e1d4)|
|로그인 페이지|회원가입 페이지|
|---|---|
|![image](https://github.com/user-attachments/assets/fbcbad9b-cc9c-410f-b6f0-14e602ed4d83)|![image](https://github.com/user-attachments/assets/13efcc1b-97ab-47cc-bcc7-52b958c4777e)|

<h2> Admin </h2>

|회원관리|상품관리|
|---|---|
|![image](https://github.com/user-attachments/assets/34ea233d-52fb-4542-a2fc-44834f997c88)|![image](https://github.com/user-attachments/assets/9ac604f4-aab6-48ec-858c-22d04f727b2e)|
