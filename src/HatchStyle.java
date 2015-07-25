// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:33:54
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HatchStyle.java



public class HatchStyle
{

    HatchStyle()
    {
        _$5388 = 0;
    }

    HatchStyle(byte hStyle)
    {
        SetHS(hStyle);
    }

    public boolean SetHS(byte hStyle)
    {
        if(hStyle < 0 || hStyle > 7)
        {
            _$5388 = 0;
            return false;
        } else
        {
            _$5388 = hStyle;
            return true;
        }
    }

    public byte GetHS()
    {
        return _$5388;
    }

    private byte _$5388;
    public static final byte hsSolid = 0;
    public static final byte hsPatternData = 1;
    public static final byte hsHorizontal = 2;
    public static final byte hsVertical = 3;
    public static final byte hsFDiagonal = 4;
    public static final byte hsBDiagonal = 5;
    public static final byte hsCross = 6;
    public static final byte hsDiagCross = 7;
}
