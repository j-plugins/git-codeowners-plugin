package com.github.xepozz.gitcodeowners.language

import com.intellij.lang.Language

class GitCodeownersLanguage : Language("GitCodeowners") {
    companion object {
        val INSTANCE = GitCodeownersLanguage();
    }
}