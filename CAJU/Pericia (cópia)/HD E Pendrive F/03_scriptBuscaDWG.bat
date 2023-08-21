@echo off
echo "03_Busca por DWGs..."
setlocal

rem change to the correct directory
cd /d H:\

rem count the files
dir /b *.dwg/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.dwg/s 1>> F:\Pericia\busca_03_dwg_%computername%.txt

endlocal
