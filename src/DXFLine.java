// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:28:36
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFLine.java



// Referenced classes of package dxfExporter:
//            DXFFigure, DXFPoint, DXFData, DXFExport

public class DXFLine extends DXFFigure
{

    public DXFLine(DXFData Dt)
    {
        super(Dt);
    }

    public DXFPoint getStartPoint()
    {
        return new DXFPoint(super.Data.Point);
    }

    public DXFPoint getEndPoint()
    {
        return new DXFPoint(super.Data.Point1);
    }

    public void setStartPoint(DXFPoint Pt)
    {
        super.Data.Point = new DXFPoint(Pt);
    }

    public void setEndPoint(DXFPoint Pt)
    {
        super.Data.Point1 = new DXFPoint(Pt);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        Xprt.addName(this, "LINE", "AcDbLine");
        Xprt.addColor(super.Data.Color);
        Xprt.addThickness(super.Data);
        Xprt.addPoint(10, super.Data.Point);
        Xprt.addPoint(11, super.Data.Point1);
    }
}
