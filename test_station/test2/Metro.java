package com.exo8_6_1;

/**
 * Class NomMachine
 */
public class NomMachine {

    //
    private String machine;
    private String domain;
    private String local;

    /**
     * Builder: default
     */
    public NomMachine()
    {
        this.machine = "unknown";
        this.domain = "unknown";
        this.local = "unknown";
    }

    /**
     * Builder
     */
    public NomMachine( String machine, String domain, String local )
    {
        this.machine = machine;
        this.domain = domain;
        this.local = local;
    }

    /**
     * Getter: get machine
     */
    public String getMachine() { return this.machine; }

    /**
     * Setter: set new machine
     */
    public void setMachine( String newMachine ) { this.machine = newMachine; }

    /**
     * Getter: get domain
     */
    public String getDomain() { return this.domain; }

    /**
     * Setter: set domain
     */
    public void setDomain( String newDomain ) { this.domain = newDomain; }

    /**
     * Getter: get local
     */
    public String getLocal() { return this.local; }

    /**
     * Setter: set local
     */
    public void setLocal( String newLocal ) { this.local = newLocal; }

    /**
     * @Override
     */
    public String toString() { return this.machine+"."+this.domain+"."+this.local; }

    /**
     * @Override
     */
    public boolean equals( NomMachine other )
    {
        return this.machine.equals( other.getName() )
                & this.domain.equals( other.getQualified() )
                & this.local.equals( other.getMachine() );
    }
}