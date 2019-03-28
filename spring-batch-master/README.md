# spring-batch
Sample Spring Batch to demonstrate running Spring Cloud Task on Cloud Foundry

## Setup steps
1. Compile the application
   ```bash
   gradlew clean build
   ```
2. Run the application
   ```bash
	java -jar build/libs/spring-batch-master-0.0.1-SNAPSHOT.jar
   ```	
3. Push the application into PCF
   ```bash
   cf push    
   ```

 Refer this wiki for full details how to use Credhub to store and retrieve the credentials:

 https://wiki.ae.sda.corp.telstra.com/display/SEG/PCF+Credhub+Integration
