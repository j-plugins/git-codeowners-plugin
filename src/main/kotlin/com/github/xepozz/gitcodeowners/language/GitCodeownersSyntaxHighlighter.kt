package com.github.xepozz.gitcodeowners.language

import com.github.xepozz.gitcodeowners.language.parser.GitCodeownersLexerAdapter
import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class GitCodeownersSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = GitCodeownersLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
//        GitCodeownersTypes.SCHEDULE -> COMMENT_KEYS
//        GitCodeownersTypes.TIME_POINTER -> CONSTANT_KEYS
//        GitCodeownersTypes.COMMAND -> KEYWORD_KEYS
//        GitCodeownersTypes.EQUAL_SIGN -> OPERATION_KEYS

        GitCodeownersTypes.COMMENT -> COMMENT_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    companion object {
        private val BAD_CHAR_KEYS = arrayOf(
            HighlighterColors.BAD_CHARACTER,
        )

        private val COMMENT_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.DOC_COMMENT
        )
        private val OPERATION_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.OPERATION_SIGN
        )
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}