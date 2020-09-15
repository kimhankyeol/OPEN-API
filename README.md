# OPEN-API
API 예제를 다루는 Repository


## 1.KAKAO VISION API
얼굴 검출 <br>
OPEN-API/kakao/vision/src/kakaovisionapi.java

카카오 REST 호출할 공통클래스 <br>
CurlToJavaRestConverter.java 생성자 오버로딩을 이용하여 구현 <br>
KakaoVisionCall.java 에서 CurlToJavaRestConverter.java 생성자 만들고 restAPI() 호출
