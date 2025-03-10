package com.github.xepozz.gitcodeowners.language.psi

import com.intellij.psi.PsiElement

object CodeownersPsiTreeUtils {
    fun findTeam(psiElement: PsiElement): CodeownersTeam? = psiElement.parent as? CodeownersTeam
}