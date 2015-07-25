// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:35:11
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFBezier.java


import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFFigure, DXFPoint, DXFData, DXFExport

class DXFBezier extends DXFFigure
{

    public DXFBezier(DXFData Dt)
    {
        super(Dt);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        int amount = 4;
        int NumberOfControlPoints = (super.Data.Count - 1) + (int)Math.floor(super.Data.Count / 3);
        float inc = (float)(1.0D / (double)NumberOfControlPoints);
        Xprt.addName(this, "SPLINE", "AcDbSpline");
        Xprt.addColor(super.Data.Color);
        Xprt.addThickness(super.Data);
        Xprt.add3DPoint(210, _$5881);
        Xprt.addInt(70, 8);
        Xprt.addInt(71, 3);
        Xprt.addInt(72, NumberOfControlPoints + 4);
        Xprt.addInt(73, NumberOfControlPoints);
        Xprt.addInt(74, 0);
        Xprt.addFloat(42, 1.0000000116860974E-007D);
        Xprt.addFloat(43, 1.0000000116860974E-007D);
        int i = 0;
        float knot = 0.0F;
        int j;
        for(; i < NumberOfControlPoints; i += 4)
        {
            for(j = 0; j < 4; j++)
                Xprt.addFloat(40, knot);

            knot += inc;
        }

        j = 0;
        knot = 1.0F;
        for(; j < 4; j++)
            Xprt.addFloat(40, knot);

        for(i = 0; i < super.Data.Count; i++)
        {
            Xprt.add3DPoint(10, (DXFPoint)super.Data.Points.get(i));
            if(i % 3 == 0 && i != 0 && i != super.Data.Count - 1)
                Xprt.add3DPoint(10, (DXFPoint)super.Data.Points.get(i));
        }

    }

    private static final DXFPoint _$5881 = new DXFPoint(0.0F, 0.0F, 1.0F);
    private static final int _$5882 = 8;
    private static final int _$5883 = 3;
    private static final int _$5884 = 0;
    private static final float _$5885 = 1E-007F;
    private static final float _$5886 = 1E-007F;

}
