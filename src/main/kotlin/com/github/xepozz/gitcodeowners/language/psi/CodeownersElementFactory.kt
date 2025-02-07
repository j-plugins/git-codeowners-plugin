package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.CodeownersFile
import com.github.xepozz.gitcodeowners.language.CodeownersFileType
import com.intellij.openapi.project.Project

object CodeownersElementFactory {
//    fun createCodeownersTimeList(project: Project, values: List<String>): CodeownersTimeList {
//        val file = createFile(project, values.joinToString(","))
//
//        return PsiTreeUtil.findChildOfType(file, CodeownersTimeList::class.java) as CodeownersTimeList
//    }
//
//    fun createCodeownersTimeRange(project: Project, first: Int, last: Int): CodeownersTimeRange {
//        val file = createFile(project, "$first-$last")
//
//        return PsiTreeUtil.findChildOfType(file, CodeownersTimeRange::class.java) as CodeownersTimeRange
//    }
//    fun createCodeownersTimeExact(project: Project, value: Int): CodeownersTimeExact {
//        val file = createFile(project, "$value")
//
//        return PsiTreeUtil.findChildOfType(file, CodeownersTimeExact::class.java) as CodeownersTimeExact
//    }

    fun createFile(project: Project, text: String): CodeownersFile {
        val name = "dummy.gitcodeowners"
        return com.intellij.psi.PsiFileFactory.getInstance(project)
            .createFileFromText(name, CodeownersFileType.INSTANCE, text) as CodeownersFile
    }
}