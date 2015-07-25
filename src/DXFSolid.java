// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:35:55
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFSolid.java



// Referenced classes of package dxfExporter:
//            DXFFigure, DXFData, DXFExport

public class DXFSolid extends DXFFigure
{

    public DXFSolid(DXFData Dt)
    {
        super(Dt);
    }

    protected void exportPoints(DXFExport Xprt)
    {
        Xprt.addColor(super.Data.Color);
        Xprt.add3DPoint(10, super.Data.Point);
        Xprt.add3DPoint(11, super.Data.Point1);
        Xprt.add3DPoint(12, super.Data.Point2);
        Xprt.add3DPoint(13, super.Data.Point3);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        Xprt.addName(this, "SOLID", "AcDbTrace");
        exportPoints(Xprt);
    }
}
