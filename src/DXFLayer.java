// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:22:05
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFLayer.java



// Referenced classes of package dxfExporter:
//            DXFExport, Constants

public class DXFLayer
{

    public DXFLayer(String Nm)
    {
        _$3928 = Nm;
        _$3948 = 0;
        _$519 = 7;
        _$4305 = "Continuous";
        _$4306 = -3;
    }

    public int getColor()
    {
        return _$519;
    }

    public void setColor(int Clr)
    {
        _$519 = Clr;
    }

    public byte getFlags()
    {
        return _$3948;
    }

    public String getLinetypeName()
    {
        return new String(_$4305);
    }

    public int getLineWeight()
    {
        return _$4306;
    }

    public String getName()
    {
        return _$3928;
    }

    public void setFlags(byte Flgs)
    {
        _$3948 = Flgs;
    }

    public void setLinetypeName(String LTpName)
    {
        _$4305 = LTpName;
    }

    public void setLineWeight(int LWght)
    {
        _$4306 = LWght;
    }

    public void setName(String Nm)
    {
        _$3928 = Nm;
    }

    public void ExportAsDXF(DXFExport Xprt)
    {
        Xprt.addName(null, "LAYER", "");
        Xprt.addString(330, Constants.TABLES_LAYER[7]);
        Xprt.addString(100, "AcDbSymbolTableRecord");
        Xprt.addString(100, "AcDbLayerTableRecord");
        Xprt.addString(2, _$3928);
        Xprt.addInt(70, _$3948);
        Xprt.addInt(62, _$519);
        Xprt.addString(6, _$4305);
        if(Xprt.AutoCADVer.compareTo("AC1014") != 0)
        {
            Xprt.addInt(370, _$4306);
            Xprt.addString(390, Constants.OBJECTS_R2000[95]);
        }
    }

    private String _$3928;
    private String _$4305;
    private byte _$3948;
    private int _$519;
    private int _$4306;
}
