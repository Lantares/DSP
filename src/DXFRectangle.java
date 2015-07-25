// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:29:01
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFRectangle.java



// Referenced classes of package dxfExporter:
//            DXFFigure, DXFData, DXFPoint, DXFExport

class DXFRectangle extends DXFFigure
{

    public DXFRectangle(DXFData Dt)
    {
        super(Dt);
    }

    public DXFPoint getLeftTop()
    {
        return toDXFPoint(super.Data.Point1.X, super.Data.Point.Y);
    }

    public void setLeftTop(DXFPoint Pt)
    {
        super.Data.Point1.X = Pt.X;
        super.Data.Point.Y = Pt.Y;
    }

    public DXFPoint getRightBottom()
    {
        return toDXFPoint(super.Data.Point.X, super.Data.Point1.Y);
    }

    public void setRightBottom(DXFPoint Pt)
    {
        super.Data.Point.X = Pt.X;
        super.Data.Point1.Y = Pt.Y;
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        super.Data.Count = 4;
        Xprt.beginPoly(this);
        Xprt.addVertex(super.Data.Point);
        Xprt.addVertex(toDXFPoint(super.Data.Point1.X, super.Data.Point.Y));
        Xprt.addVertex(super.Data.Point1);
        Xprt.addVertex(toDXFPoint(super.Data.Point.X, super.Data.Point1.Y));
        Xprt.addVertex(super.Data.Point);
    }
}
