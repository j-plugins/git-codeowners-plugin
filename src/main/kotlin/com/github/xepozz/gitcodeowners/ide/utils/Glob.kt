//package com.github.xepozz.gitcodeowners.ide.utils
//
//import com.github.xepozz.gitcodeowners.ide.services.IgnoreMatcher
//import com.github.xepozz.gitcodeowners.ide.utils.Utils.getRelativePath
//import com.github.xepozz.gitcodeowners.ide.utils.Utils.isVcsDirectory
//import com.intellij.openapi.util.text.StringUtil
//import com.intellij.openapi.vfs.VfsUtil
//import com.intellij.openapi.vfs.VirtualFile
//import com.intellij.openapi.vfs.VirtualFileVisitor
//import com.intellij.psi.PsiElement
//import com.jetbrains.rd.util.concurrentMapOf
//import java.util.regex.Pattern
//import java.util.regex.PatternSyntaxException
//
///**
// * Glob util class that prepares glob statements or searches for content using glob rules.
// */
//object Glob {
//
//    /**
//     * Finds for [VirtualFile] list using glob rule in given root directory.
//     *
//     * @param root  root directory
//     * @param entry ignore entry
//     * @return search result
//     */
//    fun findOne(root: VirtualFile, entry: PsiElement, matcher: IgnoreMatcher) =
//        find(root, listOf(entry), matcher, false)[entry]?.firstOrNull()
//
//    /**
//     * Finds for [VirtualFile] list using glob rule in given root directory.
//     *
//     * @param root          root directory
//     * @param entries       ignore entries
//     * @param includeNested attach children to the search result
//     * @return search result
//     */
//    fun find(root: VirtualFile, entries: List<PsiElement>, matcher: IgnoreMatcher, includeNested: Boolean) =
//        concurrentMapOf<PsiElement, MutableList<VirtualFile>>().apply {
//            val map = concurrentMapOf<PsiElement, Pattern>()
//
//            entries.forEach {
//                this[it] = mutableListOf()
//                createPattern(it)?.let { pattern ->
//                    map[it] = pattern
//                }
//            }
//
//            val visitor = object : VirtualFileVisitor<Map<PsiElement, Pattern?>>(NO_FOLLOW_SYMLINKS) {
//                @Suppress("ReturnCount")
//                override fun visitFile(file: VirtualFile): Boolean {
//                    if (root == file) {
//                        return true
//                    }
//                    val current = mutableMapOf<PsiElement, Pattern?>()
//                    val path = getRelativePath(root, file)
//                    if (currentValue.isEmpty() || path == null || isVcsDirectory(file)) {
//                        return false
//                    }
//
//                    currentValue.forEach { (key, value) ->
//                        var matches = false
//                        if (value == null || matcher.match(value, path)) {
//                            matches = true
//                            get(key)?.add(file)
//                        }
//                        current[key] = value.takeIf { !includeNested || !matches }
//                    }
//
//                    setValueForChildren(current)
//                    return true
//                }
//            }
//
//            visitor.setValueForChildren(map)
//            VfsUtil.visitChildrenRecursively(root, visitor)
//        }
//
//    /**
//     * Finds for [VirtualFile] paths list using glob rule in given root directory.
//     *
//     * @param root          root directory
//     * @param entries       ignore entry
//     * @param includeNested attach children to the search result
//     * @return search result
//     */
//    fun findAsPaths(root: VirtualFile, entries: List<PsiElement>, matcher: IgnoreMatcher, includeNested: Boolean) =
//        find(root, entries, matcher, includeNested).mapValues { (_, value) ->
//            value
//                .asSequence()
//                .map { getRelativePath(root, it) }
//                .filterNotNull()
//                .toSet()
//        }
//
//    /**
//     * Creates regex [Pattern] using [PsiElement].
//     *
//     * @param entry          [PsiElement]
//     * @param acceptChildren Matches directory children
//     * @return regex [Pattern]
//     */
//    fun createPattern(entry: PsiElement, acceptChildren: Boolean = false) = createPattern(entry.value, entry.syntax, acceptChildren)
//
//    /**
//     * Creates regex [Pattern] using glob rule.
//     *
//     * @param rule   rule value
//     * @param syntax rule syntax
//     * @return regex [Pattern]
//     */
//    fun createPattern(rule: String, syntax: IgnoreBundle.Syntax, acceptChildren: Boolean = false) =
//        getPattern(getRegex(rule, syntax, acceptChildren))
//
//    /**
//     * Returns regex string basing on the rule and provided syntax.
//     *
//     * @param rule           rule value
//     * @param syntax         rule syntax
//     * @param acceptChildren Matches directory children
//     * @return regex string
//     */
//    fun getRegex(rule: String, syntax: IgnoreBundle.Syntax, acceptChildren: Boolean) = when (syntax) {
//        IgnoreBundle.Syntax.GLOB -> createRegex(rule, acceptChildren)
//        else -> rule
//    }
//
//    /**
//     * Converts regex string to [Pattern] with caching.
//     *
//     * @param regex regex to convert
//     * @return [Pattern] instance or null if invalid
//     */
//    fun getPattern(regex: String) = try {
//        Pattern.compile(regex)
//    } catch (e: PatternSyntaxException) {
//        null
//    }
//
//    /**
//     * Creates regex [String] using glob rule.
//     *
//     * @param glob           rule
//     * @param acceptChildren Matches directory children
//     * @return regex [String]
//     */
//    @Suppress("ComplexMethod", "LongMethod", "NestedBlockDepth")
//    fun createRegex(glob: String, acceptChildren: Boolean): String = glob.trim { it <= ' ' }.let {
//        val sb = StringBuilder("^")
//        var escape = false
//        var star = false
//        var doubleStar = false
//        var bracket = false
//        var beginIndex = 0
//
//        if (StringUtil.startsWith(it, "**")) {
//            sb.append("(?:[^/]*?/)*")
//            beginIndex = 2
//            doubleStar = true
//        } else if (StringUtil.startsWith(it, "*/")) {
//            sb.append("[^/]*")
//            beginIndex = 1
//            star = true
//        } else if (StringUtil.equals("*", it)) {
//            sb.append(".*")
//        } else if (StringUtil.startsWithChar(it, '*')) {
//            sb.append(".*?")
//        } else if (StringUtil.startsWithChar(it, '/')) {
//            beginIndex = 1
//        } else {
//            val slashes = StringUtil.countChars(it, '/')
//            if (slashes == 0 || slashes == 1 && StringUtil.endsWithChar(it, '/')) {
//                sb.append("(?:[^/]*?/)*")
//            }
//        }
//
//        val chars = it.substring(beginIndex).toCharArray()
//        chars.forEach { ch ->
//            if (bracket && ch != ']') {
//                sb.append(ch)
//                return@forEach
//            } else if (doubleStar) {
//                doubleStar = false
//                if (ch == '/') {
//                    sb.append("(?:[^/]*/)*?")
//                    return@forEach
//                } else {
//                    sb.append("[^/]*?")
//                }
//            }
//            if (ch == '*') {
//                when {
//                    escape -> {
//                        sb.append("\\*")
//                        star = false
//                        escape = star
//                    }
//                    star -> {
//                        val prev = if (sb.isNotEmpty()) sb[sb.length - 1] else '\u0000'
//                        if (prev == '\u0000' || prev == '^' || prev == '/') {
//                            doubleStar = true
//                        } else {
//                            sb.append("[^/]*?")
//                        }
//                        star = false
//                    }
//                    else -> {
//                        star = true
//                    }
//                }
//                return@forEach
//            } else if (star) {
//                sb.append("[^/]*?")
//                star = false
//            }
//            when (ch) {
//                '\\' -> {
//                    if (escape) {
//                        sb.append("\\\\")
//                    }
//                    escape = !escape
//                }
//                '?' ->
//                    if (escape) {
//                        sb.append("\\?")
//                        escape = false
//                    } else {
//                        sb.append('.')
//                    }
//                '[' -> {
//                    if (escape) {
//                        sb.append('\\')
//                        escape = false
//                    } else {
//                        bracket = true
//                    }
//                    sb.append(ch)
//                }
//                ']' -> {
//                    if (!bracket) {
//                        sb.append('\\')
//                    }
//                    sb.append(ch)
//                    bracket = false
//                    escape = false
//                }
//                '.', '(', ')', '{', '}', '+', '|', '^', '$', '@', '%' -> {
//                    sb.append('\\')
//                    sb.append(ch)
//                    escape = false
//                }
//                else -> {
//                    escape = false
//                    sb.append(ch)
//                }
//            }
//        }
//        if (star || doubleStar) {
//            if (StringUtil.endsWithChar(sb, '/')) {
//                sb.append(".+")
//            } else {
//                sb.append("[^/]*/?")
//            }
//        } else {
//            if (StringUtil.endsWithChar(sb, '/')) {
//                if (acceptChildren) {
//                    sb.append("[^/]*")
//                }
//            } else {
//                sb.append(if (acceptChildren) "(?:/.*)?" else "/?")
//            }
//        }
//        sb.append('$')
//        return sb.toString()
//    }
//}