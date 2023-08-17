@echo off
echo "00_Busca por Seriais..."
rem setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *serial*/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *serial*/s 1>> e:\Pericia\busca_00_serial_%computername%.txt

rem endlocal
