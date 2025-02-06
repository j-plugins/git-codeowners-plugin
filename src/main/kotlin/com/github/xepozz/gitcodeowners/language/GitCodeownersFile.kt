package com.github.xepozz.gitcodeowners.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class GitCodeownersFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, GitCodeownersLanguage.INSTANCE) {
    override fun getFileType() = GitCodeownersFileType.INSTANCE

    override fun toString() = "Git Codeowners File"
}