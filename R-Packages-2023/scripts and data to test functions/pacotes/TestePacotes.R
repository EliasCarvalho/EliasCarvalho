library(bnpa)

remove.packages("bnlearn")
remove.packages("parallel")
remove.packages("plyr")
remove.packages("stats")
remove.packages("semPlot")
remove.packages("lavaan")
remove.packages("Rgraphviz")
remove.packages("graph")
#====================================
pacman::p_load(
                bnlearn,
                plyr,
                semPlot,
                Rgraphviz
              )

#====================================
package.name<-"RGraphviz"
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

package.name<-"bnlearn" 
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

package.name<-"parallel" 
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

package.name<-"plyr"
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

package.name<-"stats"
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

package.name<-"semPlot" 
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

package.name<-"lavaan"
bnpa::check.package(package.name)

bnpa::check.package(package.name)
#====================================OK

/

package.name<-"graph"
bnpa::check.package(package.name)

