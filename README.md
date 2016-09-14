![alt tag](https://mirror.openshift.com/pub/openshift/logo/openshift_logo_wide_blk250.png)

# "OpenShift" Spring Rest App Example


Example of an application that uses spring rest services. 
Hosted in **OpenShift** server.  

It is a basic CRUD to be used as a base project for those who wants to start to develop rest web services with spring.

The OpenShift `jbossews` cartridge documentation can be found at:

<<<<<<< HEAD
http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat teste
=======
http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat


.

#### Available Services

**GET**
	
http://<localhost:8080/springapp/person/
- *get a list of Messages*

http://<localhost:8080/springapp/person/{id}
- *get a Message by a given name*
	
**POST**

http://<localhost:8080/springapp/person
- *Adds a new Message in the list*
- *body example: { "name": "dan", "email": "any text" }*
	
**PUT**

http://<localhost:8080/springapp/person/{id}
- *Update a Message in the list. A name of a message should be passed as a url parameter*
- *body example: { "name": "dan", "email": "any text" }*

**DELETE** 

http://<localhost:8080/springapp/person/{id}
- *Deletes a Message in the list by a given name* 


 
###### Installation 
It is a Maven based project. To run in eclipse it is necessary to import this project as a Existing Maven Project:
- (Import->Maven->Existing Maven Project).
 
 
###### Developer
> Danilo Braga

> https://br.linkedin.com/in/bragadanilo
>>>>>>> 8e64519e15abcf59e4ec7f468db0882c1e61a17c
