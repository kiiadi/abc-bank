@echo off
setlocal
set path="c:\Program Files\Java\jdk1.8.0_25\bin";%path%
..\tools\sbt\bin\sbt %*
endlocal