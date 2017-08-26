//----------------------------------------------
// place to hold anything that is sepcific to the CI
// environment. Don't store environmental information in the 
//sccripts. instead place it ihere and reference in the scripts
//
// We can then Manage the environments independant of the scripts
//----------------------------------------------





//******** IMPORTANT. CALL THIS AS THE FIRST LINE TO SETUP YOUR ENVIRONEMTN




//IMPORTANT run this before changng direction
def setup(config) {
   env.WORKSPACE = pwd() // needs to be done because this variable doesn't exist
   setupJavaEnv(config)
}



def setupJavaEnv(config) {
  jdk = tool name: config.JDK_TOOL // must be setup in the global tool list...
  env.JAVA_HOME = "${jdk}"
}
