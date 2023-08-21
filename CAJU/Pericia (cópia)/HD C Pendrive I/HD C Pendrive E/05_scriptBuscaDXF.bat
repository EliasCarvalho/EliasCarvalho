@echo off
echo "05_Busca por DXFs..."
setlocal

rem change to the correct directory
cd /d c:\

rem count the files
dir /b *.dxf/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *.dxf/s 1>> e:\Pericia\busca_05_dxf_%computername%.txt

endlocal
