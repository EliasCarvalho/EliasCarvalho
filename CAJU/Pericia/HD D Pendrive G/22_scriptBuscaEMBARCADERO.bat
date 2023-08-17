@echo off
echo "22_Busca por EMBARCADERO.."
setlocal

rem change to the correct directory
cd /d d:\

rem count the files
dir /b *Embarcadero*/s 2> nul | find "" /v /c > %temp%\count
set /p _count=<%temp%\count

rem cleanup
del %temp%\count

rem output the number of files
echo Files found : %_count%

rem list the files
echo Files Paths :
dir /b *Embarcadero*/s 1>> G:\Pericia\busca_22_Embarcadero_%computername%.txt

endlocal
