package com.github.xepozz.gitcodeowners.language

import com.github.xepozz.gitcodeowners.language.parser.CodeownersLexerAdapter
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class CodeownersSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = CodeownersLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
//        CodeownersTypes.SCHEDULE -> COMMENT_KEYS
//        CodeownersTypes.TIME_POINTER -> CONSTANT_KEYS
//        CodeownersTypes.COMMAND -> KEYWORD_KEYS
//        CodeownersTypes.EQUAL_SIGN -> OPERATION_KEYS

        CodeownersTypes.COMMENT -> COMMENT_KEYS
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