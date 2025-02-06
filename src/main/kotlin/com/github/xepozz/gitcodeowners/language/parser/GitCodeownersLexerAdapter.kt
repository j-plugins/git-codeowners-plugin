package com.github.xepozz.gitcodeowners.language.parser

import com.intellij.lexer.FlexAdapter

class GitCodeownersLexerAdapter : FlexAdapter(GitCodeownersLexer(null))