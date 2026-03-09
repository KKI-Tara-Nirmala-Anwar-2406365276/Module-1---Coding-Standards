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

Reflection Module 4.2
1. Code quality issues that I fixed and how I fixed them

During this exercise, the code analysis workflow detected issues related to code quality and test coverage. 
One main issue I fixed was the lack of unit tests in the service layer. 
To solve this, I added a new unit test (`ProductServiceImplTest`) to test the core logic of the product service, 
such as creating and retrieving products.
I focused on fixing issues by first checking the workflow output, then locating the exact part of the code that caused the warning. 

2. Reflection on CI/CD implementation

I think the current implementation already meets the definition of Continuous Integration and Continuous Deployment.
Every time I push code to the repository, GitHub Actions automatically runs the test suites and performs code quality analysis. 
This means code changes are continuously checked and validated, which matches the idea of Continuous Integration.
For deployment, the application is connected to a PaaS (Render) that automatically redeploys the app when changes are pushed to the main branch. 
This means deployment happens without manual steps, which satisfies Continuous Deployment.

Reflection 3
1. Principles that I applied

Single Responsibility Principle (SRP)
I applied SRP by separating CarController from ProductController.
Before refactoring, both product and car logic were placed in the same file, which means the class had more than one responsibility.
After the refactor, each controller only handles one domain.
ProductController handles product features, and CarController handles car features.
Now each class has only one reason to change.

Dependency Inversion Principle (DIP)
I applied DIP by making the controller depend on the CarService interface instead of the concrete `CarServiceImpl` class.
This means the high-level module (controller) depends on an abstraction.
The implementation can be changed without affecting the controller.
For example, if I later change the service implementation to use a database instead of in-memory storage, the controller does not need to change.

Interface Segregation Principle (ISP)
I applied ISP by splitting CarService into smaller interfaces: CarReadService and CarWriteService.
Instead of having one large interface, I separated read operations and write operations.
This makes the interfaces more specific and focused.
Classes only depend on the methods they actually need.

Open Closed Principle (OCP)
The project follows OCP because new features can be added by extending the system instead of modifying existing logic.
For example, the Car module was added by creating new classes (model, repository, service, controller, and templates) without changing the existing product feature.
The system is open for extension but does not require modification of existing modules.

Liskov Substitution Principle (LSP)
Although there is no complex inheritance structure in this project, the design supports substitutability.
For example, CarServiceImpl can be replaced with another implementation of CarService without affecting the controller.
This keeps the behavior consistent and maintains correctness.

2. Advantages of applying SOLID principles
Applying SOLID makes the code easier to understand and maintain. 
- With SRP, each class has a clear responsibility, so it is easier to debug and modify. 
- With DIP, the controller is not tightly coupled to implementation details. 
- With ISP, interfaces are cleaner and not overloaded with unnecessary methods. 
- With OCP, new features can be added without breaking existing functionality.

Overall, the project becomes more structured and scalable.

3. Disadvantages of not applying SOLID principles
If SOLID principles were not applied, the code would become harder to manage as the project grows.
- Without SRP, classes would become large and difficult to maintain. 
- Without DIP, changing implementations would require modifying many parts of the code. 
- Without ISP, interfaces could become too complex and force classes to implement methods they do not use. 
- Without OCP, adding new features would require modifying existing classes, increasing the risk of bugs.

Reflection 4
1. Percival
In this module I followed the Test Driven Development workflow while implementing the Order feature, including the model, repository, and service layers. 
The process followed the RED–GREEN–REFACTOR cycle where tests are written first, then the code is implemented until the tests pass, and finally the code is improved. 
Based on the self reflective questions proposed by Percival (2017), I think this TDD flow is useful because it forces me to clearly define the expected behavior before writing the implementation. 
The tests act as a guide for development and help ensure that the logic works as intended.

During the exercise, writing the tests first made it easier to understand what each method should do, such as creating an order, updating the order status, and retrieving orders. 
When running the tests, I could immediately see which parts of the implementation were incorrect. 
This helped me detect mistakes earlier and made debugging more focused. 
The use of mocking in the service tests also helped isolate the service logic from the repository layer.

However, using TDD also requires careful planning of the test scenarios. 
If the tests do not fully represent the required behavior, the implementation may still contain logical problems even though all tests pass. 
In the future, when writing tests, I should think more about edge cases and invalid inputs before implementing the code so that the tests better represent real usage conditions.

2. F.I.R.S.T
The unit tests written in this tutorial generally follow the FIRST principle. 
The tests are fast because they only test small parts of the application and use in memory data instead of external systems. 
For the service layer, Mockito is used to mock the repository, which keeps the tests lightweight and quick to run.

The tests are independent because each test case prepares its own data in the setup method. 
This prevents tests from affecting each other. 
They are also repeatable because the environment is reset before every test, which means the tests produce the same results every time they are executed.

The tests are self validating because they use assertions such as assertEquals, assertNull, and assertThrows to automatically determine whether the expected behavior occurs. 
This removes the need for manual verification.

The tests are also timely because they were written before implementing the logic, following the TDD workflow where the expected behavior is defined first and the implementation is created afterward.

Although the tests already follow the FIRST principle fairly well, there is still room for improvement. 
Some setup logic is repeated when creating products and orders, which could be simplified using helper methods or shared test utilities. 
This would make the tests cleaner and easier to maintain in the future.