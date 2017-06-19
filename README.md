# BrewYou
#### An app where the user can add beers to lists that the user either likes or dislikes. Stretch goal to find beers the user might like or want to avoid based off their previous decisions.

#### Created By _** Arlen Burton **_

## Description

An app where the user can add beers to lists that the user either likes or dislikes. Stretch goal to find beers the user might like or want to avoid based off their previous decisions.

## Requirements

Android device operating on AndroidOS version 4.0.1 (icecream sandwich)

## Known Bugs
_{Switching toand from landscape is sometimes slow and buggy, fragments will load in landscape or protrait as intened, but uppon switching orientation app will crash with an out of bounds exception}_
_{when loading app from android studio users will not be able to authorize with google. the workarround to this is when the project is pulled from github:}_ 
1) Use android studio instant run
2) Uninstall the app from the emulator
3) Use the finder/explorer to select the highest version of app-release.sdk from inside the app folder
4) drag and drop this file onto your emulator to install the signed version that will work with firebase
5) restart emulator (if restarted via instant run, do not uninstall current app if sdk file has been installed via step 4)
6) run app as intended

## Support and contact details

Questions? Concerns? Ideas? Contact BurtonArlen on github at github.com/burtonarlen

## Technologies Used

Built in Android Studio 2.3.2 using Java, XML, and the BreweryDB Api. Tested on android version 7.0 (Nougat)

### License

*Released under the MIT license.*

Copyright (c) 2016 **_Arlen Burton_**
