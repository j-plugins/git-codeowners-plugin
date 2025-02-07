package com.github.xepozz.gitcodeowners.language.psi.impl//package com.github.xepozz.gitcodeowners.language.psi.impl
//
//import com.github.xepozz.gitcodeowners.language.psi.CodeownersVariableDefinition
//import com.intellij.extapi.psi.ASTWrapperPsiElement
//import com.intellij.icons.AllIcons
//import com.intellij.lang.ASTNode
//
//abstract class CodeownersVariableBaseImpl :
//    ASTWrapperPsiElement,
//    CodeownersVariableDefinition {
//    constructor(node: ASTNode) : super(node)
//
//    override fun getText() = this.node.text
//
//    override fun getIcon(flags: Int) = AllIcons.Nodes.Variable
//}