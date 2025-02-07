package com.github.xepozz.gitcodeowners.ide

import com.github.xepozz.gitcodeowners.language.CodeownersLanguage
import com.intellij.codeInsight.daemon.impl.HighlightRangeExtension
import com.intellij.psi.PsiFile

class IgnoreHighlightRangeExtension : HighlightRangeExtension {
    override fun isForceHighlightParents(file: PsiFile) = file.language is CodeownersLanguage
}