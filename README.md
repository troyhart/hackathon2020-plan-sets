# sets of plan steps

This is a dumb service that has a couple sets of plan steps.

To get the set of all plan set names:
```curl --request GET --url http://localhost:8080/plan-sets
```

To get a named set of steps:
```
curl --request GET --url http://localhost:8080/plan-sets/<set name>
```