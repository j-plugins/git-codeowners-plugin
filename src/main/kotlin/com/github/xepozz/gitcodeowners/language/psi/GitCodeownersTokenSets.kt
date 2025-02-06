package com.github.xepozz.gitcodeowners.language.psi

import com.intellij.psi.tree.TokenSet

object GitCodeownersTokenSets {
    val EMPTY_SET = TokenSet.EMPTY

    val COMMENTS = TokenSet.create(GitCodeownersTypes.COMMENT)
    val STRING_LITERALS = TokenSet.create(GitCodeownersTypes.PATTERN)
    val WHITESPACES = TokenSet.WHITE_SPACE
}