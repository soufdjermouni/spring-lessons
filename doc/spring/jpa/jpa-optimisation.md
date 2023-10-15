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


### @BatchSize 
Dans la cas d'une relation @OneToMany , qui est par défaut en mode Lazy, on risque d'avoir le 
n+1 problème.   

>Among other solutions, Hibernate provides @BatchSize to solve this issue.
Instead of (N + 1) this will roughly execute (N/M + 1) queries.

Pour le code suivant :    
```java
List<Customer> customers = entityManager.createQuery("from Customer", Customer.class).getResultList();
for (Customer customer : customers) {
customer.getRequests().size();
}
```

Sans @BatchSize, on aura  

select .. from request where customer_id=? (9 times)

Hibernate: select customer0_.id as id1_1_, customer0_.first_name as first_na2_1_, customer0_.last_name as last_nam3_1_ from customer customer0_
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?
Hibernate: select requests0_.customer_id as customer3_4_0_, requests0_.id as id1_4_0_, requests0_.id as id1_4_1_, requests0_.code as code2_4_1_, requests0_.customer_id as customer3_4_1_ from request requests0_ where requests0_.customer_id=?


Avec @BatchSize    

Hibernate: select customer0_.id as id1_1_, customer0_.first_name as first_na2_1_, customer0_.last_name as last_nam3_1_ 
from customer customer0_
Hibernate: select requests0_.customer_id as customer3_4_1_, requests0_.id as id1_4_1_, requests0_.id as id1_4_0_, 
requests0_.code as code2_4_0_, requests0_.customer_id as customer3_4_0_ from request requests0_ where requests0_.
customer_id in (?, ?, ?, ?, ?, ?, ?, ?, ?)

Hibernate @BatchSize greatly improves the performance by solving (N + 1) problem. However it should be noted that this 
still executes multiple queries if your entity size is greater than batch size. This is why the documentation itself 
recommends using direct JOIN or projection (or even custom IN).
Also it should be noted that Hibernate executes queries even if you check the size, check whether it is empty. Be 
careful of this as this might not be your real intention.
One more thing to keep in mind is that your database will have a limit (maximum ids inside the clause) for IN clauses.


