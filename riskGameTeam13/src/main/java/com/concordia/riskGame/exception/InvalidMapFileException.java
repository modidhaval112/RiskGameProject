package com.concordia.riskGame.exception;


/**
 * This class contains custom exception for Invalid map file.
 * @author d_modi
 */
public class InvalidMapFileException extends Exception{
	
    /**
     * Parameterless Constructor
     */
    public InvalidMapFileException() {}

    /**
     * Constructor that accepts a message
     * @param message : Invalid map file message
     */
    public InvalidMapFileException(String message)
    {
       super(message);
    }

}
