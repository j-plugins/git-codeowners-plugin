package com.github.xepozz.gitcodeowners.ide.documentation

import com.github.xepozz.gitcodeowners.language.psi.CodeownersTypes
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.elementType

object CodeownersDocumentationUtils {
    fun findCrontabElementDocumentation(element: PsiElement?): String =
        findContextualDocumentationElement(element)?.text?.replaceFirst("[# ]+", "") ?: ""

    fun findContextualDocumentationElement(element: PsiElement?): PsiComment? {
        if (element == null) return null

        var element = element.prevSibling
        while (element is PsiComment || element is PsiWhiteSpace || element.elementType == CodeownersTypes.EOL) {
            if (element is PsiComment) {
                return element
            }
            element = element.prevSibling
        }

        return null
    }
}