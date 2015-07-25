// Decompiled by DJ v3.12.12.99 Copyright 2015 Atanas Neshkov  Date: 2015/7/9 12:32:57
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DXFHatch.java


import java.util.AbstractCollection;
import java.util.ArrayList;

// Referenced classes of package dxfExporter:
//            DXFFigure, DXFPoint, HatchBoundaryType, HatchStyle, 
//            DXFHatchPatternData, DXFData, DXFExport

public class DXFHatch extends DXFFigure
{

    public DXFHatch(DXFData Dt)
    {
        super(Dt);
        _$5864(Dt);
        _$5862 = null;
        switch(_$5860.getHBT())
        {
        case 1: // '\001'
        case 2: // '\002'
        default:
            break;

        case 0: // '\0'
            if(super.Data.Count > 0)
            {
                _$5862 = new ArrayList(super.Data.Count);
                for(int i = 0; i < Dt.Count; i++)
                {
                    ArrayList tmpList = (ArrayList)Dt.Points.get(i);
                    if(tmpList.size() < 3)
                        continue;
                    _$5862.add(new ArrayList(tmpList.size()));
                    for(int k = 0; k < tmpList.size(); k++)
                    {
                        DXFPoint Pt = new DXFPoint((DXFPoint)tmpList.get(k));
                        if(k >= 2 && Pt.X == ((DXFPoint)tmpList.get(k - 2)).X && Pt.Y == ((DXFPoint)tmpList.get(k - 2)).Y)
                            Pt = null;
                        else
                            ((ArrayList)_$5862.get(_$5862.size() - 1)).add(Pt);
                    }

                    tmpList = (ArrayList)_$5862.get(_$5862.size() - 1);
                    if(tmpList.size() >= 3)
                        continue;
                    for(int k = 0; k < tmpList.size(); k++)
                        tmpList.set(k, null);

                    tmpList.clear();
                    _$5862.remove(tmpList);
                }

            }
            break;
        }
    }

    private void _$5866()
    {
        if(_$5862 == null)
            return;
        for(int i = 0; i < _$5862.size(); i++)
        {
            for(int k = 0; k < ((ArrayList)_$5862.get(i)).size(); k++)
                ((ArrayList)_$5862.get(i)).set(k, null);

            ((ArrayList)_$5862.get(i)).clear();
        }

        _$5862.clear();
    }

    public DXFPoint getPoint(int BndIndex, int PtIndex)
    {
        if(_$5862 != null)
            return new DXFPoint((DXFPoint)((ArrayList)_$5862.get(BndIndex)).get(PtIndex));
        else
            return new DXFPoint(0.0F, 0.0F, 0.0F);
    }

    public int getBndAmount()
    {
        if(_$5862 != null)
            return _$5862.size();
        else
            return 0;
    }

    private void _$5864(DXFData Dt)
    {
        _$5860 = new HatchBoundaryType(super.Data.SelfType);
        _$2648 = new HatchStyle(super.Data.Style);
        _$5861 = new String("SOLID");
        super.Data.Flags = 1;
        if(_$2648.GetHS() != 0)
        {
            super.Data.Flags = 0;
            if(_$2648.GetHS() == 2 || _$2648.GetHS() == 3)
                _$5861 = new String("LINE");
            if(_$2648.GetHS() == 4 || _$2648.GetHS() == 5)
                _$5861 = new String("ANSI31");
            if(_$2648.GetHS() == 6)
                _$5861 = new String("NET");
            if(_$2648.GetHS() == 7)
                _$5861 = new String("ANSI37");
        }
    }

    private void _$5870(DXFExport Xprt)
    {
        float Nil = 0.0F;
        int NumLines = 1;
        DXFHatchPatternData hpd[] = new DXFHatchPatternData[2];
        hpd[0] = new DXFHatchPatternData();
        hpd[1] = new DXFHatchPatternData();
        hpd[0].BasePointX = hpd[0].BasePointY = 0.0F;
        hpd[0].OffsetX = -_$5859;
        hpd[0].OffsetY = _$5859;
        hpd[1].BasePointX = hpd[1].BasePointY = 0.0F;
        hpd[1].OffsetX = -_$5859;
        hpd[1].OffsetY = _$5859;
        byte style = _$2648.GetHS();
        if(style == 2)
        {
            hpd[0].Angle = 0.0F;
            hpd[0].OffsetX = 0.0F;
        } else
        if(style == 3)
        {
            hpd[0].Angle = 90F;
            hpd[0].OffsetY = 0.0F;
        } else
        if(style == 4)
        {
            hpd[0].Angle = 135F;
            hpd[0].OffsetY = -hpd[0].OffsetY;
        } else
        if(style == 5)
            hpd[0].Angle = 45F;
        else
        if(style == 6)
        {
            NumLines = 2;
            hpd[0].Angle = 0.0F;
            hpd[0].OffsetX = 0.0F;
            hpd[1].Angle = 90F;
        } else
        if(style == 7)
        {
            NumLines = 2;
            hpd[0].Angle = 45F;
            hpd[1].Angle = 135F;
            hpd[1].OffsetY = -hpd[0].OffsetY;
        }
        Xprt.addFloat(52, 0.0D);
        Xprt.addFloat(41, 1.0D);
        Xprt.addInt(77, 0);
        Xprt.addInt(78, NumLines);
        for(int i = 0; i < NumLines; i++)
        {
            Xprt.addFloat(53, hpd[i].Angle);
            Xprt.addFloat(43, hpd[i].BasePointX);
            Xprt.addFloat(44, hpd[i].BasePointY);
            Xprt.addFloat(45, hpd[i].OffsetX);
            Xprt.addFloat(46, hpd[i].OffsetY);
            Xprt.addInt(79, 0);
        }

    }

    private void _$5879(DXFExport Xprt)
    {
label0:
        switch(_$5860.getHBT())
        {
        default:
            break;

        case 0: // '\0'
            Xprt.addInt(91, _$5862.size());
            int k = 0;
            do
            {
                if(k >= _$5862.size())
                    break label0;
                Xprt.addInt(92, 1);
                ArrayList tmpList = (ArrayList)_$5862.get(k);
                Xprt.addInt(93, tmpList.size());
                for(int i = 1; i < tmpList.size(); i++)
                {
                    Xprt.addInt(72, 1);
                    Xprt.addPoint(10, (DXFPoint)tmpList.get(i - 1));
                    Xprt.addPoint(11, (DXFPoint)tmpList.get(i));
                }

                Xprt.addInt(72, 1);
                Xprt.addPoint(10, (DXFPoint)tmpList.get(tmpList.size() - 1));
                Xprt.addPoint(11, (DXFPoint)tmpList.get(0));
                Xprt.addInt(97, 0);
                k++;
            } while(true);

        case 1: // '\001'
            Xprt.addInt(91, 1);
            Xprt.addInt(92, 1);
            Xprt.addInt(93, 1);
            Xprt.addInt(72, 2);
            Xprt.addPoint(10, super.Data.Point);
            Xprt.addFloat(40, super.Data.Radius);
            Xprt.addFloat(50, super.Data.StartAngle);
            Xprt.addFloat(51, super.Data.EndAngle);
            Xprt.addInt(73, 1);
            Xprt.addInt(97, 0);
            break;

        case 2: // '\002'
            Xprt.addInt(91, 1);
            Xprt.addInt(92, 1);
            Xprt.addInt(93, 1);
            Xprt.addInt(72, 3);
            Xprt.addPoint(10, super.Data.Point);
            Xprt.addPoint(11, super.Data.Point1);
            Xprt.addFloat(40, super.Data.Radius);
            Xprt.addFloat(50, super.Data.StartAngle);
            Xprt.addFloat(51, super.Data.EndAngle);
            Xprt.addInt(73, 1);
            Xprt.addInt(97, 0);
            break;
        }
    }

    public void exportAsDXF(DXFExport Xprt)
    {
        DXFPoint Pt = new DXFPoint(0.0F, 0.0F, 0.0F);
        _$5859 = 5F;
        if(_$5860.getHBT() == 0 && (_$5862 == null || _$5862.size() == 0))
            return;
        Xprt.addName(this, "HATCH", "AcDbHatch");
        Xprt.addColor(super.Data.Color);
        Xprt.addPoint(10, Pt);
        Xprt.addFloat(30, 0.0D);
        Xprt.addFloat(210, 0.0D);
        Xprt.addFloat(220, 0.0D);
        Xprt.addFloat(230, 1.0D);
        Xprt.addString(2, _$5861);
        Xprt.addInt(70, super.Data.Flags);
        Xprt.addInt(71, 0);
        _$5879(Xprt);
        Xprt.addInt(75, 0);
        Xprt.addInt(76, 1);
        if(super.Data.Flags == 0)
            _$5870(Xprt);
        Xprt.addInt(98, 1);
        Xprt.addPoint(10, Pt);
    }

    public void finalize()
    {
        _$5866();
        super.Data = null;
    }

    private float _$5859;
    private HatchBoundaryType _$5860;
    private HatchStyle _$2648;
    private String _$5861;
    private ArrayList _$5862;
}
