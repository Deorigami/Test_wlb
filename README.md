## What's Done
1. Movie List
   + Top Rated
   + Upcoming
   + Now PLaying
   + Popular
2. TV Series List
   + Airing Today
   + On The Air
   + Popular
   + Top Rated
3. Offline Mode
   + Movie List Only
4. Set Favourite / Watch Later ( Movie Only )
5. Search Funtion and Add Save To Local
6. Movie Detail with List of Cast Of The Movie

## Unit Test
This repository is already integrated with Sonarqube
I have made a task to run Unit Test until Update to Sonarqube
Just Run:

    /.gradlew codescan

For configuration, go to file *build.gradle.kts* in root level

```kotlin
sonarqube.properties {  
  property("sonar.projectName", "com.ardinata.test")  
  property("sonar.projectKey", "com.ardinata.test")  
  property("sonar.host.url", "http://localhost:8585")  
  property("sonar.language", "kotlin")  
  property("sonar.sourceEncoding", "UTF-8")  
  property("sonar.login", "admin")  
  property("sonar.password", "ardinata")  
  property("sonar.sources", "src/main/java")  
  property("sonar.binaries", "build/intermediates/classes/debug")  
  property("sonar.java.binaries", "build/intermediates/classes/debug")  
  property("sonar.tests", "src/test/java, src/androidTest/java")  
  property("sonar.java.test.binaries", "build/intermediates/classes/debug")  
  
  val unit = fileTree(mapOf("dir" to projectDir, "includes" to listOf("**/*.exec"))).files  
  property("sonar.jacoco.reportPaths", unit.joinToString(", "))  
  
  val jacocoReportXmlFiles = fileTree(  
  mapOf(  
  "dir" to project.projectDir,  
            "includes" to listOf("**/jacoco/*.xml")  
 ) ).files  
  property("sonar.coverage.jacoco.xmlReportPaths", jacocoReportXmlFiles.joinToString(", "))  
  property("sonar.java.coveragePlugin", "jacoco")  
  property("sonar.junit.reportsPath", "build/reports/tests/testDebugUnitTest")  
  property("sonar.android.lint.report", "build/reports/lint-results.xml")  
}
```

![CodescanResult](https://lh3.googleusercontent.com/c1cqycaDEMSdC3SlpYDAT1EeeWY70P2oMPbnyiFPUNqQHKSS7ohHWGafXMvUtrsbVcyHn48u0-wDtI6QjuhRPQi2jyuYbabS7q-G1qiN2Nz-988Sa9gnSO98Fe59HfsS7dLMuQyN3g=w2400)

## Which I have tested
#### Only In Service Layer
* UseCase
* RoomDB Repository
* Mapper
