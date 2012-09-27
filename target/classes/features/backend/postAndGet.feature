#--Teoca Vasile Bogdan--
#--Youtube search automation test demo--

Feature: Youtube HTTP Post and Get Interogation

Scenario Outline: 1 HTTP Get
	Given a url connection "<link>"
	And I should get response code <resp_code>
	Then I make get interogation and view server message
	Examples:
	| link                      | resp_code |
	| http://www.google.com     | 200       |
	| http://www.udemy.com      | 200       |
	| http://www.wikipedia.org  | 200       |
	| http://www.facebook.com   | 200       |
	| http://www.esgdfg.com     | 404       |	
	
Scenario:2 HTTP Post
	Given a url connection "http://dippy.trei.ro"
	And I should get response code 200
 	Then I make post message with user: "demo" and password: "demopass"