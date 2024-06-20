############# EXPLORANDO O DATASET

#---------- Baixando Dados

df <- dados::dados_starwars

#---------- base

View(df)

str(df)

head(df)

dim(df)

summary(df)

#---------- dplyr

dplyr::glimpse(df)

#---------- skimr

skimr::skim(df)
