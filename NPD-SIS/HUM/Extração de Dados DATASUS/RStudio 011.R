################# UNIFICANDO DATASETS

#---------- Adicionando colunas a um banco de dados

nome <- c("Antonio", "Maria", "Paulo", "João", "Mariana")
idade <- c(42, 35, 27, 46, 32)
renda <- c(1200, 2600, 5900, 1800, 6500)

socio <- data.frame(nome, idade, renda)
rm(nome, idade, renda)

imc <- c(24.5, 28.6, 31.8, 34.2, 22.2)
diabetes <- c("Não", "Não", "Sim", "Sim", "Não")
hipertensao <- c("Sim", "Não", "Sim", "Não", "Não")

clinica <- data.frame(imc, diabetes, hipertensao)
rm(imc, diabetes, hipertensao)

dados <- cbind(socio, clinica)

#---------- Adicionando linhas a um banco de dados

SIH2201 <- read.dbc::read.dbc("/home/ironman/Tutoriais/RDMT2201.dbc")
SIH2202 <- read.dbc::read.dbc("/home/ironman/Tutoriais/RDMT2202.dbc")

janitor::compare_df_cols_same(SIH2201, SIH2202)
janitor::compare_df_cols(SIH2201, SIH2202)

SIH <- rbind(SIH2201, SIH2202)
nrow(SIH2201) + nrow(SIH2202)
