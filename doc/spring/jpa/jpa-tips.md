## JPA/JPQL Astuces   

- Utilisation de `h-schema` dans les requêtes natives   
You could use {h-schema} for native SQL queries (verified for Hibernate v4). This is defined by hibernate.default_schema configuration property.
Example:  

Dans application.yml , add :
```yaml
spring.jpa.properties: 
    hibernate.default_schema = BDD_NAME
```

Dans les repository :  
```java  
   @Query(value = SELECT * FROM {h-schema}LOCATION",  nativeQuery = true)
   Set<Location> findAll***;
```