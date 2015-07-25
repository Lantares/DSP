// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:32:10
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ArcType.java


public class ArcType
{

    ArcType()
    {
        _$5854 = 0;
    }

    ArcType(byte aType)
    {
        setAT(aType);
    }

    public boolean setAT(byte aType)
    {
        if(aType < 0 || aType > 1)
        {
            _$5854 = 0;
            return false;
        } else
        {
            _$5854 = aType;
            return true;
        }
    }

    public byte getAT()
    {
        return _$5854;
    }

    private byte _$5854;
    public static final byte atCircle = 0;
    public static final byte atEllipse = 1;
}
