# What is this ?

Intended to run as CLI, it extracts the artwork embedded in audio files (FLAC/MP3) and display it as ascii art.

# Why ?

Who knows ?

# How does it work ?

- Uses `Spring Boot` as base, because life is too short.
- Uses [Jaudiotagger](https://www.jthink.net/jaudiotagger/).

# Requirements for hacking the codebase

- Java 21 / maven 3.6+
- GraalVM 23+ for native image compilation support

# GraalVM Native image support

- Full native image support. Run `mvn -Pnative native:compile`.
- Run `target/flhacker -f /tmp/my.flac|mp3`. Don't forget to escape spaces etc.
- See the artwork displayed as ascii art in your terminal.

The needed `reachabilty metadata` are in the [src/main/resources/META-INF/native-image](src/main/resources/META-INF/native-image]) folder.

# Five me a downloadable bin

A running executable is available for download in the [bin](https://github.com/mpalourdio/flhacker/raw/main/bin/flhacker) folder (linux x64 only. Windows users must compile it themselves, or get a true OS).

# Warning

Use at your own risk, no unit-test, let's live dangerously.

