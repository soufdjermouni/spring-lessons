Spring Security est un sujet sur lequel il est toujours possible d’en apprendre un peu plus, d’aller un peu plus en 
profondeur. Une connaissance encyclopédique du sujet n’est cependant pas nécessaire pour mettre en place une
sécurité raisonnable sur une application. 

Voici les quelques points à conserver sur une fiche pense-bête pour s’y retrouver aisément :

>Spring Security est pour l’essentiel un enchainement de filtres à appliquer (ou non) et à paramétrer.  

> La sécurité apportée à l’application repose sur le duo authentification / autorisation   

> La classe annotée @EnableWebSecurity est celle dans laquelle sont paramétrés les filtres à appliquer
et les URLs à protéger : c’est elle qui permet de comprendre en un clin d’oeil quelles sont 
les sécurités mises en oeuvre dans une application.