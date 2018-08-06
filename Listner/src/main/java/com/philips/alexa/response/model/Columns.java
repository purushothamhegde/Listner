package com.philips.alexa.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Columns 
{
	@JsonProperty("sortIndex")
	private String sortIndex;

	@JsonProperty("fieldNameOrPath")
    private String fieldNameOrPath;

	@JsonProperty("ascendingLabel")
    private String ascendingLabel;

	@JsonProperty("hidden")
    private String hidden;

	@JsonProperty("sortable")
    private String sortable;

	@JsonProperty("label")
    private String label;

	@JsonProperty("sortDirection")
    private String sortDirection;

	@JsonProperty("type")
    private String type;

	@JsonProperty("selectListItem")
    private String selectListItem;

	@JsonProperty("descendingLabel")
    private String descendingLabel;

	
	@JsonProperty("value")
    private String value1;

	
	
	
	
    public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getSortIndex ()
    {
        return sortIndex;
    }

    public void setSortIndex (String sortIndex)
    {
        this.sortIndex = sortIndex;
    }

    public String getFieldNameOrPath ()
    {
        return fieldNameOrPath;
    }

    public void setFieldNameOrPath (String fieldNameOrPath)
    {
        this.fieldNameOrPath = fieldNameOrPath;
    }

    public String getAscendingLabel ()
    {
        return ascendingLabel;
    }

    public void setAscendingLabel (String ascendingLabel)
    {
        this.ascendingLabel = ascendingLabel;
    }

    public String getHidden ()
    {
        return hidden;
    }

    public void setHidden (String hidden)
    {
        this.hidden = hidden;
    }

    public String getSortable ()
    {
        return sortable;
    }

    public void setSortable (String sortable)
    {
        this.sortable = sortable;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    public String getSortDirection ()
    {
        return sortDirection;
    }

    public void setSortDirection (String sortDirection)
    {
        this.sortDirection = sortDirection;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getSelectListItem ()
    {
        return selectListItem;
    }

    public void setSelectListItem (String selectListItem)
    {
        this.selectListItem = selectListItem;
    }

    public String getDescendingLabel ()
    {
        return descendingLabel;
    }

    public void setDescendingLabel (String descendingLabel)
    {
        this.descendingLabel = descendingLabel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sortIndex = "+sortIndex+", fieldNameOrPath = "+fieldNameOrPath+", ascendingLabel = "+ascendingLabel+", hidden = "+hidden+", sortable = "+sortable+", label = "+label+", sortDirection = "+sortDirection+", type = "+type+", selectListItem = "+selectListItem+", descendingLabel = "+descendingLabel+"]";
    }
}