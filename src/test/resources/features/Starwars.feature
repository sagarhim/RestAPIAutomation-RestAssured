
Feature: This feature will give you information for different starwars movie & their details 

  Scenario Outline: This scenario will fetch names of 1st 4 starwars movie.
    Given the user wants to know the names of starwars movies
    When the user call the starwars api to know the <version> movie name
    Then the api gives the requested movie <name>
    
    Examples:
    |version|name|
    |"1st"|"A New Hope"|
    |"2nd"|"The Empire Strikes Back"|
    |"3rd"|"Return of the Jedi"|
    |"7th"|"The Force Awakens"|
    
  Scenario Outline: This scenario will get the names of few warships.
    Given the user wants to know the names of warships in starwars movies
    When the user call the starwars api to know the name of the warship <id>
    Then the api gives the <name> and <model> of the warship
    
    Examples:
    |  id   |         name         |             model               |
    |   3   |   "Star Destroyer"   |"Imperial I-class Star Destroyer"|
    |   9   |	  "Death Star"       |"DS-1 Orbital Battle Station"    |
    |   10  |   "Millennium Falcon"|"YT-1300 light freighter"        |
    
  Scenario Outline: This scenario will get the names of few planets named in the movie.
    Given the user wants to know the names of planets in starwars movies
    When the user call the starwars api to know the name of the planet <id>
    Then the api gives <name of the planet>
    
    Examples:
    | id | name of the planet |
    | 1  |     "Tatooine"     |
    | 2  |     "Alderaan"     |
    | 3  |     "Yavin IV"     |