#--Teoca Vasile Bogdan--
#--Youtube search automation test demo--

Feature: Youtube test 1

Scenario: Youtube Search And Play Video Test Demo
	Given I search for: "Pickle & Hyde: A Cucumber Story" search term
	Then I should see result list
	Given I select resul list item "Pickle & Hyde: A Cucumber Story"
	Then I should see the video
	Then wait for 20000
	Then close browser and quit
	

