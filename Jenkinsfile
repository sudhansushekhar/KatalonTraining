pipeline {
    agent any // This pipeline can run on any available Jenkins agent

    environment {
        // WORKSPACE is a built-in Jenkins environment variable pointing to the job's workspace directory.
        PROJECT_DIR     = "${WORKSPACE}/KatalonTraining"
        // Define the absolute path to your Katalon Runtime Engine executable.
        KATALON_PATH    = "/opt/katalon/Katalon_Studio_Engine_Linux_64-8.6.5/katalonc"
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
                    sh """
                    ${KATALON_PATH} -noSplash \\
                    -runMode=console \\
                    -projectPath="${PROJECT_DIR}/KatalonTraining.prj" \\
                    -retry=0 \\
                    -testSuitePath="Test Suites/YourSuite" \\
                    -executionProfile="default" \\
                    -browserType="Chrome" \\
                    -apiKey="${TESTOPS_API_KEY_VAR}" \\
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
