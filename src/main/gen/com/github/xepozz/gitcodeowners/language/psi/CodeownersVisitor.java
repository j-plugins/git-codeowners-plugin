// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.NavigatablePsiElement;

public class CodeownersVisitor extends PsiElementVisitor {

  public void visitComment(@NotNull CodeownersComment o) {
    visitPsiElement(o);
  }

  public void visitDefinition(@NotNull CodeownersDefinition o) {
    visitPsiElement(o);
  }

  public void visitPattern(@NotNull CodeownersPattern o) {
    visitNavigatablePsiElement(o);
  }

  public void visitTeam(@NotNull CodeownersTeam o) {
    visitPsiElement(o);
  }

  public void visitNavigatablePsiElement(@NotNull NavigatablePsiElement o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
