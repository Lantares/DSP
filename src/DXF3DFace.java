// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:35:29
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXF3DFace.java


// Referenced classes of package dxfExporter:
//            DXFSolid, DXFExport, DXFData

public class DXF3DFace extends DXFSolid
{

    public DXF3DFace(DXFData Dt)
    {
        super(Dt);
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        Xprt.addName(this, "3DFACE", "AcDbFace");
        exportPoints(Xprt);
    }
}
