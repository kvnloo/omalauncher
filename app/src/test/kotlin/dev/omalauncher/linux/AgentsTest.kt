package dev.omalauncher.linux

import org.junit.Assert.assertEquals
import org.junit.Test

class AgentsTest {
    @Test fun copilotReceivesAnInteractivePromptAsOneLiteralArgument() {
        val prompt = "Explain \"it's quoted\"; \$(exit 17)\nand keep \$HOME literal."
        val script = """
            set -- ${agentInvocation("copilot", prompt)}
            test "${'$'}#" -eq 3 &&
            test "${'$'}1" = copilot &&
            test "${'$'}2" = -i &&
            test "${'$'}3" = "${'$'}EXPECTED_PROMPT"
        """.trimIndent()
        val process = ProcessBuilder("sh", "-c", script)
            .redirectErrorStream(true)
            .apply { environment()["EXPECTED_PROMPT"] = prompt }
            .start()
        val output = process.inputStream.bufferedReader().use { it.readText() }
        assertEquals(output, 0, process.waitFor())
    }
}
