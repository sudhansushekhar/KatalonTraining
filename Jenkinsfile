pipeline {
    agent any

    environment {
        // Your API key is still needed for Jenkins to authenticate with TestOps API
        // The TestOps project ID and Test Plan ID will be needed
        TESTOPS_PROJECT_ID = "2312994" // Find this in TestOps URL or settings
        TESTOPS_TEST_PLAN_ID = "1423998" // Find this in TestOps URL or settings
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

                    bat """
                    curl -X PUT ^
                    -H "Content-Type: application/json" ^
                    -H "Authorization: %AUTH_HEADER_VALUE%" ^
                    "https://testops.katalon.io/api/v1/run-configurations/${env.TESTOPS_RUN_CONFIG_ID}/execute" ^
                    -d "{}"
                    """
                    // After triggering, you might want to add steps to poll the TestOps API
                    // to check the execution status and update the Jenkins build status accordingly.
                    // This is more advanced and involves parsing JSON responses.
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
