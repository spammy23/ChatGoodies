# ChatGoodies

> A Bukkit plugin with chat features that'll make you go 'oh goody!'

#### Features:  

###### Replace dot
`Replaces the first dot when a message starts with a '.', this'll allow people to type stuff such as './help', which would show up in the chat as '/help'.`  
###### Message alias
`You can set message aliasses in the config, to translate strings of text into different strings of text. By default it only translates 'lol' into 'laughing out loud', but you can easily add more in the config. An ingame configuration command is on my to-do list (have made this before, this is a rewrite of that unreleased plugin)`
###### Command alias
`You can set command aliasses in the config, to translate commands into different commands. By default it only translates '/gmc' to '/gamemode 1', but you can easily add more in the config. An ingame configuration command is on my to-do list (have made this before, this is a rewrite of that unreleased plugin)`
###### Mentioned you
`When a player says another player's name in the chat, that player will receive a message saying something like 'Player has mentioned you!'. They'll also here a noteblock sound (BLOCK_NOTE_PLING)`
###### Clear chat
`Clears the chat of either a specific player or the global chat.`
###### Global mute
`Globally mutes the chat, players with chatgoodies.globalmute.talk will still be able to talk. Also, players with chatgoodies.globalmute.read will be able to read the messages players attempt to send while the chat is muted.`
###### Staff chat
`Allows staff to interact with eachother without players being able to read along, by default this is done by starting the message with a #. For example: Staff member A types: '#Boy, that Player C is so annoying isn't he? Should we ban him lol', then Staff member B reads it, and could type: '#Nah, he is annoying, but that would compromise everything we stand for, to create a fair and fun experience for players, without any abusiveness by staff members'. And the best thing? Player C will never know about that conversation.`

#### Commands:
  **/gmute** _The main globalmute command_  
  **/cc** _The main clearchat command_

#### Permissions:
  **chatgoodies.clearchat.all:**  
    To clear everyone's chat  
  **chatgoodies.clearchat.self:**  
    To clear own chat  
  **chatgoodies.clearchat.others:**  
    To clear specific other players chat  
  **chatgoodies.clearchat.exclude:**  
    Blocks an incoming clearchat from a player with   chatgoodies.clearchat.others  
  **chatgoodies.clearchat.force:**  
    Overrides chatgoodies.clearchat.exclude  
  **chatgoodies.globalmute.set:**  
    Allows to enable or disable global mute  
  **chatgoodies.globalmute.talk:**  
    Allows to still talk during mute  
  **chatgoodies.globalmute.read:**  
    Allows to read player's attempted messages during globalmute  
  **chatgoodies.msgalias:**  
    Allows to use aliasses in messages  
  **chatgoodies.cmdalias:**  
    Allows to use aliasses in commands  
  **chatgoodies.mention:**  
    Allows a player to mention players  
  **chatgoodies.replacedot**  
    When a player has this permission it removes the dot when a message   starts with a dot  
  **chatgoodies.staffchat.send:**  
    Allows to send a staffchat message  
  **chatgoodies.staffchat.receive:**  
    Allows to receive staffchat messages
    
#### To do list:
* Add alias configuration control from the chat
* Add more features
