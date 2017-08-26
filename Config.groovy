
def get() {	
	return [		          
		devAppServer : [
            url: 't3://llg00cys.uk.oracle.com:8021', 
            user: 'weblogic',
            password: 'welcome123'
		],
		devDbServer : [
            user: 'clientmanagement',
            password: 'myconnect1',
            url: "jdbc:oracle:thin:@llg00gzd.uk.oracle.com:1521:connectdb"
        ],
		qaAppServer : [
            url: 't3://llg00epj.uk.oracle.com:7021', 
            user: 'weblogic',
            password: 'welcome123'
		],
		qaDbServer : [
            user: 'clientmanagement',
            password: 'welcome123',
            url: "jdbc:oracle:thin:@llg00epk.uk.oracle.com:1521:CONNECTDB11"
        ],
		CM_EMAIL_LIST: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,sam.bond@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",
		CM_UI_EMAIL_LIST: "andy.darlow@oracle.com,michael.hutcherson@oracle.com,plamen.iliev@oracle.com,matheu.chambers@oracle.com,nathanael.dooley@oracle.com",		
		MCAFEE_HOME: '/u01/app/mcafee/',
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
		]		
	]
}

