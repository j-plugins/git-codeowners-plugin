package com.github.xepozz.gitcodeowners.language

//import com.github.xepozz.gitcodeowners.language.psi.CodeownersTimePointer
//import com.github.xepozz.gitcodeowners.language.psi.CodeownersVariableName
//import com.github.xepozz.gitcodeowners.language.psi.CodeownersVariableValue
import com.github.xepozz.gitcodeowners.language.psi.CodeownersPattern
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTeam
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class CodeownersAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is CodeownersPattern -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(PATTERN_HIGHLIGHT)
                    .create()
            }

            is CodeownersTeam -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(PARAMETERS_HIGHLIGHT)
                    .create()
            }
//
//            is CodeownersVariableValue -> {
//                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//                    .range(element.textRange)
//                    .textAttributes(STRING_HIGHLIGHT)
//                    .create()
//            }

//            is CodeownersCommand -> {
//                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//                    .range(element.textRange)
//                    .textAttributes(COMMAND_HIGHLIGHT)
//                    .create()
//            }
        }
    }

    companion object {
        val PATTERN_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "GITCODEOWNERS_PATTERN",
            DefaultLanguageHighlighterColors.STRING,
        )
        private val PARAMETERS_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "GITCODEOWNERS_IDENTIFIER",
            DefaultLanguageHighlighterColors.KEYWORD,
        )
//        private val STRING_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
//            "GITCODEOWNERS_STRING",
//            DefaultLanguageHighlighterColors.STRING,
//        )
//        private val OPERATION_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
//            "GITCODEOWNERS_OPERATION",
//            DefaultLanguageHighlighterColors.OPERATION_SIGN,
//        )
//        private val COMMAND_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
//            "GITCODEOWNERS_COMMAND",
//            DefaultLanguageHighlighterColors.STRING,
//        )
    }
}