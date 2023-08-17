@echo off
echo "09_Busca por DCUs..."
setlocal

rem change to the correct directory
cd /d c:\

rem count the files
dir /b *.dcu/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.dcu/s 1>> F:\Pericia\busca_09_dcu_%computername%.txt

endlocal
