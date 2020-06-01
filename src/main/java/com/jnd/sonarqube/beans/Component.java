package com.jnd.sonarqube.beans;

public class Component
{
    private Measures[] measures;

    private String qualifier;

    private String name;

    private String id;

    private String key;

    public Measures[] getMeasures ()
    {
        return measures;
    }

    public void setMeasures (Measures[] measures)
    {
        this.measures = measures;
    }

    public String getQualifier ()
    {
        return qualifier;
    }

    public void setQualifier (String qualifier)
    {
        this.qualifier = qualifier;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [measures = "+measures+", qualifier = "+qualifier+", name = "+name+", id = "+id+", key = "+key+"]";
    }
}