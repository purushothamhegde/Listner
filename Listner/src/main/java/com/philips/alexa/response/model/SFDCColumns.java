package com.philips.alexa.response.model;

public class SFDCColumns 
{
    private String sortIndex;

    private String fieldNameOrPath;

    private String ascendingLabel;

    private String hidden;

    private String sortable;

    private String label;

    private String sortDirection;

    private String type;

    private String selectListItem;

    private String descendingLabel;

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