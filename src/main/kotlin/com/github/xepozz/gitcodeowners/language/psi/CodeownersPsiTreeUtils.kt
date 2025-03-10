package com.github.xepozz.gitcodeowners.language.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

object CodeownersPsiTreeUtils {
    fun findTeam(psiElement: PsiElement): CodeownersTeam? =
        PsiTreeUtil.collectParents(psiElement, CodeownersTeam::class.java, true)
        { it is CodeownersDefinition }
            .firstOrNull()

}