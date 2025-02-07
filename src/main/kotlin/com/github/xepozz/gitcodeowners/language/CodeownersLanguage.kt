package com.github.xepozz.gitcodeowners.language

import com.intellij.lang.Language

class CodeownersLanguage : Language("Codeowners") {
    companion object {
        val INSTANCE = CodeownersLanguage();
    }
}