// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:26:10
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFPixel.java



// Referenced classes of package dxfExporter:
//            DXFFigure, DXFExport, DXFData

public class DXFPixel extends DXFFigure
{

    public DXFPixel(DXFData Dt)
    {
        super(Dt);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        Xprt.addName(this, "POINT", "AcDbPoint");
        Xprt.addColor(super.Data.Color);
        Xprt.addPoint(10, super.Data.Point);
    }
}
