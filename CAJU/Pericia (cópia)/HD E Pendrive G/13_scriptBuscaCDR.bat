@echo off
echo "13_Busca por CDRs..."
setlocal

rem change to the correct directory
cd /d E:\

rem count the files
dir /b *.cdr/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.cdr/s 1>> G:\Pericia\busca_13_cdr_%computername%.txt

endlocal
