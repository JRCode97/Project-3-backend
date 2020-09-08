DROP TABLE IF EXISTS solution;
DROP TABLE IF EXISTS bug_report;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS reset_password;

CREATE TABLE reset_password(
	id int PRIMARY KEY AUTO_INCREMENT,
	username varchar(200) UNIQUE,
	email varchar(200),
	api_key varchar(200)
);

CREATE TABLE application (
application_id int(11) PRIMARY KEY AUTO_INCREMENT,
application_title varchar(1000) UNIQUE NOT NULL,
application_git_link varchar(1000) NOT NULL
);

CREATE TABLE bug_report (
  bug_report_id int(11) NOT NULL AUTO_INCREMENT,
  bug_report_title varchar(300) NOT NULL,
  bug_report_description varchar(25000) NOT NULL,
  bug_report_reproduction_steps varchar(25000) DEFAULT NULL,
  client_username varchar(1000) DEFAULT NULL,
  severity varchar(1000) DEFAULT NULL,
  priority varchar(1000) DEFAULT NULL,
  status varchar(1000) DEFAULT NULL,
  location varchar(1000) DEFAULT NULL,
  approved_time mediumtext DEFAULT NULL,
  resolved_time mediumtext DEFAULT NULL,
  date_created mediumtext DEFAULT NULL,
  point_value int(11) DEFAULT NULL,
  application_id int(11) NOT NULL,
  PRIMARY KEY (bug_report_id),
  CONSTRAINT FK_application_id FOREIGN KEY (application_id) REFERENCES application (application_id)
);
	
CREATE TABLE client (
  client_id int(11) NOT NULL AUTO_INCREMENT,
  client_first_name varchar(1000) NOT NULL DEFAULT 'hero with no name',
  client_last_name varchar(1000) NOT NULL DEFAULT 'and no family name',
  client_username varchar(1000) NOT NULL,
  client_email varchar(1000) NOT NULL,
  client_password varchar(1000) NOT NULL,
  client_role int(11) NOT NULL,
  PRIMARY KEY (client_id),
  UNIQUE KEY client_username (client_username)
);

CREATE TABLE solution (
  solution_id int(11) NOT NULL AUTO_INCREMENT,
  solution_title varchar(2000) DEFAULT NULL,
  solution_description varchar(25000) DEFAULT NULL,
  status varchar(2000) DEFAULT NULL,
  submitted_time mediumtext DEFAULT NULL,
  bug_report_id int(11) DEFAULT NULL,
  solver_client_id int(11) DEFAULT NULL,
  PRIMARY KEY (solution_id),
  CONSTRAINT FK_bug_report_id FOREIGN KEY (bug_report_id) REFERENCES bug_report (bug_report_id),
  CONSTRAINT FK_client_id_solution FOREIGN KEY (solver_client_id) REFERENCES client (client_id)
);

INSERT INTO application(application_id,application_title,application_git_link) VALUES (1,'Bug Bounty System','git.com');
INSERT INTO application(application_id,application_title,application_git_link) VALUES (2,'Pizza Ordering Application','www.site1.com');
INSERT INTO application(application_id,application_title,application_git_link) VALUES (237,'Expense Reimbursement System','www.asite2.com');
INSERT INTO application(application_id,application_title,application_git_link) VALUES (238,'DriveForce','www.bsite3.com');
INSERT INTO application(application_id,application_title,application_git_link) VALUES (424,'Twitter','https://github.com/twitter');

INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (1,'The java class keeps change the name','Hello I Would like to know how to make somthing that looks like "Glass" the example would be in the game called Pikmin.','Hello I Would like to know how to make somthing that looks like "Glass" the example would be in the game called Pikmin.','Nuria','High','Low','Resolved','Line 3',1588465092328,1588492948000,1588464000000,20,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (2,'Intellij Idea CE - how to run Java main class Called: Doom','I''ve been using IntelliJ CE for a Spring Boot project. The Run/Debug has always been a little flaky, but I''ve usually been able to figure it out. But now, it''s only giving the option to create a template. So I''m trying to create a template to run a Spring Boot application.Called the doomslayers','Step 1: installing the Java Runtime Environment (JRE) for browsers and call dark magic to power the world.  Once the installer has finished downloading, run it to begin the installation. On OS X, double-click the .dmg file to begin the installation.','TheRaidman','High','Medium','Resolved','Line 55',1588551809306,1588555497000,1588550400000,10,237);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (3,'Jackson objectmapper ignores additional ''}'' at the end of json string while deserialising json string to Hollowing Object','import com. Dark.souls import public class public class MyClass { public class MyClass { String introText: Humans are turning into undead.  As we can see, string which I am passing to mapper.readValue is invalid json string(has additional } at the end). But when I run the code it runs successfully and giving following output. The first flames fades and and bells of awakening must be found and one undead will change the world. But I can''t beat the first boss!','import com.fasterxml.jackson.databind.ObjectMapper; import java.io.IOException; import com.fasterxml.jackson.core.JsonParseException; import com.fasterxml.jackson.databind.JsonMappingException; public class MyClass { public static void main(String[] args) { ObjectMapper mapper = new ObjectMapper(); String jsonString = "{"name":"Mahesh", "age":21}}"; try{ Student student = mapper.readValue(jsonString, Student.class); System.out.println(student); jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student); System.out.println(jsonString); } catch (JsonParseException e) { e.printStackTrace();} catch (JsonMappingException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } } } class Student { private String name; private int age; public Student(){} public String getName() { return name; } public void setName(String name) { this.name = name; } public int getAge() { return age; } public void setAge(int age) { this.age = age; } public String toString(){ return "Student [ name: "+name+", age: "+ age+ " ]"; } } This cause the the fire fades','Nuria','Medium','Low','Resolved','Line 124',1588637366384,1588648449000,1588636800000,123,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (4,'DataBuffer doesn''t write to file called: The flood','so I''m trying to send videochunks(Blobs) from a client to my server through reactive websocket in webflux and save each chunk into the same file, for now. The blobs are about 100kb each and it seems like the serverside is receiving them, because the terminal gives me a cryptic output every time the clients sends a blob but does cause The flood has come to earth','ReactiveWebSocketHandler.java [...] @Override public Mono<Void> handle(WebSocketSession webSocketSession) { String filename = "Streamname_" + webSocketSession.getId(); Path path = FileSystems.getDefault().getPath("C:\Program Files (x86)\Apache Software Foundation\Tomcat 9.0\webapps\stream\videos"); //File filelocation = new File(path + filename); System.out.println("The path is: " + path); Flux<DataBuffer> videoDataFlux = webSocketSession.receive() .map(WebSocketMessage::getPayload); try{ //AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE); //DataBufferUtils.write(videoDataFlux, channel, 0).subscribe(); Path file = Files.createTempFile(path, filename, ".webm"); WritableByteChannel channel = Files.newByteChannel(file, StandardOpenOption.WRITE); DataBufferUtils.write(videoDataFlux, channel) .map(DataBufferUtils::release) .then(Mono.just(file)); } catch(IOException e){ } return webSocketSession.send(intervalFlux .map(webSocketSession::textMessage)) .and(webSocketSession.receive()); //.map(WebSocketMessage::getPayloadAsText).log()); } Finish the fight','Nuria','Low','Medium','Resolved','Line 22',1589330112570,1589359173000,1589328000000,123,237);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (5,'How to call back a parents name called "God of tablewars" from child table','I''ve linked them via the Designer view so when I go to insert a name game (I''m doing this via the phpMyAdmin control panel) as soon as I click on "console_id" it gives me a drop down of 1 - PS4, 2 - Xbox and so on. So the games table can now read from consoles table no problem so I think I''ve got <b>everything correct<b> from that side of things.','0 I have two tables as follows: games id | game_name | console_id 1 God of War 1 2 Zelda 3 3 Sonic 4 consoles id | console_name 1 PS4 2 Xbox 3 Switch 4 Mega Drive','AlwaysDeugging','High','High','Resolved','Line 129',1589501740642,1589503224000,1589500800000,213,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (8,'Q.spread and pyramid of Endless Horizons','2 I have this code: Q.spread([ Q.nfcall(employee.save.bind(employee)), ],function(emp){ Q.spread([Q.nfcall(dept.save.bind(dept))],function(dept){ console.log("success") },function(e){ console.error(e); mongoose.disconnect(); }) },function(e){ console.error(e); mongoose.disconnect(); }) Although it works great, it starts to look like the pyramid of doom. Is there a way to refactor it to be more "promising"? I expected something like this to be working: Q.spread([ Q.nfcall(employee.save.bind(employee)) ]).then(function(emp){ var dept = new Department(); return Q.spread([ Q.nfcall(dept.save.bind(dept)) ]) }).then(function(dept){ console.log("success"); }).catch(function(e){ console.error(e); }) but it isn''t.','Q.spread([ Q.nfcall(employee.save.bind(employee)), ],function(emp){ Q.spread([Q.nfcall(dept.save.bind(dept))],function(dept){ console.log("success") },function(e){ console.error(e); mongoose.disconnect(); }) },function(e){ console.error(e); mongoose.disconnect(); }) Although it works great, it starts to look like the pyramid of doom. Is there a way to refactor it to be more "promising"? I expected something like this to be working: Q.spread([ Q.nfcall(employee.save.bind(employee)) ]).then(function(emp){ var dept = new Department(); return Q.spread([ Q.nfcall(dept.save.bind(dept)) ]) }).then(function(dept){ console.log("success"); }).catch(function(e){ console.error(e); }) but it isn''t. The doomslayer is playing with a cute dog','Nuria','Low','High','Resolved','Line 342',1589761134507,1589776173000,1589760000000,40,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (21,'When using the code from Bioshock it causes my compter','<p><em><b>The Big Daddy Header files aren''t compiled by themselves; they''re #included into .cpp files, and the .cpp files are compiled. So it''s possible for a header file X to reference macros that are defined in some other header file Y (that does not include X), and when both Y and X are included (in that specific order) into some source (.cpp) file, then all the references will work out.</b></p></em>','I think it''s not only about Python. In many languages I have not found any default prime number generator or prime checker. Why e.g Python math module has no prime number methods? Or maybe there is somewhere, but I don''t know it? If not, what is the reason to not implementing standard prime number generator or prime checking algorithms?','TheRaidMan','Low','High','Resolved','Line 2',1590798688421,1590821473000,1590796800000,214,237);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (22,'Javasimpleservice is not sending a email to my email','<p>I have been studying (old) 3D rendering and Stomp mail techniques for the past weeks and think that I now have a fair understanding of the way 3D rendering in Doom works. It uses raycasting to render the 3D scene … rendering different from Doom rendering? Does the Quake world use 3D vertices and are they all projected instead of raycasting for intersections? I would love to hear a clear explanation of the … IT BUGGES ME OUT.<p>','<pconst message: string = ''hello world'';<p>','Nuria','Medium','Low','Resolved','Line 123',1591058068138,1591088746000,1591056000000,343,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (25,'My jenkins is not working anymore!','<p>The jekins would not open in the tomcat today. The port 8080 is not working</p>','I think it''s not only about Python. In many languages I have not found any default prime number generator or prime checker. Why e.g Python math module has no prime number methods? Or maybe there is somewhere, but I don''t know it? If not, what is the reason to not implementing standard prime number generator or prime checking algorithms?','AlwaysDeugging','High','Medium','Resolved','In the back end',1591229404679,1591258993000,1591228800000,420,238);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (31,'My email services is failing to send emails.','I would like to have a table which has words included in td. I am wondering if there is any way that search result shown irrespective of the way user searched. for example td contains " love and dance and sing " but the code that I have show result only when user types "love and dance and sing " . If user type "dance and love" it doesn''t show.I want to show result irrespective of arrangement of words, ie , if user types "sing and dance" or dance and love" result should show up.','still trying to help the backend','Thehiddenbug','Medium','High','Unresolved','Line 234',1592094840745,0,1592092800000,124,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (61,'My Half-Life program is keeps getting null pointer errors','Everyone keeps changing the database so I don''t know when but when running a test all of my point went missing and now it is saying I have null points not 0 points','Physicist Gordon Will Free-Wael data based is changed to remove any points under his namelate for work at the Black Mesa Research Facility  Gordon arrives at the Lambda Complex, where scientists under tiff learn the portal is being forced open on the other side by an immensely powerful entity. They have developed teleportation technology that allows Gordon to travel to Xen, where he is tasked to stop the entity','TheRaidman','High','High','Unresolved','Line 4',1592093494974,0,1592092851234,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (65,'Getting the sum of humans in the database keep return the wrong number','Avi wants us to find how many people in the data base have the same interest as him. But I cant find the sum of the people with the same interest','solution.solver_client_id, client.client_username,','Nuria','Low','Low','Unresolved','Line 12',1592093364140,0,1592092800000,10,237);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (66,'HTML5 Canvas - deleting event listener','If I want to save on memory, does an event listener need to be deleted from an element if the element is already deleted. If I do something like this:','I think it''s not only about Python. In many languages I have not found any default prime number generator or prime checker. Why e.g Python math module has no prime number methods? Or maybe there is somewhere, but I don''t know it? If not, what is the reason to not implementing standard prime number generator or prime checking algorithms?','Nuria','Medium','High','Denied','In the back end',1592612011755,0,1592611200000,0,237);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (67,'Is there an endpoint from where we can retrieve Oauth2 certificates from Facebook to validate a Mario cart token','would like to have a table which has words included in td. I am wondering if there is any way that search result shown irrespective of the way user searched. for example td contains " love and dance and sing " but the code that I have show result only when user types "love and dance and sing " . If user type "dance and love" it doesn''t show.I want to show result irrespective of arrangement of words, ie , if user types "sing and dance" or dance and love" result should show up.','I have an application and I want to use SSO login mechanism with Facebook. In order to be able to easily validate the tokens coming to my app i need a list with the public keys coresponding to the private keys which are used to generate Oauth2 access token by Facebook. In order to validate the token I don''t want to use the user info endpoint, that''s why I''m asking this.','Nuria','Low','Low','Requested','Line 53',0,1592801828000,1592784000000,10,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (68,'Recreating RE4 dynamic difficulty','I am trying to subtly tweak your experience depending on how well you were playing. Enemies would deliver greater damage, for instance, and appear more aggressive if you were easily charging through each area','Check this in order to create your (authenticationManager(),rememberMeServices()','Thehiddenbug','High','High','Requested','Line 13',0,1593402198000,1593388800000,10,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (69,'I am trying to write an endpoint that allows the use of optional sorters in the backend this bug has a Mass effect on my code please help','I added logs and the connection with the duplicate key error will always try to insert that problematic row in other unrelated requests. Is it because I don''t clear the session? Shouldn''t it be done automatically when the request is over?','The fire fades','TheRaidman','High','High','Resolved','In the back end',1593735458337,1593749602000,1593734400000,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (70,'Best practice for Unit Testing class which is mostly responsible to call methods of dependencies, but contains logic as well','Let''s assume I have StartCommandHandler which has responsibility to create some file with required files. But for doing this I have to give him a set of sub-responsibilities, like:','I already tested all dependencies of that command handler. But, hat command handler also includes some small logics like, is download file needed, or temp files are deleted or not and so on...','Nuria','Low','High','Requested','In the back end',0,1594169526000,1594166400000,10,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (71,'GOOD Unit Test?','As far as I''m aware of, not everybody agree on what should be covered by UT. I would say it also depends on your team, your company and what you really are trying to achieve.','You can use unit testing for this method as well. This can be done by mocking the dependencies. The principle is as follows:','Thehiddenbug','Medium','Low','Requested','In the back end',0,1594368879000,1594339200000,10,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (98,'Flexbox is give problems','I cant move stuff to the center of the center','I am generating PDF file from my HTML string, But when PDF file getting generated the content in HTML and PDF does not match. The content is PDF is some random content. I read about the issue on google and they suggest using Unicode notation like %u0627%u0646%u0627%20%u0627%u0633%u0645%u0649%20%u0639%u0628%u062F%u0627%u0644%u0644%u0647. But I am putting this into my HTML it is getting printing as it is','Nuria','High','Medium','Unresolved','In the back end',1594426192167,0,1594425600000,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (109,'Testing form creation','<p>&nbsp;</p>
<div class="post-text">
<p>I am new to AWS CodePipeline and I am getting this Error on AWS CodeBuild</p>
<p>"YAML_FILE_ERROR Message: Wrong number of container tags, expected 1"</p>
<p>I have setup AWS CodePipeline with CodeBuild and CloudFormation for aspnet core 2.1 project. Here is my buildspec.yml</p>
<pre><code> {
  "name": "Utility",
  "source": {
    "type": "S3",
     "location": "&lt;location&gt;/windows-dotnetcore.zip"
 },
    "artifacts": {
    "type": "S3",
    "location": "&lt;location&gt;",
    "packaging": "ZIP",
    "name": "Utility.zip"
  },
  "environment": {
  "type": "LINUX_CONTAINER",
  "image": "aws/codebuild/dot-net:core-2.1",
  "computeType": "BUILD_GENERAL1_SMALL"
 },
 "serviceRole": "&lt;value&gt;",
 "encryptionKey": "&lt;value&gt;"
 }
</code></pre>
</div>','<p>&nbsp;</p>
<div class="post-text">
<p>I received this error when I had a blank buildspec.yml checked in to CodeCommit. Once I updated it with something like this I was good to go:</p>
<pre><code>version: 0.2

phases:
  install:
    commands:
      - echo Installing Mocha...
      - npm install -g mocha
  pre_build:
    commands:
      - echo Installing source NPM dependencies...
      - npm install unit.js
  build:
    commands:
      - echo Build started on `date`
      - echo Compiling the Node.js code
      - mocha HelloWorld.js
  post_build:
    commands:
      - echo Build completed on `date`
artifacts:
  files:
    - HelloWorld.js
</code></pre>
<p>Out of curiosity I thought it might have been a formatting error, but I tried checking in some garbage text and received the following error instead:</p>
<pre><code>Phase context status code: YAML_FILE_ERROR Message: stat 
</code></pre>
</div>','Nuria','Low','High','Requested','In the back end',0,1594702003000,1594684800000,6,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (110,'My API keeps changing when I change the size of my screen','better','<p>Use the front end for more than 5 minutes.</p>','AlwaysDeugging','Medium','Medium','Unresolved','In the back end',1594858223623,0,1594857600000,5,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (175,'Getting wrong arabic translation in PDF iText','<pre class="lang-java prettyprint prettyprinted"><code><span class="lit">@SpringBootApplication</span>
<span class="kwd">public</span> <span class="kwd">class</span> <span class="typ">DemoApplication</span> <span class="pun">{</span>

    <span class="kwd">public</span> <span class="kwd">static</span> <span class="kwd">void</span><span class="pln"> main</span><span class="pun">(</span><span class="typ">String</span><span class="pun">[]</span><span class="pln"> args</span><span class="pun">)</span> <span class="kwd">throws</span> <span class="typ">IOException</span> <span class="pun">{</span>
        <span class="typ">SpringApplication</span><span class="pun">.</span><span class="pln">run</span><span class="pun">(</span><span class="typ">DemoApplication</span><span class="pun">.</span><span class="kwd">class</span><span class="pun">,</span><span class="pln"> args</span><span class="pun">);</span>
        <span class="typ">String</span><span class="pln"> htmlSource </span><span class="pun">=</span><span class="pln"> getContent</span><span class="pun">();</span>
        <span class="typ">ByteArrayOutputStream</span><span class="pln"> outputStream </span><span class="pun">=</span> <span class="kwd">new</span> <span class="typ">ByteArrayOutputStream</span><span class="pun">();</span>
        <span class="typ">ConverterProperties</span><span class="pln"> converterProperties </span><span class="pun">=</span> <span class="kwd">new</span> <span class="typ">ConverterProperties</span><span class="pun">();</span>
        <span class="typ">FontProvider</span><span class="pln"> dfp </span><span class="pun">=</span> <span class="kwd">new</span> <span class="typ">DefaultFontProvider</span><span class="pun">(</span><span class="kwd">true</span><span class="pun">,</span> <span class="kwd">false</span><span class="pun">,</span> <span class="kwd">false</span><span class="pun">);</span><span class="pln">
        dfp</span><span class="pun">.</span><span class="pln">addFont</span><span class="pun">(</span><span class="str">"/Library/Fonts/Arial.ttf"</span><span class="pun">);</span><span class="pln">
        converterProperties</span><span class="pun">.</span><span class="pln">setFontProvider</span><span class="pun">(</span><span class="pln">dfp</span><span class="pun">);</span><span class="pln">
        converterProperties</span><span class="pun">.</span><span class="pln">setMediaDeviceDescription</span><span class="pun">(</span><span class="kwd">new</span> <span class="typ">MediaDeviceDescription</span><span class="pun">(</span><span class="typ">MediaType</span><span class="pun">.</span><span class="pln">PRINT</span><span class="pun">));</span>
        <span class="typ">HtmlConverter</span><span class="pun">.</span><span class="pln">convertToPdf</span><span class="pun">(</span><span class="pln">htmlSource</span><span class="pun">,</span><span class="pln"> outputStream</span><span class="pun">,</span><span class="pln"> converterProperties</span><span class="pun">);</span>
        <span class="kwd">byte</span><span class="pun">[]</span><span class="pln"> bytes </span><span class="pun">=</span><span class="pln"> outputStream</span><span class="pun">.</span><span class="pln">toByteArray</span><span class="pun">();</span>
        <span class="typ">File</span><span class="pln"> pdfFile </span><span class="pun">=</span> <span class="kwd">new</span> <span class="typ">File</span><span class="pun">(</span><span class="str">"java19.pdf"</span><span class="pun">);</span>
        <span class="typ">FileOutputStream</span><span class="pln"> fos </span><span class="pun">=</span> <span class="kwd">new</span> <span class="typ">FileOutputStream</span><span class="pun">(</span><span class="pln">pdfFile</span><span class="pun">);</span><span class="pln">
        fos</span><span class="pun">.</span><span class="pln">write</span><span class="pun">(</span><span class="pln">bytes</span><span class="pun">);</span><span class="pln">
        fos</span><span class="pun">.</span><span class="pln">flush</span><span class="pun">();</span><span class="pln">
        fos</span><span class="pun">.</span><span class="pln">close</span><span class="pun">();</span>
    <span class="pun">}</span>

    <span class="kwd">private</span> <span class="kwd">static</span> <span class="typ">String</span><span class="pln"> getContent</span><span class="pun">()</span> <span class="pun">{</span>
        <span class="kwd">return</span> <span class="str">"&lt;!DOCTYPE html&gt;
"</span> <span class="pun">+</span>
                <span class="str">"&lt;html lang="en"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"
"</span> <span class="pun">+</span>
                <span class="str">"&lt;head&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;meta charset="UTF-8"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;meta name="viewport" content="width=device-width, initial-scale=1.0"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;meta http-equiv="X-UA-Compatible" content="ie=edge"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;title&gt;Document&lt;/title&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;style&gt;
"</span> <span class="pun">+</span>
                <span class="str">"      @page {
"</span> <span class="pun">+</span>
                <span class="str">"        margin: 0;
"</span> <span class="pun">+</span>
                <span class="str">"        font-family: arial;
"</span> <span class="pun">+</span>
                <span class="str">"      }
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;/style&gt;
"</span> <span class="pun">+</span>
                <span class="str">"&lt;/head&gt;
"</span> <span class="pun">+</span>
                <span class="str">"
"</span> <span class="pun">+</span>
                <span class="str">"&lt;body
"</span> <span class="pun">+</span>
                <span class="str">"    style="margin: 0;padding: 0;font-family: arial, sans-serif;font-size: 14px;line-height: 125%;width: 100%;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;color: #222222;"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;table cellpadding="0" cellspacing="0" width="100%" style="background: white; direction: rtl;"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"        &lt;tbody&gt;
"</span> <span class="pun">+</span>
                <span class="str">"            &lt;tr&gt;
"</span> <span class="pun">+</span>
                <span class="str">"                &lt;td style="padding: 0 35px;"&gt;
"</span> <span class="pun">+</span>
                <span class="str">"                    &lt;/p&gt;
"</span> <span class="pun">+</span>
                <span class="str">"                &lt;/td&gt;
"</span> <span class="pun">+</span>
                <span class="str">"            &lt;/tr&gt;
"</span> <span class="pun">+</span>
                <span class="str">"        &lt;/tbody&gt;
"</span> <span class="pun">+</span>
                <span class="str">"    &lt;/table&gt;
"</span> <span class="pun">+</span>
                <span class="str">"
"</span> <span class="pun">+</span>
                <span class="str">"&lt;/body&gt;
"</span> <span class="pun">+</span>
                <span class="str">"
"</span> <span class="pun">+</span>
                <span class="str">"&lt;/html&gt;"</span><span class="pun">;</span>
    <span class="pun">}</span>
<span class="pun">}</span></code></pre>','<p>I am generating PDF file from my HTML string, But when PDF file getting generated the content in HTML and PDF does not match.</p>
<p>The content is PDF is some random content.</p>
<p>I read about the issue on google and they suggest using Unicode notation like <code>%u0627%u0646%u0627%20%u0627%u0633%u0645%u0649%20%u0639%u0628%u062F%u0627%u0644%u0644%u0647</code>.</p>
<p>But I am putting this into my HTML it is getting printing as is:</p>','Nuria','Medium','High','Requested','Line 47',0,1595389199000,1595376000000,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (176,'Recyclerview not scrolling inside Nested ScrollView','I am trying to set the date and time of a linux system from a remote system using Java. In order to do that I have created a server to accept time from the remote system as:','I am trying to use the update query with the LIMIT clause using sqlite-JDBC. Let''s say there are 100 bob''s in the table but I only want to update one of the records. Sample code: String name1 = "..','Nuria','High','High','Requested','Line 53',0,1595565937000,1595548800000,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (177,'Can I find out userid of the remote person logged into windows desktop?','I am running a Java Desktop appplication on one of our windows machine. And to make any manual changes in our application, one of our Support persons remote login from his machine using mstsc','I am trying to use the update query with the LIMIT clause using sqlite-JDBC. Let''s say there are 100 bob''s in the table but I only want to update one of the records. Sample code: String name1 = "..','Nuria','High','High','Requested','Line 53',0,1596696323000,1596672000000,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (178,'Access to root application support macos java','I need to save data inside the /Library/Application Support/ of macOS within a java application. To access the user application data, I know I can use System.getProperty("user.home") but I want the','I am trying to use the update query with the LIMIT clause using sqlite-JDBC. Let''s say there are 100 bob''s in the table but I only want to update one of the records. Sample code: String name1 = "..','Nuria','High','High','Requested','Line 53',0,1596875595345,1596844802345,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (179,'Polymorphic association in PostgreSQL with JPA/Hibernate','I have three entities video_guide text_guide and discussion that represent type of learning material available on the platform. A student can create topic-wise boards and add learning material to them','I am trying to use the update query with the LIMIT clause using sqlite-JDBC. Let''s say there are 100 bob''s in the table but I only want to update one of the records. Sample code: String name1 = "..','TheRaidman','High','High','Requested','Line 53',0,1597125019112,1597104000112,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (180,'Recyclerview not scrolling inside Nested ScrollView','I have Recyclerview which is under Coordinatorlayout > NestedScrollview > ViewPager and ViewPager has 3 fragment, one has image gallery which is working with the help of Recyclerview.','I have Recyclerview which is under Coordinatorlayout > NestedScrollview > ViewPager and ViewPager has 3 fragment, one has image gallery which is working with the help of Recyclerview.','TheRaidman','High','High','Unresolved','Line 64',1597191741293,0,1597190400000,10,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (184,'Help I can''t type wael','<p><em><strong>Is it well?</strong></em></p>',NULL,'Nuria','Low','High','Requested','in the title',0,0,1597276800000,0,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (186,'Swift send compressed video frames using GPUImage','<p>I''m writing a <strong>Swift </strong>app that sends an iPhone camera video input (frames) through the network, so I can later display them on a macOS app.</p>
<p>Currently, I''m grabbing video frames from an <code>AVCaputreSession</code>, and get a <code>PixelBuffer</code> from the <code>captureOutput</code> method. Since each frame is huge (RAW pixels) I''m converting the <code>CVPixelBuffer</code> that to a <code>CGImage</code> with <code>VTCreateCGImageFromCVPixelBuffer</code> and later to a <code>UIImage</code> with JPEG compression (50%). I then send that JPEG through the network and display it on the Mac OS app.</p>
<p>As you can see this is far from ideal and runs at ~25 FPS on an iPhone 11. After some research, I came up with <a href="https://github.com/BradLarson/GPUImage2" rel="nofollow noreferrer">GPU Image 2</a>. It seems that I could get the data from the camera and apply something like this (so that the transformation is done in GPU):</p>
<pre class="default prettyprint prettyprinted"><code><span class="pln">camera </span><span class="pun">=</span> <span class="kwd">try</span> <span class="typ">Camera</span><span class="pun">(</span><span class="pln">sessionPreset</span><span class="pun">:</span><span class="typ">AVCaptureSessionPreset640x480</span><span class="pun">)</span>
<span class="kwd">let</span><span class="pln"> pictureOutput </span><span class="pun">=</span> <span class="typ">PictureOutput</span><span class="pun">()</span><span class="pln">
pictureOutput</span><span class="pun">.</span><span class="pln">encodedImageFormat </span><span class="pun">=</span> <span class="pun">.</span><span class="pln">JPEG
pictureOutput</span><span class="pun">.</span><span class="pln">imageAvailableCallback </span><span class="pun">=</span> <span class="pun">{</span><span class="pln">image </span><span class="kwd">in</span>
    <span class="com">// Send the picture through the network here</span>
<span class="pun">}</span><span class="pln">
camera </span><span class="pun">--&gt;</span><span class="pln"> pictureOutput
</span></code></pre>
<p>And I should be able to transmit that UIImage and display it on the macOS app. Is there a better way to implement this whole process? Maybe I could use the iPhone''s H264 hardware encoding instead of converting images to JPEG, but it seems that it''s not that straightforward (and it seems that GPUImage does something like that from what I read).</p>
<p><em>Any help is appreciated, thanks in advance!</em></p>','<p>Apart from these, GPUImage is not a suitable solution for you. If you are going to transfer videos, you have to encode H264 or H265 (HEVC) in every moment. In this way, you can transmit video in a performance way.</p>
<p>The solution you are doing now is CMSampleBuffer-&gt; CVPixelBuffer-&gt; JPEG-&gt; Data conversion seriously burden the processor. It also increases the risk of memory leak.</p>
<p>If you can tell a little bit, I would like to help. I have experience with video processing.</p>','Nuria','Medium','Low','Resolved','Swift encoder',1597537703286,1597546384000,1597536000000,400,238);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (193,'A new bug','<p>All the formatting is<em> saved</em>, and inserted <strong>html </strong>is character escaped for safety.</p>',NULL,'Nuria','Medium','High','Requested','line 42 of a file',0,0,1597622400000,0,424);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (194,'The list of toppings isn''t showing up','<p>When I try to submit an order, I get a weird "CORS" error. I don''t know what this means!</p>','<p>Any time I try to make a pull request for my list of toppings to show up, it gives me an error and none of my data shows up. Happens any time I try to open any page that should show a list of toppings.</p>','mo','Medium','High','Requested','Javascript',0,0,1597708800000,0,2);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (201,'Test bug timing','<p>Messed up making dummy bugs</p>','<p>1) make a new bug report</p>
<p>2) Voila, you have a bug</p>','TheRaidman','High','Medium','Resolved','I have no Idea',0,1597713858678,1597708845678,0,1);
INSERT INTO bug_report(bug_report_id,bug_report_title,bug_report_description,bug_report_reproduction_steps,client_username,severity,priority,status,location,approved_time,resolved_time,date_created,point_value,application_id) VALUES (202,'Test br','<p>This is a testing bug report</p>','<p>Just refresh page</p>','user1','Medium','Medium','Resolved','repos',0,1598492770000,1598486400000,0,1);

INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (4,'Zakaria','Zak lastname','Zak','2007wvu@gmail.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (1,'Jesse','the real jesse','TheRaidman','nb231111@gmail.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (2,'Jian','Qiu','JianQiu','jian@email.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (3,'Bin','Nai','Emailman','MircoPassword@gmail.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (20,'Dylan','Graham','Nuria','Dylangraham140@gmail.com','Password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (21,'James','Brandow','Thehiddenbug','James140@gmail.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (22,'Will','newell','TheMatrix','Willnew@gmail.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (23,'Tejas','silent','StrongSilentType','Tejas@gmail.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (24,'Naibing','jiang','posttest','nb243@gmail.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (32,'Raymond','Calapatia','Rcala','raymond.50cal93@gmail.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (102,'Ehi','Agboneni','eagboneni','eagboneni0@gmail.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (103,'James','bradow','AlwaysDeugging','Dont@me.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (108,'lo','jo','mo','mo','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (109,'adam','the first man','Adam the master trainer','Nope','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (282,'The','Unknown','TheUnknown','unknown@gmail.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (285,'shar','chad','testnewlog2','s@s.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (287,'user1vv','user1vv','user1','user1@gmail.com','123',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (294,'Nia','Nuri','jumba','juice@fake.com','jian',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (295,'jesse','rivera','Jrivera','jr@gmail.com','pword',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (304,'jesse','rivera','jr','jr2@gmail.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (305,'Dylan','Graham','2','Dylangraham1402@gmail.com','password',1);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (309,'Kenneth','Davis','KD','kenneth.client@revature.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (310,'Kenneth','Davis','user','user@fake.com','password',0);
INSERT INTO client(client_id,client_first_name,client_last_name,client_username,client_email,client_password,client_role) VALUES (317,'Kenneth','Davis','kenny.davis','kenneth.davis2@revature.com','password',0);

INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (1,'project 3 bug fix 2','change version of Spring to 2.6.6 from 3.1.0','Pending',1590541476321,1,1);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (2,'Import world.doom.','package org.odata4j.tomcat ;','Accepted',1590541476321,22,3);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (4,'Remove the old NG repeat and put ng-for on the "knights of the old" tables','If you want to implement a custom behavior when authentication process (with remember me feature) is success you can try and worry no more  Dream no more of this bug!','Accepted',1590541476321,22,20);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (5,'Change the header file to match the class "crowbar"','Header files aren''t compiled by themselves; they''re #included into .cpp files, and the .cpp files are compiled. So it''s possible for a header file X to reference macros that are defined in some other header file Y (that does not include X), and when both Y and X are included (in that specific order) into some source (.cpp) file, then all the references will work out.','Rejected',1590541476321,1,22);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (6,'Update the sorting using comparable','You could implement sorting with spring with Pageable support.','Pending',1590541476321,22,32);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (9,'Update the version the java and import com.bells','Have you tried importing the bell of awakening package and  implement sorting with spring with Pageable support.','Pending',1590541476321,22,32);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (10,'Use prime numbers','try changing this settings: - wait_timeout default value 28.800 seconds. - interactive_timeout default value 28.800 seconds. - innodb_lock_wait_timeout default value 50 seconds. (if you are using INNODB)','Rejected',1590541476321,22,23);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (11,'Live session and use the 117  memory','It''s because of badly managed live sessions or options for doing everything even querying or because of poorly choiced Transaction Isolation level in the halo ring database.','Pending',1590541476321,1,1);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (12,'Can spring boot can come handy in multiple ways her','Spring can come handy in multiple ways here:','Pending',1590539311178,22,103);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (13,'This a problem with spring 3.0.0','The X of your xyproblem.info is still unclear. You''re literally stating "And I want to perform a specific action each time a request comes to MyServlet". This is incomplete information. As it stands now, the answer would simply be, "just put it in your service() method as you did". But it has absolutely not the same lifecycle as the ServletRequestListener. It''s still unclear why you''re trying to compare to it. What exactly is that "some clean up stuff" that you''re apparently trying to perform. If it were e.g. closing a JDBC connection then everything of this all is wrong.','Pending',1590539300178,1,32);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (14,'Fix the Html','There are indeed different solutions depending on the content of the "clean up stuff". If it only concerns the current request and no external, no injected or shared resources and does not involve http stuff (such as headers, auth, ..) then try-finally could be fine. Otherwise a more suitable <Filter> would do the job in most other cases.','Pending',1590539321008,22,24);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (15,'Problem in setting date and time from java code','In a real application you would use the initAsync function such that the loading of the libraries does not block the main thread. In a simple example this does not matter.','Pending',1590039324908,1,23);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (16,'Change the import to java.util.list','Assuming you run on Linux, you have to make sure you can perform ''sudo'' without entering the password. You did not specify how the server is launched, but for regular accounts, sudo will require password per session. Also, please log the executed command on the server, including the return code. – dash-o yesterday','Rejected',1590088261112,25,2);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (17,'Import ng-repeat is out of date, use ng for.','import { Injectable } from ''@angular/core'';
import { HttpClient } from ''@angular/common/http'';
import { BugReport } from ''src/app/models/BugReport''
import { Application } from ''src/app/models/application''
import { promise } from ''protractor'';
import Solution from ''../models/Solution'';
import Client from ''../models/Client'';

@Injectable({
  providedIn: ''root''
})
export class ApiServiceService {

  constructor(private http: HttpClient) { }

  path: string = ''http://ec2-52-14-153-164.us-east-2.compute.amazonaws.com:9000''
 // path: string = ''http://localhost:9000''




  submitNewBugReport(bugReport: BugReport): Promise<BugReport> {
    return this.http.post<BugReport>(this.path + ''/BugReport'', bugReport).toPromise();
  }

  getApplications(): Promise<Application[]> {
    return this.http.get<Application[]>(this.path + ''/Application'').toPromise();
  }

  getBugReports(): Promise<BugReport[]> {
    return this.http.get<BugReport[]>(this.path + ''/bugreports'').toPromise();
  }

  getBugReportById(id: number) {
    return this.http.get<BugReport>(this.path + `/bugreports/NULL`).toPromise();
  }

  getSolutionById(id: number) {
    return this.http.get<Solution>(this.path + `/solutions/NULL`).toPromise();
  }



  //################ Start of Client Section ###################

  getClientById(id: number): Promise<Client> {
    return this.http.get<Client>(this.path + `/clients/NULL`).toPromise();
  }
  //to be set within the login function 
  setLoggedClient(client: Client) {
    localStorage.setItem(''client'', JSON.stringify(client));
  }
  //to be used anywhere the user objec is needed  , it is better to call it in the component constructor 
  getLoggedClient(): Client {
    let val = localStorage.getItem(''client'');
    let client = new Client();
    client = JSON.parse(val)
    return client;
  }
  //used on logout 
  clearLoggedClient() {
    localStorage.clear();
  }

  //################ End of Client  Section ###################


  //################ Start of Solution Section ###################

  //1. Add new Solution 
  async postSolution(slution: Solution): Promise<any> {
    let ticketPromise = await this.http.post(this.path + "/solutions", slution).toPromise();
    return ticketPromise;
  }
  //2. Get all Solutions  by Bug Report ID 
  getSolutionsByBugId(brId: number): Promise<Array<Solution>> {
    return this.http.get<Array<Solution>>(this.path + ''/query/solutions/bugreport?id='' + brId).toPromise();
  }
  
  //################ End of Solution Section ###################
}','Accepted',1590088470121,25,23);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (18,'Fix the Service Html  3','<div class="container">
    <h1 class="titleH1 col-sm-3"> Bug Report View#{{br.bId}}</h1>

    <div class="form-group row">

        <label for="txttitle" class="col-sm-2 col-form-label">Title:</label>

        <label id="txttitle" class="col-sm-10 form-control-plaintext"> {{br.title}} </label>
    </div>

    <div class="form-group row">

        <label for="lblapplication" class="col-sm-2 col-form-label">Application:</label>
        <ng-container *ngIf="br.app !=null">
            <label id="lblapplication" class="col-sm-2 form-control-plaintext"> {{br.app.title}}</label>

      
        </ng-container>      <label for="lbllocation" class="col-sm-2 col-form-label">Location:</label>
        <label id="lbllocation" class="col-sm-2 form-control-plaintext"> {{br.location}}</label>

    </div>
    <div class="form-group row">

        <label for="lblseverity" class="col-sm-2 col-form-label">Severity: </label>

        <label id="lblseverity" class="col-sm-2  form-control-plaintext">{{br.severity}}</label>

        <label for="lblpriority" class="col-sm-2 col-form-label">Priority: </label>

        <label id="lblpriority" class="col-sm-2  form-control-plaintext">{{br.priority}}</label>
        <label for="txtreporter" class="col-sm-1 col-form-label">Points:</label>

        <label class="col-sm-1  form-control-plaintext" id="txtreporter">{{br.pointValue}}</label>
    </div>
    <div class="form-group row">
        <label for="txtdate" class="col-sm-2 col-form-label">Creation Time:</label>

        <label type="datetime" readonly class="col-sm-2  form-control-plaintext" id="txtreporter">
            {{br.createdTime|date:''medium''}} </label>

        <label for="txtreporter" class="col-sm-2 col-form-label">Reporter:</label>

        <label class="col-sm-2  form-control-plaintext" id="txtreporter">{{br.username}} </label>

    </div>
    <div class="form-group row">

        <label for="lblapprovedTime" class="col-sm-2 col-form-label">Approved Time: </label>

        <label id="lblapprovedTime" class="col-sm-2  form-control-plaintext">{{br.approvedTime|date:''medium''}}</label>

        <label for="lblresolvedTime" class="col-sm-2 col-form-label">Resolved Time: </label>

        <label id="lblresolvedTime" class="col-sm-2  form-control-plaintext">{{br.resolvedTime|date:''medium''}}</label>

    </div>

    <div class="form-group row">

        <label for="txtdescription" class="col-sm-2 form-label"> Description:</label>

        <textarea id="txtdescription" readonly
            class="col-sm-8 form-control textareaDisabled">{{br.description}}</textarea>

    </div>



    <div class="form-group row">

        <label for="txtreproduceSteps" class="col-sm-2 form-label">Reproduce Steps:</label>

        <textarea id="txtreproduceSteps" readonly
            class="col-sm-8 form-control textareaDisabled">{{br.repSteps}}</textarea>

    </div>

    <hr>
    <div class="form-group ">
        <h1>Posted Solution(s)</h1>
        <app-posted-solutions-table></app-posted-solutions-table>
    </div>
    <br>
    <div class="row">

        <div class="col-12">
            <table class="table">
                <thead>
                    <th>
                        Title
                    </th>
                    <th>
                        Describtion
                    </th>
                    <th>
                        Status
                    </th>
                    <th>
                        Solver
                    </th>

                </thead>
                <tr *ngFor="let item of solutions">
                    <td>
                        {{item.title}}
                    </td>
                    <td>
                        {{item.description}}
                    </td>
                    <td>
                        {{item.status}}
                    </td>
                    <td>
                        {{item.client.username}}
                    </td>

                </tr>
            </table>
        </div>
    </div>

</div>
<div class="container">
    <h1 class="titleH1 col-sm-3">Post new Solution</h1>

    <div class="form-group row">

        <label class="col-sm-2 form-label">Title: </label>


        <input type="text" [(ngModel)]="SolTitle" class="col-sm-6 form-control" placeholder="Solution title">

    </div>
    <div class="form-group row">


        <label class="col-sm-2 form-label">Description: </label>
        <textarea [(ngModel)]="SolDescription" placeholder="Solution Describtion"
            class="col-sm-8 form-control textarea"> </textarea>
    </div>

    <div class="buttonGroup">
        <button type="button" class="btn btn-secondary">Cancel</button> <span> &nbsp;&nbsp; </span>
        <button type="button" (click)="postSolution()" class="btn btn-success">Submit</button>
    </div>
    <br>
</div>','Accepted',1590088511993,25,22);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (19,'I tried to reproduce the issue and I got the following warning in the logging when running the example code:','Please check to make sure that your sourcefile and compiler use the same encoding, e.g. UTF-8. I sometimes check that by including characters that are only available in unicode and not in other classic codepages.','Accepted',1590090168327,1,2);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (20,'You''re probably assigning them wrongfully,','Set the timeout option to Infinity to avoid a return if position is not available (yet).','Accepted',1590090202225,3,20);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (21,'Update the Java 5 to Java 8','All Android devices or emulators don''t have access to storages outside like your Linux storage partition, they have access to their internal storage or sdcard. In the case of the emulator, their internal storage is emulated using a file with a certain format that cannot be easily read. In an emulator or a device that has Developer options enabled, one could use the adb command found within the Android SDK platform-tools folder to transfer files into it without the java','Accepted',1590090271625,1,21);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (23,'Please check to make sure that your sourcefile','Make sure your fonts support the characters you need and if you use Maven resource directory to include extra fonts during the build check that the font file is not filtered (properties replacement) as that corrupts the file: Maven corrupting binary files in source/main/resources when building jar','Accepted',1590090308930,1,20);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (24,'Check to your Main file','You should look into alpha blending for drawing the bubbles. Assuming the reflections on them are static, all you need is a simple image. (If you aren''t sure what the image should be like, look at a black wall in the game. The white parts you see should be what the red, green, blue AND alpha channels of the image look like.)','Rejected',1590095990243,3,108);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (25,'Get hired at revature','They will train you! Learn everything you need to solve this bug and get paid!','Accepted',1590541476321,3,108);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (26,'You need to change the vsyc code to find the fps of the game','understand that you want to do this operation in a non-internet environment.','Accepted',1590000555555,22,109);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (27,'set session.gc_maxlifetime > 65535 seconds','Hi! thanks for your response. Regarding your questions: 1. At least 30 fps 2. At least 1080 3. If I can transmit the video with audio, that would be awesome. Or at least send it sepparate from the video 4. It''s a local environment, so don''t worry about that. Assume a really high bandwith 5. We can use the latest versions of both Yes, compressing things in H264 or H265 would be ideal, but sending that "on the fly" through the network without storing it to the disk (maybe that''s the only way) seems to be a nontrivial thing. –','Accepted',1580001111111,22,109);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (28,'You are using the wrong tag','Thanks for the link. Does the maximum depend on the session.save_handler? From what I understand from scottmac@php.net it could. @Ryan Vincent - Right now I have set my session_set_cookie_params(4*7*24*60*60) in my phpinfo() I read that the session.cookie_lifetime is indeed this value. However after 24 minutes (the maximum lifetime set by session.gc_maxlifetime) kills it.','Accepted',1570123213123,1,102);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (29,'set session to false if it is off','yes it is possible, if you do self session handler','Accepted',1591204212122,1,102);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (37,'At least 30 fps','compressing things in H264 or H265 would be ideal, but sending that "on the fly" through the network without storing it to the disk (maybe that''s the only way) seems to be a nontrivial thing','Pending',1590678680645,186,108);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (38,'The Observer','CurrentItem.AddObserver(this, new NSString("status"), 
    NSKeyValueObservingOptions.New | NSKeyValueObservingOptions.Initial,
    StatusObservationContext.Handle);','Accepted',1590678709868,186,2);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (39,'Load Time Ranges','CurrentItem.AddObserver(this, new NSString("loadedTimeRanges"), 
    NSKeyValueObservingOptions.Initial | NSKeyValueObservingOptions.New,
    LoadedTimeRangesObservationContext.Handle);','Pending',1590678730641,186,32);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (43,'Use sudo','Use basic linux commands to run any programm (application) as root. MacOS should also support them, for example:

$ sudo java -jar /home/user/application.jar
or you may run your IDE as root in graphical mode

$ beesu /opt/idea-IU/bin/idea.sh %f #fedora
or

$ gksudo /opt/idea-IU/bin/idea.sh %f #GTK+
or you may simply switch to root in terminal and do as you wish

$ su
P.S. root password required..','Approved',15312012509,3,20);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (44,'Use yum to install','Hi, thanks so much for your reply. Here was my thought process. When I run my web application, because DeployApplicationServlet is the first welcome-file in my web.xml, it starts up DeployApplicationServlet. I instantiate ScheduleEmail in the doGet method of my DeployApplicationServlet, so when the DeployApplicationServlet is loaded, the sendAutomatedEmail method (the one with the @Schedule annotation) is called, which invokes another servlet (not shown above) that sends an email to a user at a specific time.','Approved',15312012509,3,20);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (45,'Summon 117','Jump off the ship','Approved',1231201259,2,3);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (47,'New Solution','magic','Rejected',1590765193263,186,20);
INSERT INTO solution(solution_id,solution_title,solution_description,status,submitted_time,bug_report_id,solver_client_id) VALUES (48,'Summon 117','Jump off the ship','approved',1231201259,3,20);
