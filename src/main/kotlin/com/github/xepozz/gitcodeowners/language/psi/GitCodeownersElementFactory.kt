package com.github.xepozz.gitcodeowners.language.psi

import com.github.xepozz.gitcodeowners.language.GitCodeownersFile
import com.github.xepozz.gitcodeowners.language.GitCodeownersFileType
import com.intellij.openapi.project.Project

object GitCodeownersElementFactory {
//    fun createGitCodeownersTimeList(project: Project, values: List<String>): GitCodeownersTimeList {
//        val file = createFile(project, values.joinToString(","))
//
//        return PsiTreeUtil.findChildOfType(file, GitCodeownersTimeList::class.java) as GitCodeownersTimeList
//    }
//
//    fun createGitCodeownersTimeRange(project: Project, first: Int, last: Int): GitCodeownersTimeRange {
//        val file = createFile(project, "$first-$last")
//
//        return PsiTreeUtil.findChildOfType(file, GitCodeownersTimeRange::class.java) as GitCodeownersTimeRange
//    }
//    fun createGitCodeownersTimeExact(project: Project, value: Int): GitCodeownersTimeExact {
//        val file = createFile(project, "$value")
//
//        return PsiTreeUtil.findChildOfType(file, GitCodeownersTimeExact::class.java) as GitCodeownersTimeExact
//    }

    fun createFile(project: Project, text: String): GitCodeownersFile {
        val name = "dummy.gitcodeowners"
        return com.intellij.psi.PsiFileFactory.getInstance(project)
            .createFileFromText(name, GitCodeownersFileType.INSTANCE, text) as GitCodeownersFile
    }
}