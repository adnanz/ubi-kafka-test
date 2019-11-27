# ubi_kafka

This project processes the sampled events from the provided data file and generate the following 
information per player (profileId).

- First time seen (you can use the serverDate attribute in the header)
- Last time seen (you can use the serverDate attribute in the header)
- Number of match played

# Create jar

To create jar go to the root of the project and execute the following command

    mvn clean package -DskipTests
    
# Getting started
 
Run the jar the server with the following command

    java -jar 
    
# Points To Note

1. The application has the capability to scale vertically within an instance. The user
can add more consumers in `application.properties` by increasing the `kafka.partitions`
2. The application cannot be scaled horizontally amongst nodes since state is stored
in a Singleton class within the application. The next step would have been to use
Kafka Streams to setup a topology and make the application stateful.
3. I'm expecting each json object to come in as a separate message.
    
# Results
 
1. Result will be printed when the application shuts down. It will print all 
the stats of all players
2. `serverDate` attribute in the header is the source of truth for dates
 
