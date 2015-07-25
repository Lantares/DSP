// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:24:08
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFData.java


import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFPoint

public class DXFData
{

    public DXFData()
    {
    }

    public Object clone()
    {
        DXFData Dt = new DXFData();
        Dt.Tag = Tag;
        Dt.Count = Count;
        Dt.Flags = Flags;
        Dt.Style = Style;
        Dt.SelfType = SelfType;
        Dt.Color = Color;
        Dt.Thickness = Thickness;
        Dt.Rotation = Rotation;
        Dt.Text = null;
        if(Text != null)
            Dt.Text = new String(Text);
        Dt.Point = new DXFPoint(Point);
        Dt.Point1 = new DXFPoint(Point1);
        Dt.Point2 = new DXFPoint(Point2);
        Dt.Point3 = new DXFPoint(Point3);
        Dt.Radius = Radius;
        Dt.StartAngle = StartAngle;
        Dt.EndAngle = EndAngle;
        Dt.Scale = new DXFPoint(Scale);
        Dt.HAlign = HAlign;
        Dt.VAlign = VAlign;
        Dt.RWidth = RWidth;
        Dt.RHeight = RHeight;
        Dt.FHeight = FHeight;
        Dt.FScale = FScale;
        Dt.Points = Points;
        Dt.Bold = Bold;
        Dt.Italic = Italic;
        Dt.Name = null;
        if(Name != null)
            Dt.Name = new String(Name);
        Dt.LayerName = null;
        if(LayerName != null)
            Dt.LayerName = new String(LayerName);
        return Dt;
    }

    public int Tag;
    public int Count;
    public int Flags;
    public byte Style;
    public byte SelfType;
    public int Color;
    public float Thickness;
    public float Rotation;
    public String Text;
    public DXFPoint Point;
    public DXFPoint Point1;
    public DXFPoint Point2;
    public DXFPoint Point3;
    public float Radius;
    public float StartAngle;
    public float EndAngle;
    public DXFPoint Scale;
    public byte HAlign;
    public byte VAlign;
    public float RWidth;
    public float RHeight;
    public float FHeight;
    public float FScale;
    public ArrayList Points;
    public byte Bold;
    public byte Italic;
    public String LayerName;
    public String Name;
}
