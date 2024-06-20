### IMPORTAÇÃO DE BASE DE DADOS DO DATASUS

#------------- Instalando o pacote

install.packages("read.dbc")

#------------- Realizando a importação

SINASC <- read.dbc::read.dbc("/home/epihoffmann/Tutoriais/DNMT2020.dbc")
