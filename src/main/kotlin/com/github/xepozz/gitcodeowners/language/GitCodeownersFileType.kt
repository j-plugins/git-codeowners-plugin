package com.github.xepozz.gitcodeowners.language

import com.github.xepozz.gitcodeowners.GitCodeownersIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class GitCodeownersFileType private constructor() : LanguageFileType(GitCodeownersLanguage.INSTANCE) {
    override fun getName() = "Git Codeowners File"

    override fun getDescription() = "Git Codeowners language file"

    override fun getDefaultExtension() = ""

    override fun getIcon() = GitCodeownersIcons.FILE

    companion object {
        val INSTANCE = GitCodeownersFileType()
    }
}