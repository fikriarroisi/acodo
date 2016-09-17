#ACODO
CLI based count down timer program written in Java.

There are two mode two mode for counting : 

1. List countdown   : you specify your task and time as much as you want.
2. Toggle countdown : you specify just 2 tasks and number of loop for looping that 2 tasks.

###How To
This is how to input a value or time:

- Input duration in hours, minutes, and seconds:
    - `x` for `x` seconds, eg. `121` for 121 seconds.
    - `x/y` for `x` minutes and `y` seconds, eg. `2/10` for 2 minutes and 10 seconds.
    - `x/y/z` for `x` hours, `y` minutes, and `z` seconds, eg. `1/2/3` for 1 hours, 2 minutes, and 3 seconds
- Input in specific time (using 24 hours format):
    - `x:` for `x`:00:00, eg. `23:` for 23:00:00
    - `x:y` for `x`:`y`:00, eg. `23:21` for 23:21:00
    - `x:y:z` for `x`:`y`:`z`, eg. `23:21:55` for 23:21:55
- Trick for toggle countdown:
    - If you want the two tasks to loop FOREVER, input number of round with `-1` (negative one), but remember you have to force close the program to stop it (usually CTRL + C).
- How to change the alert sounds:
    - You can change the alert sounds by replacing the .mp3 files in the "Sounds" directory (located in the same directory as the source file).
    - Make sure you replace it with .mp3 file.
    - file named change.mp3 used when the task starting or changing.
    - file named almost.mp3 used when the value or time less than 10 or 10 seconds.
    - file named done.mp3 used when all task done.
    - make sure you replace it with the **SAME EXACT** name!
    - **NOTE** : Place the `Sound` folder in the same directory as the source file or jar file to make the notification sound work.

###Requirement 
To run this program you need : 

- [Java](https://java.com/en/download/)

###Other
Made with <3 by Fikri Arroisi

Version : 160916

Default sound source:

- Task change - http://soundbible.com/1252-Bleep.html
- Task under 10 seconds remaining - http://soundbible.com/535-Alarm-Clock.html
- All tasks done - http://soundbible.com/1656-House-Fire-Alarm.html

Tested run in Linux, Windows, and Mac.
Be sure to let me know if you like and find this program is useful.