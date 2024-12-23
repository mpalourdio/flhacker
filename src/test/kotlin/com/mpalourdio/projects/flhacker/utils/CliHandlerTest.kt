package com.mpalourdio.projects.flhacker.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CliHandlerTest {

    private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()
    private val standardOut: PrintStream = System.out

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun testNoOptionsPrintsHelp() {
        CliHandler(arrayOf()).toString()
        assertThat(outputStreamCaptor.toString().contains("-f,--file")).isTrue()
    }

    @Test
    fun testWrongOptionsPrintsHelp() {
        CliHandler(arrayOf("a", "hello")).toString()
        assertThat(outputStreamCaptor.toString().contains("-f,--file")).isTrue()
    }

    @Test
    fun shouldSetFilePathIfExists() {
        val filePath = "/dev/null"
        val cliHandler = CliHandler(arrayOf("-f", filePath))
        assertThat(cliHandler.filePath).isEqualTo(filePath)
    }
}
