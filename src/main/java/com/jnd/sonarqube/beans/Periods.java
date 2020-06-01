package com.jnd.sonarqube.beans;

public class Periods
{
    private String index;

    private String bestValue;

    private String value;

    public String getIndex ()
    {
        return index;
    }

    public void setIndex (String index)
    {
        this.index = index;
    }

    public String getBestValue ()
    {
        return bestValue;
    }

    public void setBestValue (String bestValue)
    {
        this.bestValue = bestValue;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [index = "+index+", bestValue = "+bestValue+", value = "+value+"]";
    }
}
