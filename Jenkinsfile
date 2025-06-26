pipeline {
    agent any // This pipeline can run on any available Jenkins agent

    environment {
        // WORKSPACE is a built-in Jenkins environment variable pointing to the job's workspace directory.
        PROJECT_DIR     = "${WORKSPACE}/KatalonTraining"
        // Define the absolute path to your Katalon Runtime Engine executable.
        KATALON_PATH    = "\\katalon\\Katalon_Studio_Engine_Windows_64-10.2.2\\katalonc.exe"
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code from GitHub...'
                // 'github-token' is the Jenkins credential ID for your GitHub access token.
                // This allows Jenkins to clone your private repository.
                git credentialsId: 'github-token', url: 'https://github.com/sudhansushekhar/KatalonTraining.git'
            }
        }

        stage('Run Katalon Tests via TestOps') {
            steps {
                echo "Running Katalon tests with TestOps reporting enabled..."
                // 'TESTOPS_API_KEY_VAR' is the name of the environment variable that will hold the secret value.
                withCredentials([string(credentialsId: 'Katalon-API-key', variable: 'TESTOPS_API_KEY_VAR')]) {
                    // Execute the Katalon Runtime Engine command in a shell.
                    // The backslashes '\' are used for line continuation in the shell script.
                    bat """
                    ${KATALON_PATH} -noSplash ^
                    -runMode=console ^
                    -projectPath="${PROJECT_DIR}/KatalonTraining.prj" ^
                    -retry=0 ^
                    -testSuitePath="Test Suites/LoginSuite2/LoginToCura2" ^
                    -executionProfile="QA" ^
                    -browserType="Chrome" ^
                    -apiKey="${TESTOPS_API_KEY_VAR}" ^
                    -reportFolder="Reports"
                    """
                }
            }
        }
    }

    post {
        // 'always' block ensures these actions run regardless of the pipeline's success or failure.
        always {
            echo 'Archiving Katalon reports...'
            // Archives all files named 'Reports' or within 'Reports' directories
            // found anywhere in the workspace.
            // 'fingerprint: true' ensures content-based tracking of artifacts.
            archiveArtifacts artifacts: '**/Reports/**', fingerprint: true
        }
    }
}
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
                echo 'Checking out source code (still useful for general pipeline management, even if TestOps pulls its own copy)...'
                git credentialsId: 'github-token', url: 'https://github.com/sudhansushekhar/KatalonTraining.git'
            }
        }

        stage('Trigger Katalon Tests via TestOps') {
            steps {
                echo "Triggering Katalon tests via TestOps..."

                withCredentials([string(credentialsId: 'Katalon-API-key', variable: 'TESTOPS_API_KEY_VAR')]) {
                    // This is a conceptual example. You need the exact TestOps API endpoint for triggering a Test Plan/Run.
                    // You might use 'curl' to make a POST request to the TestOps API.
                    // Refer to Katalon TestOps API documentation for the precise endpoint and JSON payload.
                    bat """
                    curl -X POST ^
                    -H "Content-Type: application/json" ^
                    -H "apiKey: ${TESTOPS_API_KEY_VAR}" ^
                    "https://testops.katalon.com/api/v1/your-project/${TESTOPS_PROJECT_ID}/test-plans/${TESTOPS_TEST_PLAN_ID}/execute" ^
                    -d "{ \\"agentId\\": \\"your_testops_agent_id\\" }" // Or specify cloud execution, etc.
                    """
                    // You might also need to poll the TestOps API for the execution status
                    // and report success/failure back to Jenkins. This can get more complex.
                }
            }
        }
    }

    post {
        always {
            echo 'No local reports to archive as TestOps handles reporting.'
            // You might still archive Jenkins' own build logs, but not Katalon reports.
        }
    }
}
