# SilentBot
<p><img src="https://i.imgur.com/uEmGPsr.png" width="200" height="200" /></img></p>

## What is it?
SilentBot is a bot that starts a timer when the last person who messaged in a server has messaged. The timer stops when somebody in the server talks. If there is a new record for the longest time the discord server has been silent, it will announce it.

### [Click here to add the bot to your Discord!](https://tinyurl.com/AddSilentBot)

## Setup
A config.yaml file must be made in the source root. The format for the YAML file is as follows:
```
discord:
  token: "TOKEN AS PER DISCORDAPP.COM"
```
After that, simply run SilentBot.kt!

## Commands
As of right now, the bot only supports the `!!!help` command. The bot's only planned command is `!!!record` which will display the current record for the longest server silence time.