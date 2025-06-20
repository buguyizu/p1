@echo off
set MVNW_DIR=%~dp0
set PROPERTIES_FILE=%MVNW_DIR%\.mvn\wrapper\maven-wrapper.properties
set MAVEN_VERSION=3.8.7
set REPO_URL=https://repo.maven.apache.org/maven2
if exist "%PROPERTIES_FILE%" (
  for /F "usebackq tokens=1,2 delims==" %%A in (%PROPERTIES_FILE%) do set %%A=%%B
)
if defined distributionUrl (
  set MAVEN_URL=%distributionUrl%
  for %%A in (%distributionUrl%) do set MAVEN_VERSION=%%~nA
  set MAVEN_VERSION=%MAVEN_VERSION:apache-maven-=%%
  set MAVEN_VERSION=%MAVEN_VERSION:-bin=%%
  set MAVEN_VERSION=%MAVEN_VERSION:.zip=%
) else (
  set MAVEN_URL=%REPO_URL%/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip
)
set MAVEN_DIR=%MVNW_DIR%\.mvn\wrapper\apache-maven-%MAVEN_VERSION%
if not exist "%MAVEN_DIR%" (
  echo Downloading Maven %MAVEN_VERSION%...
  if exist "%MVNW_DIR%\wget.exe" (
    "%MVNW_DIR%\wget.exe" -O "%MVNW_DIR%/apache-maven-%MAVEN_VERSION%-bin.zip" %MAVEN_URL%
  ) else if exist "%MVNW_DIR%\curl.exe" (
    "%MVNW_DIR%\curl.exe" -L -o "%MVNW_DIR%/apache-maven-%MAVEN_VERSION%-bin.zip" %MAVEN_URL%
  ) else (
    echo Error: curl or wget required to download Maven
    exit /b 1
  )
  tar -xf "%MVNW_DIR%/apache-maven-%MAVEN_VERSION%-bin.zip" -C "%MVNW_DIR%\.mvn\wrapper"
)
"%MAVEN_DIR%\bin\mvn" %*
