package com.github.xepozz.gitcodeowners.ide

import com.intellij.ide.BrowserUtil
import com.intellij.psi.PsiElement

object CodeownersIdeUtils {
    fun openInBrowser(crontabSchedule: PsiElement) {
        val url = generateUrl(crontabSchedule)
        if (url != null) {
            println("url: $url")
            BrowserUtil.browse(url)
        }
    }

    fun generateUrl(element: PsiElement): String? {
        val text = element.text
        if (!text.startsWith("@")) return null

        val owners = text.substringAfter("@").split("/")

        if (owners.isEmpty()) return null

        val platformUrl = "https://github.com"

        if (owners.size == 1) {
            return "$platformUrl/${owners[0]}"
        }
        return "$platformUrl/orgs/${owners[0]}/teams/${owners[1]}"
    }
}