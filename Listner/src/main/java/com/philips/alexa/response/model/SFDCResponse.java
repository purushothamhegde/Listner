package com.philips.alexa.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SFDCResponse {
	
	@JsonProperty("id")
	private String id;

	@JsonProperty("done")
    private String done;

	@JsonProperty("developerName")
    private String developerName;

	
	@JsonProperty("columns")
    private Columns[] columns;

    @JsonProperty("label")
    private String label;
    
    @JsonProperty("records")
    private Records[] records;

    @JsonProperty("size")
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

    public Columns[] getColumns ()
    {
        return columns;
    }

    public void setColumns (Columns[] columns)
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

    public Records[] getRecords ()
    {
        return records;
    }

    public void setRecords (Records[] records)
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
