# myblog

# Use Case
![UseCase](https://github.com/s2cocos2/myblog/assets/120706046/28f57f83-5cf8-4afa-872e-48f5e724d914)

# API 명세서
![image](https://github.com/s2cocos2/myblog/assets/120706046/235c55e2-6060-4e01-b64f-c399ae4c71d9)

# 답변
**1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)<br>**
   수정과 삭제 모두 body를 사용하였습니다.<br>
**2. 어떤 상황에 어떤 방식의 request를 써야하나요?<br>**
   param: 경로의 일부로 데이터를 전달하는 방식.<br>
   query: URL에 파라미터를 추가하여 데이터를 전달하는 방식.<br>
   body: 본문에 데이터를 포함시키는 방식.<br>
**3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?<br>**
   RESTful한 API를 설계했습니다. 엔드포인트는 리소스를 나타내는 명사 형태로 설계되었고, HTTP 메서드는 해당 작업의 의미에 맞게 사용되었습니다. 그러나 리소스에 대한 단일
   항목 조회를 위한 엔드포인트가 누락되었으며, 자원에 대한 필터링 또는 정렬을 지원하는 쿼리 파라미터가 누락되었습니다.<br>
**4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)<br>**
   적절한 관심사 분리를 적용하고 있습니다. Controller는 클라이언트의 요청을 처리하고, Repository는 데이터베이스와의 상호작용을 담당하며, Service는 비즈니스 로직을 담당합 니다.<br>
**5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!<br>**
   API의 동작 방식, 인증 방법, 예외 처리 등에 대한 설명 등이 포함되어 있지 않습니다.
