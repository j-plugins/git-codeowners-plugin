package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.CodeownersLanguage
import com.intellij.psi.tree.IElementType

class CodeownersTokenType(debugName: String) : IElementType(debugName, CodeownersLanguage.INSTANCE) {
    override fun toString() = "CodeownersTokenType." + super.toString()
}