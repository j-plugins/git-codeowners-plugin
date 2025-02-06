package com.github.xepozz.gitcodeowners.language.parser

import com.github.xepozz.gitcodeowners.language.GitCodeownersFile
import com.github.xepozz.gitcodeowners.language.GitCodeownersLanguage
import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTokenSets
import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

internal class GitCodeownersParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = GitCodeownersLexerAdapter()

    override fun getCommentTokens() = GitCodeownersTokenSets.COMMENTS

    override fun getWhitespaceTokens(): TokenSet = GitCodeownersTokenSets.WHITESPACES

    override fun getStringLiteralElements(): TokenSet = GitCodeownersTokenSets.STRING_LITERALS

    override fun createParser(project: Project?) = GitCodeownersParser()

    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider) = GitCodeownersFile(viewProvider)

    override fun createElement(node: ASTNode): PsiElement = GitCodeownersTypes.Factory.createElement(node)

    companion object {
        val FILE = IFileElementType(GitCodeownersLanguage.INSTANCE)
    }
}