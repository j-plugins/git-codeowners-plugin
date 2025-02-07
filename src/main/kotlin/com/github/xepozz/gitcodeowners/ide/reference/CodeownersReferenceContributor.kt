package com.github.xepozz.gitcodeowners.ide.reference

import com.github.xepozz.gitcodeowners.language.psi.CodeownersPattern
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileSystemItem
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.psi.impl.file.PsiDirectoryImpl
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet
import com.intellij.util.ProcessingContext

class CodeownersReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(psiReferenceRegistrar: PsiReferenceRegistrar) {
        psiReferenceRegistrar.registerReferenceProvider(
            PlatformPatterns.psiElement()
//            PlatformPatterns.psiElement(CodeownersPattern::class.java)
//                .inFile(PlatformPatterns.psiFile(CodeownersFile::class.java))
            ,
            IgnoreReferenceProvider()
        )
    }

    private class IgnoreReferenceProvider : PsiReferenceProvider() {
        override fun getReferencesByElement(
            psiElement: PsiElement,
            processingContext: ProcessingContext
        ): Array<out PsiReference> {
            val project = psiElement.project
            val projectPsi = PsiManager.getInstance(project).findDirectory(project.baseDir)
            return when (psiElement) {
                is CodeownersPattern -> {
                    val referenceSet = object : FileReferenceSet(psiElement) {
                        override fun computeDefaultContexts(): Collection<PsiFileSystemItem?> {
                            return listOf(projectPsi)
                            if (element.text.startsWith("/")) {
                                return listOf(projectPsi)
                            }
                            return super.computeDefaultContexts()
                        }
                    }
                    val result = referenceSet.allReferences
//                    println("result: ${result.toList().map { println("it: $it") }}")

                    result
                }
                else -> PsiReference.EMPTY_ARRAY
            }
        }
    }
}