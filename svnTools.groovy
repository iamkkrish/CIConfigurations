/**
 * some simple tools for making SVN a little easier to use 
 * (instead of the masses of lines you need to add in the raw 
 * commands)
 */
  def checkOut(svnConfig) {
    echo "checking out with ${svnConfig}"
    checkout([$class: 'SubversionSCM', 
             additionalCredentials: [], 
             excludedCommitMessages: '', 
             excludedRegions: '', 
             excludedRevprop: '', 
             excludedUsers: '', 
             filterChangelog: false, 
             ignoreDirPropChanges: false, 
             includedRegions: '', 
             locations: [
                   [credentialsId: svnConfig.jenkinsCredentialsId,
                    depthOption: 'infinity', 
                    ignoreExternalsOption: true, 
                    local: svnConfig.localFolder, 
                    remote: svnConfig.svnURL]], 
                workspaceUpdater: [$class: 'UpdateUpdater']])
}
