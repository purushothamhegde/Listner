package com.philips.alexa.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Records
{
	@JsonProperty("columns")
    private Columns[] columns;

    public Columns[] getColumns ()
    {
        return columns;
    }

    public void setColumns (Columns[] columns)
    {
        this.columns = columns;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Columns = "+columns+"]";
    }
}
