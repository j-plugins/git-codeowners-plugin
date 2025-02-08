package com.github.xepozz.gitcodeowners.ide

import com.github.xepozz.gitcodeowners.CodeownersIcons
import com.github.xepozz.gitcodeowners.language.psi.CodeownersPattern
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class CodeownersLineMarkerProvider : RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
        when (element) {
            is CodeownersPattern -> {
                val target = element.references.firstNotNullOfOrNull { it.resolve() }
//                println("target: $target")
                if (target !is PsiFile) {
                    return
                }

                val builder = NavigationGutterIconBuilder.create(target.getIcon(0) ?: AllIcons.Nodes.Target)
                    .setTargets(target)
                    .setTooltipText("Navigate to ${target.text}")

                result.add(builder.createLineMarkerInfo(element))
            }
        }
    }
}