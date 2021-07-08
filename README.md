# Template repository

Template repository for the Jetpack Compose [#AndroidDevChallenge](https://developer.android.com/dev-challenge).

## Tutorial
Part I: https://www.waseefakhtar.com/android/recyclerview-in-jetpack-compose/ </br>
Part II: https://www.waseefakhtar.com/android/jetpack-compose-styles-and-themes/ </br>
Part III: https://www.waseefakhtar.com/android/jetpack-compose-navigating-to-a-detail-view/

## Screenshots
<img src="https://github.com/rascalyen/bark/blob/demo/screenshots/20210708-092825.png?raw=true" width="200"/> <img src="https://github.com/waseefakhtar/bark/blob/main/screenshots/device-2021-02-27-190647.png?raw=true" width="200"/>

## Demo

https://github.com/waseefakhtar/bark/blob/main/demo/device-2021-02-27-180752.mp4

## Getting started
Copy this repository by pressing the "Use this template" button in Github.
Clone your repository and open it in the latest [Android Studio (Canary build)](https://developer.android.com/studio/preview).

## Submission requirements
- Follow the challenge description on the project website: [developer.android.com/dev-challenge](https://developer.android.com/dev-challenge)
- All UI should be written using Jetpack Compose
- The Github Actions workflow should complete successfully
- Include two screenshots of your submission in the [results](results) folder. The names should be
  screenshot_1.png and screenshot_2.png.
- Include a screen record of your submission in the [results](results) folder. The name should be
  video.mp4
- Replace the contents of [README.md](README.md) with the contents of [README-template.md](README-template.md) and fill out the template.

## Code formatting
The CI uses [Spotless](https://github.com/diffplug/spotless) to check if your code is formatted correctly and contains the right licenses.
Internally, Spotless uses [ktlint](https://github.com/pinterest/ktlint) to check the formatting of your code.
To set up ktlint correctly with Android Studio, follow one of the [listed setup options](https://github.com/pinterest/ktlint#-with-intellij-idea).

Before committing your code, run `./gradlew app:spotlessApply` to automatically format your code.

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
