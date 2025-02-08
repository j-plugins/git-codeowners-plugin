// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.CodeownersFileType
import com.github.xepozz.gitcodeowners.language.CodeownersLanguage
import com.github.xepozz.gitcodeowners.language.psi.CodeownersElementFactory
import com.github.xepozz.gitcodeowners.language.psi.impl.CodeownersElementImpl
import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.IncorrectOperationException

class CodeownersElementManipulator : AbstractElementManipulator<CodeownersElementImpl>() {

    @Throws(IncorrectOperationException::class)
    override fun handleContentChange(entry: CodeownersElementImpl, range: TextRange, newContent: String): CodeownersElementImpl {
        val language = entry.language as? CodeownersLanguage ?: return entry

        val fileType = language.associatedFileType as CodeownersFileType
        val file = CodeownersElementFactory
            .createFile(entry.project, entry.text)

        val newEntry = PsiTreeUtil.findChildOfType(file, CodeownersElementImpl::class.java)

        return when (newEntry) {
            null -> entry
            else -> entry.replace(newEntry) as CodeownersElementImpl
        }
    }
}