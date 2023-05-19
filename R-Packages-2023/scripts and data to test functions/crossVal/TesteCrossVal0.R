# learning.test = data
# hc = algorithm to learn structure
# loss="pred" - the label of a loss function. If none is specified, the default
#       loss function is the Classification Error for Bayesian networks classifiers; 
#       otherwise, the Log-Likelihood Loss for both discrete and continuous data sets
# loss.args=list(target = "F") - a list of extra arguments to be passed to the loss 
#                                function specified by loss
# 
bn.cv(learning.test,'hc',loss="pred",loss.args=list(target = "F"))

# gaussian.test = data
# mmhc = algorithm to learn structure
# method="hold-out",
# k=5 - the number of groups into which the data will be spli
# m=50 - the size of the test set in hold-out cross-validation
# runs=2 - the number of times cross-validation will be run
bn.cv(gaussian.test,'mmhc',method="hold-out",k=10, m=50,runs=10)

gaussian.subset = gaussian.test[1:50, ]
cv.gs = bn.cv(gaussian.subset, 'gs' , runs = 10)
cv.iamb = bn.cv(gaussian.subset, 'iamb' , runs = 10)
cv.inter = bn.cv(gaussian.subset, 'inter.iamb' , runs = 10)
dev.off()
plot(cv.gs, cv.iamb, cv.inter, xlab = c("Grow-Shrink", "IAMB", "Inter-IAMB"), connect = TRUE)
