package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.GitCodeownersLanguage
import com.intellij.psi.tree.IElementType

class GitCodeownersElementType(debugName: String) : IElementType("GitCodeownersElementType($debugName)", GitCodeownersLanguage.INSTANCE)
