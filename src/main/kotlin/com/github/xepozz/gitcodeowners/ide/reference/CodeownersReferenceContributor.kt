package com.github.xepozz.gitcodeowners.ide.reference

import com.github.xepozz.gitcodeowners.language.GitCodeownersFile
import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersPattern
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet
import com.intellij.util.ProcessingContext

class CodeownersReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(psiReferenceRegistrar: PsiReferenceRegistrar) {
        psiReferenceRegistrar.registerReferenceProvider(
            PlatformPatterns.psiElement()
//            PlatformPatterns.psiElement(GitCodeownersPattern::class.java)
//                .inFile(PlatformPatterns.psiFile(GitCodeownersFile::class.java))
            ,
            IgnoreReferenceProvider()
        )
    }

    private class IgnoreReferenceProvider : PsiReferenceProvider() {
        override fun getReferencesByElement(
            psiElement: PsiElement,
            processingContext: ProcessingContext
        ): Array<out PsiReference> {
            println("psi element:: $psiElement")
            return when (psiElement) {
                is GitCodeownersPattern -> FileReferenceSet(psiElement).allReferences
                else -> PsiReference.EMPTY_ARRAY
            }
        }
    }
}