@echo off
setlocal

REM -------------------------
REM Build & Push workflow for IC2Classic (Windows .bat)
REM Save this file as build_and_push_workflow.bat in project root (where build.gradle is).
REM Run in cmd.exe to execute.
REM -------------------------

REM Check we're in project root
if not exist "build.gradle" (
  echo ERROR: build.gradle not found. Run this script from the project root folder.
  pause
  exit /b 1
)

REM Make sure git is available
git --version >nul 2>&1
if errorlevel 1 (
  echo ERROR: git not found in PATH.
  pause
  exit /b 1
)

set "BRANCH=1.20.1"

REM Check/create branch
git show-ref --verify --quiet refs/heads/%BRANCH%
if %ERRORLEVEL%==0 (
  echo Branch %BRANCH% exists. Checking out...
  git checkout %BRANCH% || goto gitfail
) else (
  echo Creating branch %BRANCH%...
  git checkout -b %BRANCH% || goto gitfail
)

REM Ensure workflows dir exists
if not exist ".github\workflows" (
  mkdir ".github\workflows"
)

REM Write workflow file reliably using grouped ECHO
(
echo name: Build IC2Classic
echo.
echo on:
echo   push:
echo     branches: [ '1.20.1' ]
echo   workflow_dispatch: {}
echo.
echo jobs:
echo   build:
echo     runs-on: ubuntu-latest
echo.
echo     steps:
echo       - name: Checkout
echo         uses: actions/checkout@v4
echo.
echo       - name: Set up Java 21
echo         uses: actions/setup-java@v4
echo         with:
echo           distribution: temurin
echo           java-version: '21'
echo           cache: gradle
echo.
echo       - name: Grant execute permission for gradlew
echo         run: chmod +x ./gradlew
echo.
echo       - name: Refresh dependencies and generate runs
echo         run: ./gradlew --no-daemon --refresh-dependencies genIntelliJRuns prepareRuns
echo.
echo       - name: Build
echo         run: ./gradlew --no-daemon build --stacktrace
echo.
echo       - name: Upload artifacts
echo         uses: actions/upload-artifact@v3
echo         with:
echo           name: ic2classic-jar
echo           path: build/libs/*.jar
) > ".github\workflows\build.yml"

if %ERRORLEVEL% neq 0 (
  echo ERROR: Failed to write workflow file.
  pause
  exit /b 1
)

REM Stage, commit and push
git add .github\workflows\build.yml
git commit -m "CI: add workflow to build and upload IC2Classic jar (1.20.1)" || (
  echo Note: nothing to commit or commit failed.
)

echo Pushing branch %BRANCH% to origin...
git push -u origin %BRANCH% || goto gitfail

REM Optional: run local gradle prepare + build (only if gradlew present)
if exist "gradlew.bat" (
  echo Running local gradle preparation and build...
  call gradlew.bat --no-daemon --refresh-dependencies genIntelliJRuns prepareRuns
  if %ERRORLEVEL% neq 0 goto gradlefail
  call gradlew.bat build --stacktrace
  if %ERRORLEVEL% neq 0 goto gradlefail
  echo Local build finished. Check build\libs\ for jar.
) else (
  echo gradlew.bat not found — skipping local build. Workflow has been pushed and GitHub Actions will run.
)

echo DONE: workflow pushed and (if gradle present) built locally.
echo Check GitHub Actions at: https://github.com/%USERNAME%/%REPO%/actions (replace with your repo path)
pause
exit /b 0

:gitfail
echo ERROR: Git command failed. Check that you have push access and network connectivity.
pause
exit /b 1

:gradlefail
echo ERROR: Gradle build failed. Inspect output above to see compile errors.
pause
exit /b 1