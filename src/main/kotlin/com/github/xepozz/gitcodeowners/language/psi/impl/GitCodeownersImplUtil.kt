package com.github.xepozz.gitcodeowners.language.psi.impl

import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

//package com.github.xepozz.gitcodeowners.language.psi.impl
//
//import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTimeRange
//
//object GitCodeownersImplUtil {
//    @JvmStatic
//    fun getFirst(element: GitCodeownersTimeRange) = element.node.firstChildNode.text.toIntOrNull() ?: 0
//
//    @JvmStatic
//    fun getSecond(element: GitCodeownersTimeRange): Int = element.node.lastChildNode.text.toIntOrNull() ?: 0
//
//    @JvmStatic
//    fun getIntRange(element: GitCodeownersTimeRange): IntRange = IntRange(element.first, element.second)
//
//    fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)
//}
