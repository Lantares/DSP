// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:31:50
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFArc.java


import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFFigure, ArcType, DXFPoint, DXFData, 
//            DXFExport, Constants

public class DXFArc extends DXFFigure
{

    public DXFArc(DXFData Dt)
    {
        super(Dt);
        _$5841 = new ArcType(Dt.SelfType);
    }

    public DXFPoint getPoint()
    {
        return new DXFPoint(super.Data.Point);
    }

    public float getRadius()
    {
        return super.Data.Radius;
    }

    public float getStartAngle()
    {
        return super.Data.StartAngle;
    }

    public float getEndAngle()
    {
        return super.Data.EndAngle;
    }

    public byte getType()
    {
        return _$5841.getAT();
    }

    public void setType(byte aType)
    {
        _$5841.setAT(aType);
    }

    public void setPoint(DXFPoint Pt)
    {
        super.Data.Point = null;
        super.Data.Point = new DXFPoint(Pt);
    }

    public void setRadius(float Radius)
    {
        super.Data.Radius = Radius;
    }

    public void setStartAngle(float StartAngle)
    {
        super.Data.StartAngle = StartAngle;
    }

    public void setEndAngle(float EndAngle)
    {
        super.Data.EndAngle = EndAngle;
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        switch(_$5841.getAT())
        {
        default:
            break;

        case 0: // '\0'
            Xprt.addName(this, "ARC", "AcDbCircle");
            Xprt.addColor(super.Data.Color);
            Xprt.addThickness(super.Data);
            Xprt.addPoint(10, super.Data.Point);
            Xprt.addFloat(40, super.Data.Radius);
            Xprt.Current.add("100");
            Xprt.Current.add("AcDbArc");
            Xprt.addFloat(50, super.Data.StartAngle);
            Xprt.addFloat(51, super.Data.EndAngle);
            break;

        case 1: // '\001'
            Xprt.addName(this, "ELLIPSE", "AcDbEllipse");
            Xprt.addColor(super.Data.Color);
            Xprt.addThickness(super.Data);
            Xprt.addPoint(10, super.Data.Point);
            Xprt.addPoint(11, super.Data.Point1);
            Xprt.addFloat(40, super.Data.Radius);
            if(Math.abs(super.Data.StartAngle - super.Data.EndAngle) > 1E-006F)
            {
                Xprt.addFloat(41, Constants.getRadian(super.Data.StartAngle));
                Xprt.addFloat(42, Constants.getRadian(super.Data.EndAngle));
            }
            break;
        }
    }

    private ArcType _$5841;
}
