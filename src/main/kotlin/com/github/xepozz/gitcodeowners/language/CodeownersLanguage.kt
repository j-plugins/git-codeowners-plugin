package com.github.xepozz.gitcodeowners.language

import com.intellij.lang.Language

class CodeownersLanguage : Language("Codeowners") {
    companion object {
        @JvmStatic
        val INSTANCE = CodeownersLanguage();
    }
}