// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:19:38
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFExport.java


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFPoint, DXFLayer, DXFPixel, DXFLine, 
//            DXFRectangle, DXFPolyline, DXFEllipse, DXFArc, 
//            DXFText, DXFMText, DXFHatch, DXFBezier, 
//            DXF3DFace, DXFData, DXFFigure, Constants

public class DXFExport
{

    public DXFExport()
    {
        DrawingUnits = 4;
        AutoCADVer = "AC1014";
        _$3913 = new ArrayList();
        _$3914 = new ArrayList();
        _$3915 = new ArrayList();
        Current = _$3913;
        _$3911 = new ArrayList();
        _$3916 = new DXFPoint(3.402823E+038F, 3.402823E+038F, 3.402823E+038F);
        _$3917 = new DXFPoint(-3.402823E+038F, -3.402823E+038F, -3.402823E+038F);
        _$3918 = 32;
        _$3919 = new DXFLayer("0");
        _$3914.add(_$3919);
    }

    private void _$3922()
    {
        addString(5, Integer.toHexString(_$3918));
        _$3918++;
    }

    public void addName(DXFFigure Figure, String Name, String SubName)
    {
        addString(0, Name);
        _$3922();
        if(Name.compareTo("LAYER") == 0)
            return;
        Current.add("100");
        if(Current == _$3915)
        {
            Current.add("AcDbSymbolTableRecord");
        } else
        {
            Current.add("AcDbEntity");
            if(Figure != null && Figure.Data.LayerName != null)
                addString(8, Figure.Data.LayerName);
        }
        addString(100, SubName);
    }

    public void addInt(int Code, int Value)
    {
        Current.add(String.valueOf(Code));
        Current.add(String.valueOf(Value));
    }

    public void addFloat(int Code, double Value)
    {
        Current.add(String.valueOf(Code));
        Current.add(String.valueOf(Value));
    }

    public void addString(int Code, String Str)
    {
        Current.add(String.valueOf(Code));
        Current.add(new String(Str));
    }

    public void addPoint(int Code, DXFPoint Pt)
    {
        addFloat(Code, Pt.X);
        addFloat(Code + 10, Pt.Y);
        doLimits(Pt);
    }

    public void add3DPoint(int Code, DXFPoint Pt)
    {
        addPoint(Code, Pt);
        addFloat(Code + 20, Pt.Z);
    }

    public void addVertex(DXFPoint Pt)
    {
        addPoint(10, Pt);
    }

    public void addColor(int Color)
    {
        if(Color != 0)
            addInt(62, Color);
    }

    public void addThickness(DXFData Dt)
    {
        if(AutoCADVer.compareTo("AC1014") == 0)
            return;
        if(Dt.Text != null && Dt.Text.compareTo("") != 0)
        {
            Current.add("  6");
            Current.add(new String(Dt.Text));
        }
        int T;
        try
        {
            T = Math.round(Dt.Thickness) * 10;
        }
        catch(Exception e)
        {
            T = 100;
        }
        if(T < 5)
            return;
        if(T >= 211)
        {
            T = 211;
        } else
        {
            int i = 0;
            do
            {
                if(i > Constants.DXFLineWeights.length)
                    break;
                if(T < Constants.DXFLineWeights[i])
                {
                    T = Constants.DXFLineWeights[i - 1];
                    break;
                }
                i++;
            } while(true);
        }
        addInt(370, T);
    }

    public void beginPoly(DXFFigure Figure)
    {
        addName(Figure, "LWPOLYLINE", "AcDbPolyline");
        addColor(Figure.Data.Color);
        addThickness(Figure.Data);
        addInt(90, Figure.Data.Count);
        addInt(70, Figure.Data.Flags);
    }

    public void addPixel(DXFData Dt)
    {
        _$3911.add(new DXFPixel(Dt));
    }

    public void addLine(DXFData Dt)
    {
        _$3911.add(new DXFLine(Dt));
    }

    public void addRectangle(DXFData Dt)
    {
        _$3911.add(new DXFRectangle(Dt));
    }

    public void addPolyline(DXFData Dt)
    {
        _$3911.add(new DXFPolyline(Dt));
    }

    public void addCircle(DXFData Dt)
    {
        _$3911.add(new DXFEllipse(Dt, false));
    }

    public void addEllipse(DXFData Dt)
    {
        _$3911.add(new DXFEllipse(Dt, true));
    }

    public void addArc(DXFData Dt)
    {
        _$3911.add(new DXFArc(Dt));
    }

    public void addText(DXFData Dt)
    {
        _$3911.add(new DXFText(Dt));
    }

    public void addMText(DXFData Dt)
    {
        _$3911.add(new DXFMText(Dt));
    }

    public void addHatch(DXFData Dt)
    {
        _$3911.add(new DXFHatch(Dt));
    }

    public void addBezier(DXFData Dt)
    {
        _$3911.add(new DXFBezier(Dt));
    }

    public void add3DFace(DXFData Dt)
    {
        _$3911.add(new DXF3DFace(Dt));
    }

    public void addLType(String Name, float Parts[])
    {
        float Total;
        ArrayList Old;
        Total = 0.0F;
        for(int i = 0; i < Parts.length; i++)
            Total += Math.abs(Parts[i]);

        Old = Current;
        Current = _$3915;
        addName(null, "LTYPE", "AcDbLinetypeTableRecord");
        _$3915.add("  2");
        _$3915.add(new String(Name));
        _$3915.add("  3");
        _$3915.add("");
        _$3915.add(" 70");
        _$3915.add("0");
        _$3915.add(" 72");
        _$3915.add("65");
        _$3915.add(" 73");
        _$3915.add(String.valueOf(Parts.length + 1));
        _$3915.add(" 40");
        _$3915.add(String.valueOf(Total));
        for(int i = 0; i < Parts.length; i++)
        {
            _$3915.add(" 49");
            _$3915.add(String.valueOf(Parts[i]));
            _$3915.add(" 74");
            _$3915.add("0");
        }

        Current = Old;
//        break MISSING_BLOCK_LABEL_263;
//        Exception exception;
//        exception;
//        Current = Old;
//        throw exception;
    }

    private void _$3956(String Strs[])
    {
        for(int i = 0; i < Strs.length; i++)
            if(Strs[i] != null)
                Current.add(new String(Strs[i]));
            else
                Current.add("");

    }

    private void _$3958(ArrayList list)
    {
        if(list != null && list.size() != 0)
            Current.addAll(list);
    }

    private static boolean _$3960(float Top, float Left, float Right, float Bottom)
    {
        return Math.abs((Right - Left - Bottom) + Top) <= 1.0F;
    }

    public static DXFData doArcParams(float Top, float Left, float Right, float Bottom, float StartPtX, float StartPtY, float EndPtX, float EndPtY)
    {
        DXFData Dt = doEllipseParams(Top, Left, Right, Bottom);
        switch(Dt.SelfType)
        {
        default:
            break;

        case 0: // '\0'
            Dt.StartAngle = (float)(57.295779513082323D * Math.atan2(Dt.Point1.Y * (-StartPtY - Dt.Point.Y), Dt.Point1.X * (StartPtX - Dt.Point.X)));
            Dt.EndAngle = (float)(57.295779513082323D * Math.atan2(Dt.Point1.Y * (-EndPtY - Dt.Point.Y), Dt.Point1.X * (EndPtX - Dt.Point.X)));
            break;

        case 1: // '\001'
            if(Dt.Point1.X < Dt.Point1.Y)
            {
                Dt.StartAngle = (float)(Math.atan2(Dt.Point1.X * (-StartPtY - Dt.Point.Y), Dt.Point1.Y * (StartPtX - Dt.Point.X)) - 1.5707963267948966D);
                Dt.EndAngle = (float)(Math.atan2(Dt.Point1.X * (-EndPtY - Dt.Point.Y), Dt.Point1.Y * (EndPtX - Dt.Point.X)) - 1.5707963267948966D);
            } else
            {
                Dt.StartAngle = (float)(-Math.atan2(Dt.Point1.X * (-StartPtY - Dt.Point.Y), Dt.Point1.Y * (StartPtX - Dt.Point.X)) + 1.5707963267948966D);
                Dt.EndAngle = (float)(-Math.atan2(Dt.Point1.Y * (EndPtX - Dt.Point.X), Dt.Point1.X * (-EndPtY - Dt.Point.Y)) + 1.5707963267948966D);
            }
            break;
        }
        return Dt;
    }

    public static DXFData doEllipseParams(float Top, float Left, float Right, float Bottom)
    {
        DXFData Dt = new DXFData();
        boolean is_square = _$3960(Top, Left, Right, Bottom);
        if(Top > Bottom)
        {
            float temp = Top;
            Top = Bottom;
            Bottom = temp;
        }
        Dt.Point.X = (Left + Right) / 2.0F;
        Dt.Point.Y = (Top + Bottom) / 2.0F;
        Dt.Point1.X = (Right - Left) / 2.0F;
        Dt.Point1.Y = (Bottom - Top) / 2.0F;
        Dt.Radius = Dt.Point1.X;
        if(Dt.Radius < Dt.Point1.Y)
            Dt.Radius = Dt.Point1.Y;
        Dt.Point1.X = Dt.Point1.X / Dt.Radius;
        Dt.Point1.Y = Dt.Point1.Y / Dt.Radius;
        Dt.StartAngle = 0.0F;
        Dt.EndAngle = 360F;
        Dt.SelfType = 0;
        if(!is_square)
        {
            Dt.SelfType = 1;
            if(Dt.Point1.X > Dt.Point1.Y)
            {
                Dt.Point1.X = Dt.Radius;
                Dt.Radius = Dt.Point1.Y;
                Dt.Point1.Y = 0.0F;
            } else
            {
                Dt.Point1.Y = Dt.Radius;
                Dt.Radius = Dt.Point1.X;
                Dt.Point1.X = 0.0F;
            }
        }
        return Dt;
    }

    protected void doLimits(DXFPoint Pt)
    {
        if(Current == _$3913)
        {
            if(_$3916.X > Pt.X)
                _$3916.X = Pt.X;
            if(_$3916.Y > Pt.Y)
                _$3916.Y = Pt.Y;
            if(_$3917.X < Pt.X)
                _$3917.X = Pt.X;
            if(_$3917.Y < Pt.Y)
                _$3917.Y = Pt.Y;
        }
    }

    public void setCurrentLayer(DXFLayer Lr)
    {
        _$3919 = Lr;
        if(_$3914.indexOf(_$3919) == -1)
            _$3914.add(_$3919);
    }

    public void saveToFile(String FilePath) throws IOException
    {
        ArrayList Old;
        ArrayList temporaryCurrent;
        String ENDSEC[] = {
            "  0", "ENDSEC", "  0", "SECTION", "  2", "ENTITIES"
        };
        temporaryCurrent = new ArrayList();
        for(int i = 0; i < _$3911.size(); i++)
        {
            DXFFigure dxfFig = (DXFFigure)_$3911.get(i);
            if(dxfFig != null)
                dxfFig.exportAsDXF(this);
        }

        DXFData Dt = new DXFData();
        Dt.Color = 1;
        Dt.FHeight = (_$3917.Y - _$3916.Y) / 4F;
        Dt.Point = new DXFPoint(_$3916.X, _$3916.Y + 2.0F * Dt.FHeight, 0.0F);
        Dt.Point1 = new DXFPoint(Dt.Point);
        if(Dt.FHeight <= 0.0F)
            Dt.FHeight = 1.0F;
        Dt.FScale = Dt.FHeight / (_$3917.X - _$3916.X);
        //Dt.Text = "Demo DXF Export by Soft Gold Ltd. (c) 2006";
        DXFText txt = new DXFText(Dt);
        txt.exportAsDXF(this);
        txt = null;
        Dt.Point.setTo(_$3916.X, _$3916.Y, 0.0F);
        //Dt.Text = "Register on www.cadsofttools.com";
        txt = new DXFText(Dt);
        txt.exportAsDXF(this);
        Old = Current;
        Current = temporaryCurrent;
        _$3956(Constants.HEADER);
        addString(9, "$ACADVER");
        addString(1, AutoCADVer);
        addString(9, "$HANDSEED");
        addString(5, Integer.toHexString(_$3918 + _$3914.size() + 2));
        addString(9, "$INSUNITS");
        addInt(70, DrawingUnits);
        addString(9, "$LIMMIN");
        addPoint(10, _$3916);
        addString(9, "$LIMMAX");
        addPoint(10, _$3917);
        _$3956(Constants.TABLES_LTYPE);
        _$3958(_$3915);
        _$3956(Constants.TABLES_LAYER);
        for(int i = 0; i < _$3914.size(); i++)
            ((DXFLayer)_$3914.get(i)).ExportAsDXF(this);

        _$3956(Constants.TABLES_STYLE);
        _$3956(Constants.TABLES_DIMSTYLE);
        if(AutoCADVer.compareTo("AC1014") != 0)
            _$3956(Constants.TABLES_DIMSTYLE_R2000);
        _$3956(Constants.TABLES_BLOCK_RECORD);
        _$3956(Constants.BLOCKS);
        _$3956(ENDSEC);
        _$3958(_$3913);
        if(AutoCADVer.compareTo("AC1014") == 0)
            _$3956(Constants.OBJECTS_R14);
        else
            _$3956(Constants.OBJECTS_R2000);
        _$3956(Constants.END_OF_DXF);
        FileOutputStream fileOutStream = new FileOutputStream(FilePath);
        for(int i = 0; i < Current.size(); i++)
        {
            fileOutStream.write(((String)Current.get(i)).getBytes());
            fileOutStream.write(13);
            fileOutStream.write(10);
        }

        fileOutStream.close();
        fileOutStream = null;
        Current = Old;
        temporaryCurrent.clear();
//        break MISSING_BLOCK_LABEL_687;
//        Exception e;
//        e;
//        e.notify();
//        Current = Old;
//        temporaryCurrent.clear();
//        break MISSING_BLOCK_LABEL_687;
//        Exception exception;
//        exception;
//        Current = Old;
//        temporaryCurrent.clear();
//        throw exception;
    }

    private void _$4001(ArrayList list)
    {
        if(list != null)
        {
            for(int i = 0; i < list.size(); i++)
                list.set(i, null);

            list.clear();
            list = null;
        }
    }

    public void finalize()
    {
        AutoCADVer = null;
        _$3916 = _$3917 = null;
        _$4001(_$3911);
        _$4001(Current);
        _$4001(_$3913);
        _$4001(_$3914);
        _$4001(_$3915);
    }

    public int DrawingUnits;
    public String AutoCADVer;
    private ArrayList _$3911;
    public ArrayList Current;
    private ArrayList _$3913;
    private ArrayList _$3914;
    private ArrayList _$3915;
    private DXFPoint _$3916;
    private DXFPoint _$3917;
    private int _$3918;
    private DXFLayer _$3919;
}
