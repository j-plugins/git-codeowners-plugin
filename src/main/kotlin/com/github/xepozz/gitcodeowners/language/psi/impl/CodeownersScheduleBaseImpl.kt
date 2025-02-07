package com.github.xepozz.gitcodeowners.language.psi.impl//package com.github.xepozz.gitcodeowners.language.psi.impl
//
//import com.github.xepozz.gitcodeowners.language.CodeownersLineMarkerProvider
//import com.github.xepozz.gitcodeowners.language.psi.CodeownersSchedule
//import com.intellij.extapi.psi.ASTWrapperPsiElement
//import com.intellij.icons.AllIcons
//import com.intellij.ide.projectView.PresentationData
//import com.intellij.lang.ASTNode
//
//abstract class CodeownersScheduleBaseImpl : ASTWrapperPsiElement, CodeownersSchedule {
//    constructor(node: ASTNode) : super(node)
//
//    override fun getText() = this.node.text
//
//    override fun getPresentation() = PresentationData(text, null, getIcon(0), CodeownersLineMarkerProvider.SCHEDULE_HIGHLIGHT)
//
//    override fun getIcon(flags: Int) = AllIcons.Nodes.DataTables
//}