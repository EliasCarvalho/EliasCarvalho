@echo off
echo "12_Busca por PRTs..."
setlocal

rem change to the correct directory
cd /d H:\

rem count the files
dir /b *.prt/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.prt/s 1>> F:\Pericia\busca_12_prt_%computername%.txt

endlocal
