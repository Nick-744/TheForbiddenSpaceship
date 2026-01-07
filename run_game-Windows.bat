@echo off
REM First-Run Setup Script for The Forbidden Spaceship
cd %~dp0

REM 1. Clean previous builds to ensure a fresh start
if exist bin (
    echo Cleaning old build...
    rd /s /q bin
)

mkdir bin

REM 2. Compile using the -sourcepath flag
REM This tells Java where the root of your packages is.
echo Compiling Java files...
javac -d bin -sourcepath . mainPacket\MainClass.java

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b %errorlevel%
)

REM 3. Copy Resources
echo Copying resources...
if exist resources\images (
    xcopy resources\images bin\images /E /I /Y
)
if exist resources\audio (
    xcopy resources\audio bin\audio /E /I /Y
)

REM 4. Run the game from the bin directory
echo Running the game...
java -cp bin mainPacket.MainClass

pause