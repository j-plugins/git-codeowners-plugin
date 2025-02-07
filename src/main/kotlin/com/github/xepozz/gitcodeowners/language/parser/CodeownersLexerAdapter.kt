package com.github.xepozz.gitcodeowners.language.parser

import com.intellij.lexer.FlexAdapter

class CodeownersLexerAdapter : FlexAdapter(CodeownersLexer(null))