package com.github.xepozz.gitcodeowners.ide

import com.intellij.lang.Commenter

class GitCodeownersCommenter : Commenter {
    override fun getLineCommentPrefix() = "#"

    override fun getBlockCommentPrefix() = null

    override fun getBlockCommentSuffix() = null

    override fun getCommentedBlockCommentPrefix() = null

    override fun getCommentedBlockCommentSuffix() = null
}