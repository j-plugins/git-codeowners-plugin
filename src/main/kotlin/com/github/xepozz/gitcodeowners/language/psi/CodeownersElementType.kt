package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.CodeownersLanguage
import com.intellij.psi.tree.IElementType

class CodeownersElementType(debugName: String) : IElementType("CodeownersElementType($debugName)", CodeownersLanguage.INSTANCE)
