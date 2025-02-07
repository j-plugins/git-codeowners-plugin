package com.github.xepozz.gitcodeowners.ide.reference

import com.github.xepozz.gitcodeowners.language.psi.CodeownersPattern
import com.intellij.openapi.paths.PathReference
import com.intellij.openapi.paths.PathReferenceManager
import com.intellij.openapi.paths.PathReferenceProvider
import com.intellij.openapi.roots.FileIndex
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileSystemItem
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceFactory
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.psi.PsiReferenceService
import com.intellij.psi.impl.file.PsiDirectoryImpl
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceUtil
import com.intellij.psi.impl.source.resolve.reference.impl.providers.PsiFileReferenceHelper
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.ProcessingContext
import com.intellij.util.indexing.FileBasedIndex
import com.intellij.webcore.resourceRoots.WebResourceFileReferenceHelper

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
                    val referenceManager = PathReferenceManager.getInstance()

//                    val ref = FileIndexPathReferenceProvider(GlobalSearchScope.projectScope(project))

                    return referenceManager.createReferences(
                        psiElement,
                        true,
                        false,
                        false,
//                        ref,
                    )

                    val referenceSet = object : FileReferenceSet(psiElement) {
                        override fun computeDefaultContexts(): Collection<PsiFileSystemItem?> {
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
//
//class FileIndexPathReferenceProvider(val scope: GlobalSearchScope) : PathReferenceProvider {
//    override fun createReferences(
//        psiElement: PsiElement,
//        references: List<PsiReference?>,
//        soft: Boolean
//    ): Boolean {
//        val references = references as? MutableList ?: return false
//        val project = scope.project ?: return false
//
//        var hasMatch = false
//        val pattern = psiElement.text
//
//        FilenameIndex.processAllFileNames({
//            if (it == pattern) {
//                hasMatch = true
//                false
//            } else {
//                true
//            }
//        }, scope, null)
//        if (hasMatch) {
//            FilenameIndex.getVirtualFilesByName(pattern, scope)
//                .forEach {
//                    println("vf: $it")
//                    val psiFile = PsiManager.getInstance(project).findFile(it) ?: return@forEach
//                    println("psi: $psiFile")
//
//                    val refs = VirtualFileReference(psiFile, it)
//                    println("reference: $refs")
//                    references.add(refs)
//                }
//        }
//
//        println("hasMatch $hasMatch $pattern, references: $references")
//        return hasMatch
//    }
//
//    override fun getPathReference(
//        path: String,
//        element: PsiElement
//    ): PathReference? {
//        println("getPathReference $path, $element")
//        return PathReference(path) { element.getIcon(0) }
//    }
//}
//
//class VirtualFileReference(element: PsiElement, private val file: VirtualFile) : PsiReferenceBase<PsiElement>(element) {
//    override fun resolve(): PsiElement? {
//        val psiFile = PsiManager.getInstance(element.project).findFile(file)
//        return psiFile // Return the resolved file or null if not found
//    }
//
//    override fun getVariants(): Array<Any> = emptyArray()
//}