package com.github.xepozz.gitcodeowners.language.psi.impl//package com.github.xepozz.gitcodeowners.language.psi.impl
//
//import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTimeRange
//import com.intellij.extapi.psi.ASTWrapperPsiElement
//import com.intellij.lang.ASTNode
//
//abstract class GitCodeownersTimeRangeBaseImpl : ASTWrapperPsiElement, GitCodeownersTimeRange {
//    constructor(node: ASTNode) : super(node)
//
//    override fun getText(): String {
//        return this.node.text
//    }
//}