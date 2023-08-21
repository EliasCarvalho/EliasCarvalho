@echo off
echo "01. Busca por Cracks..."
rem setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *crack*/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *crack*/s 1>> F:\Pericia\busca_01_crack_%computername%.txt

rem endlocal
