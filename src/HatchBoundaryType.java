// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:33:24
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HatchBoundaryType.java


public class HatchBoundaryType
{

    HatchBoundaryType()
    {
        _$5362 = 0;
    }

    HatchBoundaryType(byte hbType)
    {
        setHBT(hbType);
    }

    public boolean setHBT(byte hbType)
    {
        if(hbType < 0 || hbType > 2)
        {
            _$5362 = 0;
            return false;
        } else
        {
            _$5362 = hbType;
            return true;
        }
    }

    public byte getHBT()
    {
        return _$5362;
    }

    private byte _$5362;
    public static final byte hbtPolyPolyline = 0;
    public static final byte hbtCircle = 1;
    public static final byte hbtEllipse = 2;
}
