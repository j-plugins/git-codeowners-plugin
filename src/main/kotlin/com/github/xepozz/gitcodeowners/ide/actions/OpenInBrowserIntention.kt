package com.github.xepozz.gitcodeowners.ide.actions

import com.github.xepozz.gitcodeowners.CodeownersIcons
import com.github.xepozz.gitcodeowners.ide.CodeownersIdeUtils
import com.github.xepozz.gitcodeowners.language.psi.CodeownersPsiTreeUtils
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Iconable
import com.intellij.psi.PsiElement

class OpenInBrowserIntention : PsiElementBaseIntentionAction(), Iconable, DumbAware {
    override fun invoke(
        project: Project,
        editor: Editor?,
        element: PsiElement
    ) {
        val team = CodeownersPsiTreeUtils.findTeam(element)

        if (team != null) {
            CodeownersIdeUtils.openInBrowser(team)
        }
    }

    override fun isAvailable(project: Project, editor: Editor?, element: PsiElement): Boolean {
        val team = CodeownersPsiTreeUtils.findTeam(element)

        return team != null
    }

    override fun getFamilyName() = "Codeowners intentions"

    override fun getText() = "Open in browser"

    override fun getIcon(flags: Int) = CodeownersIcons.FILE
}