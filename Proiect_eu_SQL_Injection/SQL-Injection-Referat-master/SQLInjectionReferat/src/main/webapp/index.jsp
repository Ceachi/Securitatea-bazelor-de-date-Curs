
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<h1>This is SQLInjection Example!</h1>

	<h2> Table User: </h2>
	<img src="database.JPG" alt="table User">
 
	<h2>Example of Tauntology</h2>
	
	The aim of a tautology-based attack is to inject code in one or more conditional statements such that the<br>
	evaluation is always true. In this type of SQLIA, an attacker exploits an injectable field that is used in a query’s<br>
	WHERE conditional i.e. the queries always return results upon evaluation of a WHERE conditional parameter.<br>
	All the rows in the database table targeted by the query are returned while transforming the conditional into a<br>
	tautology. Example: In this example, an attacker submits “ ’ or 1=1 - -” for the login input field (the input<br>
	submitted for the other fields is irrelevant). The resulting query is:<br><br><br>
	SELECT accounts FROM users WHERE<br>
	login=’’ or 1=1 -- AND pass=’’ AND pin=<br>
	The code injected in the conditional (OR 1=1) transforms the entire WHERE clause into a tautology<br>
	<br>
	<br>
	
	Practical:<br>
	
	Query: "SELECT * FROM User where userid='"+user+"' and password='"+password+"'";<br><br>
	SQL Injection:<br>
	   
	a) username : something' or 1=1 --<br>
	   password: '<br>
	b) username : ' or 1=1 -- <br>
	   password :' <br> 
	   
	   
	<form action="userCheck">
		<input type="text" placeholder="Username" name="username"> 
		<input type="text" placeholder="Password" name="password">
		<button type="submit">Login</button>
	</form>
	
	<br>
	Correction using PrepareStatement:<br><br>
	Query = "SELECT * FROM User WHERE userId = ? and password = ?"<br>
	<form action="userCheckCorrection">
		<input type="text" placeholder="Username" name="username"> 
		<input type="text" placeholder="Password" name="password">
		<button type="submit">Login</button>
	</form>
	<br><br>
	<h2>Example of Inference</h2>
	
	In this attack, the query is being modified into the form of an action which is executed based on the answer to a<br>
	true/-false question about data values in the database. In this type of injection, attacker try to attack a site that is<br>
	enough secured not to provide acceptable feedback via database error messages when an injection has<br>
	succeeded. The attacker must use a different method to obtain the response from the database since database<br>
	error messages are unavailable to him. In this situation, the attacker injects commands into the site and then<br>
	observes how the function/response of the website changes. By carefully observing the changing behavior of the<br>
	site , attacker can extrapolate not only vulnerable parameters, but also additional information about the values in<br>
	the database. Researchers have reported that with these techniques they have been able to achieve a data
	extraction rate of 1B/s .<br><br>
	
	
	Example: Consider two possible injections into the login field. The first being “legalUser’ and 1=0 - -” and the<br>
second, “legalUser’ and 1=1 - ”.These injections result in the following two queries:<br><br>
	SELECT accounts FROM users WHERE login=’legalUser’<br>
	and 1=0 -- ’ AND pass=’’ AND pin=0<br>
	SELECT accounts FROM users WHERE login=’legalUser’<br>
	and 1=1 -- ’ AND pass=’’ AND pin=0<br><br>
	
	
	In the first scenario, the application is secure and the input for login is validated correctly. In this case, both<br>
	injections would return login error messages, and the attacker would know that the login parameter is not<br>
	vulnerable. In the second scenario, application is insecure and the login parameter is vulnerable to injection. The<br>
	attacker submits the first injection and, gets a login error message, because it always evaluates to false.<br>
	However, the attacker does not know if this is because the application validated the input correctly and blocked<br>
	the attack attempt or because the attack itself caused the login error. The attacker then submits the second query,<br>
	which always evaluates to true. If in this case there is no login error message, then the attacker knows that the<br>
	attack went through and that the login parameter is vulnerable to injection<br><br>
	
	You can test it here for table User<br>	
	Query: "SELECT * FROM User where userid='"+user+"' and password='"+password+"'";<br>
	SQL Injection:<br>
	First scenario:<br>
	<!-- a) username : sandeep' and 1=0 --<br>
	   password: '<br>
	 Second scenario:<br>
	 a) username : sandeep' and 1=1 --<br>
	   password: '<br> -->
	   
	 a) username : sandeep' and 1=0 --<br>
	   password:'<br>
	   and we observe that we don't get the message in console: SELECT * FROM User WHERE userId = 'sandeep\' and 1=0 --' and password = '\'' <br>
	   this means that we got vulnerabilities in the query <br>
	   Now we know we can make sql injection : <br>
	 b) username : sandeep' and 1=1 --<br>
	   password:'<br>
	<form action="userCheck">
		<input type="text" placeholder="Username" name="username"> 
		<input type="text" placeholder="Password" name="password">
		<button type="submit">Login</button>
	</form>
	
 	<h2>Example Basic UNION Queries</h2>
 	
 	The set operator UNION is frequently used in SQL injection attacks. The goal is to manipulate a SQL statement into returning rows from another table<br><br>
 	Query: "SELECT * FROM  User where userid='"+user+"'";<br>
 	username: 'ramki' UNION SELECT * FROM User Where '1'='1' <br>
	<form action="example0">
		<input type="text" placeholder="Username" name="username"> 
		<input type="text" placeholder="Password" name="password">
		<button type="submit">Login</button><br>
	</form>
	
	Other examples:<br>
	1) ' ' or 1 = '1' <br>
	2) Find database name:  <br>
	First try: <br>
	' ' union select table_schema from information_schema.tables union select '1' <br>
	here we get the error message:  The used SELECT statements have a different number of columns <br>
	Second try: <br> 
	 ' ' union select 1, 1, 1, 1, 1, 1, 1,table_schema from information_schema.tables union select 1,1,1,1,1,1,1,'1' <br>
	where table_schema is the database name <br>
	3) Find table names : <br>
	' ' union select 1, 1, 1, 1, 1, 1, 1,table_name from information_schema.tables where table_schema = 'test' union select 1,1,1,1,1,1,1,'1'<br>
	4) Enumerate columns of table 'user' <br>
	' ' union select 1, 1, 1, 1, 1, 1, 1,column_name from information_schema.columns where table_name = 'user' union select 1,1,1,1,1,1,1,'1'<br>
	
	5) Dump userId and password: <br>
	' ' union select 1, 1, 1, 1, 1, 1, 1,concat(userId, ":",password) from User union all select 1,1,1,1,1,1,1,'1'<br>
	
	
	
	<h2> Example Piggy-Backed Queries</h2>
	
	In this SQLIA, attackers do not aim to modify the query instead; they try to include new and distinct queries into<br>
	the original query. This result database to receive multiple SQL queries and can be proved extremely harmful.<br>
	Example: If the attacker inputs “’; drop table users - -” into the pass field, the application generates the query:<br><br>
	SELECT accounts FROM users WHERE login=’doe’ AND<br>
	pass=’’; drop table users -- ’ AND pin=123<br><br>
	After execution of first query, the database would recognize the query delimiter (“;”) and proceed for the<br>
	injected second query. The execution of second query would lead to drop table ‘users’, which would likely<br>
	damage valuable information<br>
	
	Example:<br>
	Query: "SELECT * FROM User where userid='"+user+"' and password='"+password+"'";<br>
	username = sandeep<br>
	password = ';drop table user -- <br>
	
	
	<form action="userCheck">
		<input type="text" placeholder="Username" name="username"> 
		<input type="text" placeholder="Password" name="password">
		<button type="submit">Login</button>
	</form>
	
	
	
<!-- 	<h2> Example </h2>
	Query: "SELECT email, password, userId, firstName FROM User where email='"+email+"'"</br>	
	<form action="example2">
		<input type="text" placeholder="Email" name="email"> 
		<button type="submit">Submit</button>
	</form> -->
</body>
</html>
