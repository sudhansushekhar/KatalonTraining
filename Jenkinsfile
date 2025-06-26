pipeline {
    agent any

    environment {
        // Path where your Katalon project will be checked out
        PROJECT_DIR     = "${WORKSPACE}/KatalonTraining"

        // Full path to your Katalon Runtime Engine (katalonc)
        KATALON_PATH    = "/opt/katalon/Katalon_Studio_Engine_Linux_64-8.6.5/katalonc"

        // Jenkins credential ID for TestOps API key (set this in Jenkins > Credentials)
        TESTOPS_API_KEY = credentials('9e57475d-303f-474f-b015-fa3a6c4c342f')
    }

    stages {
        stage('Checkout Code') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/sudhansushekhar/KatalonTraining.git'
            }
        }

        stage('Run Katalon Tests via TestOps') {
            steps {
                echo "Running Katalon tests with TestOps reporting..."

                sh """
                ${KATALON_PATH} -noSplash \
                -runMode=console \
                -projectPath="${PROJECT_DIR}/KatalonTraining.prj" \
                -retry=0 \
                -testSuitePath="Test Suites/YourSuite" \
                -executionProfile="default" \
                -browserType="Chrome" \
                -apiKey="${TESTOPS_API_KEY}" \
                -reportFolder="Reports" \
                -testOpsProjectId=2312994 \
                -testOpsReleaseId=948092
                """
            }
        }
    }

    post {
        always {
            echo 'Archiving Katalon reports...'
            archiveArtifacts artifacts: '**/Reports/**', fingerprint: true
        }
    }
}
