// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.xepozz.gitcodeowners.language.psi.CodeownersTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.xepozz.gitcodeowners.language.psi.*;

public class CodeownersUnaryDefinitionImpl extends ASTWrapperPsiElement implements CodeownersUnaryDefinition {

  public CodeownersUnaryDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CodeownersVisitor visitor) {
    visitor.visitUnaryDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CodeownersVisitor) accept((CodeownersVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CodeownersPattern getPattern() {
    return findNotNullChildByClass(CodeownersPattern.class);
  }

  @Override
  @NotNull
  public List<CodeownersTeam> getTeamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CodeownersTeam.class);
  }

}
