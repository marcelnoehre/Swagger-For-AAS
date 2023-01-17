# Swagger for Asset Administration Shells

## 1: AASX file server
- Download: <a href="https://github.com/admin-shell-io/aasx-package-explorer.git">aasx-package-explorer</a>
- Run: AasxPackageExplorer.exe
- Load AAS: drag + drop .aasx file
- Serve REST server: Shift + F6

## 2: Generate Swagger JSON
- Run SwaggerServiceTest.java (update attributes if necessary)
- Load aas.json into <a href="https://editor.swagger.io/">Swagger-Editor</a> to see the result

## 3: Generate Swagger CodeGen
- Run "mvn clean compile" in the root directory of this project
- Using Eclipse, you need to generate a accessible target folder with the command "mvn eclipse:eclipse"
- The results can be found in "target/generated-sources/swagger/src/main/java/swagger2java"
- Run SwaggerToJavaTest.java to check if the generated classes work (update attributes if necessary)