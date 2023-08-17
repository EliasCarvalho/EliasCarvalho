@echo off
echo "06_Busca por RVTs..."
setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *.rvt/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.rvt/s 1>> F:\Pericia\busca_06_rvt_%computername%.txt

endlocal
