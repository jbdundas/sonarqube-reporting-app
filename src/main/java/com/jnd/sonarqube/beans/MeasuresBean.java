package com.jnd.sonarqube.beans;

public class MeasuresBean
{
    private Component component;

    private Periods[] periods;

    public Component getComponent ()
    {
        return component;
    }

    public void setComponent (Component component)
    {
        this.component = component;
    }

    public Periods[] getPeriods ()
    {
        return periods;
    }

    public void setPeriods (Periods[] periods)
    {
        this.periods = periods;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [component = "+component+", periods = "+periods+"]";
    }
}