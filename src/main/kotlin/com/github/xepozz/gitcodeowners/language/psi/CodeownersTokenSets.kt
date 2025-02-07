package com.github.xepozz.gitcodeowners.language.psi

import com.intellij.psi.tree.TokenSet

object CodeownersTokenSets {
    val EMPTY_SET = TokenSet.EMPTY

    val COMMENTS = TokenSet.create(CodeownersTypes.COMMENT)
    val STRING_LITERALS = TokenSet.create(CodeownersTypes.PATTERN)
    val WHITESPACES = TokenSet.WHITE_SPACE
}