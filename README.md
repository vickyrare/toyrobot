# ToyRobot

###Maven setup
1. mvn clean install
2. mvn spring-boot:run 

e.g usage
```
POST http://localhost:8080/api/command HTTP/1.1
Content-Type: application/json

{
    "command" : "PLACE 4,4,SOUTH MOVE RIGHT MOVE LEFT MOVE RIGHT MOVE LEFT MOVE RIGHT MOVE LEFT MOVE RIGHT MOVE REPORT"
}
```

