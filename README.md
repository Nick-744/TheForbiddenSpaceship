## **The Forbidden Spaceship**

A retro-inspired 2D space shooter game built in Java. Players navigate through levels, battle enemy spaceships, and experience animated cutscenes. Featuring dynamic backgrounds, custom sound effects, and a modular design for easy enhancements. Perfect for learning game development in Java or enjoying a classic arcade experience!

---

## How to Run

### Prerequisites:
1. Install Java Development Kit (JDK) version 8 or later.
2. Use a Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code) or prepare to run via the command line.
3. Ensure you have the project files downloaded or cloned.

---

### Steps to Run:

#### 1. Using an IDE:
- Open the project in your preferred Java IDE.
- Locate the main class file: `MainClass.java` (in the `mainPacket` package).
- Run the program:
  - Right-click `MainClass.java` and select "Run".
  - OR
  - Set up a Run Configuration for `MainClass` in your IDE.

#### 2. Using the Command Line:
- Open a terminal or command prompt.
- Navigate to the project directory:
  ```
  cd path/to/project
  ```
- Compile the Java files:
  ```
  javac -d bin -cp TheForbiddenSpaceship-main mainPacket\MainClass.java GUI\*.java spaceshipsPacket\*.java laserGun\*.java soundPacket\*.java
  ```
- Copy the `images` and `audio` folders from the `resources` directory to the `bin` directory:
  ```
  xcopy resources\images bin\images /E /I
  xcopy resources\audio bin\audio /E /I
  ```
- Run the main class:
  ```
  java -cp bin mainPacket.MainClass
  ```

---

### File Structure:
Ensure the `resources` folder (for images, audio) is correctly placed:

```
mainPacket/
GUI/
laserGun/
soundPacket/
resources/
  images/
  audio/
```

The `resources` folder must remain intact for the game to load assets.
