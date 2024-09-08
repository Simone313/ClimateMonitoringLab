package com.climatemonitoring.climatemonitoringlab;
/**
 * SystemInfo
 */
public class SystemInfo {
    /**
    * Costruttore predefinito per la classe SystemInfo.
    */
    public SystemInfo() {}
    
    /**
     * metodo javaVersion
     * @return System.getProperty("java.version")
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }
    /**
     * metodo javafxVersion
     * @return System.getProperty("javafx.version")
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}