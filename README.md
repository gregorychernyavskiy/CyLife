# CyLife

CyLife is a comprehensive platform designed to enhance collaboration and communication within clubs, organizations, and events at educational institutions. The project aims to create a dynamic and user-friendly ecosystem for students and staff to interact, share, and manage activities efficiently.

## Features

### Real-Time Communication
- **Chat WebSocket**: Facilitates real-time communication within clubs or organizations. Only users who are members of a specific club can view and participate in its chat.
- **Notification WebSocket**: Notifies users when a student joins a specific club and displays the chat history for that club.

### Event Management
- Clubs and organizations can create and manage events.
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
- **Organizations Table**: Manages organization data.
- **Events Table**: Connected to both Clubs and Organizations, allowing them to create and manage events.

## How to Run the Project

### Prerequisites
1. Install Java 17 or later.
2. Install Apache Maven.
3. Set up a MySQL database and configure connection details.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/gregorychernyavskiy/CyLife.git
   ```
2. Navigate to the backend directory:
   ```bash
   cd CyLife/Backend
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   java -jar target/Backend-0.0.1-SNAPSHOT.jar
   ```
5. Access the frontend by opening the appropriate entry point in your browser.

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
