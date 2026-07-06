// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.CodeownersFileType
import com.github.xepozz.gitcodeowners.language.CodeownersLanguage
import com.github.xepozz.gitcodeowners.language.psi.impl.CodeownersElementImpl
import com.github.xepozz.gitcodeowners.language.psi.impl.CodeownersPatternBaseImpl
import com.github.xepozz.gitcodeowners.language.psi.impl.CodeownersTeamBaseImpl
import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import com.intellij.util.IncorrectOperationException

class CodeownersElementManipulator : AbstractElementManipulator<CodeownersElementImpl>() {

    @Throws(IncorrectOperationException::class)
    override fun handleContentChange(entry: CodeownersElementImpl, range: TextRange, newContent: String): CodeownersElementImpl {
        if (entry.language !is CodeownersLanguage || entry.language.associatedFileType !is CodeownersFileType) return entry
        val updatedText = range.replace(entry.text, newContent)

        val newEntry = when (entry) {
            is CodeownersPatternBaseImpl -> CodeownersElementFactory.createPattern(entry.project, updatedText)
            is CodeownersTeamBaseImpl -> CodeownersElementFactory.createTeam(entry.project, updatedText)
            else -> null
        }

        return when (newEntry) {
            null -> entry
            else -> entry.replace(newEntry) as CodeownersElementImpl
        }
    }
}
