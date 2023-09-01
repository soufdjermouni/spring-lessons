# OAS - The OpenAPI Specification : springdoc-openapi v2.1.0

The Swagger UI page will then be available at http://server:port/swagger-ui/index.html and the OpenAPI 
description will be available at the following url for json format: http://server:port/context-path/v3/api-docs

Exemple :   
https://medium.com/cuddle-ai/testing-spring-boot-application-using-wiremock-and-junit-5-d514a47ab931   

- Url Swagger-UI : http://localhost:8080/swagger-ui/index.html
  we can see all the endpoints for our application using the Swagger-UI. Open the swagger UI and check the endpoint

- Dépendance Maven  :   springdoc
Pour JDK 17 and Spring Boot 2.7.1, utiliser la dépendance maven ci-dessous :  
```xml 
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.9</version>
</dependency>
```

- Doc Swagger accesible via : [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Le JSON est accessible via l'url  suivante : [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- 
- Pour Springboot 3, utiliser la version suivante   

```xml 
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.0.3</version>
</dependency>
```

- Annotation Swagger-UI 
Sur les api()s rest (@RestController), on peut utiliser les annotation ci-dessous pour la décrire. 
  - @Operation : contient l'attribut summary pour décrire l'api par exemple 
  - @ApiResponses : contient les @ApiResponse avec les attributs : responseCode, description, schema et content

Exemple :   
http://localhost:8080/api/university?country=france

```java 
@GetMapping
@Operation(summary = "Get the universities for a given country")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Found the Universities for the given country",
        content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = UniversityDTO.class))}),
    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
        content = @Content),
    @ApiResponse(responseCode = "404", description = "Universities not found for the given country",
        content = @Content)})
public List<UniversityDTO> getUniversitiesForCountry(@RequestParam String country) {
    return universityService.getUniversitiesForCountry(country);
}
```