// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:27:09
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFText.java


import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFFigure, DXFPoint, DXFExport, DXFData

class DXFText extends DXFFigure
{

    public DXFText(DXFData Dt)
    {
        super(Dt);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        String sub_name = "AcDbText";
        Xprt.addName(this, "TEXT", "AcDbText");
        Xprt.addColor(super.Data.Color);
        Xprt.addPoint(10, super.Data.Point);
        Xprt.addFloat(40, super.Data.FHeight);
        Xprt.doLimits(new DXFPoint(super.Data.Point.X, super.Data.Point.Y + super.Data.FHeight, 0.0F));
        if(super.Data.FScale != 0.0F)
            Xprt.addFloat(41, super.Data.FScale);
        if(super.Data.Rotation != 0.0F)
            Xprt.addFloat(50, super.Data.Rotation);
        if(super.Data.Flags != 0)
            Xprt.addFloat(51, 15D);
        if(super.Data.HAlign != 0 || super.Data.VAlign != 0)
        {
            if(super.Data.HAlign != 0)
                Xprt.addInt(72, super.Data.HAlign);
            Xprt.addPoint(11, super.Data.Point1);
        }
        Xprt.Current.add("  1");
        if(super.Data.Text != null)
            Xprt.Current.add(new String(super.Data.Text));
        else
            Xprt.Current.add("");
        Xprt.addString(100, "AcDbText");
        if(super.Data.VAlign != 0)
            Xprt.addInt(73, super.Data.VAlign);
    }
}
