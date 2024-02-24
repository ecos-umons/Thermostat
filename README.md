# README

This project is developed and used as part of a Software Modeling course
by the Software Engineering Lab of University of Mons, Belgium.

It provides an example of a statechart-based Thermostat controller in Java.
The statechart was created with Yakindu Statechart Tools
and Java code was generated from the statechart.
A Swing GUI executes the behaviour of this generated statechart code.

==================

## Version history

In progress:
 - statechart-level unit tests
 - JUnit tests in Java

Version 0.5 - 24 February 2024 (Tom Mens)
 - mainly code improvements
   
Version 0.4 - 4 September 2020 (Gauvain Devillez)

- added gradle build automation tool. Use
  - `gradle build`        to compile, run unit tests and generate the jar file
  - `gradle javadoc`   to generate javadoc documentation
  - `gradle run`		 to execute the application
  - `gradle jar`		 to generate the jar file
  - `gradle test`		 to run the unit tests
  - `gradle classes`   to compile
	
Version 0.3 - 24 August 2020 (Tom Mens, Gauvain Devillez)

* added operation in statechart for measuring outside temperature, and callback in Java code
* improved GUI by adding the current time; and changing some button labels dynamically


## Contributors

- Tom Mens (versions 0.1, 0.2, 0.3, 0.5)
- Gauvain Devillez (changes to Swing GUI in v0.3, adding Gradle support in v0.4)
- Mohammed Saidi (version 0.0)

