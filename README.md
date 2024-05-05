[![CI](https://github.com/mpalourdio/flhacker/actions/workflows/main.yml/badge.svg)](https://github.com/mpalourdio/flhacker/actions/workflows/main.yml)

# What is this ?

Intended to run as CLI, it extracts the artwork embedded in audio files (FLAC/MP3) and display it as ascii art.

# TLDR

- Download the [bin](https://github.com/mpalourdio/flhacker/raw/main/bin/flhacker) (linux x64 only. Windows users must compile it themselves, or get a true OS).
- Run `flhacker -f /tmp/my.flac`
- Let the magic happen

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

The needed `reachabilty metadata` are in the [src/main/resources/META-INF/native-image](src/main/resources/META-INF/native-image) folder.

# JUST MAKE A FUCKING .EXE FILE AND GIVE IT TO ME

The running executable available in the [bin](https://github.com/mpalourdio/flhacker/raw/main/bin/flhacker) is linux x64 only. I may forget to update the bin because this is not automated for now. It may also not target your OS/CPU architecture. 

The best way to be up-to-date is to clone the repo and compile the sources if you know what you are doing (or open an issue).

Also, read [this](https://www.reddit.com/r/github/comments/1at9br4/i_am_new_to_github_and_i_have_lots_to_say/?utm_source=share&utm_medium=mweb3x&utm_name=mweb3xcss&utm_term=1&utm_content=share_button) and learn how NOT to be an a$$.

# Warning

Use at your own risk, no unit-test, let's live dangerously.

