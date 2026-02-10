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

Reflection 2

After writing the unit tests, I felt more confident about my code.
Testing helped me see whether each feature actually worked as expected, especially for cases like editing or deleting a product that does not exist.

There is no fixed number of unit tests that must be written in a class. 
What matters is that all important logic is tested. 
In this exercise, I tested both positive and negative scenarios to make sure the program behaves correctly in different conditions. 
To check whether tests are sufficient, code coverage can be used to see how much of the code is tested. 
However, having 100% code coverage does not mean the program has no bugs. 
Code coverage only shows that the code was executed, not that the logic is always correct.

If I were to create another functional test to verify the number of items in the product list, the code would likely repeat the same setup logic as the previous functional tests. 
This causes duplicated code, which reduces code cleanliness and makes maintenance harder. 
If something changes in the setup, it would need to be updated in multiple places.
To improve this, the shared setup code can be moved into a base functional test class or helper methods. 
This reduces duplication and makes the functional tests easier to read, maintain, and extend in the future.
