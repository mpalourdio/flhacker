[![CI](https://github.com/mpalourdio/flhacker/actions/workflows/main.yml/badge.svg)](https://github.com/mpalourdio/flhacker/actions/workflows/main.yml)

# What is this ?

Intended to run as CLI, it extracts the artwork embedded in audio files (FLAC/MP3) and display it as ascii art.

# TLDR

- Download & extract the [bin and the .so](https://mpalourdio.github.io/flhacker/flhacker.zip) (linux x86-64 only. Windows users must compile it themselves, or get a true OS).
- Run `./flhacker -f /tmp/my.flac`
- Let the magic happen

# Example
`./flhacker -f ~/Desktop/Time.flac`

![Example](examples/dsotm.png)

# Why ?

Who knows ?

# How does it work ?

- Uses `Spring Boot` as base, because life is too short.
- Uses [Jaudiotagger](https://www.jthink.net/jaudiotagger/).
- Uses [image-to-ascii](https://github.com/seujorgenochurras/image-to-ascii).
- The needed JDK `.so` are dynamically linked.

# Requirements for hacking the codebase

- Java 21 / maven 3.6+
- GraalVM 23+ for native image compilation support

# GraalVM Native image support

- Full native image support. Run `mvn -Pnative native:compile`.
- Run `target/flhacker -f /tmp/my.flac|mp3`. Don't forget to escape spaces etc.
- See the artwork displayed as ascii art in your terminal.

The needed `reachabilty metadata` are in the [src/main/resources/META-INF/native-image](src/main/resources/META-INF/native-image) folder.

# JUST MAKE A FUCKING .EXE FILE AND GIVE IT TO ME

The running executable available in the [zip](https://mpalourdio.github.io/flhacker/flhacker.zip) is linux x86-64 only, built on `ubuntu-20.04` via `GitHub Actions`. So, it may not target your OS/CPU/libc architecture. 

The best way to be up-to-date is to clone the repo and compile the sources if you know what you are doing (or open an issue).

Also, read [this](https://www.reddit.com/r/github/comments/1at9br4/i_am_new_to_github_and_i_have_lots_to_say/?utm_source=share&utm_medium=mweb3x&utm_name=mweb3xcss&utm_term=1&utm_content=share_button) and learn how NOT to be an a$$.
