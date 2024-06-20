#------------- Criando vetores

tempo <- c("0h", "1h", "2h", "3h", "6h")
temperatura <- c(69.6, 66.4, 59.0, 51.9, 38.7)

#------------- Calculando a média

mean(temperatura)

#------------- Criando dataframe

garrafa <- data.frame(tempo, temperatura)

#------------- Removendo vetores

rm(tempo, temperatura)

#------------- Gráfico de barra

barplot(garrafa$temperatura ~ garrafa$tempo, col = "steelblue",
        ylab = "Temperatura °C", xlab = "Momento de aferição",
        main = "Decaimento da temperatura de garrafa térmica")
