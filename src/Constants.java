// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:25:16
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

import java.awt.Color;

public final class Constants
{

    public static float getRadian(float Angle)
    {
        return (Angle * 3.141593F) / 180F;
    }

    public static int convertColorRGBToDXF(Color Value)
    {
        int C[] = {
            0, 1, 3, 2, 5, 6, 4, 0, 1, 251, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 50, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 251, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 252, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 163, 1, 1, 1, 
            1, 1, 1, 1, 253, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 171, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
            1, 1, 1, 1, 1, 254
        };
        Color clAqua = new Color(0, 255, 255);
        Color clMaroon = new Color(128, 0, 0);
        Color clFuchsia = new Color(255, 0, 255);
        Color clNavy = new Color(0, 0, 128);
        Color clTeal = new Color(0, 128, 128);
        Color clPurple = new Color(128, 0, 128);
        Color clLime = new Color(0, 255, 0);
        Color clSilver = new Color(192, 192, 192);
        Color clOlive = new Color(128, 128, 0);
        if(Value.equals(clMaroon))
            return 14;
        if(Value.equals(clAqua) || Value.equals(Color.cyan))
            return 4;
        if(Value.equals(clFuchsia))
            return 6;
        if(Value.equals(clNavy))
            return 154;
        if(Value.equals(clTeal))
            return 12;
        if(Value.equals(clPurple))
            return 224;
        if(Value.equals(clLime))
            return 3;
        if(Value.equals(clSilver))
            return 9;
        if(Value.equals(clOlive))
            return 44;
        if(Value.equals(Color.green))
            return 74;
        if(Value.equals(Color.gray))
            return 8;
        if(Value.equals(Color.red))
            return 1;
        if(Value.equals(Color.yellow))
            return 2;
        if(Value.equals(Color.blue))
            return 5;
        if(Value.equals(Color.white))
            return 255;
        if(Value.equals(Color.black))
        {
            return 0;
        } else
        {
            int RGB = Value.getRGB();
            return C[RGB & 0xc0 | (RGB & 0xe000) >> 10 | (RGB & 0xe00000) >> 21];
        }
    }

    private Constants()
    {
    }

    public static final String HEADER[] = {
        "  0", "SECTION", "  2", "HEADER"
    };
    public static final String TABLES_LTYPE[] = {
        "  0", "ENDSEC", "  0", "SECTION", "  2", "TABLES", "  0", "TABLE", "  2", "VPORT", 
        "  5", "  1", "100", "AcDbSymbolTable", "  0", "ENDTAB", "  0", "TABLE", "  2", "LTYPE", 
        "  5", "  2", "100", "AcDbSymbolTable", "  0", "LTYPE", "  5", "  3", "100", "AcDbSymbolTableRecord", 
        "100", "AcDbLinetypeTableRecord", "  2", "BYBLOCK", " 70", "     0", "  0", "LTYPE", "  5", "  4", 
        "100", "AcDbSymbolTableRecord", "100", "AcDbLinetypeTableRecord", "  2", "BYLAYER", " 70", "     0"
    };
    public static final String TABLES_LAYER[] = {
        "  0", "ENDTAB", "  0", "TABLE", "  2", "LAYER", "  5", " 1A", "330", "  0", 
        "100", "AcDbSymbolTable"
    };
    public static final String TABLES_STYLE[] = {
        "  0", "ENDTAB", "  0", "TABLE", "  2", "STYLE", "  5", "  5", "100", "AcDbSymbolTable", 
        "  0", "STYLE", "  5", "  6", "100", "AcDbSymbolTableRecord", "100", "AcDbTextStyleTableRecord", "  2", "STANDARD", 
        " 70", "     0", " 40", "0.0", " 41", "1.0", " 50", "0.0", " 71", "     0", 
        " 42", "10.0", "  3", "txt", "  4", "bigfont", "  0", "ENDTAB", "  0", "TABLE", 
        "  2", "VIEW", "  5", "  7", "100", "AcDbSymbolTable", "  0", "ENDTAB", "  0", "TABLE", 
        "  2", "UCS", "  5", "  8", "100", "AcDbSymbolTable", "  0", "ENDTAB", "  0", "TABLE", 
        "  2", "APPID", "  5", "  9", "100", "AcDbSymbolTable", "  0", "APPID", "  5", "  A", 
        "100", "AcDbSymbolTableRecord", "100", "AcDbRegAppTableRecord", "  2", "ACAD", " 70", "0"
    };
    public static final String TABLES_DIMSTYLE[] = {
        "  0", "ENDTAB", "  0", "TABLE", "  2", "DIMSTYLE", "  5", "  B", "100", "AcDbSymbolTable"
    };
    public static final String TABLES_DIMSTYLE_R2000[] = {
        " 70", "     1", "100", "AcDbDimStyleTable", " 71", "     0"
    };
    public static final String TABLES_BLOCK_RECORD[] = {
        "  0", "ENDTAB", "  0", "TABLE", "  2", "BLOCK_RECORD", "  5", "  C", "100", "AcDbSymbolTable", 
        "  0", "BLOCK_RECORD", "  5", "  D", "100", "AcDbSymbolTableRecord", "100", "AcDbBlockTableRecord", "  2", "*MODEL_SPACE", 
        "  0", "BLOCK_RECORD", "  5", "  E", "100", "AcDbSymbolTableRecord", "100", "AcDbBlockTableRecord", "  2", "*PAPER_SPACE"
    };
    public static final String BLOCKS[] = {
        "  0", "ENDTAB", "  0", "ENDSEC", "  0", "SECTION", "  2", "BLOCKS", "  0", "BLOCK", 
        "  5", "  F", "330", "  D", "100", "AcDbEntity", "  8", "0", "100", "AcDbBlockBegin", 
        "  2", "*MODEL_SPACE", " 70", "     0", "  0", "ENDBLK", "  5", " 10", "100", "AcDbEntity", 
        "  8", "0", "100", "AcDbBlockEnd", "  0", "BLOCK", "  5", " 11", "330", "  E", 
        "100", "AcDbEntity", "  8", "0", "100", "AcDbBlockBegin", "  2", "*PAPER_SPACE", " 70", "     0", 
        "  0", "ENDBLK", "  5", " 12", "100", "AcDbEntity", "  8", "0", "100", "AcDbBlockEnd"
    };
    public static final String OBJECTS_R14[] = {
        "  0", "ENDSEC", "  0", "SECTION", "  2", "OBJECTS", "  0", "DICTIONARY", "  5", "13", 
        "100", "AcDbDictionary", "  3", "ACAD_GROUP", "350", "14", "  3", "ACAD_MLINESTYLE", "350", "15", 
        "  0", "DICTIONARY", "  5", "14", "102", "{ACAD_REACTORS", "330", "13", "102", "}", 
        "100", "AcDbDictionary", "  0", "DICTIONARY", "  5", "15", "102", "{ACAD_REACTORS", "330", "13", 
        "102", "}", "100", "AcDbDictionary", " 3", "STANDARD", "350", "16", "  0", "MLINESTYLE", 
        "  5", "16", "102", "{ACAD_REACTORS", "330", "15", "102", "}", "100", "AcDbMlineStyle", 
        "  2", "STANDARD", " 70", "     0", "  3", " ", " 62", "   256", " 51", "90.0", 
        " 52", "90.0", " 71", "     2", " 49", "0.5", " 62", "   256", "  6", "BYLAYER", 
        " 49", "-0.5", " 62", "   256", "  6", "BYLAYER"
    };
    public static final String OBJECTS_R2000[] = {
        "  0", "ENDSEC", "  0", "SECTION", "  2", "OBJECTS", "  0", "DICTIONARY", "  5", " 13", 
        "330", "0", "100", "AcDbDictionary", "281", "     1", "  3", "ACAD_GROUP", "350", " 14", 
        " 3", "ACAD_MLINESTYLE", "350", " 16", "  3", "ACAD_PLOTSETTINGS", "350", " 17", "  3", "ACAD_PLOTSTYLENAME", 
        "350", " 18", "  0", "DICTIONARY", "  5", " 14", "330", " 13", "100", "AcDbDictionary", 
        "281", "     1", "  0", "DICTIONARY", "  5", " 16", "330", " 13", "100", "AcDbDictionary", 
        "281", "     1", "  0", "DICTIONARY", "  5", " 17", "102", "{ACAD_REACTORS", "330", " 13", 
        "102", "}", "330", " 13", "100", "AcDbDictionary", "281", "     1", "  0", "ACDBDICTIONARYWDFLT", 
        "  5", " 18", "102", "{ACAD_REACTORS", "330", " 13", "102", "}", "330", " 13", 
        "100", "AcDbDictionary", "281", "     1", "  3", "Normal", "350", " 19", "100", "AcDbDictionaryWithDefault", 
        "340", " 19", "  0", "ACDBPLACEHOLDER", "  5", " 19", "102", "{ACAD_REACTORS", "330", " 18", 
        "102", "}", "330", " 18"
    };
    public static final String END_OF_DXF[] = {
        "  0", "ENDSEC", "  0", "EOF"
    };
    public static final float EXACTITUDE = 1E-006F;
    public static final String DXFVERSION_R14 = "AC1014";
    public static final String DXFVERSION_R2000 = "AC1015";
    public static final short DXFLineWeights[] = {
        0, 5, 9, 13, 15, 18, 20, 25, 30, 35, 
        40, 50, 53, 60, 70, 80, 90, 100, 106, 120, 
        140, 158, 200, 211
    };
    public static final short DXF_BYLAYER = 0;
    public static final short DXF_RED = 1;
    public static final short DXF_YELLOW = 2;
    public static final short DXF_LIME = 3;
    public static final short DXF_AQUA = 4;
    public static final short DXF_BLUE = 5;
    public static final short DXF_FUCHSIA = 6;
    public static final short DXF_BLACK = 7;
    public static final short DXF_GRAY = 8;
    public static final short DXF_SILVER = 9;
    public static final short DXF_OLIVE = 44;
    public static final short DXF_GREEN = 74;
    public static final short DXF_TEAL = 12;
    public static final short DXF_NAVY = 154;
    public static final short DXF_PURPLE = 224;
    public static final short DXF_MAROON = 14;
    public static final short DXF_WHITE = 255;
    public static final short DXF_DKGRAY = 251;
    public static final short DXF_LTGRAY = 254;
    public static final short DXF_ALTERNATIVEBLACK = 250;
    public static final String DXFNAME_ARC = "ARC";
    public static final String DXFNAME_CIRCLE = "CIRCLE";
    public static final String DXFNAME_ELLIPSE = "ELLIPSE";
    public static final String DXFNAME_HATCH = "HATCH";
    public static final String DXFNAME_LINE = "LINE";
    public static final String DXFNAME_LAYER = "LAYER";
    public static final String DXFNAME_MTEXT = "MTEXT";
    public static final String DXFNAME_POINT = "POINT";
    public static final String DXFNAME_SOLID = "SOLID";
    public static final String DXFNAME_SPLINE = "SPLINE";
    public static final String DXFNAME_TEXT = "TEXT";
    public static final String DXFNAME_3DFACE = "3DFACE";
    public static final String HATCHPATTERN_SOLID = "SOLID";
    public static final String HATCHPATTERN_ANSI31 = "ANSI31";
    public static final String HATCHPATTERN_ANSI37 = "ANSI37";
    public static final String HATCHPATTERN_NET = "NET";
    public static final String HATCHPATTERN_LINE = "LINE";

}
