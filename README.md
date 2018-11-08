# EclipsePluginDownloader

Simple Java application that let you create offline plugins repository from eclipse P2.

## When to use it ?

This application is for you if you : 

* are on a network without network connectivity
* are behind a proxy that filter p2 repository or eclipse marketplace
* have a slow internet speed and want to speed up your eclipse installation.

## How to use ?

```bash
java -jar epd.jar <config.json_path> 
```

## Example of config.json file

EPD use a json config file to work. This file contains the list of plugins to download as follow.
```json
{
	"eclipseDir" : "<ECLIPSE_HOME_DIR>",
	"pluginDir" : "<PLUGIN_TARGET_DIR",
	"verbose" : 1,
	"plugins" : [
		{
			"name" : "SonarLint",
			"path": "https://eclipse-uc.sonarlint.org",
			"enable": 1
		},
	]
}
```