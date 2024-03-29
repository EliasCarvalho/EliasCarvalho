#'Learn the Bayesian Network structure from data and build a PA model
#'
#'This function receives a data set, a list of parameters to learn the BN structure based on this data set. Then with the BN ready it will build a PA model if required. The process will then save the graphs of BN and PA and PA parameters.
#'@param data.to.work is a data from which the BN structure will be learned.
#'@param white.list  is a list of mandatory connections of BN structure to be created.
#'@param black.list is a list of forbiden connections of BN structure to be created.
#'@param nreplicates is how many times the boostrap will run.
#'@param cb.algorithms the name of constrained-based algorithms.
#'@param sb.algorithms the name of score-based algorithms.
#'@param outcome.var is the outcome (dependent) variable.
#'@param build.pa indicates if the process will bulld a PA model or not.
#'@return NULL
#'@author Elias Carvalho
#'@references Scutari M (2017). Bayesian Network Constraint-Based Structure Learning Algorithms: Parallel and Optimized Implementations in the bnlearn R Package. Journal of Statistical Software, 77(2), 1-20.
#'@examples
#'
#'# Clean environment

#'@export

# data.to.work=data.to.work.sem.CA
# white.list=wl
# black.list=bl
# nreplicates=1000
# cb.algorithms = c("gs","iamb", "fast.iamb", "inter.iamb")
# sb.algorithms = c("hc","tabu")
# outcome.var
# build.pa=0

gera.bn.pa <-function(data.to.work, white.list="", black.list="", nreplicates=1000, cb.algorithms = c("gs","iamb", "fast.iamb", "inter.iamb"),
                      sb.algorithms = c("hc","tabu"), outcome.var, build.pa=1)
{

    # Check if outcome.var is on data set
    if (!any(names(data.to.work) == outcome.var))
    {
      cat(paste("Your outcome.var: ",outcome.var," does not exist on data set. Check please!", sep = ""))
      return()
    }

    # Check if the cb algorithms area allowed
    for (x in 1:length(cb.algorithms))
    {
      check.algorithms (cb.algorithms[x])
    } # for (x in 1:nroe(cb.algorithms))

    # Check if the sb algorithms area allowed
    for (x in 1:length(sb.algorithms))
    {
      check.algorithms (sb.algorithms[x])
    } # for (x in 1:nroe(sb.algorithms))

    # Verify the type of variables
    type.of.variable <- bnpa::check.types(data.to.work)

    # Check if the data set has numeric only or categorical only variables
    if (type.of.variable > 3)
    {
      cat("Your data set need to have or only categorical or only numeric variables. Check please!")
      return()
    } # if type.of.variable > 3

    # Mount a white list
    if (!is.null(white.list) && white.list!="")
    {
      cat("\n\n==== Building the white list...")
      white.list <- bnpa::mount.wl.bl.list(white.list)

      # Check if variables on white list are on data set
      for (x in 1:nrow(white.list))
      {
        for (y in 1:ncol(white.list))
        {
          commandAssign <- paste("any(names(data.to.work) == '", white.list[x,y],"')", sep = "")
          if (eval(parse(text=commandAssign))=="FALSE")
          {
            cat(paste("\nThe variable: ", white.list[x,y]," on white.list dos not exist on your data set. Check please!", sep = ""))
            return()
          } # if (eval(parse(text=commandAssign))=="FALSE")
        } # for (x in 1:nrow(white.list))
      } # for (x in 1:nrow(white.list))

    } else # if (white.list != "")
    {
      white.list <- NULL
    } # else # if (black.list != "")

    # Mount a black list
    if (!is.null(black.list) && black.list!="")
    {
      cat("\n\n==== Building the black list...")
      black.list <- bnpa::mount.wl.bl.list(black.list)

      # Check if variables on white list are on data set
      for (x in 1:nrow(black.list))
      {
        for (y in 1:ncol(black.list))
        {
          commandAssign <- paste("any(names(data.to.work) == '", black.list[x,y],"')", sep = "")
          if (eval(parse(text=commandAssign))=="FALSE")
          {
            cat(paste("\nThe variable: ", black.list[x,y]," on black.list dos not exist on your data set. Check please!", sep = ""))
            return()
          } # if (eval(parse(text=commandAssign))=="FALSE")
        } # for (x in 1:nrow(white.list))
      } # for (x in 1:nrow(white.list))

    }  else # if (black.list != "")
    {
      black.list <- NULL
    } # else # if (black.list != "")

    # Generate ci tests and networks scores
    # If variables are categorical check if is ordered or not
    if (type.of.variable == 3)
    {
      # Scan the variables of dataset and if there is one or more ordered variables
      for (variable.name in names(data.to.work))
      {
        if (bnpa::check.ordered.one.var(data.to.work, variable.name))
        {
          # Set cb.tests to  Jonckheere-Terpstra for indepence test for categorical ordered variables
          cb.tests <- "jt"
          # Stop the for loop
          break
        } else # if (bnpa::check.ordered.one.var(data.to.work.train, variable.names))
        {
          # Set cb.tests to mutual information, shrinkage estimator, Pearson's X^2 for categorical NOT ordered variables
          cb.tests <- c("mi", "mi-sh", "x2")
        } # else # if (bnpa::check.ordered.one.var(data.to.work.train, variable.names))
      } # for (variable.names in names(data.to.work.train))
      # Set scores for categorical variables
      sb.tests <- c("aic","bic", "bde")
    } else if (type.of.variable == 1 || # if (type.of.variable == 3)
               type.of.variable == 2 || # Variables as 1-integer, 2-numeric, 4-both
               type.of.variable == 4)
    {
      # Set cb.tests to linear correlation, Fisher's Z, mutual information, shrinkage estimator for the mutual information
      cb.tests <- c("cor", "zf", "mi-g","mi-g-sh")
      # Set sb.tests (score) to Gaussian log-likelihood, Akaike Information Criterion, Bayesian Information Criterion, score equivalent Gaussian posterior density
      sb.tests <- c("aic-g","bic-g", "bge")
    } # else if (type.of.variable == 1 || # if (type.of.variable == 3)

  # Transform all categorical variables into ordered, if necessary, to work with 'jt' when using cb algorithms. OBS: and to work with sb algorithms the
  # variables will be treated as categorical
  if (type.of.variable==3)
  {
    # Transform all not ordinal variables (probably the) in ordinal
    data.to.work.all.ordinal <- bnpa::transf.into.ordinal(data.to.work) ####################################
    # Show message
    cat("\n\n==== Checking the consistence of levels on data set...")
    # Create a variable to work
    flag.levels <- 0
    levels.of.variable <- 0
    # Scan the variables of data set
    for (x in 1:length(names(data.to.work)))
    {
      # Calculate the number of levels each variable has
      commandAssign <- paste("levels.of.variable <- summary(data.to.work$", names(data.to.work)[x],")", sep = "")
      eval(parse(text=commandAssign))
      # Scan each level and verify how manu occurency it has
      for (y in 1:length(levels.of.variable))
      {
        # Check if there are 0 occurrency on level
        if (levels.of.variable[y] == 0)
        {
          cat("\nYour data set has variables with 0 elements on level ", y,"(", names(data.to.work)[x],").")
          flag.levels <- 1
        } # if (levels.of.variable[y] == 0)

      }# for (y in 1:number.of.levels)
    } # for (x in 1:length(names(data.to.work)))
    # stop in case of variable with less than 2 levels
    if (flag.levels != 0)
    {
      cat("\n\nFix this problem, please.")
      return()
    } #  if (flag.levels != 0)
  } # if (type.of.variable==3)

  # Creates a data frame to store the final results
  cat("\n\n==== Creating a data frame to store algorithms, ci tests and network scores")
  # Define of rows based on algorithms, ci tests and network scores
  df.bn.alg.data <- data.frame(matrix(ncol = 2, nrow = (length(cb.algorithms)*length(cb.tests)+length(sb.algorithms)*length(sb.tests))))
  # Name the columns
  names(df.bn.alg.data)[1] <- "Algorithm"
  names(df.bn.alg.data)[2] <- "Type of Algorithm"
  # Start loading the dat frame
  ncounter = 0
  # Load constrained-based algorithms
  for (x in 1:length(cb.algorithms))
  {
    for (y in 1:length(cb.tests))
    {
      ncounter <- ncounter + 1
      df.bn.alg.data[ncounter,1] <- paste(cb.algorithms[x],"--", cb.tests[y], sep = "")
      df.bn.alg.data[ncounter,2] <- "constrained-based"
      cat(paste("\nALgorithm is ", cb.algorithms[x], " and test is ", cb.tests[y], sep = ""))
    } # for (y in 1:length(cb.tests))
  } # for (x in 1:nrow(cb.algorithms))
  # Load score-based algorithms
  for (x in 1:length(sb.algorithms))
  {
    for (y in 1:length(sb.tests))
    {
      ncounter <- ncounter + 1
      df.bn.alg.data[ncounter,1] <- paste(sb.algorithms[x],"--", sb.tests[y], sep = "")
      df.bn.alg.data[ncounter,2] <- "score-based"
      cat(paste("\nALgorithm is ", sb.algorithms[x], " and test is ", sb.tests[y], sep = ""))
    } # for (y in 1:length(cb.tests))
  } # for (x in 1:nrow(cb.algorithms))

  # Start bootstrap
  # Creates a structure to retain BN to avoid interference of another package
  bn.imgname <- ""
  bn.model.data <- vector("list", nrow(df.bn.alg.data))
  bn.imgname.txt <-  vector("list", nrow(df.bn.alg.data))
  # Creates a structure to retain PA model data to avoid interference of another package
  pa.model.data <- vector("list", nrow(df.bn.alg.data))
  pa.model.txt <-  vector("list", nrow(df.bn.alg.data))
  pa.imgname.txt <-  vector("list", nrow(df.bn.alg.data))
  # Creates a counter to control the load of this data
  counter.model.data <- 0
  bn.structure <- ""


  x=17
  y=3


  # Scan df.bn.alg.data and identify the bn structure learning algorithm to be used
  for (x in 1:nrow(df.bn.alg.data))
  {
    # Scan the first colum of dataframe to find the algorithm/test or score
    for (y in 1:nchar(df.bn.alg.data[x,1]))
    {
      # If identify the label of BN test or score "--"
      if(substr(df.bn.alg.data[x,1],y,y+1)=="--")
      {
        # Load the bn structure learning algorithm
        bn.algorithm <- substr(df.bn.alg.data[x,1],1,y-1)
        # Load the test or score
        bn.score.test <- substr(df.bn.alg.data[x,1],y+2, nchar(df.bn.alg.data[x,1]))
        # Set the type of algorithm
        type.of.algorithm <- df.bn.alg.data[x,2]
        # Booststrap, average and export BN graph
        if (type.of.variable==3)
        {
          bn.structure <- bnpa::boot.strap.bn(bn.algorithm, bn.score.test, data.to.work.all.ordinal, black.list, white.list, nreplicates, type.of.algorithm, outcome.var)
        } else # if (type.of.variable==3)
        {
          bn.structure <- bnpa::boot.strap.bn(bn.algorithm, bn.score.test, data.to.work, black.list, white.list, nreplicates, type.of.algorithm, outcome.var)
        } # else # if (type.of.variable==3)
        # Build and print a PA model if required
        if (!is.null(bn.structure))
        {
          if (build.pa==1)
          {
            # Set the PA param and image name
            pa.name <- paste("docPA_Param_", bn.algorithm, "_", bn.score.test,"_", nrow(data.to.work), ".txt", sep = "")
            pa.imgname <- paste("imgPA_", bn.algorithm, "_", bn.score.test,"_", nrow(data.to.work), ".png", sep = "")
            # Creating Path Analysis model ################
            utils::timestamp()
            cat(paste("\n\n==== Creating a Path Analysis model calculation for BN structure learning score-based algorithm --> '", bn.algorithm, "' , score test '", bn.score.test,  sep = ""))
            bnpa::gera.pa(bn.structure, data.to.work, pa.name, pa.imgname, bn.algorithm, bn.score.test, outcome.var)
          } #  if (build.pa==1)
        } # if (bn.structure!=NULL)
      } # if(substr(df.bn.alg.data[x,1],y,y+1)=="--")
    } # for (y in 1:nchar(df.bn.alg.data[x,1]))
  } # for (x in 1:nrow(df.bn.alg.data))
} # gera.bn.structure <-function
