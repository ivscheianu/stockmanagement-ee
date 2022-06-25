# Stock Management - Java EE

---

###Ok, you can handle Spring, but let's try Java EE! This is my first App using Java EE :). 

It's obviously a pet project, so don't expect to have everything handled (things like users being able to edit each other's stocks and so on, but I probably handled at least some of them, or made it pretty easy to be fixed).

Features: 
- CRUD ready flows: you just need to extend ```AbstractEntityController```, ```AbstractEntityService```, ```AbstractEntityMapper```, ```AbstractEntityRepository```  parameterize them, and you are done!
- Secured App (authentication and authorization) using JWT. Additionally, ```UserService``` contains methods that offer you context about the current user. This way, you will always know who is asking for what. For example, you can prevent current user to access a product or a stock which do not belong to him.
- Integrated with AWS

You can explore it further, I omitted a lot of interesting stuff. For an easier interaction with the API, here's a [Postman collection](https://github.com/ivscheianu/stockmanagement-ee/blob/master/stock-management.postman_collection.json) (the app also has Swagger).
