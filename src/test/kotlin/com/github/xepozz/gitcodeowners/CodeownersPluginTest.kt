package com.github.xepozz.gitcodeowners

import com.github.xepozz.gitcodeowners.ide.CodeownersHighlightUsagesHandlerFactory
import com.github.xepozz.gitcodeowners.ide.actions.OpenInBrowserIntention
import com.github.xepozz.gitcodeowners.language.CodeownersFileType
import com.github.xepozz.gitcodeowners.language.psi.CodeownersElementManipulator
import com.github.xepozz.gitcodeowners.language.psi.CodeownersPattern
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTeam
import com.github.xepozz.gitcodeowners.language.psi.impl.CodeownersElementImpl
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.Consumer

class CodeownersPluginTest : BasePlatformTestCase() {
    fun testTeamRenameReplacesOwnerText() {
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src @old\n")
        val team = findTeam("@old")

        WriteCommandAction.runWriteCommandAction(project) {
            team.setName("@new")
        }

        assertEquals("/src @new\n", myFixture.file.text)
    }

    fun testElementManipulatorUsesNewContentForTeamAndPattern() {
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src @old\n")
        val pattern = findPattern("/src")
        val team = findTeam("@old")
        val manipulator = CodeownersElementManipulator()

        WriteCommandAction.runWriteCommandAction(project) {
            manipulator.handleContentChange(pattern as CodeownersElementImpl, TextRange.allOf(pattern.text), "/docs")
            manipulator.handleContentChange(team as CodeownersElementImpl, TextRange.allOf(team.text), "@new")
        }

        assertEquals("/docs @new\n", myFixture.file.text)
    }

    fun testCompletionKeepsEmailCandidatesWhenCurrentOwnerStartsWithAt() {
        myFixture.configureByText(
            CodeownersFileType.INSTANCE,
            "/src @ops user@example.com\n/docs @<caret>\n"
        )

        val variants = myFixture.completeBasic().orEmpty().map { it.lookupString }

        assertContainsElements(variants, "ops", "user@example.com")
        assertFalse(variants.contains("ser@example.com"))
    }

    fun testOpenInBrowserIntentionIsUnavailableForEmailOwner() {
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src user@example<caret>.com\n")

        val element = myFixture.file.findElementAt(myFixture.caretOffset)!!

        assertFalse(OpenInBrowserIntention().isAvailable(project, myFixture.editor, element))
    }

    fun testOpenInBrowserIntentionIsAvailableForGithubOwner() {
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src @org/team<caret>\n")

        val element = myFixture.file.findElementAt(myFixture.caretOffset - 1)!!

        assertTrue(OpenInBrowserIntention().isAvailable(project, myFixture.editor, element))
    }

    fun testOpenInBrowserIntentionIsUnavailableForIncompleteGithubOwner() {
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src @<caret>\n")

        val element = myFixture.file.findElementAt(myFixture.caretOffset - 1)!!

        assertFalse(OpenInBrowserIntention().isAvailable(project, myFixture.editor, element))
    }

    fun testRootRelativePatternReferenceResolvesFromProjectRoot() {
        myFixture.addFileToProject("src/App.kt", "class App\n")
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src/App.kt @team\n")
        val pattern = findPattern("/src/App.kt")

        val resolvesToAppFile = pattern.references
            .mapNotNull { it.resolve() as? PsiFile }
            .any { it.name == "App.kt" }

        assertTrue(resolvesToAppFile)
    }

    fun testHighlightUsagesSelectTargetsPassesTargetsToConsumer() {
        myFixture.configureByText(CodeownersFileType.INSTANCE, "/src @ops\n/docs @ops\n")
        val target = myFixture.file.findElementAt(myFixture.file.text.indexOf("@ops") + 1)!!
        val handler = CodeownersHighlightUsagesHandlerFactory()
            .createHighlightUsagesHandler(myFixture.editor, myFixture.file, target)!!

        val selectedTargets = mutableListOf<CodeownersElementImpl>()
        val selectTargets = handler.javaClass.getDeclaredMethod("selectTargets", List::class.java, Consumer::class.java)
        selectTargets.isAccessible = true
        selectTargets.invoke(handler, handler.targets, Consumer<List<CodeownersElementImpl>> { selectedTargets.addAll(it) })

        assertTrue(selectedTargets.isNotEmpty())
    }

    private fun findPattern(text: String): CodeownersPattern =
        PsiTreeUtil.findChildrenOfType(myFixture.file, CodeownersPattern::class.java)
            .first { it.text == text }

    private fun findTeam(text: String): CodeownersTeam =
        PsiTreeUtil.findChildrenOfType(myFixture.file, CodeownersTeam::class.java)
            .first { it.text == text }
}
