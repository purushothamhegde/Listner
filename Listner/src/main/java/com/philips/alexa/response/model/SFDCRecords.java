package com.philips.alexa.response.model;


public class SFDCRecords
{
    private SFDCColumns[] columns;

    public SFDCColumns[] getColumns ()
    {
        return columns;
    }

    public void setColumns (SFDCColumns[] columns)
    {
        this.columns = columns;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SFDCColumns = "+columns+"]";
    }
}
