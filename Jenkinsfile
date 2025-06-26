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

    // --- ADD THIS 'post' SECTION FOR EMAIL NOTIFICATIONS ---
    post {
        always { // This block runs regardless of the build outcome
            echo 'TestCloud handles execution and reporting to TestOps. No local reports to archive.'
        }
        success { // This block runs ONLY if the build fully succeeds
            emailext (
                to: 'your_email@example.com', // <<< IMPORTANT: Replace with the actual recipient email
                subject: "Jenkins Pipeline: ${currentBuild.displayName} - SUCCESS",
                body: """
                    <p>Hello Team,</p>
                    <p>The Jenkins pipeline '${currentBuild.displayName}' has completed successfully!</p>
                    <p>Build Number: ${currentBuild.number}</p>
                    <p>Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <p>Check Katalon TestOps for test results: <a href="https://testops.katalon.io/project/${env.TESTOPS_PROJECT_ID}/test-runs">Katalon TestOps Link</a></p>
                    <p>Regards,</p>
                    <p>Your Jenkins Bot</p>
                """
            )
        }
        failure { // This block runs ONLY if the build fails
            emailext (
                to: 'your_email@example.com, other_team_member@example.com', // <<< IMPORTANT: List your recipients here (comma-separated)
                subject: "Jenkins Pipeline: ${currentBuild.displayName} - FAILED!",
                body: """
                    <p>Hello Team,</p>
                    <p>The Jenkins pipeline '${currentBuild.displayName}' has **FAILED**!</p>
                    <p>Build Number: ${currentBuild.number}</p>
                    <p>Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    <p>Please investigate the console output for details.</p>
                    <p>Regards,</p>
                    <p>Your Jenkins Bot</p>
                """
            )
        }
        // You can add more conditions if needed, like:
        // unstable { ... } // For builds that passed but had warnings/failures
        // fixed { ... }    // For builds that were previously failing and are now successful
        // aborted { ... }  // For builds manually aborted
    }
}
