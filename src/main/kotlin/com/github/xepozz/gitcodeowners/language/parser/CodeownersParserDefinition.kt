package com.github.xepozz.gitcodeowners.language.parser

import com.github.xepozz.gitcodeowners.language.CodeownersFile
import com.github.xepozz.gitcodeowners.language.CodeownersLanguage
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTokenSets
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

internal class CodeownersParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = CodeownersLexerAdapter()

    override fun getCommentTokens() = CodeownersTokenSets.COMMENTS

    override fun getWhitespaceTokens(): TokenSet = CodeownersTokenSets.WHITESPACES

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createParser(project: Project?) = CodeownersParser()

    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider) = CodeownersFile(viewProvider)

    override fun createElement(node: ASTNode): PsiElement = CodeownersTypes.Factory.createElement(node)

    companion object {
        val FILE = IFileElementType(CodeownersLanguage.INSTANCE)
    }
}