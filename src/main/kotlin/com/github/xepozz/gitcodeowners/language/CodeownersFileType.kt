package com.github.xepozz.gitcodeowners.language

import com.github.xepozz.gitcodeowners.CodeownersIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import java.io.Serializable

class CodeownersFileType private constructor() : LanguageFileType(CodeownersLanguage.INSTANCE), Serializable {
    override fun getName() = "Git Codeowners File"

    override fun getDescription() = "Git Codeowners language"

    override fun getDefaultExtension() = ""

    override fun getIcon() = CodeownersIcons.FILE

    companion object {
        @JvmStatic
        val INSTANCE = CodeownersFileType()
    }
}