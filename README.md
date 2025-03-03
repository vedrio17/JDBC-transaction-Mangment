# JDBC Image Handling Project

## Overview
This project demonstrates how to handle images using JDBC (Java Database Connectivity) with MySQL. It includes inserting and retrieving images from the database, along with transaction management and prepared statements.

## Features
- Insert images into a MySQL database using JDBC
- Retrieve images from the database and display them
- Use `PreparedStatement` for efficient query execution
- Implement transaction management for database operations

## Technologies Used
- Java
- JDBC (Java Database Connectivity)
- MySQL
- MySQL Connector/J

## Prerequisites
Before running the project, ensure you have the following installed:
- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J (JDBC Driver)

## Setup and Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/jdbc-image-handling.git
   ```
2. Open the project in your preferred IDE (Eclipse, IntelliJ, etc.).
3. Add MySQL Connector/J to the classpath.
4. Update database configurations in the necessary files:
   ```java
   // Database Configuration
   private static final String URL = "jdbc:mysql://localhost:3306/your_database";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";
   ```
5. Run the respective files for different functionalities:
   - `imageinsertionJDBC.java` → Insert images into the database
   - `imageretriveJDBC.java` → Retrieve and display images
   - `preperdprac.java` → Use prepared statements for database operations
   - `transationmange.java` → Manage transactions efficiently

## Usage
- **Image Insertion:** Uploads images to the database.
- **Image Retrieval:** Fetches and displays images.
- **Prepared Statements:** Executes queries securely.
- **Transaction Management:** Ensures atomic operations.

## Example Code
### Inserting an Image
```java
String query = "INSERT INTO images (image_name, image_data) VALUES (?, ?)";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setString(1, "example.jpg");
FileInputStream fis = new FileInputStream("example.jpg");
pstmt.setBinaryStream(2, fis, (int) new File("example.jpg").length());
pstmt.executeUpdate();
```

### Retrieving an Image
```java
String query = "SELECT image_data FROM images WHERE image_name = ?";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setString(1, "example.jpg");
ResultSet rs = pstmt.executeQuery();
if (rs.next()) {
    InputStream is = rs.getBinaryStream("image_data");
    FileOutputStream fos = new FileOutputStream("retrieved.jpg");
    byte[] buffer = new byte[1024];
    while (is.read(buffer) > 0) {
        fos.write(buffer);
    }
}
```

## Contributing
Feel free to contribute to this project by submitting pull requests or reporting issues.

## License
This project is licensed under the MIT License.

## Contact
For any inquiries, reach out at [your email or GitHub profile].
