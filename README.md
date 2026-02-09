Reflection 1

In this exercise, I built a simple product management application using Spring Boot and the MVC architecture. 
The application allows users to create, view, edit, and delete products. 
While implementing these features, I tried to follow the coding standards and clean code principles that are mentioned in this module and in class.

One principle that I applied is separation of concerns. 
The application logic is divided into model, repository, service, and controller layers. 
Each layer has its own responsibility, which makes the code easier to understand and maintain. 
For example, the controller only handles requests and responses, while the service contains the business logic and the repository manages the data.

I also tried to use clear and meaningful names for classes, methods, and variables. 
This makes the code more readable and helps others understand the purpose of each part without needing extra comments. 
The methods are kept small and focused, so each method only does one task.

For secure coding practices, the application uses a layered approach where data is not accessed directly from the controller. 
All operations go through the service and repository layers. 
In the delete feature, I used an iterator to remove products from the list, which avoids errors related to modifying a collection while iterating over it.

For improvement for the future, right now, the application does not validate user input, so it is possible to submit empty names or invalid quantities. 
This could be improved by adding input validation and proper error handling. 
In addition, the data is stored in memory, so it will be lost when the application stops. In the future, this can be improved by using a database.

Overall, this exercise helped me understand how applying clean code principles and proper structure can make an application easier to develop, read, and maintain.
