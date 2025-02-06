package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.GitCodeownersLanguage
import com.intellij.psi.tree.IElementType

class GitCodeownersTokenType(debugName: String) : IElementType(debugName, GitCodeownersLanguage.INSTANCE) {
    override fun toString() = "GitCodeownersTokenType." + super.toString()
}