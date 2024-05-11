package com.mpalourdio.projects.flhacker.utils

import org.jaudiotagger.audio.exceptions.CannotReadException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

class AudiofileHandlerTest {

    @Test
    fun shouldSetUpGracefully() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist")
        assertDoesNotThrow(audiofileHandler::setUp)
    }

    @Test
    fun shouldTearDownGracefully() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist")
        assertDoesNotThrow(audiofileHandler::tearDown)
    }

    @Test
    fun shouldFailIfNoExtension() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist")
        assertThrows(CannotReadException::class.java, audiofileHandler::extractResizeSaveArtwork)
    }

    @Test
    fun shouldFailIfFileNotFound() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist.mp3")
        assertThrows(FileNotFoundException::class.java, audiofileHandler::extractResizeSaveArtwork)
    }
}
