package com.github.xepozz.gitcodeowners.ide.completion

import com.github.xepozz.gitcodeowners.CodeownersIcons
import com.github.xepozz.gitcodeowners.language.CodeownersFile
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTeam
import com.github.xepozz.gitcodeowners.language.psi.CodeownersTypes
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import com.intellij.vcs.log.VcsUser
import com.intellij.vcs.log.VcsUserEditor
import com.intellij.vcs.log.VcsUserRegistry
import com.intellij.vcsUtil.VcsUtil

class CodeownersCompletionContributor : CompletionContributor(), DumbAware {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement()
                .withParent(PlatformPatterns.psiElement(CodeownersTypes.TEAM)),
            object : CompletionProvider<CompletionParameters>(), DumbAware {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val team = parameters.position.parent as? CodeownersTeam ?: return
                    val file = team.containingFile as? CodeownersFile ?: return

                    var result = result
                    val hasDogPrefix = team.text.startsWith("@")

                    PsiTreeUtil.findChildrenOfType(file, CodeownersTeam::class.java)
                        .filter { it !== team }
                        .forEach {
                            result.addElement(
                                LookupElementBuilder.create(if (hasDogPrefix) it.text.substring(1) else it.text)
                                    .withIcon(CodeownersIcons.FILE)
                            )
                        }
                }
            }
        )
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement()
                .withParent(PlatformPatterns.psiElement(CodeownersTypes.TEAM)),
            object : CompletionProvider<CompletionParameters>(), DumbAware {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val project = parameters.position.project

                    val service = project.getServiceIfCreated(VcsUserRegistry::class.java) ?: return

                    service.users
                        .map {
                            LookupElementBuilder.create(it.email)
                                .withTypeText(it.name, AllIcons.General.User, true)
                                .withTailText(" [${it.email}]")
                                .withIcon(CodeownersIcons.FILE)
                        }
                        .apply { result.addAllElements(this) }
                }
            }
        )
    }
}