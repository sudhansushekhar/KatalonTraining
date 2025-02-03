<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>LoginToCura2</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>false</rerunImmediately>
   <testSuiteGuid>784c704b-3329-451c-9fce-46af2f9e2135</testSuiteGuid>
   <testCaseLink>
      <guid>985a8cf2-6a8b-4cb6-a4b0-504a07891294</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Login/LoginWithTestData</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>4d7d1226-a1b6-4118-893c-d5289d5b96ef</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/LoginData/loginCuraData</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>4d7d1226-a1b6-4118-893c-d5289d5b96ef</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>userName</value>
         <variableId>c1e3016d-650f-464b-9d22-afde9126f844</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>4d7d1226-a1b6-4118-893c-d5289d5b96ef</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>password</value>
         <variableId>bd0a37b3-44d3-46a6-b387-b53dbe0ac45a</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>c4b520c0-f166-4a8a-b271-fed778673358</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Login/LoginUsingGlobalVariables</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>6c8cb84f-e989-47c2-b15e-7d5ebc23ff46</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/LoginData/loginCuraData</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
</TestSuiteEntity>
