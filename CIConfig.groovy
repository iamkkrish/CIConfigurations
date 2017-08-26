//----------------------------------------------
// place to hold anything that is sepcific to the CI
// environment. Don't store environmental information in the 
//sccripts. instead place it ihere and reference in the scripts
//
// We can then Manage the environments independant of the scripts
//----------------------------------------------

def getEmailConfig() {
	return [
		CM_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com,kunal.sahu@oracle.com",
		CM_UI_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,plamen.iliev@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com,kunal.sahu@oracle.com",
		CM_Portal_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,plamen.iliev@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com,kunal.sahu@oracle.com",
		Reporting_failure_emailList: "senshan.yang@oracle.com,padmesh.neelamchalil@oracle.com,chhaya.shrestha@oracle.com,shane.bai@oracle.com,andy.darlow@oracle.com",
		Reporting_sucess_emailList: "ben.griessenbock@oracle.com,victor.konopik@oracle.com,kunal.sahu@oracle.com,andy.darlow@oracle.com",
		OWS_adaptor_emailList: "parepalli.krishna@oracle.com",
		MetadataAdapter_emailList: "sriharish.koduri@oracle.com,andy.darlow@oracle.com,michael.hutcherson@oracle.com,anirban.ab.banerjee@oracle.com",
		ExpoAdapter_emailList: "parepalli.krishna@oracle.com",
		OSB_emailList: "sriharish.koduri@oracle.com,krishnaiah.kunta@oracle.com, anirban.ab.banerjee@oracle.com",
		Common_emailList:"parepalli.krishna@oracle.com"
	]
}

def getOHIPConfig() {

	def config;
	switch (env.BUILD_TYPE) {
		case "PRE_RELEASE":
			println "Getting pre-release build config";
			config = getPreReleaseConfig();
			break;
		case "TRUNK":
			println "Getting trunk build config";
			config = new CIConfig().getTrunkConfig();
			break;
		case "RELEASE":
			println "Getting release build config";
			//config = new CIConfig().getPreReleaseConfig();
			break;
		default:
			println "Getting trunk build config";
			config = new CIConfig().getTrunkConfig();
			break;
	}
	return config
}

def getTrunkConfig() {
	return [ 
		ExpoAdapter : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'ohip-expov1-adapter',
            targets: 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/ExpoAdapter',
			svnFortifyUrl:'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/ExpoAdapter/Fortify',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
		    qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
        ],
		MetadataAdapter : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'ohip-metadata-adapter',
            targets: 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/MetadataAdapter',
			svnFortifyUrl:'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/MetadataAdapter/ohip-metadata-adapter/fortify',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
		    qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'

        ],
		OWS_adaptor : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'ohip-ows-adapter',
            targets: 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/OWSAdaptor/',
			svnFortifyUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/OWSAdaptor/Fortify',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
		    qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
        ],
		ReportJavaApp : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			remoteWarName: 'OHIP_Reporting_ETL_Application',
			repoProviderWarName: 'OHIP_Reporting_Provider',
			repoProviderTargets: 'osb_cluster',
			targets : 'wls_server1',
			SecondRepoServer: [
				url: 't3://den00qrb.us.oracle.com:9500',
				user: 'weblogic',
				password: 'welcome123',
				repoServerTargets: 'reporting_server'
			],
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/reporting/Java',
			svnFortifyUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/reporting/Fortify',
			libFolder: 'llg00cys.uk.oracle.com:/scratch/gbuora/u00/app/ora_fmw/oracle/Middleware/Oracle_Home/user_projects/domains/ohip_dev_domain/lib'
		],
		ReportDB : [
			dbUser: 'OHIP_REPORTING',
			dbPassword: 'welcome123',
			dbUrl: 'jdbc:oracle:thin:@llg00gzd.uk.oracle.com:1521/connectdb',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/reporting/database/liquibase'
		],
		ReportRDP : [
            user: 'weblogic',
            password: 'welcome123',
            serverInstance: 'ssi',
            rpdPwd:  'admin123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/reporting/OBI',
			svnFortifyUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/reporting/Fortify',
			rdpCopyTo: 'den00qrb.us.oracle.com:Downloads',
			obieeDomain: 'den00qrb.us.oracle.com:/scratch/gbuora/Oracle_Home/user_projects/domains/bi_prerelease',
			bieeHome: 'den00qrb.us.oracle.com:/scratch/gbuora/Oracle_Home/bi/bifoundation/web/msgdb/pages/bieehome',
			obieeServer: 'den00qrb.us.oracle.com',
			inputRdpName: 'ConnectReporting.rpd',
			outputRdpName: 'ConnectReportingPre.rpd',
			dbXmlFileName: 'OHIPReportingDevPreRelease.xml',
			biServerXmlExecLoc: '/scratch/gbuora/Oracle_Home/user_projects/domains/bi_prerelease/bitools/bin/biserverxmlexec.sh',
			dataModelCmdLoc: '/scratch/gbuora/Oracle_Home/user_projects/domains/bi_prerelease/bitools/bin/data-model-cmd.sh'
		],            
		CM_appServer : [
            url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'clientManagement',
            targets : 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/clientmanagement/service',
			svnOatsUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/qa/branches/AutomationScripts',
			svnSoapUiUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/qa/branches/Client_Management'
		],
        CM_UI_appServer : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'client-management-ui',                            
            targets : 'wls_server2',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/dev-in-progress/clientmanagement-ui/feature_redesign/'
			// svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/clientmanagement-ui/'

        ],
        CM_Portal_appServer : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'client-portal-ui',                            
            targets : 'wls_server5',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/portal-ui/'
        ],
		DBServer : [
            user: 'clientmanagement',
            password: 'myconnect1',
            url: "jdbc:oracle:thin:@llg00gzd.uk.oracle.com:1521:connectdb"
        ],
		OSB_OWSRESTServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/OWSRESTServices',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_MetadataRESTServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/MetadataRESTServices',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_HospitalityRESTServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/HospitalityRESTServices',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_HospitalityRESTRouter : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/HospitalityRESTRouter',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_EXPOV1RESTServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/EXPOV1RESTServices',
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_CommonResources : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/CommonResources',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],		
		OSB_OWSResources : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/OWSResources',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_OWSServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/OWSServices',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_OHEICSResources : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/OHEICSResources',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_OHEICSServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/OHEICSServices',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_ADSResources : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/ADSResources',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		OSB_ADSServices : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/ADSServices',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],		
		OSB_CacheInvalidation : [
			url: 't3://llg00cys.uk.oracle.com:8021',
			user: 'weblogic',
			password: 'welcome123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/osb/OHIPServiceBusApp/CacheInvalidation',			
			qa_repo: 'http://den01cee.us.oracle.com:8081/nexus/content/repositories/connect-release/',
			qa_repo_id: 'den01cee',
			artifact_base_version: '1.1.0.'
		],
		ORACLE_HOME : '/u01/app/oracle/middleware/product/oracle_home/',
		JDK_TOOL : 'default',
		MVN_TOOL : 'M3',
		FORTIFY_HOME: '/u01/app/HPE_Security/Fortify_SCA_and_Apps_17.10/',
		NODE_HOME: '/scratch/tools/node-v4.4.5-linux-x64/',
		SONAR_QUBE : [
			url : 'http://llg00fco.uk.oracle.com:9000',
            scannerToollName : 'DEVSonarQubeScanner',
            password : 'FLDnc6GB',
            username  : 'robot',
			timeout : 600
		],
		MCAFEE_HOME: '/u01/app/mcafee/'
	]
}

def getPreReleaseConfig() {

	return [
		ExpoAdapter : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'expo-adapter',
            targets: 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/trunk/adapters/ExpoAdapter'
        ],
		MetadataAdapter : [
			url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123',
            remoteWarName: 'metadata-adapter',
            targets: 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/adapters/MetadataAdapter'
        ],
		OWS_adaptor : [
			url: 't3://den00ohf.us.oracle.com:7081', 
            user: 'connectukuser',
            password: 'connect1',
            remoteWarName: 'connect-OWS-adaptor',
            targets : 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/adapters/OWSAdaptor/',
			svnFortifyUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/adapters/OWSAdaptor/Fortify'
        ],
		ReportJavaApp : [
			url: 't3://den00ohf.us.oracle.com:7081',
			user: 'connectukuser',
			password: 'connect1',
			remoteWarName: 'OHIP_Reporting_ETL_Application',
			repoProviderWarName: 'OHIP_Reporting_Provider',
			repoProviderTargets: 'osb_cluster',
			targets : 'wls_server1',
			SecondRepoServer: [
				url: 't3://den00qrb.us.oracle.com:9500',
				user: 'weblogic',
				password: 'welcome123',
				repoServerTargets: 'reporting_server'
			],
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/reporting/Java',
			svnFortifyUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/reporting/Fortify',
			libFolder: 'connctci@den00ohf.us.oracle.com:/scratch/connect1/app/oracle/middleware/12211/user_projects/domains/ohip_domain/lib'
		],
		ReportDB : [
			dbUser: 'connect_reporting',
			dbPassword: 'Connect123',
			dbUrl: 'jdbc:oracle:thin:@den00ohd.us.oracle.com:1521/CONNECTDEV1.us.oracle.com',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/reporting/database/liquibase'
		],
		ReportRDP : [
            user: 'weblogic',
            password: 'welcome123',
            serverInstance: 'ssi',
            rpdPwd:  'admin123',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/reporting/OBI',
			svnFortifyUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/reporting/Fortify',
			rdpCopyTo: 'den00qrb.us.oracle.com:Downloads',
			obieeDomain: 'den00qrb.us.oracle.com:/scratch/gbuora/Oracle_Home/user_projects/domains/bi_connect',
			bieeHome: 'den00qrb.us.oracle.com:/scratch/gbuora/Oracle_Home/bi/bifoundation/web/msgdb/pages/bieehome',
			obieeServer: 'den00qrb.us.oracle.com',
			inputRdpName: 'ConnectReporting.rpd',
			outputRdpName: 'ConnectReportingDev.rpd',
			dbXmlFileName: 'ConnectReportingDBPatchTest.xml',
			biServerXmlExecLoc: '/scratch/gbuora/Oracle_Home/user_projects/domains/bi_connect/bitools/bin/biserverxmlexec.sh',
			dataModelCmdLoc: '/scratch/gbuora/Oracle_Home/user_projects/domains/bi_connect/bitools/bin/data-model-cmd.sh'
		],            
		CM_appServer : [
            url: 't3://den00ohf.us.oracle.com:7081', 
            user: 'connectukuser',
            password: 'connect1',
            remoteWarName: 'clientManagement',
            targets : 'wls_server1',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/clientmanagement/service',
			svnOatsUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/qa/branches/AutomationScripts',
			svnSoapUiUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/qa/branches/Client_Management'
		],
        CM_UI_appServer : [
			url: 't3://den00ohf.us.oracle.com:7081', 
            user: 'connectukuser',
            password: 'connect1',
            remoteWarName: 'client-management-ui',                            
            targets : 'wls_server2',
			svnSrcUrl: 'svn+ssh://scmadm@hgbusvn.us.oracle.com/svn/hgbu_prod_fb/connect/branches/v1.0.0/clientmanagement-ui/'
        ],
		DBServer : [
            user: 'clientmanagement',
            password: 'myconnect1',
            url: "jdbc:oracle:thin:@den00ohd.us.oracle.com:1521:connectdev1"
        ],
		ORACLE_HOME : '/u01/app/oracle/middleware/product/oracle_home',
		JDK_TOOL : 'default',
		MVN_TOOL : 'M3',
		FORTIFY_HOME: '/u01/app/HPE_Security/Fortify_SCA_and_Apps_17.10/',
		NODE_HOME: '/scratch/tools/node-v4.4.5-linux-x64',
		SONAR_QUBE : [
			url : 'http://llg00fco.uk.oracle.com:9000',
            scannerToollName : 'DEVSonarQubeScanner',
            password : 'FLDnc6GB',
            username  : 'robot',
			timeout: 3600
		],
		MCAFEE_HOME: '/u01/app/mcafee/'
	]
}

// This method is a backup needs to be removed later
def getConfiguration() {
	return [ 
		prerel : [
			OWS_adaptor : [
				url: 't3://llg00cys.uk.oracle.com:8021', 
                user: 'weblogic',
                password: 'welcome123',
                remoteWarName: 'connect-OWS-adaptor',
                targets : 'wls_server1'
            ],
		    CM_reportJavaApp : [
				url: 't3://llg00cys.uk.oracle.com:8021',
				user: 'weblogic',
				password: 'welcome123',
				remoteWarName: 'ConnectReportingJava-EAR',
				targets : 'wls_server1'
			],
			CM_report_PRD : [
                user: 'weblogic',
                password: 'welcome123',
                serverInstance: 'ssi',
                rpdPwd:  'admin123',
                remoteWarName: 'ConnectReportingJava-EAR'
			],            
			CM_appServer : [
                url: 't3://llg00cys.uk.oracle.com:8021', 
                user: 'weblogic',
                password: 'welcome123',
                remoteWarName: 'clientManagement',
                targets : 'wls_server1'
			],
            CM_UI_appServer : [
				url: 't3://llg00cys.uk.oracle.com:8021', 
                user: 'weblogic',
                password: 'welcome123',
                remoteWarName: 'client-management-ui',                            
                targets : 'wls_server2' 
            ],
			DBServer : [
                user: 'clientmanagement',
                password: 'myconnect1',
                url: "jdbc:oracle:thin:@llg00gzd.uk.oracle.com:1521:connectdb"
            ],
			CM_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",
			CM_UI_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,plamen.iliev@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",
		    Reporting_failure_emailList: "senshan.yang@oracle.com,padmesh.neelamchalil@oracle.com,chhaya.shrestha@oracle.com,shane.bai@oracle.com,andy.darlow@oracle.com",
		    Reporting_sucess_emailList: "ben.griessenbock@oracle.com,victor.konopik@oracle.com,kunal.sahu@oracle.com,andy.darlow@oracle.com",
			OWS_adaptor_emailList: "padmesh.neelamchalil@oracle.com,shane.bai@oracle.com,andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,anirban.ab.banerjee@oracle.com"
		],
		dev : [
			OWS_adaptor : [
				url: 't3://den00ohf.us.oracle.com:7081', 
                user: 'connectukuser',
                password: 'connect1',
                remoteWarName: 'connect-OWS-adaptor',
                targets : 'wls_server1'
            ],
		    CM_reportJavaApp : [
				url: 't3://den00ohf.us.oracle.com:7081',
				user: 'connectukuser',
				password: 'connect1',
				remoteWarName: 'ConnectReportingJava-EAR',
				targets : 'wls_server1'
			],
			CM_report_PRD : [
                user: 'weblogic',
                password: 'welcome123',
                serverInstance: 'ssi',
                rpdPwd:  'admin123',
                remoteWarName: 'ConnectReportingJava-EAR'
			],            
			CM_appServer : [
                url: 't3://den00ohf.us.oracle.com:7081', 
                user: 'connectukuser',
                password: 'connect1',
                remoteWarName: 'clientManagement',
                targets : 'wls_server1'
			],
            CM_UI_appServer : [
				url: 't3://den00ohf.us.oracle.com:7081', 
                user: 'connectukuser',
                password: 'connect1',
                remoteWarName: 'client-management-ui',                            
                targets : 'wls_server2' 
            ],
			DBServer : [
                user: 'clientmanagement',
                password: 'myconnect1',
                url: "jdbc:oracle:thin:@den00ohd.us.oracle.com:1521:connectdev1"
            ],
			CM_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",
			CM_UI_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,plamen.iliev@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",
		    Reporting_failure_emailList: "senshan.yang@oracle.com,padmesh.neelamchalil@oracle.com,chhaya.shrestha@oracle.com,shane.bai@oracle.com,andy.darlow@oracle.com",
		    Reporting_sucess_emailList: "ben.griessenbock@oracle.com,victor.konopik@oracle.com,kunal.sahu@oracle.com,andy.darlow@oracle.com",
			OWS_adaptor_emailList: "padmesh.neelamchalil@oracle.com,shane.bai@oracle.com,andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,anirban.ab.banerjee@oracle.com"
		], 
		qa : [
            CM_appServer : [
                url: 't3://den00tnc.us.oracle.com:7021',
                user: 'connect',
                password: 'connect1',
                remoteWarName: 'clientManagement',
                targets : 'wls_cluster'
            ],
            CM_UI_appServer : [
				url: 't3://den00tnc.us.oracle.com:7021',
                user: 'connect',
                password: 'connect1',
                remoteWarName: 'client-management-ui',
                targets : 'ess_server1,wls_cluster'
            ],
            DBServer : [
				user: 'clientManagement',
                password: 'welcome123',
                url: "jdbc:oracle:thin:@den00szx.us.oracle.com:1521:infradb"
			],
            CM_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",
            CM_UI_emailList: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,plamen.iliev@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com" 
		],
		ORACLE_HOME : '/u01/app/oracle/middleware/product/oracle_home',
		JDK_TOOL : 'default',
		MVN_TOOL : 'M3',
		FORTIFY_HOME: '/u01/app/HPE_Security/Fortify_SCA_and_Apps_17.10/',
		NODE_HOME: '/scratch/tools/node-v4.4.5-linux-x64',
		SONAR_QUBE : [
			url : 'http://llg00fco.uk.oracle.com:9000',
            scannerToollName : 'DEVSonarQubeScanner',
            password : 'FLDnc6GB',
            username  : 'robot'
		],
		MCAFEE_HOME: '/u01/app/mcafee/'
	]

}