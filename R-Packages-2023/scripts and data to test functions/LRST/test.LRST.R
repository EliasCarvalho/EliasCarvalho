# script.Test.LRST

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/R-Packages")

# If you need to generates a .reda uncomment lines below
#  Load dataset
## data.test <- read.csv(file.choose(),header = TRUE, na.strings=c("", " ", "NA")) # Keep orginal data saved
# Save the file as .rda
##save(data.test, file="data.test.rda")

# Load Package
library(LRST)
library(bnpa)

# Load dataset of package LRST
data(data.test)

data1 <- read.csv(file.choose(),header = TRUE, na.strings=c("", " ", "NA"))

# Creating a new data.table1
#data.table1  =============== TABELA DEMORGRAFICA N (%)
data <- "data1"
fields <-  c("Country",
             "TypeOfBN"
             )
dataset.name <- "data.table1"

create.subdatasets(data, fields, dataset.name) 



# Set terms to identify objective
# create a list of terms to scan in abstract
# add here a new term if necessary
# terms<-c(
#   # The most common terms, this is searched first
#   "aim",
#   "objective",
#   "Objective",
#   "objectives:",
#   "Objectives:"
# )
##########################################################################################
# This procedure look for a term "novel" inside the text and a
# dd a new column named "NovelProposal" to dataset
##########################################################################################
terms<-c(
  # The most common terms, this is searched first
  "novel"
)
# set if you wish to print (prt) objectives and write (wrt) the results - 0 = No, 1 = Yes
prt <- 1
wrt <- 1

# set the name of a new column
newColumn <- "NovelProposal"
# set the name of dataset to have a new column
dataset.name <- "data.table1"

# call objectives function
check.terms(data.table1, dataset.name, terms, prt, wrt, newColumn)

##########################################################################################
# This procedure look for a term "BN" inside the text and a
# dd a new column named "NovelProposal" to dataset
##########################################################################################
terms<-c(
  # The most common terms, this is searched first
  "BN",
  "Bayesian"
)
# set if you wish to print (prt) objectives and write (wrt) the results - 0 = No, 1 = Yes
prt <- 1
wrt <- 1

# set the name of a new column NO SPACe
newColumn <- "TypeOfBN"
# set the name of dataset to have a new column
dataset.name <- "data.table1"

# call objectives function
check.terms(data.table1, dataset.name, terms, prt, wrt, newColumn)

##########################################################################################
# This procedure look for what the BN do inside the text and a
# dd a new column named "NovelProposal" to dataset
##########################################################################################
terms<-c(
  # The most common terms, this is searched first
  "Inference",
  "inference",
  "Classifi",
  "classifi",
  "Regression",
  "regression"
)
# set if you wish to print (prt) objectives and write (wrt) the results - 0 = No, 1 = Yes
prt <- 1
wrt <- 1

# set the name of a new column NO SPACe
newColumn <- "ObjectiveOfBN"
# set the name of dataset to have a new column
dataset.name <- "data.table1"

# call objectives function
check.terms(data.table1, dataset.name, terms, prt, wrt, newColumn)

##########################################################################################
# This procedure look for a COUNTRY inside the text and a
# dd a new column named "NovelProposal" to dataset
##########################################################################################
terms<-c(
  "Afghanistan",
  "Akrotiri",
  "Albania",
  "Algeria",
  "American Samoa",
  "Andorra",
  "Angola",
  "Anguilla",
  "Antarctica",
  "Antigua and Barbuda",
  "Arctic Ocean",
  "Argentina",
  "Armenia",
  "Aruba",
  "Ashmore and Cartier Islands",
  "Atlantic Ocean",
  "Australia",
  "Austria",
  "Azerbaijan",
  "Bahamas",
  "Bahrain",
  "Bangladesh",
  "Barbados",
  "Belarus",
  "Belgium",
  "Belize",
  "Benin",
  "Bermuda",
  "Bhutan",
  "Bolivia",
  "Bosnia and Herzegovina",
  "Botswana",
  "Bouvet Island",
  "Brazil",
  "British Indian Ocean Territory",
  "British Virgin Islands",
  "Brunei",
  "Bulgaria",
  "Burkina Faso",
  "Burma",
  "Burundi",
  "Cambodia",
  "Cameroon",
  "Canada",
  "Cape Verde",
  "Cayman Islands",
  "Central African Republic",
  "Chad",
  "Chile",
  "China",
  "Christmas Island",
  "Clipperton Island",
  "Cocos",
  "Colombia",
  "Comoros",
  "Congo-Brazzaville",
  "Congo-Kinshasa",
  "Cook Islands",
  "Coral Sea Islands",
  "Costa Rica",
  "Croatia",
  "Cuba",
  "Cyprus",
  "Czech Republic",
  "Denmark",
  "Dhekelia",
  "Djibouti",
  "Dominica",
  "Dominican Republic",
  "East Timor",
  "Ecuador",
  "Egypt",
  "England",
  "Equatorial Guinea",
  "Eritrea",
  "Estonia",
  "Ethiopia",
  "European Union",
  "Faeroe",
  "Falkland Islands",
  "Fiji",
  "Finland",
  "France",
  "French Polynesian",
  "French Southern Territories",
  "Gabon",
  "Gambia",
  "Gaza Strip",
  "Georgia",
  "Germany",
  "Ghana",
  "Gibraltar",
  "Greece",
  "Greenland",
  "Grenade",
  "Guam",
  "Guatemala",
  "Guernsey",
  "Guiana",
  "Guinea Bissau",
  "Guinea",
  "Haiti",
  "Heard and McDonald Islands",
  "Honduras",
  "Hong Kong",
  "Hungary",
  "Iceland",
  "India",
  "Indian Ocean",
  "Indonesia",
  "Iran",
  "Iraq",
  "Ireland",
  "Israel",
  "Italy",
  "Ivory Coast",
  "Jamaica",
  "Jan Mayen",
  "Japan",
  "Jersey",
  "Jordan",
  "Kazakhstan",
  "Kenya",
  "Kiribati",
  "Kuwait",
  "Kyrgyzstan",
  "Laos",
  "Latvia",
  "Lebanon",
  "Lesotho",
  "Liberia",
  "Libya",
  "Liechtenstein",
  "Lithuania",
  "Luxembourg",
  "Macao",
  "Macedonia",
  "Madagascar",
  "Malawi",
  "Malaysia",
  "Maldives",
  "Malta",
  "Man, Isle of",
  "Marshall Islands",
  "Mauritania",
  "Mauritius",
  "Mayotte",
  "Mexico",
  "Micronesia",
  "Mali",
  "Moldavia",
  "Monaco",
  "Mongolia",
  "Monserrate",
  "Montenegro",
  "Morocco",
  "Mozambique",
  "Namibia",
  "Nauru",
  "Navassa Island",
  "Nepal",
  "Netherlands Antilles",
  "Netherlands",
  "New Caledonia",
  "New Zealand",
  "Nicaragua",
  "Niger",
  "Nigeria",
  "Niue",
  "Norfolk island",
  "North Korea",
  "Northern Marianas",
  "Norway",
  "Oman",
  "Pacific Ocean",
  "Pakistan",
  "Palau",
  "Panama",
  "Papua New Guinea",
  "Paracel Islands",
  "Paraguay",
  "Peru",
  "Philippines",
  "Pitcairn",
  "Poland",
  "Portugal",
  "Puerto Rico",
  "Qatar",
  "Romania",
  "Russia",
  "Rwanda",
  "Saint Helen",
  "Saint Kitts and Nevis",
  "Saint Lucia",
  "Saint Pierre and Miquelon",
  "Saint Vincent and the Grenadines",
  "Samoa",
  "San Marino",
  "Sao Tome and Principe",
  "Saudi Arabia",
  "Savior",
  "Senegal",
  "Serbia",
  "Seychelles",
  "Sierra Leone",
  "Singapore",
  "Slovakia",
  "Slovenia",
  "Solomon Islands",
  "Somalia",
  "South Africa",
  "South Georgia and South Sandwich",
  "South Korea",
  "Southern Ocean",
  "Spain",
  "Spratly Islands",
  "Sri Lanka",
  "Sudan",
  "Suriname",
  "Svalbard and Jan Mayen",
  "Swaziland",
  "Sweden",
  "Switzerland",
  "Syria",
  "Taiwan",
  "Tajikistan",
  "Tanzania",
  "Thailand",
  "Togo",
  "Tokelau",
  "Tonga",
  "Trinidad and Tobago",
  "Tunisia",
  "Turkey",
  "Turkmenistan",
  "Turks and Caicos",
  "Tuvalu",
  "U.S",
  "Uganda",
  "Ukraine",
  "United Arab Emirates",
  "United Kingdom",
  "Uruguay",
  "US Virgin Islands",
  "Uzbekistan",
  "Vanuatu",
  "Vatican",
  "Venezuela",
  "Vietnam",
  "Wake Island",
  "Wallis and Futuna",
  "West Bank",
  "Western Sahara",
  "World",
  "Yemen",
  "Zambia",
  "Zimbabwe"  
)
# set if you wish to print (prt) objectives and write (wrt) the results - 0 = No, 1 = Yes
prt <- 1
wrt <- 1

# set the name of a new column NO SPACe
newColumn <- "Country"
# set the name of dataset to have a new column
dataset.name <- "data.table1"

# call objectives function
check.terms(data.table1, dataset.name, terms, prt, wrt, newColumn)


##########################################################################################
# This procedure look for several terms inside the text and a
# add a new column named "Objective" to dataset
##########################################################################################
term<-c( 
  "aim",
  "objective",
  "Objective",
  "objectives:",
  "Objectives:",
  
  "In order to",
  "In the present study",
  "In this article",
  "In this paper",            
  "In this paper, we",        
  "In this paper we",
  "In this review",           
  "In this study",            
  "In this work",             
  "In this work we",    
  
  "method is proposed",       
  "model is constructed",     
  "presented here",           
  "Presented here",  
  
  "the article describes",    
  "The article describes",    
  "the article focuses",      
  "The article focuses",  
  
  "the authors propose",
  "The authors propose",
  
  
  "the content of this paper",
  "The content of this paper",
  "the current study",
  "The current study",
  "the main goal of this article",
  "The main goal of this article",
  
  "the present research",     
  "The present research",  
  "the present study",
  "The present study",
  
  "the paper applies",        
  "The paper applies", 
  "the paper considers",
  "The paper considers",
  "The paper utilizes",
  "The paper utilizes",
  "The paper will show",
  
  "The purpose of this paper",
  
  "this article elaborates",
  "This article elaborates",
  "this article evaluates",
  "This article evaluates",
  "this article focused",     
  "This article focused",     
  "this article uses",        
  "This article uses",        
  "this article addresses",   
  "This article addresses",   
  "this article describes",    
  "This article describes",  
  "this article presents",    
  "This article presents",   
  "This article proposes",
  
  "this document",            
  "This document",       
  
  "this is the first study",  
  "This is the first study",
  
  "this study",               
  "This study",  
  
  "this paper",               
  "This paper",   
  "this paper describes",
  "This paper describes",
  
  
  "this research focuses",    
  "This research focuses", 
  
  "this review",
  "This review",
  
  "this work is",             
  "This work is",             
  "this work introduces",     
  "This work introduces",     
  "this work offers",
  "This work offers",
  "this work presents",       
  "This work presents",  
  "this work proposes",
  "This work proposes",
  "this work provides",
  "This work provides",
  
  "to address",               
  "To address",    
  
  "to carry out the study",   
  "To carry out the study", 
  
  "was used",
  
  "we analyse",               
  "We analyse",  
  "we attempted to",
  "We attempted to",
  "we augmented",             
  "We augmented",
  "we apply and evaluate",
  "We apply and evaluate",
  "we built",                 
  "We built",    
  "we come up with",          
  "We come up with",          
  "we compare",               
  "We compare",               
  "we create",                
  "We create",   
  "we demonstrate",           
  "We demonstrate",   
  "we developed",             
  "We developed", 
  "we describe",             
  "We describe",
  "we described",             
  "We described",
  "we designed",              
  "We designed", 
  "we develop",               
  "We develop",               
  "we devise",                
  "We devise",                
  
  "we employed",              
  "We employed",  
  "we explored",
  "We explored",
  "we establish",             
  "We establish", 
  "we evaluate",
  "We evaluate",
  "we evaluated ",
  "We evaluated ",
  "we examined",              
  "We examined",              
  "we examine",              
  "We examine",  
  "we exploit",
  "We exploit",
  "we give",                  
  "We give",                  
  "we have developed",        
  "We have developed",        
  "we focus on",              
  "We focus on",              
  "we formalise and present", 
  "We formalise and present", 
  "we identified",
  "We identified",
  "we illustrate",
  "We illustrate",
  "we introduce",             
  "We introduce",   
  "we investigate",
  "We investigate",
  "we investigated",
  "We investigated",
  "we model",                 
  "We model",  
  "we performed",
  "We performed",
  "we propose",               
  "We propose",               
  "we present",               
  "We present", 
  "we quantified",
  "We quantified",
  "we report",                
  "We report",                
  "we recently proposed",     
  "We recently proposed",  
  "we represent the classes",
  "We represent the classes",
  
  "we review",               
  "We review",               
  "we show here",             
  "We show here",             
  "we show how",              
  "We show how",   
  "we show that",              
  "We show that",   
  "we study how",             
  "We study how",             
  "we synthesize",            
  "We synthesize",  
  "We tested",
  "We tested ",
  "We use",                   
  "we use",                    
  "We used",                  
  "we used",                   
  "were identified and modeled",
  
  "are developed",
  "are presented",
  "are proposed",             
  "has been developed",       
  "carried out",                  
  "experiments examined",    
  "have developed",
  "I offer",    
  "I show that",
  "I demonstrate",
  "It is demonstrated",       
  "is also presented", 
  "is applied",
  "is developed",
  "is introduced",  
  "is modeled by",
  "is presented", 
  "is presented in this paper",
  "is proposed",      
  "it is shown that",
  "It is shown that",
  "is used here",
  "model was developed",
  "model was implemented",
  "our analysis",
  "Our analysis",
  "studies examined",   
  "the proposed",
  "The proposed",
  "to identify ",
  "To identify ",
  "was applied to evaluate",
  "was conducted",
  "was developed to better understand"
)  

# set if you wish to print (prt) objectives and write (wrt) the results - 0 = No, 1 = Yes
prt <- 1
wrt <- 1

# set the name of a new column
newColumn <- "Objective"
# set the name of dataset to have a new column
dataset.name <- "data.test"

# call objectives function
check.terms(data.test, dataset.name, terms, prt, wrt, newColumn)





