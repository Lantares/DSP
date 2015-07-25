// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:31:15
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFEllipse.java



// Referenced classes of package dxfExporter:
//            DXFFigure, DXFPoint, DXFData, DXFExport

class DXFEllipse extends DXFFigure
{

    public DXFEllipse(DXFData Dt, boolean Ellipse)
    {
        super(Dt);
        _$5839 = Ellipse;
    }

    public DXFPoint getLeftTop()
    {
        DXFPoint Pt = new DXFPoint(super.Data.Point);
        Pt.Z = 0.0F;
        if(!_$5839)
        {
            Pt.X = Pt.X - super.Data.Radius;
            Pt.Y = Pt.Y - super.Data.Radius;
        }
        return Pt;
    }

    public DXFPoint getRightBottom()
    {
        DXFPoint Pt = new DXFPoint();
        if(_$5839)
        {
            Pt.X = super.Data.Point.X + super.Data.Scale.X * 10F;
            Pt.Y = super.Data.Point.Y - super.Data.Scale.Y * 10F;
        } else
        {
            Pt.X = super.Data.Point.X + super.Data.Radius;
            Pt.Y = super.Data.Point.Y + super.Data.Radius;
        }
        return Pt;
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        if(!_$5839)
        {
            Xprt.addName(this, "CIRCLE", "AcDbCircle");
            Xprt.addColor(super.Data.Color);
            Xprt.addThickness(super.Data);
            Xprt.addPoint(10, super.Data.Point);
            Xprt.addFloat(40, super.Data.Radius);
        } else
        {
            Xprt.addName(this, "ELLIPSE", "AcDbEllipse");
            Xprt.addColor(super.Data.Color);
            Xprt.addThickness(super.Data);
            Xprt.addPoint(10, super.Data.Point);
            Xprt.addPoint(11, super.Data.Point1);
            Xprt.addFloat(40, super.Data.Radius);
        }
    }

    private boolean _$5839;
}
