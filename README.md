Task F.c9  Quick Poll - RestTemplate, testing controllers
(https://app.box.com/s/zgy45wz6rjcrvelq5bklxbgea4x2jdkv)

YouTube link: https://youtu.be/VjlHLNz2O1k
    
For Ubuntu 20+:

1.   Download and instal JDK 16:
       https://www.oracle.com/java/technologies/javase-jdk16-downloads.html
       Command : $ tar xzvf /home/Users/<USER-NAME>/Downloads/jdk-16_linux-x64_bin.tar.gz
       Command : sudo cp /home/Users/<USER-NAME>/Downloads/jdk-16 /usr/lib/jvm
       Command : export JAVA_HOME=/usr/lib/jvm/java-16
       Command : export PATH=$PATH:$JAVA_HOME/bin
2. Download the latest version from the Apache Maven site, select the Maven binary tar.gz file, for example: apache-maven-3.6.3-bin.tar.gz. (http://maven.apache.org/download.cgi)    
3. Open a new Terminal and install Maven
      Command : $ cd /home/<USER-NAME>/Downloads
      Command : $ tar xzvf /home/Users/<USER-NAME>/Downloads/apache-maven-3.6.3-bin.tar.gz
      Command : $ echo "PATH=/home/<USER-NAME>/Downloads/apache-maven-3.6.3/bin:$PATH" >> ~/.profile
      Command : $ source ~/.profile
4.  Go to directory /quick-poll
      Command : ./mvnw spring-boot:run 
5. Follow the link "http://localhost:8080/swagger-ui.html"

For MacOS Sierra+:

1. Download and instal JDK 16:
      https://www.oracle.com/java/technologies/javase-jdk16-downloads.html
2. Download the latest version from the Apache Maven site, select the Maven binary .zip file, for example: apache-maven-3.6.3-bin.zip
3. Extract distribution archive in directory "/Users/<USER-NAME>/Downloads/apache-maven-3.6.3"
4. Open a new Terminal 
       Command : export PATH=/Users/<USER-NAME>/Documents/java/apache-maven-3.6.3/bin:$PATH
5.  Go to directory /quick-poll
       Command : ./mvnw spring-boot:run 
6. Follow the link "http://localhost:8080/swagger-ui.html"

For Windows 10+:

1. To install the latest version Maven on windows, select the Maven zip file, for example apache-maven-3.6.3-bin.zip.
      https://maven.apache.org/download.cgi   Unzip it to the folder "C:\Maven\"
2. Download and instal JDK and JRE from "C:\Java\jdk-16" this:
      https://www.oracle.com/java/technologies/javase-jdk16-downloads.html
3. Open Command prompt with "run as administrator":
      Command : setx JAVA_HOME "C:\Java\jdk-16"
      Command : setx M2_HOME "C:\Maven\apache-maven-3.6.3"
      Command : setx MAVEN_HOME "C:\Maven\apache-maven-3.6.3"
4. Open System Properties via Run or Command Line: 
      Press Windows + R keys together, type the command “sysdm.cpl” in the Run dialog box and press Enter. Click the Advanced. Click Environment Variables.
      Add path in PATH "C:\Maven\apache-maven-3.6.3\bin" and "C:\Java\jdk-16\bin" 
5.  Go to directory \quick-poll
       Command : .\mvnw spring-boot:run 
6. Follow the link "http://localhost:8080/swagger-ui.html"


Example for Ubuntu:

![Ubuntu](/docs/screenshots/Ubuntu(1).png)

Example for MacOS:

![MacOS](/docs/screenshots/MacOS(1).png)

Example for Windows:

![Windows](/docs/screenshots/Windows(1).png)
