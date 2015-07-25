// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:23:39
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFFigure.java



// Referenced classes of package dxfExporter:
//            DXFData, DXFPoint, DXFExport

public abstract class DXFFigure
{

    public DXFFigure(DXFData Dt)
    {
        Data = (DXFData)Dt.clone();
    }

    protected DXFPoint toDXFPoint(float X, float Y)
    {
        return new DXFPoint(X, Y, 0.0F);
    }

    public abstract void exportAsDXF(DXFExport dxfexport);

    protected DXFData Data;
}
