spring-one-jar with specs2 testing
==============

- curl http://localhost:8080/rest/api/people -X POST -d "email=a@b.com&firstName=Tom&lastName=Knocker"
- curl http://localhost:8080/rest/api/people/a@b.com -X PUT -d "firstName=Tommy"
- curl http://localhost:8080/rest/api/people/a@b.com -X DELETE
- curl http://localhost:8080/rest/api/people
