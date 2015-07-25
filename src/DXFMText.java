// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:32:38
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFMText.java


// Referenced classes of package dxfExporter:
//            DXFFigure, DXFExport, DXFData

class DXFMText extends DXFFigure
{

    public DXFMText(DXFData Dt)
    {
        super(Dt);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        Xprt.addName(this, "MTEXT", "AcDbMText");
        Xprt.addColor(super.Data.Color);
        Xprt.addPoint(10, super.Data.Point);
        Xprt.addFloat(40, super.Data.FHeight);
        if(super.Data.Rotation != 0.0F)
            Xprt.addFloat(50, super.Data.Rotation);
        if(super.Data.HAlign != 0)
            Xprt.addInt(71, super.Data.HAlign + 1);
        Xprt.addString(1, super.Data.Text);
    }
}
