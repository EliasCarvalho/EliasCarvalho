rm(list=ls(all=TRUE))
#gc(reset=TRUE)
##################################################################
# Bibliotecas que são necessárias 
##################################################################
require(read.dbc) # para ler os arquivo DBC direto
require(parallel) # para executar as operações em múltplos núcleos
##################################################################
# seleção dos arquivos 
##################################################################
#############################
# Uso de expressão regulares#
#############################
# ^ início do texto
# RD.. RD e mais 2 caracteres quaisquer
# "1" tem que ter o número 1
# [1234567] tem que ter um dos números entre os colchetes
# .. 2 caracteres quaisquer
arquivos<-list.files(path = '/media/tura/DataLake/datasus/SIHSUS/200801_/Dados', pattern = '^RDPR....\\.dbc$', full.names = T,ignore.case=T) 

importa_AIH <- function(base){
	aux <- read.dbc:::read.dbc(base,as.is=T)
	aux <- aux[,c('CNES','ANO_CMPT','N_AIH','DT_INTER','DT_SAIDA','SEXO','COD_IDADE','MORTE','IDADE','DIAG_PRINC','QT_DIARIAS','VAL_TOT')]
	aux <- subset(aux,CNES == '2587335')
	return(aux)
	}
########################################
nucleos <- detectCores() - 1
clust <- makeCluster(nucleos)
aux <- parLapply(clust,arquivos,importa_AIH)
stopCluster(clust) 

aih_hospital <- do.call(rbind,aux)

