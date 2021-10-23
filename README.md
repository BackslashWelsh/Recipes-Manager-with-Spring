# Recipe-Manager-with-Spring

<h3>Recipe Manager with Spring and H2 database.</h3>
What project currently have:

 - Bidirectional @OneToMany relationship database: User can have many Recipes;
 - Basic registration and authorization systems;
 - Auto-updating Date field when an entity changed;
 - Custom evaluator to check if user own a recipe. It uses Bean injection in @PreAuthorize annotation;
 - Custom Constraint validator for Email that check: length, regexp, uniqueness in database and response with a message error according to the type of error;
 - Recipe service have basic CRUD operations and a search by name or category.
<hr>
 
The main problems when creating this project:
<ol>
 <li>Program throw Exception when I use a repository in custom Constraint Validator.</li>
<p>Spring makes validation (first time) of fields and uses the repository when you pass an entity through a controller. 
Then you are saving the entity to a database. The database makes its own validation with 
a help of the repository (second time) but the first operation didn't end and then an Exception appears.</p>
Solution:<br>
Disable database validation by adding option to '/resources' file 'application.properties':
<code>spring.jpa.properties.javax.persistence.validation.mode=none</code>
<p>
 <li>Spring didn't autowire a bean in @PreAuthorize annotation.</li> 
<br>
Solution:<br>
Enable Global Method for Post by adding to Security Configuration class:
<code>@EnableGlobalMethodSecurity(prePostEnabled = true)</code>
</ol>
