package com.smartseat.workgroup.main.elian;

/**
 * Created by yangyuan on 12/17/14.
 */
public class ElianNative {
    static {
//        System.loadLibrary("elianjni");
    }

    public ElianNative()
    {

    }

	public static boolean LoadLib() 
	{
		try {
	        System.loadLibrary("elianjni");
			return true;
		} catch (UnsatisfiedLinkError ule) {
			System.err.println("WARNING: Could not load elianjni library!");
			return false;
		}
	}
}
