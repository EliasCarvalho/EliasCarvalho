@echo off
echo "02_Busca por Keygens..."
setlocal

rem change to the correct directory
cd /d c:\

rem count the files
dir /b *keygen* /s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *keygen* /s 1>> d:\Pericia\busca_02_Keygen%computername%.txt

endlocal
