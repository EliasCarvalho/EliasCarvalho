################# COMBINANDO DATASETS COM dplyr

codigo <- c(001, 002, 003, 004, 005, 006)
nome <- c("Antonio", "Maria", "Paulo", "João", "Mariana", "José")
idade <- c(42, 35, 27, 46, 32, NA)

df1 <- data.frame(codigo, nome, idade)
rm(codigo, nome, idade)

codigo <- c(003, 002, 005, 004, 001, NA)
diabetes <- c("Não", "Não", "Sim", NA, "Não", "Sim")
hipertensao <- c("Sim", "Não", "Sim", "Não", "Não", "Não")

df2 <- data.frame(codigo, diabetes, hipertensao)
rm(codigo, diabetes, hipertensao)

#------------- Unindo incorretamente

dados1 <- cbind(df1, df2)

#------------- Unindo à esquerda

dados2 <- dplyr::left_join(df1, df2, by = "codigo")

#------------- Unindo todos coincidentes

dados3 <- dplyr::inner_join(df1, df2, by = "codigo")

#------------- Unindo tudo, coincidentes ou não

dados4 <- dplyr::full_join(df1, df2, by = "codigo")

#------------- Unindo apenas quem não coincide

dados5 <- dplyr::anti_join(df1, df2, by = "codigo")
