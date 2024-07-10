#----------- Pergunta

# Qual foi o risco de óbito entre pacientes idosos hospitalizados 
# por infarto agudo do miocárdio em São Paulo em janeiro de 2022?

#----------- Pacotes

library(tidyverse)
library(microdatasus)
library(DescTools)
library(gtsummary)

options(scipen = 999)

#----------- Dados

df <- fetch_datasus(information_system = "SIH-RD",
                    year_start = 2022, month_start = 1,
                    year_end = 2022, month_end = 1,
                    uf = "SP")

#----------- Processamento

df <- process_sih(df)

glimpse(df)
Desc(df$MORTE, plotit = FALSE)

df <- df |> 
  filter(COD_IDADE == "Anos",
         IDADE >= 18,
         DIAG_PRINC == "I219") |> 
  mutate(FAIXA = case_when(IDADE < 60 ~ "18 a 59 anos",
                           IDADE >= 60 ~ "60 anos ou mais")) |> 
  select(SEXO, FAIXA, MORTE)

#----------- Análise Bivariada

Desc(df$FAIXA ~ df$MORTE, plotit = FALSE)

df$FAIXA <- factor(df$FAIXA, levels = c("60 anos ou mais", "18 a 59 anos"))
