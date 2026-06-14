# Weather Display

[![Build executables](https://github.com/aydsman/WeatherDisplay/actions/workflows/build.yml/badge.svg)](https://github.com/aydsman/WeatherDisplay/actions/workflows/build.yml)

A desktop weather app (Java + Swing) with a card-based 5-day forecast, light/dark mode,
multiple colour themes, and a saved-cities sidebar. Forecast data comes from the
OpenWeatherMap API.

## Download and run (no build needed)

Every push to GitHub builds downloadable executables automatically via GitHub Actions.

1. Go to the repo's **Actions** tab on GitHub.
2. Open the most recent **Build executables** run.
3. Under **Artifacts**, download one of:
   - **WeatherDisplay-windows-exe** — a `.zip` containing `WeatherDisplay.exe` with a
     bundled Java runtime. Unzip it and double-click `WeatherDisplay.exe`.
     No Java installation required.
   - **WeatherApp-jar** — a single `WeatherApp.jar` that runs anywhere Java 25+ is
     installed. Double-click it, or run `java -jar WeatherApp.jar`.

Tip: pushing a version tag (for example `v1.0`) also publishes these files on the
repo's **Releases** page for easy public download.

```bash
git tag v1.0
git push origin v1.0
```

## Build it yourself

Requires JDK 25 and Maven.

```bash
# Build the self-contained runnable JAR -> target/WeatherApp.jar
mvn package

# Run it
java -jar target/WeatherApp.jar
```

To create a native Windows `.exe` locally (JDK includes `jpackage`):

```powershell
mkdir staging
copy target\WeatherApp.jar staging\
jpackage --type app-image --name WeatherDisplay --input staging --main-jar WeatherApp.jar --main-class Main --dest dist
# Result: dist\WeatherDisplay\WeatherDisplay.exe
```

## Project layout

| Path | Purpose |
|------|---------|
| `src/Main.java` | Application entry point |
| `src/API_Client.java`, `src/WeatherController.java` | Fetch and parse forecast data |
| `src/Weather_Helper.java`, `src/WeatherDateOBJ.java`, `src/CountryOBJ.java` | Data model and helpers |
| `src/Preferences.java` | Saves theme + saved cities to JSON |
| `src/ui/` | All UI: themes, hero, forecast cards, sidebar, settings |

User preferences are stored at `~/.weatherdisplay/preferences.json`.
