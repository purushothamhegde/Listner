package com.philips.alexa.response.model;

public class SFDCResponse {
	
	private String id;

    private String done;

    private String developerName;

    private SFDCColumns[] columns;

    private String label;

    private SFDCRecords[] records;

    private String size;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDone ()
    {
        return done;
    }

    public void setDone (String done)
    {
        this.done = done;
    }

    public String getDeveloperName ()
    {
        return developerName;
    }

    public void setDeveloperName (String developerName)
    {
        this.developerName = developerName;
    }

    public SFDCColumns[] getColumns ()
    {
        return columns;
    }

    public void setColumns (SFDCColumns[] columns)
    {
        this.columns = columns;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    public SFDCRecords[] getRecords ()
    {
        return records;
    }

    public void setRecords (SFDCRecords[] records)
    {
        this.records = records;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", done = "+done+", developerName = "+developerName+", SFDCcolumns = "+columns+", label = "+label+", SFDCrecords = "+records+", size = "+size+"]";
    }
    

}
