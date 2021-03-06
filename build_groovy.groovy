#!groovy
node {
    def server = Artifactory.newServer url: SERVER_URL, credentialsId: CREDENTIALS
    def rtMaven = Artifactory.newMavenBuild()

    stage 'Build'
        git url: 'https://github.com/arjunamayakara/springboot-rest-example.git'

    stage 'Artifactory configuration'
        rtMaven.tool = MAVEN_TOOL // Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: server
        def buildInfo = Artifactory.newBuildInfo()

    stage 'Exec Maven'
        rtMaven.run pom: 'springboot-rest-example/pom.xml', goals: 'clean install', buildInfo: buildInfo

    stage 'Publish build info'
        server.publishBuildInfo buildInfo
}
