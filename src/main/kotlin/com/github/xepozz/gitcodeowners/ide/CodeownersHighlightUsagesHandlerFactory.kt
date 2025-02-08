package com.github.xepozz.gitcodeowners.ide

import com.github.xepozz.gitcodeowners.language.psi.CodeownersTeam
import com.github.xepozz.gitcodeowners.language.psi.impl.CodeownersElementImpl
import com.intellij.codeInsight.highlighting.HighlightUsagesHandler
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactory
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.Consumer
import org.eclipse.lsp4j.DocumentHighlightKind

class CodeownersHighlightUsagesHandlerFactory : HighlightUsagesHandlerFactoryBase() {
    override fun createHighlightUsagesHandler(
        editor: Editor,
        file: PsiFile,
        target: PsiElement
    ) = object : HighlightUsagesHandlerBase<CodeownersElementImpl>(editor, file) {
        override fun getTargets() =
            PsiTreeUtil.findChildrenOfType(file, CodeownersElementImpl::class.java)
                .filterNotNull()
                .toList()

        override fun selectTargets(
            targets: List<CodeownersElementImpl?>,
            selectionConsumer: Consumer<in MutableList<out CodeownersElementImpl>>
        ) {
        }

        override fun computeUsages(targets: List<CodeownersElementImpl>) =
            targets
                .filter { it::class == target.parent::class && it.text == target.text }
                .forEach(::addOccurrence)
    }
}
