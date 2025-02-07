package com.github.xepozz.gitcodeowners.language.psi.impl//package com.github.xepozz.gitcodeowners.language.psi.impl

import com.github.xepozz.gitcodeowners.language.psi.CodeownersPattern
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTypes
import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode

abstract class CodeownersPatternBaseImpl : CodeownersElementImpl, CodeownersPattern {
    constructor(node: ASTNode) : super(node)

    override fun getText(): String {
        val keyNode = this.node.findChildByType(CodeownersTypes.TEXT)

        return keyNode?.text ?: ""
    }

    override fun getPresentation() = PresentationData(text, null, getIcon(0), null)

    override fun getIcon(flags: Int) = AllIcons.Nodes.Console
//    override fun getReference(): PsiReference? {
//        println("getReference $text")
//        return super.getReference()
//    }
//    override fun canNavigate() = true
//    override fun getNavigationElement(): PsiElement {
//        println("getNavigationElement $text")
//
//        return super.getNavigationElement()
//    }
//    override fun navigationRequest(): NavigationRequest? {
//        println("navigationRequest $text")
//        val index = FilenameIndex.getVirtualFilesByName(text, GlobalSearchScope.projectScope(project))
//        val file = index.toList().firstOrNull() ?: return null
//        return NavigationRequest.sourceNavigationRequest(project, file, 0)
//    }

//    override fun getNavigationElement(): PsiElement {
//        val index = FilenameIndex.getVirtualFilesByName(text, GlobalSearchScope.projectScope(project))
//        return index.toList().firstOrNull()
//    }
}