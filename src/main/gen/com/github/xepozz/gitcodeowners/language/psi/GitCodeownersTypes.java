// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.xepozz.gitcodeowners.language.psi.impl.*;

public interface GitCodeownersTypes {

  IElementType COMMENT = new GitCodeownersElementType("COMMENT");
  IElementType DEFINITION = new GitCodeownersElementType("DEFINITION");
  IElementType PARAMETER = new GitCodeownersElementType("PARAMETER");
  IElementType PATTERN = new GitCodeownersElementType("PATTERN");
  IElementType UNARY_DEFINITION = new GitCodeownersElementType("UNARY_DEFINITION");

  IElementType EOL = new GitCodeownersTokenType("EOL");
  IElementType SINGLE_COMMENT = new GitCodeownersTokenType("SINGLE_COMMENT");
  IElementType TEXT = new GitCodeownersTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMENT) {
        return new GitCodeownersCommentImpl(node);
      }
      else if (type == DEFINITION) {
        return new GitCodeownersDefinitionImpl(node);
      }
      else if (type == PARAMETER) {
        return new GitCodeownersParameterImpl(node);
      }
      else if (type == PATTERN) {
        return new GitCodeownersPatternImpl(node);
      }
      else if (type == UNARY_DEFINITION) {
        return new GitCodeownersUnaryDefinitionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
