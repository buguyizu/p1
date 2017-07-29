@echo off
:: http://www.h2database.com/html/tutorial.html

rem 2017/05/12 modify
rem h2*.jar to h2-1.3.176.jar

cd .
::jdbc:h2:tcp://localhost/db_namem
::sa 123
::origin password is null
java -cp h2-1.3.176.jar org.h2.tools.Server -baseDir . -ifExists -tcp -tcpAllowOthers -pg -pgAllowOthers -web -webAllowOthers
pause