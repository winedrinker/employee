This maven project contains two runnable spring boot modules. Either is a console application, another one is a web application.

You can run modules if you follow next steps:<br/>
Step 1: ```git clone https://github.com/winedrinker/employee.git```<br/>
Step 2: ```cd employee```<br/>
Step 3: ```mvn clean install```<br/>

Step 4: Run console application<br/>
* ```cd consoleapp```
* ```mvn spring-boot:run```
* The app will display the employees grouped by department sorted by department name and employee's last name.

Step 5: Run web application<br/>
* ```cd webapp```
* ```mvn spring-boot:run```
* The web app contains three REST endpoints:
    * **/employee/sort** -> Returns all employees full name ordered by their surname.
    * **/employee/{employee-first-name}/{employee-last-name}/department** ->
    Returns departments the employee belongs to.
    * **/department/{department-name}/employees** -> Returns the list of employees belonging to the given department sorted by their last name. 
