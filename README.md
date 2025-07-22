# img2txt: image to text convertor

## General
This tool allows convert tiny png to emojis for Mindustry.

## Usage:

### GUI

  run without options to start gui: <br>
```
  java -jar img2txt.jar
```
<img src=screenshots/gui-sample.jpg>

### CLI
  ```
java -jar img2txt.jar help
```
```
Usage: java -jar img2txt.jar <input.png> <emoji> <output.txt>
Run without arguments to start gui.
   Available emojis are:
     salt
     ice
     dark_sand
     snow
     moss
     copper
     book
     file
     github_cat
     sharp
WARNING! if output file is not specified, then it will be same as input, but png will be replaced with txt.
  It can overwrite it! (if input = a.png, output will be a.txt)
  ```
### Mindustry 

goto settings -> game -> enable console 
<br>
press f8
<br>
type:
<br>
``` 
Call.sendMessage(OS.exec("cat", "test.txt")) 
```
<br>
OR (if image if very very tiny, perhaps will not work properly)
<br>

```
Call.sendChatMessage(OS.exec("cat", "test.txt"))
```

<br>

On windows replace "cat" on "type" and it probably will work...
