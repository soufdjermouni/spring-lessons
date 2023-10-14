## Astuces optimisation  

### Mode batch 
Nous pouvons l'activer au niveau de l'ORM (JPA), permet de faire des insertions en masse. 
Faire un démo  

### Ajout du Cache Second level : 
- Annotation `@Cacheable` sur les entités
  Faire un démo

### Pagination  
Utiliser les Objets : `Page` (SubList d'une list) et `Pageable`    

Dans le cadre de Spring Data Rest, on peut exploiter les paramètre ci-dessous :  
Parameters
 - `page`: The page number to access (0 indexed, defaults to 0).  
 - `size`: The page size requested (defaults to 20).  
 - `sort`: A collection of sort directives in the format ($propertyname,)+[asc|desc]?.  

Exemple : 
 >http://localhost:8080/people/?size=5
 
 > http://localhost:8080/api/products?page=0&size=10


"http://localhost:8080/api/products/search/findByCategoryId?id=5&page=0&size=20"

Faire un démo  


