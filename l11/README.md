# Text chat for two users on sockets

Write a text chat for two users on sockets. The chat should be implemented on the client-server principle. One user is on the server, the second is on the client. Addresses and ports are set via the command line: to the client - where to connect, to the server - on which port to listen. When the program starts, a text prompt is displayed in which you can enter one of the following commands:
- Set the user name (@name Vasya)
- Send a text message (Hello)
- Exit (@quit)
Received messages are automatically displayed on the screen. The program works over the UDP protocol.
Add the commands @cd, @ls, @pwd to view the file system of the interlocutor. In response to these commands, you need to perform an action with the file system and send back the result.
- @pwd - return the full path to the current directory
- @ls - return the contents of the current directory
- @cd - change the current directory and return the new current directory
