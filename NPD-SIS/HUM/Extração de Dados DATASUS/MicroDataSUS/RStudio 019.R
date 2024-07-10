############## CORRELAÇÃO

# Existe correlação entre ensino superior e convocação voluntária entre mesários em 2022?

#------- Pacotes

library(tidyverse)
library(DescTools)
library(ggstatsplot)

options(scipen = 999)

#------- Dados

df <- read.csv2("~/Downloads/convocacao_mesarios_2022_BRASIL.csv", encoding = "latin1")

#------- Explorando o dataset

glimpse(df)

#------- Total de mesários

sum(df$QT_MESARIOS_PERFIL)

#------- Agregação

df <- df %>% 
  group_by(SG_UF) %>% 
  summarise(Mulheres = sum(QT_MESARIOS_PERFIL[DS_GENERO == "FEMININO"]),
            Superior = sum(QT_MESARIOS_PERFIL[DS_GRAU_ESCOLARIDADE == "SUPERIOR COMPLETO"]),
            Voluntario = sum(QT_MESARIOS_PERFIL[TP_CONVOCACAO == "Voluntárias e voluntários"]),
            Solteiro = sum(QT_MESARIOS_PERFIL[DS_ESTADO_CIVIL == "SOLTEIRO"]))

#------- Teste de normalidade

shapiro.test(df$Solteiro)

#------- Correlação

Desc(df$Voluntario ~ df$Superior)

#------- Gráfico de dispersão

ggscatterstats(data = df,
               y = Voluntario,
               x = Superior,
               type = "np")

#------- Matriz de correlação

ggcorrmat(data = df,
          type = "np")
