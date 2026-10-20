package dev.omalauncher.linux

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class AgentsTest {

    /** First-run setup and later Linux installation both enumerate AGENTS. */
    @Test fun hermesIsOfferedWithItsOwnInstaller() {
        val agent = AGENTS.single { it.bin == "hermes" }
        assertEquals("Hermes Agent", agent.name)
        assertTrue(agent.install!!.contains("--non-interactive"))
        assertTrue(agent.install!!.contains("UV_LINK_MODE=copy"))
        assertTrue(agent.install!!.contains("--skip-browser"))
    }

    /** A widget prompt must not be evaluated by the guest shell as options or commands. */
    @Test fun hermesPromptUsesChatQueryFlagWithOneLiteralArgument() {
        val prompt = "--help; $(exit 99) 'quoted'"
        val script = """
            set -- ${agentInvocation("hermes", prompt)}
            test "${'$'}#" -eq 4 &&
            test "${'$'}1" = hermes &&
            test "${'$'}2" = chat &&
            test "${'$'}3" = -q &&
            test "${'$'}4" = " ${'$'}EXPECTED_PROMPT"
        """.trimIndent()
        val process = ProcessBuilder("sh", "-c", script)
            .redirectErrorStream(true)
            .apply { environment()["EXPECTED_PROMPT"] = prompt }
            .start()
        val output = process.inputStream.bufferedReader().use { it.readText() }
        assertEquals(output, 0, process.waitFor())
    }

    /** A missing alias would make opening that agent's window throw. */
    @Test fun everyAgentHasAWindowAlias() {
        val manifest = File("src/main/AndroidManifest.xml").readText()
        val aliases = Regex("""<activity-alias(.*?)/>""", RegexOption.DOT_MATCHES_ALL).findAll(manifest).map { it.groupValues[1] }.toList()
        for (agent in AGENTS) {
            val alias = aliases.firstOrNull { "android:name=\".${agent.windowAlias}\"" in it }
            assertTrue("${agent.bin}: no .${agent.windowAlias} in the manifest", alias != null)
            assertTrue("${agent.bin}: not labelled \"${agent.name}\"", "android:label=\"${agent.name}\"" in alias!!)
            assertTrue("${agent.bin}: no logo", "android:icon=\"@drawable/agent_${agent.bin.replace('-', '_')}\"" in alias)
        }
    }

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

    /** A prompt from a widget can't pass the agent an option: claude --settings=... would run its hooks. */
    @Test fun aPromptStartingWithADashIsNoOption() {
        val script = """
            set -- ${agentInvocation("claude", "--settings={}")}
            test "${'$'}#" -eq 2 &&
            test "${'$'}2" = " --settings={}"
        """.trimIndent()
        val process = ProcessBuilder("sh", "-c", script).redirectErrorStream(true).start()
        val output = process.inputStream.bufferedReader().use { it.readText() }
        assertEquals(output, 0, process.waitFor())
    }

    /** OpenCode installs from before 2 have no node, and mise needs it for OpenCode 2's postinstall. */
    @Test fun updatesAddNodeForOpencode() {
        assertEquals(
            "if [ -z \"\$(mise ls -g node)\" ] && [ -n \"\$(mise ls -g 'opencode' 'npm:@xai-official/grok' 'npm:@moonshot-ai/kimi-code')\" ]; " +
                "then mise use -g node@lts; fi",
            MISE_NODE_FOR_AGENTS,
        )
    }
}
