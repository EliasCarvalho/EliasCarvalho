@echo off

rem Muda o local de execução do script para a pasta desejada. 

pushd C:\

rem Faz a busca em todos os arquivos da pasta e da subpasta desejados. 
rem Para buscar mais de um arquivo adicione a opção /s novamente seguida 
rem do nome do arquivo.
rem Caso não saiba a extensão ou o nome todo do arquivo use * antes ou 
rem depois do nome, em parte ou em todo o nome da extensão também.   
rem No exemplo abaixo, caso encontre os arquivos especificados, ele pula 
rem para a linha printresult. Caso não encontre vá para o notfound.

dir /s /a-d "setup.txt" /s "setup2.txt" >nul && (goto printresult) || (goto notfound)

:printresult
rem Ao encontrar os arquivos o script produz um log em txt na pasta 
rem apontada, usando o nome da máquina como nome do arquivo em frente à
rem palavra FOUND.

dir /s /a-d setup.txt /s setup2.txt >>"\\servidor\pastadelogs\FOUND_%computername%.txt

rem A opção abaixo retorna o prompt para o local inicial

popd
exit

:notfound
rem Caso não encontre os arquivos o script gera um log usando o nome da 
rem máquina como nome do arquivo em frente à palavra NOTFOUND. 

echo "Este PC não possui os arquivos" >>"\\servidor\pastadelogs\NOTFOUND_%computername%.txt

rem A opção abaixo retorna o prompt para o local inicial

popd
exit
