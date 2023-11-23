# Multi-user chat 

Write a text multi-user chat.
- The user manages the client. There is no user on the server. The server is engaged in forwarding messages between clients.
- By default, the message is sent to all chat participants.
- There is a command to send a message to a specific user (@senduser
Vasya).
Each user can add other users to their blacklist (stored on the server). Messages from users from emergency situations do not come.
The program works over the TCP protocol.
