package com.github.xepozz.gitcodeowners.language.psi.impl//package com.github.xepozz.gitcodeowners.language.psi.impl
//
//import com.github.xepozz.gitcodeowners.language.psi.CodeownersTimeRange
//import com.intellij.extapi.psi.ASTWrapperPsiElement
//import com.intellij.lang.ASTNode
//
//abstract class CodeownersTimeRangeBaseImpl : ASTWrapperPsiElement, CodeownersTimeRange {
//    constructor(node: ASTNode) : super(node)
//
//    override fun getText(): String {
//        return this.node.text
//    }
//}