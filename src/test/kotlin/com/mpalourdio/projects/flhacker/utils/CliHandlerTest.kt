package com.mpalourdio.projects.flhacker.utils

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
        assertTrue(outputStreamCaptor.toString().contains("-f,--file"))
    }

    @Test
    fun testWrongOptionsPrintsHelp() {
        CliHandler(arrayOf("a", "hello")).toString()
        assertTrue(outputStreamCaptor.toString().contains("-f,--file"))
    }

    @Test
    fun shouldSetFilePathIdExists() {
        val filePath = "/dev/null"
        val cliHandler = CliHandler(arrayOf("-f", filePath))
        assertEquals(filePath, cliHandler.filePath)
    }
}
