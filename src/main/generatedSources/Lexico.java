/* The following code was generated by JFlex 1.6.1 */

package generatedSources;
import java_cup.runtime.*;
import utils.RascalSymbol;
import generatedSources.sym;
import utils.*;
import tipos.*;
import core.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>/home/mtuser/Downloads/compiladorJava/compilador/src/main/resources/jflex/lexico.flex</tt>
 */
public class Lexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\12\1\4\1\0\1\12\1\11\22\0\1\12\7\0\1\37"+
    "\1\40\1\2\1\44\1\42\1\45\1\41\1\1\1\5\11\6\1\51"+
    "\1\43\1\47\1\46\1\50\2\0\32\7\4\0\1\7\1\0\1\17"+
    "\1\32\1\22\1\24\1\23\1\26\1\16\1\35\1\31\2\7\1\33"+
    "\1\20\1\27\1\15\1\13\1\7\1\14\1\34\1\30\1\25\1\21"+
    "\1\36\3\7\2\0\1\3\54\0\1\7\12\0\1\7\4\0\1\7"+
    "\5\0\27\7\1\0\37\7\1\0\u01ca\7\4\0\14\7\16\0\5\7"+
    "\7\0\1\7\1\0\1\7\201\0\5\7\1\0\2\7\2\0\4\7"+
    "\1\0\1\7\6\0\1\7\1\0\3\7\1\0\1\7\1\0\24\7"+
    "\1\0\123\7\1\0\213\7\10\0\246\7\1\0\46\7\2\0\1\7"+
    "\7\0\47\7\110\0\33\7\5\0\3\7\55\0\53\7\25\0\12\10"+
    "\4\0\2\7\1\0\143\7\1\0\1\7\17\0\2\7\7\0\2\7"+
    "\12\10\3\7\2\0\1\7\20\0\1\7\1\0\36\7\35\0\131\7"+
    "\13\0\1\7\16\0\12\10\41\7\11\0\2\7\4\0\1\7\5\0"+
    "\26\7\4\0\1\7\11\0\1\7\3\0\1\7\27\0\31\7\107\0"+
    "\23\7\121\0\66\7\3\0\1\7\22\0\1\7\7\0\12\7\4\0"+
    "\12\10\1\0\20\7\4\0\10\7\2\0\2\7\2\0\26\7\1\0"+
    "\7\7\1\0\1\7\3\0\4\7\3\0\1\7\20\0\1\7\15\0"+
    "\2\7\1\0\3\7\4\0\12\10\2\7\23\0\6\7\4\0\2\7"+
    "\2\0\26\7\1\0\7\7\1\0\2\7\1\0\2\7\1\0\2\7"+
    "\37\0\4\7\1\0\1\7\7\0\12\10\2\0\3\7\20\0\11\7"+
    "\1\0\3\7\1\0\26\7\1\0\7\7\1\0\2\7\1\0\5\7"+
    "\3\0\1\7\22\0\1\7\17\0\2\7\4\0\12\10\25\0\10\7"+
    "\2\0\2\7\2\0\26\7\1\0\7\7\1\0\2\7\1\0\5\7"+
    "\3\0\1\7\36\0\2\7\1\0\3\7\4\0\12\10\1\0\1\7"+
    "\21\0\1\7\1\0\6\7\3\0\3\7\1\0\4\7\3\0\2\7"+
    "\1\0\1\7\1\0\2\7\3\0\2\7\3\0\3\7\3\0\14\7"+
    "\26\0\1\7\25\0\12\10\25\0\10\7\1\0\3\7\1\0\27\7"+
    "\1\0\20\7\3\0\1\7\32\0\2\7\6\0\2\7\4\0\12\10"+
    "\25\0\10\7\1\0\3\7\1\0\27\7\1\0\12\7\1\0\5\7"+
    "\3\0\1\7\40\0\1\7\1\0\2\7\4\0\12\10\1\0\2\7"+
    "\22\0\10\7\1\0\3\7\1\0\51\7\2\0\1\7\20\0\1\7"+
    "\21\0\2\7\4\0\12\10\12\0\6\7\5\0\22\7\3\0\30\7"+
    "\1\0\11\7\1\0\1\7\2\0\7\7\37\0\12\10\21\0\60\7"+
    "\1\0\2\7\14\0\7\7\11\0\12\10\47\0\2\7\1\0\1\7"+
    "\2\0\2\7\1\0\1\7\2\0\1\7\6\0\4\7\1\0\7\7"+
    "\1\0\3\7\1\0\1\7\1\0\1\7\2\0\2\7\1\0\4\7"+
    "\1\0\2\7\11\0\1\7\2\0\5\7\1\0\1\7\11\0\12\10"+
    "\2\0\4\7\40\0\1\7\37\0\12\10\26\0\10\7\1\0\44\7"+
    "\33\0\5\7\163\0\53\7\24\0\1\7\12\10\6\0\6\7\4\0"+
    "\4\7\3\0\1\7\3\0\2\7\7\0\3\7\4\0\15\7\14\0"+
    "\1\7\1\0\12\10\6\0\46\7\1\0\1\7\5\0\1\7\2\0"+
    "\53\7\1\0\u014d\7\1\0\4\7\2\0\7\7\1\0\1\7\1\0"+
    "\4\7\2\0\51\7\1\0\4\7\2\0\41\7\1\0\4\7\2\0"+
    "\7\7\1\0\1\7\1\0\4\7\2\0\17\7\1\0\71\7\1\0"+
    "\4\7\2\0\103\7\45\0\20\7\20\0\125\7\14\0\u026c\7\2\0"+
    "\21\7\1\0\32\7\5\0\113\7\6\0\10\7\7\0\15\7\1\0"+
    "\4\7\16\0\22\7\16\0\22\7\16\0\15\7\1\0\3\7\17\0"+
    "\64\7\43\0\1\7\4\0\1\7\3\0\12\10\46\0\12\10\6\0"+
    "\130\7\10\0\51\7\1\0\1\7\5\0\106\7\12\0\37\7\47\0"+
    "\12\10\36\7\2\0\5\7\13\0\54\7\25\0\7\7\10\0\12\10"+
    "\46\0\27\7\11\0\65\7\53\0\12\10\6\0\12\10\15\0\1\7"+
    "\135\0\57\7\21\0\7\7\4\0\12\10\51\0\36\7\15\0\2\7"+
    "\12\10\54\7\32\0\44\7\34\0\12\10\3\0\3\7\12\10\44\7"+
    "\153\0\4\7\1\0\4\7\3\0\2\7\11\0\300\7\100\0\u0116\7"+
    "\2\0\6\7\2\0\46\7\2\0\6\7\2\0\10\7\1\0\1\7"+
    "\1\0\1\7\1\0\1\7\1\0\37\7\2\0\65\7\1\0\7\7"+
    "\1\0\1\7\3\0\3\7\1\0\7\7\3\0\4\7\2\0\6\7"+
    "\4\0\15\7\5\0\3\7\1\0\7\7\164\0\1\7\15\0\1\7"+
    "\20\0\15\7\145\0\1\7\4\0\1\7\2\0\12\7\1\0\1\7"+
    "\3\0\5\7\6\0\1\7\1\0\1\7\1\0\1\7\1\0\4\7"+
    "\1\0\13\7\2\0\4\7\5\0\5\7\4\0\1\7\64\0\2\7"+
    "\u0a7b\0\57\7\1\0\57\7\1\0\205\7\6\0\4\7\3\0\2\7"+
    "\14\0\46\7\1\0\1\7\5\0\1\7\2\0\70\7\7\0\1\7"+
    "\20\0\27\7\11\0\7\7\1\0\7\7\1\0\7\7\1\0\7\7"+
    "\1\0\7\7\1\0\7\7\1\0\7\7\1\0\7\7\120\0\1\7"+
    "\u01d5\0\2\7\52\0\5\7\5\0\2\7\4\0\126\7\6\0\3\7"+
    "\1\0\132\7\1\0\4\7\5\0\51\7\3\0\136\7\21\0\33\7"+
    "\65\0\20\7\u0200\0\u19b6\7\112\0\u51cd\7\63\0\u048d\7\103\0\56\7"+
    "\2\0\u010d\7\3\0\20\7\12\10\2\7\24\0\57\7\20\0\37\7"+
    "\2\0\106\7\61\0\11\7\2\0\147\7\2\0\4\7\1\0\36\7"+
    "\2\0\2\7\105\0\13\7\1\0\3\7\1\0\4\7\1\0\27\7"+
    "\35\0\64\7\16\0\62\7\34\0\12\10\30\0\6\7\3\0\1\7"+
    "\4\0\12\10\34\7\12\0\27\7\31\0\35\7\7\0\57\7\34\0"+
    "\1\7\12\10\6\0\5\7\1\0\12\7\12\10\5\7\1\0\51\7"+
    "\27\0\3\7\1\0\10\7\4\0\12\10\6\0\27\7\3\0\1\7"+
    "\3\0\62\7\1\0\1\7\3\0\2\7\2\0\5\7\2\0\1\7"+
    "\1\0\1\7\30\0\3\7\2\0\13\7\7\0\3\7\14\0\6\7"+
    "\2\0\6\7\2\0\6\7\11\0\7\7\1\0\7\7\1\0\53\7"+
    "\1\0\4\7\4\0\2\7\132\0\43\7\15\0\12\10\6\0\u2ba4\7"+
    "\14\0\27\7\4\0\61\7\u2104\0\u016e\7\2\0\152\7\46\0\7\7"+
    "\14\0\5\7\5\0\1\7\1\0\12\7\1\0\15\7\1\0\5\7"+
    "\1\0\1\7\1\0\2\7\1\0\2\7\1\0\154\7\41\0\u016b\7"+
    "\22\0\100\7\2\0\66\7\50\0\14\7\164\0\5\7\1\0\207\7"+
    "\23\0\12\10\7\0\32\7\6\0\32\7\13\0\131\7\3\0\6\7"+
    "\2\0\6\7\2\0\6\7\2\0\3\7\43\0\14\7\1\0\32\7"+
    "\1\0\23\7\1\0\2\7\1\0\17\7\2\0\16\7\42\0\173\7"+
    "\u0185\0\35\7\3\0\61\7\57\0\40\7\20\0\21\7\1\0\10\7"+
    "\6\0\46\7\12\0\36\7\2\0\44\7\4\0\10\7\60\0\236\7"+
    "\2\0\12\10\126\0\50\7\10\0\64\7\234\0\u0137\7\11\0\26\7"+
    "\12\0\10\7\230\0\6\7\2\0\1\7\1\0\54\7\1\0\2\7"+
    "\3\0\1\7\2\0\27\7\12\0\27\7\11\0\37\7\141\0\26\7"+
    "\12\0\32\7\106\0\70\7\6\0\2\7\100\0\1\7\17\0\4\7"+
    "\1\0\3\7\1\0\33\7\54\0\35\7\3\0\35\7\43\0\10\7"+
    "\1\0\34\7\33\0\66\7\12\0\26\7\12\0\23\7\15\0\22\7"+
    "\156\0\111\7\u03ba\0\65\7\56\0\12\10\23\0\55\7\40\0\31\7"+
    "\7\0\12\10\11\0\44\7\17\0\12\10\20\0\43\7\3\0\1\7"+
    "\14\0\60\7\16\0\4\7\13\0\12\10\1\7\45\0\22\7\1\0"+
    "\31\7\204\0\57\7\21\0\12\10\13\0\10\7\2\0\2\7\2\0"+
    "\26\7\1\0\7\7\1\0\2\7\1\0\5\7\3\0\1\7\37\0"+
    "\5\7\u011e\0\60\7\24\0\2\7\1\0\1\7\10\0\12\10\246\0"+
    "\57\7\121\0\60\7\24\0\1\7\13\0\12\10\46\0\53\7\25\0"+
    "\12\10\u01d6\0\100\7\12\10\25\0\1\7\u01c0\0\71\7\u0507\0\u0399\7"+
    "\u0c67\0\u042f\7\u33d1\0\u0239\7\7\0\37\7\1\0\12\10\146\0\36\7"+
    "\22\0\60\7\20\0\4\7\14\0\12\10\11\0\25\7\5\0\23\7"+
    "\u0370\0\105\7\13\0\1\7\102\0\15\7\u4060\0\2\7\u0bfe\0\153\7"+
    "\5\0\15\7\3\0\11\7\7\0\12\7\u1766\0\125\7\1\0\107\7"+
    "\1\0\2\7\2\0\1\7\2\0\2\7\2\0\4\7\1\0\14\7"+
    "\1\0\1\7\1\0\7\7\1\0\101\7\1\0\4\7\2\0\10\7"+
    "\1\0\7\7\1\0\34\7\1\0\4\7\1\0\5\7\1\0\1\7"+
    "\3\0\7\7\1\0\u0154\7\2\0\31\7\1\0\31\7\1\0\37\7"+
    "\1\0\31\7\1\0\37\7\1\0\31\7\1\0\37\7\1\0\31\7"+
    "\1\0\37\7\1\0\31\7\1\0\10\7\2\0\62\10\u1000\0\305\7"+
    "\u053b\0\4\7\1\0\33\7\1\0\2\7\1\0\1\7\2\0\1\7"+
    "\1\0\12\7\1\0\4\7\1\0\1\7\1\0\1\7\6\0\1\7"+
    "\4\0\1\7\1\0\1\7\1\0\1\7\1\0\3\7\1\0\2\7"+
    "\1\0\1\7\2\0\1\7\1\0\1\7\1\0\1\7\1\0\1\7"+
    "\1\0\1\7\1\0\2\7\1\0\1\7\2\0\4\7\1\0\7\7"+
    "\1\0\4\7\1\0\4\7\1\0\1\7\1\0\12\7\1\0\21\7"+
    "\5\0\3\7\1\0\5\7\1\0\21\7\u1144\0\ua6d7\7\51\0\u1035\7"+
    "\13\0\336\7\u3fe2\0\u021e\7\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05f0\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\2\3\1\4\1\2\15\4\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\0\2\4\1\20\4\4\1\21\6\4"+
    "\1\22\3\4\1\23\1\24\1\25\1\26\1\0\2\4"+
    "\1\27\1\30\1\31\1\4\1\32\2\4\1\33\5\4"+
    "\1\2\2\4\1\34\1\35\2\4\1\36\1\37\5\4"+
    "\1\40\1\4\1\41\1\42\1\43\3\4\1\44\3\4"+
    "\1\45\1\46";

  private static int [] zzUnpackAction() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\124\0\124\0\176\0\250\0\322"+
    "\0\374\0\u0126\0\u0150\0\u017a\0\u01a4\0\u01ce\0\u01f8\0\u0222"+
    "\0\u024c\0\u0276\0\u02a0\0\u02ca\0\u02f4\0\124\0\124\0\124"+
    "\0\124\0\124\0\124\0\124\0\124\0\u031e\0\u0348\0\u0372"+
    "\0\u039c\0\u03c6\0\u03f0\0\250\0\u041a\0\u0444\0\u046e\0\u0498"+
    "\0\250\0\u04c2\0\u04ec\0\u0516\0\u0540\0\u056a\0\u0594\0\250"+
    "\0\u05be\0\u05e8\0\u0612\0\124\0\124\0\124\0\124\0\u063c"+
    "\0\u0666\0\u0690\0\250\0\250\0\250\0\u06ba\0\250\0\u06e4"+
    "\0\u070e\0\250\0\u0738\0\u0762\0\u078c\0\u07b6\0\u07e0\0\u039c"+
    "\0\u080a\0\u0834\0\250\0\250\0\u085e\0\u0888\0\250\0\250"+
    "\0\u08b2\0\u08dc\0\u0906\0\u0930\0\u095a\0\250\0\u0984\0\250"+
    "\0\250\0\250\0\u09ae\0\u09d8\0\u0a02\0\250\0\u0a2c\0\u0a56"+
    "\0\u0a80\0\250\0\250";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\1\3\1\0\1\4\1\5\1\6\1\7"+
    "\1\0\1\10\1\4\1\11\1\12\1\13\1\7\1\14"+
    "\1\7\1\15\1\7\1\16\1\17\1\7\1\20\1\21"+
    "\1\22\1\23\1\24\3\7\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\2\0\1\41\126\0\2\6\50\0\4\7\2\0\24\7"+
    "\17\0\1\4\52\0\4\7\2\0\1\7\1\42\22\7"+
    "\20\0\4\7\2\0\10\7\1\43\13\7\20\0\4\7"+
    "\2\0\1\7\1\44\22\7\20\0\4\7\2\0\14\7"+
    "\1\45\7\7\20\0\4\7\2\0\4\7\1\46\17\7"+
    "\20\0\4\7\2\0\14\7\1\47\3\7\1\50\3\7"+
    "\20\0\4\7\2\0\2\7\1\51\13\7\1\52\5\7"+
    "\20\0\4\7\2\0\4\7\1\53\5\7\1\54\11\7"+
    "\20\0\4\7\2\0\2\7\1\55\21\7\20\0\4\7"+
    "\2\0\1\7\1\56\20\7\1\57\1\7\20\0\4\7"+
    "\2\0\13\7\1\60\10\7\20\0\4\7\2\0\10\7"+
    "\1\61\13\7\20\0\4\7\2\0\1\7\1\62\20\7"+
    "\1\63\1\7\61\0\1\64\1\0\1\65\47\0\1\66"+
    "\51\0\1\67\3\0\2\41\1\70\1\0\46\41\5\0"+
    "\4\7\2\0\2\7\1\71\21\7\20\0\4\7\2\0"+
    "\4\7\1\72\17\7\20\0\4\7\2\0\11\7\1\73"+
    "\12\7\20\0\4\7\2\0\1\7\1\74\22\7\20\0"+
    "\4\7\2\0\11\7\1\75\12\7\20\0\4\7\2\0"+
    "\21\7\1\76\2\7\20\0\4\7\2\0\6\7\1\77"+
    "\15\7\20\0\4\7\2\0\20\7\1\100\3\7\20\0"+
    "\4\7\2\0\14\7\1\101\7\7\20\0\4\7\2\0"+
    "\15\7\1\102\6\7\20\0\4\7\2\0\12\7\1\103"+
    "\11\7\20\0\4\7\2\0\10\7\1\104\13\7\20\0"+
    "\4\7\2\0\3\7\1\105\20\7\20\0\4\7\2\0"+
    "\16\7\1\106\5\7\20\0\4\7\2\0\16\7\1\107"+
    "\5\7\13\0\1\41\1\110\1\70\1\0\46\41\5\0"+
    "\4\7\2\0\3\7\1\111\3\7\1\112\14\7\20\0"+
    "\4\7\2\0\11\7\1\113\12\7\20\0\4\7\2\0"+
    "\10\7\1\114\13\7\20\0\4\7\2\0\21\7\1\115"+
    "\2\7\20\0\4\7\2\0\7\7\1\116\14\7\20\0"+
    "\4\7\2\0\10\7\1\117\13\7\20\0\4\7\2\0"+
    "\14\7\1\120\7\7\20\0\4\7\2\0\16\7\1\121"+
    "\5\7\20\0\4\7\2\0\15\7\1\122\6\7\20\0"+
    "\4\7\2\0\20\7\1\123\3\7\20\0\4\7\2\0"+
    "\1\7\1\124\22\7\20\0\4\7\2\0\10\7\1\125"+
    "\13\7\20\0\4\7\2\0\10\7\1\126\13\7\20\0"+
    "\4\7\2\0\15\7\1\127\6\7\20\0\4\7\2\0"+
    "\14\7\1\130\7\7\20\0\4\7\2\0\10\7\1\131"+
    "\13\7\20\0\4\7\2\0\10\7\1\132\13\7\20\0"+
    "\4\7\2\0\4\7\1\133\17\7\20\0\4\7\2\0"+
    "\11\7\1\134\12\7\20\0\4\7\2\0\16\7\1\135"+
    "\5\7\20\0\4\7\2\0\5\7\1\136\16\7\20\0"+
    "\4\7\2\0\12\7\1\137\11\7\20\0\4\7\2\0"+
    "\2\7\1\140\21\7\20\0\4\7\2\0\1\7\1\141"+
    "\22\7\20\0\4\7\2\0\14\7\1\142\7\7\20\0"+
    "\4\7\2\0\10\7\1\143\13\7\13\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2730];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\3\11\20\1\10\11\3\1\1\0\22\1\4\11"+
    "\1\0\53\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[99];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */

   StringBuffer string = new StringBuffer();

private Symbol symbol(int type) {
	return new utils.RascalSymbol(type, yyline+1, yycolumn+1);
}

private Symbol symbol(int type, Object value) {
	return new utils.RascalSymbol(type, yyline+1, yycolumn+1, value);
}

private LexerToken createToken(String val, int start) {
    LexerToken tk = new LexerToken(val, start);
    return tk;
}
  


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexico(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2438) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return symbol(sym.T_MULTIPLICACAO);
            }
          case 39: break;
          case 2: 
            { /*Ignore*/
            }
          case 40: break;
          case 3: 
            { return symbol(sym.T_NUM, new Integer(yytext()));
            }
          case 41: break;
          case 4: 
            { return symbol(sym.T_IDENT, createToken(yytext(), yycolumn));
            }
          case 42: break;
          case 5: 
            { return symbol(sym.T_ABRE_PARENTESES);
            }
          case 43: break;
          case 6: 
            { return symbol(sym.T_FECHA_PARENTESES);
            }
          case 44: break;
          case 7: 
            { return symbol(sym.T_PONTO);
            }
          case 45: break;
          case 8: 
            { return symbol(sym.T_VIRGULA);
            }
          case 46: break;
          case 9: 
            { return symbol(sym.T_PONTO_E_VIRGULA);
            }
          case 47: break;
          case 10: 
            { return symbol(sym.T_MAIS);
            }
          case 48: break;
          case 11: 
            { return symbol(sym.T_MENOS);
            }
          case 49: break;
          case 12: 
            { return symbol(sym.T_IGUAL);
            }
          case 50: break;
          case 13: 
            { return symbol(sym.T_MENOR_QUE);
            }
          case 51: break;
          case 14: 
            { return symbol(sym.T_MAIOR_QUE);
            }
          case 52: break;
          case 15: 
            { return symbol(sym.T_DOIS_PONTOS);
            }
          case 53: break;
          case 16: 
            { return symbol(sym.T_OR);
            }
          case 54: break;
          case 17: 
            { return symbol(sym.T_DO);
            }
          case 55: break;
          case 18: 
            { return symbol(sym.T_IF);
            }
          case 56: break;
          case 19: 
            { return symbol(sym.T_MENOR_IGUAL);
            }
          case 57: break;
          case 20: 
            { return symbol(sym.T_NAO_IGUAL);
            }
          case 58: break;
          case 21: 
            { return symbol(sym.T_MAIOR_IGUAL);
            }
          case 59: break;
          case 22: 
            { return symbol(sym.T_ATRIBUICAO);
            }
          case 60: break;
          case 23: 
            { return symbol(sym.T_AND);
            }
          case 61: break;
          case 24: 
            { return symbol(sym.T_VAR);
            }
          case 62: break;
          case 25: 
            { return symbol(sym.T_END);
            }
          case 63: break;
          case 26: 
            { return symbol(sym.T_DIV);
            }
          case 64: break;
          case 27: 
            { return symbol(sym.T_NOT);
            }
          case 65: break;
          case 28: 
            { return symbol(sym.T_READ);
            }
          case 66: break;
          case 29: 
            { return symbol(sym.T_ELSE);
            }
          case 67: break;
          case 30: 
            { return symbol(sym.T_TRUE);
            }
          case 68: break;
          case 31: 
            { return symbol(sym.T_THEN);
            }
          case 69: break;
          case 32: 
            { return symbol(sym.T_FALSE);
            }
          case 70: break;
          case 33: 
            { return symbol(sym.T_BEGIN);
            }
          case 71: break;
          case 34: 
            { return symbol(sym.T_WRITE);
            }
          case 72: break;
          case 35: 
            { return symbol(sym.T_WHILE);
            }
          case 73: break;
          case 36: 
            { return symbol(sym.T_PROGRAM, createToken(yytext(), yycolumn));
            }
          case 74: break;
          case 37: 
            { return symbol(sym.T_FUNCTION);
            }
          case 75: break;
          case 38: 
            { return symbol(sym.T_PROCEDURE);
            }
          case 76: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
