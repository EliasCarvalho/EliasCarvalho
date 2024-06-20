#------------ Definindo Diretório de Trabalho

setwd("/home/epihoffmann/YouTube/Tutoriais")

#------------ Importando Banco de Dados CSV

df <- readr::read_csv2("SGMT.csv")

#------------ Importando Banco de Dados Stata

df <- foreign::read.dta("HAD.dta")
df <- rio::import("HAD.dta")

#------------ Importando Banco de Dados SPSS

df <- foreign::read.spss("Tese.sav")
df <- rio::import("Tese.sav")
