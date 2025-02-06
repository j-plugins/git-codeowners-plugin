package com.github.xepozz.gitcodeowners.language.parser;

import com.github.xepozz.gitcodeowners.language.psi.GitCodeownersTypes;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%
%class GitCodeownersLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

SINGLE_COMMENT=#[^\n]*

NUMBER=[0-9]+
WHITESPACE=[ \t]+
NEWLINE=\r|\n|\r\n

TEXT=(\\\s|[^\s])+

%state EXPRESSION, SCHEDULE, VARIABLE
%%
<YYINITIAL> {
    {SINGLE_COMMENT}({NEWLINE}{SINGLE_COMMENT})* { return GitCodeownersTypes.COMMENT; }
    {TEXT}                                       { return GitCodeownersTypes.TEXT; }
    {WHITESPACE}                                 { return TokenType.WHITE_SPACE; }
}


{WHITESPACE}                                     { return TokenType.WHITE_SPACE; }
{NEWLINE}+                                       { yybegin(YYINITIAL); return GitCodeownersTypes.EOL; }

[^]                                              { return TokenType.BAD_CHARACTER; }
//[^]                                              { throw new Error("Illegal character <"+yytext()+">"); }