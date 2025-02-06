// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class GitCodeownersVisitor extends PsiElementVisitor {

  public void visitComment(@NotNull GitCodeownersComment o) {
    visitPsiElement(o);
  }

  public void visitDefinition(@NotNull GitCodeownersDefinition o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull GitCodeownersParameter o) {
    visitPsiElement(o);
  }

  public void visitPattern(@NotNull GitCodeownersPattern o) {
    visitPsiElement(o);
  }

  public void visitUnaryDefinition(@NotNull GitCodeownersUnaryDefinition o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
