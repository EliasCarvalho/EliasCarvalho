#----------- Passo 1 - Carregar pacotes

library(ggplot2)

#----------- Passo 2 - Gerar dados de exemplo

dados <- data.frame(pacientes = c(30,60,20),
                    grau = c("Grave", "Leve", "Moderado"))

#----------- Passo 3 - Criar o gráfico

ggplot(dados, aes(y = pacientes, x = grau)) +
  geom_col()

#----------- Passo 4 - Identificar o tipo da variável

class(dados$grau)

#----------- Passo 5 - Definir como fator

dados$grau <- as.factor(dados$grau)

#----------- Passo 6 - Identificar a ordem das categorias

levels(dados$grau)

#----------- Passo 7 - Definir variável como ordinal

dados$grau <- factor(dados$grau, 
                     levels = c("Leve", "Moderado", "Grave"), 
                     ordered = TRUE)
