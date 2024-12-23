package com.mpalourdio.projects.flhacker.utils

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.jaudiotagger.audio.exceptions.CannotReadException
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

class AudiofileHandlerTest {

    @Test
    fun shouldSetUpGracefully() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist")
        assertThatCode(audiofileHandler::setUp).doesNotThrowAnyException()
    }

    @Test
    fun shouldTearDownGracefully() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist")
        assertThatCode(audiofileHandler::tearDown).doesNotThrowAnyException()
    }

    @Test
    fun shouldFailIfNoExtension() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist")
        assertThatThrownBy(audiofileHandler::extractResizeSaveArtwork).isInstanceOf(CannotReadException::class.java)
    }

    @Test
    fun shouldFailIfFileNotFound() {
        val audiofileHandler = AudiofileHandler("/i/dont/exist.mp3")
        assertThatThrownBy(audiofileHandler::extractResizeSaveArtwork).isInstanceOf(FileNotFoundException::class.java)
    }
}
