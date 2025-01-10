@echo off
REM First-Run Setup Script for The Forbidden Spaceship

REM Navigate to the project directory
cd %~dp0

REM Check if bin directory exists
if not exist bin (
    echo "bin directory not found. Creating and compiling..."
    mkdir bin
    REM Compile Java files
    echo Compiling Java files...
    javac -d bin -cp TheForbiddenSpaceship-main mainPacket\MainClass.java GUI\*.java spaceshipsPacket\*.java laserGun\*.java soundPacket\*.java
    if %errorlevel% neq 0 (
        echo Compilation failed. Please check for errors.
        exit /b %errorlevel%
    )
) else (
    echo "bin directory already exists. Skipping compilation."
)

REM Check if resources are copied
if not exist bin\images (
    echo "Resources not found in bin. Copying resources..."
    xcopy resources\images bin\images /E /I
    xcopy resources\audio bin\audio /E /I
) else (
    echo "Resources already copied. Skipping resource setup."
)

REM Run the game
echo Running the game...
java -cp bin mainPacket.MainClass
if %errorlevel% neq 0 (
    echo Failed to run the program. Please check for errors.
    exit /b %errorlevel%
)

pause
