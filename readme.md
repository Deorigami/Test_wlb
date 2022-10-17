## Unit Test
Untuk Unit Test repository ini sudah terintegrasi dengan Sonarqube
Sudah saya buatkan task untuk running Unit Test sampai Update Ke Sonarqube
Cukup Jalankan :

    /.gradlew codescan

Untuk Cofigurasinya berada di file *build.gradle.kts* di root level

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

![CodescanResult](https://doc-04-2c-docs.googleusercontent.com/docs/securesc/2rqnopokvg7jmaiv3hb3ph11cr1uefkn/hkkdhnvnvcamiocdkg9hds72d1jtrb8d/1665977625000/05662697255581183800/05662697255581183800/1DrAs1mKwON07f3tTgDK7nvvvdjr5WBGv?e=view&ax=ALW9-sB0YcHedphNT7pyR9CnR-Ms5tV7BT9eH9SV7b7nN60up0OASS2CVOwl46xTKYzBWecfJGwIDuIrSKwX6Os5Lg2oEJQsn-UAj8HtfE1FpIZBZasl6UJYC_BhbZf-PzFvuSypqxC6X-85HkaDU0jiE3Cs0c6HcqsKX5_28Th0krqnzBqqzfy_2Udf-ZcVZXp_onVzxH8cBEyztjx67VM-bC0lfnCBoV0wA7ygTqrVwSQGbdAS3fQZ9mw7HKOByUlV4bulOP5VCiUB7C54MYLi6u6VxMbvepZ-kWMfv-NBlCJadCRuEBBheudsOkTyvzon7dsCjgPN0Urz8eMrBw2prcYg94yn9wVU0ftI1CpqWesxvR71Z8SY90kpnKG8a7Dy-yLT5Y00c5e6_rYeSFnHqgr8v0uce_Z1c3uedBnAvvTTYhuxNoXs0Q1-xh0BHRrmcv4rpRiHzz-E3IYNvQRnUylYjKmRvVoM9ekXDpdwltlXwTsA4IQOJg5PuJzd3Fa_Fw8wQknJB4QFSzSwb1jgFCZ8MAmkDE_ssbMbM3delR5VDVc7KnYgZoDOswOs8rN1e5EKrWsEm8m2vGX_qNrNcJtVye7UsL_BdmBkoODGWzwejHvt6cWv9UIfVNdNKi5Occ_DW_t41ElNzUvJCXqJamTH-htBqK9CJgg4KcC7_u7KM7inc0qEMuQ3WbRUUowEAseGCE5q89jnbig9gwWB0BV7Noa5aGKUj9CFRgItG0YTGywc5SZEh5Rc4r_Lay8LtQQAXzrswDdJcnUfS-TSISfYN1YSrOgWFEhVYalUWBa_uVgd_d93J4irrqBRsiLGHtkhzq0-l51L6_btbximyPe7PfXwUVWzYD0WIws_MNfysFJ_eWnAYJx0-2X8WnoJU8rm2b15GhxMHcHFIlMSLFHD8uUh2fX6Q6ZSYY4IxvfR5wtTGmHJB2Wt17n2uXfMWg&uuid=fade5b55-304b-47b6-8e32-9f22962b93f7&authuser=0)