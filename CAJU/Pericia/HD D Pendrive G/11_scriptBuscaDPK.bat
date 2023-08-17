@echo off
echo "11_Busca por DPKs..."
setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *.dpk/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.dpk/s 1>> G:\Pericia\busca_11_dpk_%computername%.txt

endlocal
