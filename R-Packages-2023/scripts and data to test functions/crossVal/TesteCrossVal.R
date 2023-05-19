# Clean environment
closeAllConnections()
rm(list=ls())

library(bnlearn)

## Loading required package: methods
data(alarm)
bn.cv(alarm, bn = "hc", algorithm.args = list(score = "bde", iss = 1))
bn.cv(alarm, bn = "hc", algorithm.args = list(score = "bde", iss = 10))
bn.cv(alarm, bn = "hc", algorithm.args = list(score = "bic"))

loss.bic = numeric(10)
loss.bde = numeric(10)
algorithm = factor(c(rep("BIC", 10), rep("BDe", 10)))
for (i in seq_along(loss.bic))
{
  loss.bic[i] = attr(bn.cv(learning.test, bn = "hc",algorithm.args = list(score = "bic")), "mean")
}
  
for (i in seq_along(loss.bde))
{
  loss.bde[i] = attr(bn.cv(learning.test, bn = "hc",algorithm.args = list(score = "bde", iss = 10)), "mean")
}
  
boxplot(c(loss.bic, loss.bde) ~ algorithm, xlab = "scores", ylab = "log-likelihood loss")

bn.cv(alarm, bn = "iamb", k = 5)

res = empty.graph(names(alarm))

modelstring(res) = paste("[HIST|LVF][CVP|LVV][PCWP|LVV][HYP][LVV|HYP:LVF]",
                         "[LVF][STKV|HYP:LVF][ERLO][HRBP|ERLO:HR][HREK|ERCA:HR][ERCA]",
                         "[HRSA|ERCA:HR][ANES][APL][TPR|APL][ECO2|ACO2:VLNG][KINK]",
                         "[MINV|INT:VLNG][FIO2][PVS|FIO2:VALV][SAO2|PVS:SHNT][PAP|PMB][PMB]",
                         "[SHNT|INT:PMB][INT][PRSS|INT:KINK:VTUB][DISC][MVS][VMCH|MVS]",
                         "[VTUB|DISC:VMCH][VLNG|INT:KINK:VTUB][VALV|INT:VLNG][ACO2|VALV]",
                         "[CCHL|ACO2:ANES:SAO2:TPR][HR|CCHL][CO|HR:STKV][BP|CO:TPR]", sep = "")

bn.cv(alarm, res, algorithm.args = list(score = "aic"))
bn.cv(alarm, empty.graph(names(alarm)))


rand = random.graph(names(alarm))
rand

bn.cv(alarm, bn = rand)

xval = bn.cv(alarm, bn = "hc", loss = "pred", loss.args = list(target = "STKV"))
xval

OBS = unlist(lapply(xval, `[[`, "observed"))
PRED = unlist(lapply(xval, `[[`, "predicted"))
str(OBS)
str(PRED)

table(OBS, PRED)





