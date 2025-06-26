pipeline {
    agent any

    environment {
        // Your API key is still needed for Jenkins to authenticate with TestOps API
        // The TestOps project ID and Run Configuration ID will be needed
        TESTOPS_PROJECT_ID = "2312994" // Your project ID
        TESTOPS_RUN_CONFIG_ID = "1423998" // <<< CHANGED: Renamed from TESTOPS_TEST_PLAN_ID to TESTOPS_RUN_CONFIG_ID to match usage
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code (still useful for general pipeline management)...'
                git credentialsId: 'github-token', url: 'https://github.com/sudhansushekhar/KatalonTraining.git'
            }
        }

        stage('Trigger Katalon Tests via TestOps (using TestCloud)') {
            steps {
                echo "Triggering Katalon tests via TestOps, leveraging TestCloud for execution..."

                withCredentials([
                    string(credentialsId: 'katalon-testops-email', variable: 'TESTOPS_EMAIL_VAR'),
                    string(credentialsId: 'Katalon-API-key', variable: 'TESTOPS_API_KEY_VAR')
                ]) {
                    script {
                        def authString = "${TESTOPS_EMAIL_VAR}:${TESTOPS_API_KEY_VAR}"
                        def encodedAuthString = java.util.Base64.encoder.encodeToString(authString.getBytes("UTF-8"))
                        env.AUTH_HEADER_VALUE = "Basic ${encodedAuthString}"
                    }

                    // For debugging, keep this in for now to ensure the value is correct
                    echo "DEBUG: TESTOPS_RUN_CONFIG_ID value before curl: ${env.TESTOPS_RUN_CONFIG_ID}"

                    bat """
                    curl -X PUT ^
                    -H "Content-Type: application/json" ^
                    -H "Authorization: %AUTH_HEADER_VALUE%" ^
                    "https://testops.katalon.io/api/v1/run-configurations/${env.TESTOPS_RUN_CONFIG_ID}/execute" ^
                    -d "{}"
                    """
                }
            }
        }
    }

    post {
        always {
            echo 'TestCloud handles execution and reporting to TestOps. No local reports to archive.'
        }
    }
}
