@echo off
:: http://www.h2database.com/html/tutorial.html

cd .
::jdbc:h2:tcp://localhost/db_namem
::sa 123
java -cp h2*.jar org.h2.tools.Server -baseDir . -ifExists -tcp -tcpAllowOthers -pg -pgAllowOthers -web -webAllowOthers
pause