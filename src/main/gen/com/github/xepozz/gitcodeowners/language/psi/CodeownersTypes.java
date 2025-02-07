// This is a generated file. Not intended for manual editing.
package com.github.xepozz.gitcodeowners.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.xepozz.gitcodeowners.language.psi.impl.*;

public interface CodeownersTypes {

  IElementType COMMENT = new CodeownersElementType("COMMENT");
  IElementType DEFINITION = new CodeownersElementType("DEFINITION");
  IElementType PATTERN = new CodeownersElementType("PATTERN");
  IElementType TEAM = new CodeownersElementType("TEAM");
  IElementType UNARY_DEFINITION = new CodeownersElementType("UNARY_DEFINITION");

  IElementType EOL = new CodeownersTokenType("EOL");
  IElementType SINGLE_COMMENT = new CodeownersTokenType("SINGLE_COMMENT");
  IElementType TEXT = new CodeownersTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMENT) {
        return new CodeownersCommentImpl(node);
      }
      else if (type == DEFINITION) {
        return new CodeownersDefinitionImpl(node);
      }
      else if (type == PATTERN) {
        return new CodeownersPatternImpl(node);
      }
      else if (type == TEAM) {
        return new CodeownersTeamImpl(node);
      }
      else if (type == UNARY_DEFINITION) {
        return new CodeownersUnaryDefinitionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
