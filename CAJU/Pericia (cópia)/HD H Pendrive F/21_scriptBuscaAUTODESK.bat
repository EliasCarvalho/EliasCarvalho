@echo off
echo "21_Busca por AUTODESK..."
setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *autodesk*/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *autodesk*/s 1>> F:\Pericia\busca_21_autodesk_%computername%.txt

endlocal
