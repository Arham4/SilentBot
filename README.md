# SilentBot
<p><img src="https://i.imgur.com/uEmGPsr.png" width="200" height="200" /></img></p>

## What is it?
SilentBot is a bot that starts a timer when the last person who messaged in a server has messaged. The timer stops when somebody in the server talks. If there is a new record for the longest time the discord server has been silent, it will announce it.

### [Click here to add the bot to your Discord!](https://tinyurl.com/AddSilentBot)

## Setup
You must have Maven and Kotlin installed and configured. A config.yaml file must be made in the source root. The format for the YAML file is as follows:
```
discord:
  token: "TOKEN AS PER DISCORDAPP.COM"
```
After doing so, make a `data` folder at the root. After all of that, use the following command to run the bot (if you choose to use command line/terminal):
```
mvn exec:java -Dexec.mainClass="com.gmail.arhamjsiddiqui.silentbot.SilentBot"
```

## Commands
```
!!!help | Displays the list of commands.
!!!record | Shows the current record for silence in the current Discord channel.
```
