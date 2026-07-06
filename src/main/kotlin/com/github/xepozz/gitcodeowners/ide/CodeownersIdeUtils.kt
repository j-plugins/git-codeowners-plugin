package com.github.xepozz.gitcodeowners.ide

import com.intellij.ide.BrowserUtil
import com.intellij.psi.PsiElement

object CodeownersIdeUtils {
    fun openInBrowser(crontabSchedule: PsiElement) {
        val url = generateUrl(crontabSchedule)
        if (url != null) {
            BrowserUtil.browse(url)
        }
    }

    fun generateUrl(element: PsiElement): String? {
        val text = element.text
        if (!text.startsWith("@")) return null

        val ownerText = text.removePrefix("@")
        if (ownerText.isBlank()) return null

        val owners = ownerText.split("/")
        if (owners.any { it.isBlank() } || owners.size > 2) return null

        val platformUrl = "https://github.com"

        if (owners.size == 1) {
            return "$platformUrl/${owners[0]}"
        }
        return "$platformUrl/orgs/${owners[0]}/teams/${owners[1]}"
    }
}
