node('dind-node') {
  withMaven(maven:'M3') {
    stage('Checkout') {
      git url: 'https://github.com/piomin/sample-spring-microservices-new.git', credentialsId: 'piomin-github', branch: 'master'
    }
    stage('Build') {
      dir('config-service') {
        sh 'mvn clean install'
        def pom = readMavenPom file:'pom.xml'
        print pom.version
        env.version = pom.version
        currentBuild.description = "Release: ${env.version}"
      }
    }
    stage('Image') {
      dir ('config-service') {
        docker.withRegistry('https://192.168.99.100:5000') {
          def app = docker.build "piomin/config-service:${env.version}"
          app.push()
        }
      }
    }
  }
}