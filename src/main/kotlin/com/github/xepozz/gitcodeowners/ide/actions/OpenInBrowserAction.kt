package com.github.xepozz.gitcodeowners.ide.actions

import com.github.xepozz.gitcodeowners.ide.CodeownersIdeUtils
import com.github.xepozz.gitcodeowners.language.CodeownersFile
import com.github.xepozz.gitcodeowners.language.psi.CodeownersPsiTreeUtils
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class OpenInBrowserAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) as? CodeownersFile ?: return
        val psiElement = psiFile.findElementAt(editor.caretModel.offset) ?: return

        val team = CodeownersPsiTreeUtils.findTeam(psiElement)

//        println("team1: $team")
        if (team != null) {
            CodeownersIdeUtils.openInBrowser(team)
        }
    }

    override fun update(event: AnActionEvent) {
        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) as? CodeownersFile ?: return
        val psiElement = psiFile.findElementAt(editor.caretModel.offset) ?: return

        val team = CodeownersPsiTreeUtils.findTeam(psiElement)

//        println("team2: $team")

        event.presentation.isEnabledAndVisible = team != null
    }
}