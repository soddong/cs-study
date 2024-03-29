## HTTP 란?
### HTTP 의 정의
* HTML (Hyper Text Markup Language) 는 초월 텍스트라는 의미로, HTTP란 텍스트뿐 아니라 그림, 동영상 등을 포함하는 전송 프로토콜 이다. 
* 즉, HTML와 같은 문서를 전송하기위한 Application Layer에서 사용하는 규칙을 정의한 것.

### 어떻게 주고 받는가
* Request (요청) : 서버 <- 클라이언트(브라우저)
* Response (응답) : 서버 -> 클라이언트(브라우저)

### 무엇을 주고 받는가
* 프로토콜을 구성하는 하위 문서들
* ex. 텍스트, 레이아웃 설명, 이미지, 비디오, 스크립트

## HTTP와 HTTPS의 차이(TLS)
* TLS (Transport Layer Security) : 전송하고자 하는 것에 암호화를 더하여 기밀성을 보장하고자 할때 사용되는 기술

* HTTPS (HTTP Secure) : TLS을 거친 HTTP.

* 따라서 HTTP / HTTPS의 차이점은 보안계층인 TLS (혹은 SSL) 를 거쳤느냐 거치지 않았느냐 이다.


## 클라이언트-서버 모델
* 2-tier Architecture   
    * 구조 : 클라이언트 <-> 서버   
    * 의미 : 리소스를 사용하는 클라이언트와 리소스를 제공하는 서버를 분리시키는 모델을 뜻한다.

* 3-tier Architecture
    * 구조 : 클라이언트 <-> 서버 <-> DB
    * 의미 : 서버가 DB에 리소스를 저장하는 구조로, 서버는 리소스를 전달하는 역할만 한다. 대부분 요 구조.

* 브라우저   
클라이언트가 이용하는 도구. 브라우저를 통해 클라이언트가 서버에게 데이터를 요청하고, 서버가 클라이언트에게 데이터를 제공한다.

## Stateless와 Stateful
* Stateful   
    * 클라이언트의 상태 정보를 서버에서 저장함으로써 클라이언트와 서버간의 통신을 유지하는 것. 즉, 상태관리의 주체는 서버

    * How?   
    브라우저의 쿠키(Cookie)에 저장되거나, 서버의 세션(Session) 메모리에 저장

* Stateless   
    * 클라이언트의 상태 정보를 서버에서 자장하지 않고, 단순히 요청에 대한 응답만 보내게 됨.
    * 즉, 상태관리의 주체는 클라이언트