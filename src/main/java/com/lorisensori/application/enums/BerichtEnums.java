package com.lorisensori.application.enums;

import java.io.Serializable;

public enum BerichtEnums implements Serializable {
	ONGEWENSTENIVEAUDALING ("Ongewenste Niveaudaling"),	
	TANKEN ("Bijtanken"), 
	LAAGSPANNINGSNIVEAUACCU ("Laag Spanningsniveau Accu"), 
	SLOTOPEN ("Slot open buiten openingstijden");
	
	static final BerichtEnums DEFAULT = TANKEN;
	
	private final String displayName;
	 
	BerichtEnums(String displayName)
	{
		this.displayName = displayName;
	}
	 
	public String getDisplayName()
	{
		return displayName;
	}	

}
