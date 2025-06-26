pipeline {
    agent any

    environment {
        PROJECT_DIR     = "${WORKSPACE}/KatalonTraining"
        KATALON_PATH    = "/opt/katalon/Katalon_Studio_Engine_Linux_64-8.6.5/katalonc"
        TESTOPS_API_KEY = credentials('9e57475d-303f-474f-b015-fa3a6c4c342f') // your Jenkins credential ID
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
            node {
                echo 'Archiving Katalon reports...'
                archiveArtifacts artifacts: '**/Reports/**', fingerprint: true
            }
        }
    }
}
