// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GitCodeownersUnaryDefinition extends PsiElement {

  @NotNull
  List<GitCodeownersParameter> getParameterList();

  @NotNull
  GitCodeownersPattern getPattern();

}
