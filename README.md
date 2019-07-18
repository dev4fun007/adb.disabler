# adb.disabler
adb package disabler desktop support program


With package disabler pro for Samsung being paid and getting removed from playstore every now and then. 
The adb based package disabling is the only choice.

This repository has desktop support program for the main package disabler android app(in development).

# How this works
Starting the application starts - adb server and springboot server
It waits for the device to get connected
After device connection it does an adb reverse to enable android app to hit springboot rest endpoints
The android app indirectly enables/disables packages by calling appropriate rest endpoints

Still under development
