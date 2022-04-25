# client_server_chat_phase3
this repo is prepared for implementing phase 3 of client_server simple chat : java language

*****************************************************************************************************************
*****************************************************************************************************************
*****************************************************************************************************************
requirement: 
**************************
send message object via socket (remember to import message on both Client and Server side)
-	1 message should contain type and content (content varied based on message type)

**************************
-	Message types:

**************************
  	REGISTER: client sends username and password to Server. If username already existed, 
server response “Username already existed”. 
Password must contain at least 8 characters, includes upper and lower case and number.

**************************
  	LOGIN: client sends username and password to Server. If username and password are correct,
server response “Login succeed”, otherwise server response “Wrong username or password”.

**************************
  	ECHO: client send free text to server, server will remove all special character and response back to client
-	Server must have DB to store username & password.
