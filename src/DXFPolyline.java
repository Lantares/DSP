// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:30:51
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFPolyline.java


import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFFigure, DXFPoint, DXFData, DXFExport

public class DXFPolyline extends DXFFigure
{

    public DXFPolyline(DXFData Dt)
    {
        super(Dt);
        super.Data.Point.setTo(0.0F, 0.0F, 0.0F);
        super.Data.Point1.setTo(0.0F, 0.0F, 0.0F);
        if(super.Data.Count > 0 && super.Data.Points != null)
        {
            _$5833 = new ArrayList(super.Data.Count);
            for(int i = 0; i < super.Data.Count; i++)
            {
                DXFPoint Pt = new DXFPoint((DXFPoint)super.Data.Points.get(i));
                if(Pt.X < super.Data.Point.X)
                    super.Data.Point.X = Pt.X;
                if(Pt.Y < super.Data.Point.Y)
                    super.Data.Point.Y = Pt.Y;
                if(Pt.X > super.Data.Point1.X)
                    super.Data.Point1.X = Pt.X;
                if(Pt.Y > super.Data.Point1.Y)
                    super.Data.Point1.Y = Pt.Y;
                _$5833.add(Pt);
            }

        }
    }

    public int getPointCount()
    {
        return _$5833.size();
    }

    public DXFPoint getPoint(int Index)
    {
        return new DXFPoint((DXFPoint)_$5833.get(Index));
    }

    public void clearPoints()
    {
        if(_$5833 != null)
        {
            for(int i = 0; i < _$5833.size(); i++)
                _$5833.set(i, null);

            _$5833.clear();
        }
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        Xprt.beginPoly(this);
        for(int i = 0; i < super.Data.Count; i++)
            Xprt.addVertex((DXFPoint)_$5833.get(i));

    }

    public void finalize()
    {
        clearPoints();
        super.Data = null;
    }

    private ArrayList _$5833;
}
