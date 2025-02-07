// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.xepozz.gitcodeowners.language.psi.*;

public class GitCodeownersParameterImpl extends ASTWrapperPsiElement implements GitCodeownersParameter {

  public GitCodeownersParameterImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GitCodeownersVisitor visitor) {
    visitor.visitParameter(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GitCodeownersVisitor) accept((GitCodeownersVisitor)visitor);
    else super.accept(visitor);
  }

}
