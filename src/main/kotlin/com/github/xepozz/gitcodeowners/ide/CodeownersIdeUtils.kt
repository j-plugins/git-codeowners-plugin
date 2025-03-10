package com.github.xepozz.gitcodeowners.ide

import com.intellij.ide.BrowserUtil
import com.intellij.psi.PsiElement

object CodeownersIdeUtils {
    fun openInBrowser(crontabSchedule: PsiElement) {
        BrowserUtil.browse(
            generateCrontabGuruUrl(crontabSchedule)
        )
    }

    fun generateCrontabGuruUrl(element: PsiElement): String {
        val owners = element.text.substringAfter("@").split("/")

        if (owners.isEmpty()) {
            return "https://github.com/xepozz"
        }
        if (owners.size == 1) {
            return "https://github.com/${owners[0]}"
        }
        return "https://github.com/orgs/${owners[0]}/teams/${owners[1]}"
    }
}