# Weather Display

A desktop weather app built with Java and Swing. Search any city and see its
current conditions plus a 5-day forecast in a clean, card-based interface with
customisable themes.

## Features

- **City search** with friendly error messages when a city isn't found.
- **5-day forecast** shown as cards (day, date, weather icon, temperature),
  picked for the time of day closest to the city's current local time.
- **Saved cities sidebar** — add cities you check often, then click to reload
  them. Hover a saved city to reveal a minus button that removes it.
- **Themes** — light and dark mode, plus 13 colour themes (Normal, Summer,
  Night Sky, Forest, Ocean, Sunset, Lavender, Crimson, Slate, Mint, Gold,
  Grape, Coral).
- **Saved preferences** — your theme and saved cities persist between sessions.

## How it works

The app fetches forecast data from the [OpenWeatherMap](https://openweathermap.org/)
API, parses the JSON response, and displays it through a Swing interface.

| Part | Responsibility |
|------|----------------|
| `Main.java` | Launches the app |
| `API_Client.java` | Calls the weather API and parses the JSON |
| `WeatherController.java` | Coordinates a search and reports errors |
| `Weather_Helper.java`, `WeatherDateOBJ.java`, `CountryOBJ.java` | Weather data model and helpers |
| `Preferences.java` | Reads/writes theme and saved cities to JSON |
| `ui/` | All interface code: themes, hero panel, forecast cards, sidebar, settings |

Preferences are stored at `~/.weatherdisplay/preferences.json`.

## Tech

- Java 25 (Swing for the UI)
- [org.json](https://github.com/stleary/JSON-java) for JSON parsing
- Maven for building
