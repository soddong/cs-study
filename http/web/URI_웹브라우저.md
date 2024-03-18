## URI와 웹 브라우저 요청 흐름

### 웹 (World Wide Web) 이란?

1. 인터넷으로 연결된 사용자들이 정보를 공유할 수 있는 공간.
    
    즉, 인터넷에서 사용되는 서비스 중 하나
    
2. HTML 이라는 문서 형태와, HTTP 라는 문서 전송 프로토콜, URL 로 문서의 위치 표시하는 시스템.

## URI / URL / URN

### URI (Uniform Resource Idenrifier)

- 정의
    
    자원의 식별자
    
- 구조
    
    ```
     scheme:[//[user[:password]@]host[:port]][/path][?query][#fragment]
    ```
    
    | scheme | 프로토콜 종류 (http, https, ftp, mailto) |
    | --- | --- |
    | user, password | 사용자 이름 , 비밀번호 (ftp 에서 사용됨) |
    | host, port | host (ip), 서버의 네티워크 포트 (http는 기본 80) |
    | path | 서버에서 리소스의 위치 |
    | query | 서버의 DB에서 요청받기 위한 리소스의 범위를 좁히기 위한 질의 (?이름=값) |
    | fragment | 특정 부분을 가리킴 (용량이 크거나 원하는 것이 명확할때) |

### URL (Uniform Resource Locator)

- 정의
    
    자원의 위치 (일반적으로 사이트 도메인을 의미)
    
- 구조
    
    ```
     scheme:[//[user[:password]@]host[:port]][/path]
    ```
    

### URN (Uniform Resource Name)

- 정의
    
    자원의 이름
    
- 구조
    
    ```
    urn:ietf:rfc:2648
    ```
    
- URN은 한 리소스에 대해 위치와 상관없이 유일하게 해당 리소스를 식별하는 이름
- 리소스가 이름에 매핑되어 있어야 하기 때문에 이름으로 부여하면 거의 찾기가 힘들다. 그래서 대부분 URL만 쓴다.

### 예시)

- **`https://example.com`**
    
    웹상의 자원을 가리키는 URL이며, 동시에 URI
    이 주소는 웹 서버 자체를 가리키며, 이 경우 URL이면서 URI
    
- **`https://example.com/skin`**
    
    "skin"은 서버상의 특정 자원의 위치를 나타내므로, 웹상의 특정 자원을 가리키는 URL이며, URI 
    
- **`https://example.com/one/two/abc.html`**
    
    "one/two/abc.html"은 서버상의 구체적인 파일 위치를 가리키므로, URL이며 URI
    
- **`https://example.com/one?id=123`**
    
    여기서 "?id=123"는 쿼리 스트링이며, 특정 자원을 더 구체적으로 식별하는 데 사용하므로, URL은 **`https://example.com/one`** 이며 URI은 **`https://example.com/one?id=123`**
    

### 안전하지 않은 문자

- 안전하지 않은 문자란?
    
    혼동을 일으킬 수 있는 문자 (예. 공백, 특수문자 (# % & ?), ASCII문자가 아닌것)
    
    | 문자 | 선점 및 제한 |
    | --- | --- |
    | % | 인코딩된 문자에 사용할 이스케이프 토큰으로 선점 |
    | / | 경로 컴포넌트에 있는 경로 세그먼트를 나누는 용도로 선점 |
    | . | 경로 컴포넌트에서 선점 |
    | .. | 경로 컴포넌트에서 선점 |
    | # | 프래그먼트의 구획 문자로 선점 |
    | ? | 질의 문자열의 구획 문자로 선점 |
    | ; | 파라미터의 구획 문자로 선점 |
    | : | 스킴, 사용자 이름/비밀번호, 호스트/포트의 구획 문자로 선점 |
    | $,+ | 선점 |
    | @&= | 특정 스킴에서 특별한 의미가 있기 때문에 선점 |
    | {}.~[]` | 게이트웨이와 같은 여러 전송 에이전트에서 불안전하게 다루기 때문에 제한 |
    | <>" | URL 범위 밖에서 역할이 있는 문자이기 때문에 반드시 인코딩해야 함 |
    | 0x00-0x1F, 0x7F | 인쇄되지 않는 US-ASCII 문자 |
    | 0x7F보다 큰 범위 | 7비트 US-ASCII 문자가 아님 |
- 처리 방법
    - 퍼센트 인코딩 → 이스케이프 문자
        
        % 다음에 두개의 16진수 숫자 가 오는 형태로 인코딩
        
    - 문자 제한

### [ 웹 브라우저 요청 흐름 ]

### 전체적인 흐름

1. 웹 브라우저에 [www.naver.com](http://www.naver.com) URL 입력
2. URL을 IP주소로 변환
    a. local 스토리지의 hosts 파일을 찾아봄. —> ip주소가 있다면 변환 끝
        - host 파일
            
            
            # Copyright (c) 1993-2009 Microsoft Corp.
            #
            # This is a sample HOSTS file used by Microsoft TCP/IP for Windows.
            #
            # This file contains the mappings of IP addresses to host names. Each
            # entry should be kept on an individual line. The IP address should
            # be placed in the first column followed by the corresponding host name.
            # The IP address and the host name should be separated by at least one
            # space.
            #
            # Additionally, comments (such as these) may be inserted on individual
            # lines or following the machine name denoted by a '#' symbol.
            #
            # For example:
            #
            #      102.54.94.97     rhino.acme.com          # source server
            #       38.25.63.10     x.acme.com              # x client host
            
            # localhost name resolution is handled within DNS itself.
            #	127.0.0.1       localhost
            #	::1             localhost
            216.58.200.14	naver.com
            
            
    b. DNS cache 참고 —> ip주소가 있다면 변환 끝
        - cache 확인 링크
            
            chrome://net-internals/#dns
            
            
    c. DNS 서버에 접속
        - DNS 서버에서 IP 찾는법
            
3. 해당 IP주소로 TCP/IP 연결
4. client에서 서버에 get method로 HTTP request
5. server에서 HTTP response