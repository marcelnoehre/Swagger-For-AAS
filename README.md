# Swagger for Asset Administration Shells

## 1: AASX file server
- Download: <a href="https://github.com/admin-shell-io/aasx-package-explorer.git">aasx-package-explorer</a>
- Run: AasxPackageExplorer.exe
- Load AAS: drag + drop .aasx file
- Serve REST server: Shift + F6

## 2: Generate Swagger JSON
- Run SwaggerService.java (update attributes if necessary)
- Load aas.json into <a href="https://editor.swagger.io/">Swagger-Editor</a> to see the result

## 3: Generate Swagger CodeGen
- Run "mvn clean compile" in the root directory of this project
- Using Eclipse, you need to generate a accessible target folder with the command "mvn eclipse:eclipse"
- The results can be found in "target/generated-sources/swagger/src/main/java/swagger2java"
- Run SwaggerToJavaTest.java to check if the generated classes work (update attributes if necessary)

## 4: Unit Tests
- SwaggerServiceTest.java:
  - Test whether the SwaggerService has generated a valid result
- SwaggerToJavaTest.java:
  - Test whether the requests can be sent with the help of the generated Java classes
- CompareResponsesTest.java:
  - Compare whether the responses of the generated Java models correspond to the responses of the REST requests.
