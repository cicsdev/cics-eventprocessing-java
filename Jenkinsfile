pipeline {
    agent {
        docker {
            image 'openjdk:11'
            args  '-v /tmp:/tmp'
            reuseNode true
        }
    }
    
    stages {
        stage ("build") {
            steps {
                sh 'cd cics-eventprocessing-java && ./gradlew clean build'
            }
        }
    }
}
