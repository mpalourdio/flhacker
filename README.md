# What is this

Intended to run as CLI, it extracts the artwork embedded in audio files (FLAC/MP3) and display it as ascii art.

# Why ?

dunno.

# How does it work ?

- Uses `Spring Boot` as base, because life is too short.
- Uses [https://www.jthink.net/jaudiotagger/](`Jaudiotagger`).

# Native image support

- Full native image support. Run `mvn -Pnative native:compile`
- Run `target/flhacker -f /tmp/my.flac|mp3`. Don't forget to escape spaces etc.
- See the artwork displayed as ascii art in your terminal.

A running executable is available for download in the `bin` folder (linux x64 only. Windows users should compile it themselves, or get a true OS).

# Requirements for hacking

- Java 21 / maven 3.6+
- GraalVM 23+ for native image compilation support

# Warning

Use at your own risk, no unit-test, let's live dangerously.

