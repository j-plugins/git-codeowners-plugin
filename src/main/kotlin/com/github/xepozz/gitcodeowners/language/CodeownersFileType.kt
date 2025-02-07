package com.github.xepozz.gitcodeowners.language

import com.github.xepozz.gitcodeowners.CodeownersIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class CodeownersFileType private constructor() : LanguageFileType(CodeownersLanguage.INSTANCE) {
    override fun getName() = "Git Codeowners File"

    override fun getDescription() = "Git Codeowners language file"

    override fun getDefaultExtension() = ""

    override fun getIcon() = CodeownersIcons.FILE

    companion object {
        val INSTANCE = CodeownersFileType()
    }
}