package com.github.xepozz.gitcodeowners.language.psi.impl//package com.github.xepozz.gitcodeowners.language.psi.impl
//
//import com.github.xepozz.gitcodeowners.language.GitCodeownersAnnotator
//import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersSchedule
//import com.intellij.extapi.psi.ASTWrapperPsiElement
//import com.intellij.icons.AllIcons
//import com.intellij.ide.projectView.PresentationData
//import com.intellij.lang.ASTNode
//
//abstract class GitCodeownersScheduleBaseImpl : ASTWrapperPsiElement, GitCodeownersSchedule {
//    constructor(node: ASTNode) : super(node)
//
//    override fun getText() = this.node.text
//
//    override fun getPresentation() = PresentationData(text, null, getIcon(0), GitCodeownersAnnotator.SCHEDULE_HIGHLIGHT)
//
//    override fun getIcon(flags: Int) = AllIcons.Nodes.DataTables
//}