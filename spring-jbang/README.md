# spring-jbang
Rest Spring boot application on a Jbang script

https://github.com/gomezrondon/spring-jbang/tree/master

https://www.makariev.com/blog/how-to-build-spring-boot-monolith-with-jbang/

https://www.jbang.dev/documentation/guide/latest/usage.html

## Installation

### install jbang
 ```
❯ brew install jbangdev/tap/jbang
 ```

### execute the script
 ```
jbang com/springjbang/Application.java
 ```

### Then execute 

 ```
http://localhost:8080/file
 ```

## Testing

```bash
❯ curl http://localhost:8080/file --dump-header -
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 26
Date: Wed, 08 May 2024 17:10:32 GMT

2024-05-09T00:10:32396100% 

❯ curl http://localhost:8080/file --dump-header - -H 'Accept: */*'
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 26
Date: Wed, 08 May 2024 18:15:23 GMT

2024-05-09T01:15:23.585396%  

  
❯ curl http://localhost:8080/file --dump-header - -H 'Accept: application/zip+base64'
HTTP/1.1 200 
Content-Type: application/zip+base64;charset=UTF-8
Content-Length: 36
Date: Wed, 08 May 2024 17:12:10 GMT

MjAyNC0wNS0wOVQwMDoxMjoxMC44ODY0MzQ=%  


❯ curl http://localhost:8080/file --dump-header - -H 'Accept-Override: application/zip+json'
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 41
Date: Wed, 08 May 2024 18:19:24 GMT

Accept-Override: 2024-05-09T01:19:24.836489% 

❯ curl http://localhost:8080/file2 --dump-header - -H 'Accept-Override: application/zip+json'
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 48
Date: Thu, 09 May 2024 00:51:54 GMT

application/zip+json: 2024-05-09T07:51:54.780823%       
❯ curl http://localhost:8080/file2 --dump-header - -H 'Accept: application/zip+json'
HTTP/1.1 200 
Content-Type: application/zip+json
Content-Length: 34
Date: Thu, 09 May 2024 00:52:04 GMT

Accept: 2024-05-09T07:52:04.275446%          
```
