@echo off
echo "08_Busca por RTEs..."
setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *.rte/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.rte/s 1>> F:\Pericia\busca_08_rte_%computername%.txt

endlocal
