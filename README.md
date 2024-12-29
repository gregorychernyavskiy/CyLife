# CyLife

CyLife is your all-in-one platform to bring students and staff together. Built with passion and a vision for better campus life, CyLife makes it easy for clubs to thrive and events to connect people. Whether you're organizing, chatting, or sharing, CyLife is here to make every interaction seamless and fun.

## Features

### Real-Time Communication
- **Chat WebSocket**: Facilitates real-time communication within clubs. Only users who are members of a specific club can view and participate in its chat.
- **Notification WebSocket**: Notifies users when a student joins a specific club and displays the chat history for that club.

### Event Management
- Clubs can create and manage events.
- Events automatically disappear after their scheduled time ends.

### User Management
- Users are identified by their email, which acts as a unique identifier.
- Each user has:
  - A database-generated user ID.
  - An email and password for authentication.
  - A user type (either `STUDENT` or `STAFF`).

## Technology Stack

### Backend
- Built with **Spring Boot**.
- Uses **MySQL** for database management.
- Key functionalities include WebSocket management for chats and notifications, and event lifecycle management.

### Frontend
- Web-based user interface for seamless interaction.
- Focused on intuitive navigation and usability.

### Database Design
- **User Table**: Stores user details including email, password, and type.
- **Clubs Table**: Manages club information.
- **Events Table**: Connected to Clubs, allowing them to create and manage events.

## How to Run the Project

### Prerequisites
1. Install Java 17.
2. Install Apache Maven.
3. Set up a MySQL database and ensure the connection configuration matches the project requirements.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/gregorychernyavskiy/CyLife.git
   ```
2. Navigate to the backend directory:
   ```bash
   cd CyLife/Backend
   ```
3. Ensure you are in the folder containing the `mvn` executable before building the project. Build the project using Maven:
   ```bash
   mvn clean install
   ```
   This will create the `.jar` file in the `target` folder.
4. Securely copy the `.jar` file to the server using `scp`:
   ```bash
   scp target/Backend-0.0.1-SNAPSHOT.jar your_username@coms-3090-065.class.las.iastate.edu:~
   ```
5. Access the server via SSH to launch the application:
   ```bash
   ssh your_username@coms-3090-065.class.las.iastate.edu
   ```
   Navigate to the directory where the `.jar` file was copied, and run the application:
   ```bash
   java -jar Backend-0.0.1-SNAPSHOT.jar
   ```
6. Test the application on the server or local deployment to ensure both frontend and backend connectivity.

## POM.xml Configuration
The `pom.xml` file is configured to use Java 17 and includes dependencies for:
- **Spring Boot**: Core framework for backend development.
- **MySQL Connector**: For database integration.
- **Spring WebSocket**: For real-time communication.
- **Spring Data JPA**: For object-relational mapping (ORM).
- **OpenAPI (SpringDoc)**: To generate API documentation.
- **JUnit and Rest-Assured**: For testing.
- **Lombok**: To simplify Java boilerplate code.

The `spring-boot-maven-plugin` is included for packaging the application as a `.jar` file, ensuring ease of deployment and compatibility with servers.

## Development Workflow

1. **Feature Branching**: Create a new branch for each feature or bug fix.
   ```bash
   git checkout -b feature/<feature-name>
   ```
2. **Code Quality**: Follow best practices and write unit tests for new features.
3. **Pull Requests**: Ensure pull requests are reviewed and approved before merging.

## Future Enhancements
- Add more granular role-based access controls.
- Integrate a mobile-friendly interface.
- Support advanced analytics for clubs and events.

## Contributors
- **Gregory Chernyavskiy** and team.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
