// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:23:05
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFPoint.java



public class DXFPoint
{

    public DXFPoint()
    {
        setTo(0.0F, 0.0F, 0.0F);
    }

    public DXFPoint(float X, float Y, float Z)
    {
        setTo(X, Y, Z);
    }

    public DXFPoint(DXFPoint Pt)
    {
        if(Pt != null)
            setTo(Pt.X, Pt.Y, Pt.Z);
        else
            setTo(0.0F, 0.0F, 0.0F);
    }

    public void setTo(float X, float Y, float Z)
    {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public float X;
    public float Y;
    public float Z;
}
