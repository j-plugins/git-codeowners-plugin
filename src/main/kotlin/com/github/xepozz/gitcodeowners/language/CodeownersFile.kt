package com.github.xepozz.gitcodeowners.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class CodeownersFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CodeownersLanguage.INSTANCE) {
    override fun getFileType() = CodeownersFileType.INSTANCE

    override fun toString() = "Git Codeowners File"
}