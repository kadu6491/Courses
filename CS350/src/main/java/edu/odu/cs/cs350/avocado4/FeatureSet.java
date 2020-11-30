package edu.odu.cs.cs350.avocado4;

/**
 * FeatureSet Class
 * 
 * These attributes provide a definition for our supervised training.
 * The majority of these attributes are either a String or a boolean value.
 * The values of these attributes are cataloged in the Cataloger class.
 * FeatureSet and Token are one-to-one.
 */
public class FeatureSet {

	private boolean isInDictionary;
	private String partOfSpeech;
	private String lexicalType;
	private boolean isInitial;
	private boolean hasHonorific;
	private boolean hasPrefix;
	private boolean hasSuffix;
	private boolean nonPersonalProperName;
	private boolean hasStopList;
	private boolean isCommonFirstName;
	private boolean isFirstName;
	private boolean isCommonLastName;
	private boolean isLastName;
	private boolean isCityState;
	private boolean isCountry;
	private boolean isPlace;
	
	/**
	 * Default constructor for FeatureSet.
	 * FeatureSet will contain specific attributes associated with a Token.
	 * Instance data is initially set to null if object or false if boolean.
	 */
	public FeatureSet() 
	{
		partOfSpeech = null;
		lexicalType = String.valueOf(Lexical.OTHER);

		isInDictionary = false;
		isInitial = false;
		hasHonorific = false;
		hasPrefix = false;
		hasSuffix = false;
		nonPersonalProperName = false;
		hasStopList = false;
		isCommonFirstName = false;
		isFirstName = false;
		isCommonLastName = false;
		isLastName = false;
		isCityState = false;
		isCountry = false;
		isPlace = false;
	}

	/**
	 * Sets the lexicalType attribute to the parameter.
	 * 
	 * @param lexical String for the lexical type
	 */
	public void setLexicalType(String lexical)
	{
		this.lexicalType = lexical;
	}

	/**
	 * Returns the lexicalType attribute
	 * 
	 * @return lexicalType String
	 */
	public String getLexicalType()
	{
		return this.lexicalType;
	}

	/**
	 * Sets the partOfSpeech attribute to the parameter
	 * 
	 * @param pos String for the part of speech
	 */
	public void setPartOfSpeech(String pos)
	{
		this.partOfSpeech = pos;
	}

	/**
	 * Returns the partOfSpeech attribute
	 * 
	 * @return partOfSpeech String
	 */
	public String getPartOfSpeech()
	{
		return this.partOfSpeech;
	}
	
	/**
	 * Sets the isInDictionary attribute to the parameter.
	 * 
	 * @param dictionary boolean
	 */
	public void setIsInDictionary(boolean dictionary)
	{
		this.isInDictionary = dictionary;
	}

	/**
	 * Returns the isInDictionary attribute
	 * 
	 * @return isInDictionary boolean
	 */
	public boolean getIsInDictionary()
	{
		return this.isInDictionary;
	}
	
	/**
	 * Sets the isInitial attribute to the parameter
	 * 
	 * @param initial boolean
	 */
	public void setIsInitial(boolean initial)
	{
		this.isInitial = initial;
	}

	/**
	 * Returns the isInitial attribute
	 * 
	 * @return isInitial boolean
	 */
	public boolean getIsInitial()
	{
		return this.isInitial;
	}
	
	/**
	 * Sets the hasHonorific attribute
	 * 
	 * @param hasHonorific boolean
	 */
	public void setHonorific(boolean hasHonorific) 
	{
		this.hasHonorific = hasHonorific;
	}
	
	/**
	 * Returns the hasHonorific attribute
	 * 
	 * @return hasHonorific boolean
	 */
	public boolean hasHonorific() 
	{
		return this.hasHonorific;
	}
	
	/**
	 * Sets the hasPrefix attribute
	 * 
	 * @param hasPrefix boolean
	 */
	public void setPrefix(boolean hasPrefix) 
	{
		this.hasPrefix = hasPrefix;
	}
	
	/**
	 * Returns the hasPrefix attribute
	 * 
	 * @return hasPrefix boolean
	 */
	public boolean hasPrefix() 
	{
		return this.hasPrefix;
	}
	
	/**
	 * Sets the hasSuffix attribute
	 * 
	 * @param hasSuffix boolean
	 */
	public void setSuffix(boolean hasSuffix)
	{
		this.hasSuffix = hasSuffix;
	}
	
	/**
	 * Returns the hasSuffix attribute
	 * 
	 * @return hasSuffix boolean
	 */
	public boolean hasSuffix()
	{
		return this.hasSuffix;
	}
	
	/**
	 * Sets the nonPersonalProperName attribute
	 * 
	 * @param hasNonPersonalProperName boolean
	 */
	public void setNonPersonalProperName(boolean hasNonPersonalProperName) 
	{
		this.nonPersonalProperName = hasNonPersonalProperName;
	}
	
	/**
	 * Returns the nonPersonalProperName attribute
	 * 
	 * @return nonPersonalProperName boolean
	 */
	public boolean hasNonPersonalProperName()
	{
		return this.nonPersonalProperName;
	}

	/**
	 * Sets the hasStopList attribute
	 * 
	 * @param hasStopList boolean
	 */
	public void setHasStopList(boolean hasStopList)
	{
		this.hasStopList = hasStopList;
	}

	/**
	 * Returns the hasStopList attribute
	 * 
	 * @return hasStopList boolean
	 */
	public boolean hasStopList() {
		return this.hasStopList;
	}
	
	/**
	 * Sets the isCommonFirstName attribute
	 * 
	 * @param b boolean
	 */
	public void setCommonFirstName(boolean b)
	{
		this.isCommonFirstName = b;
	}

	/**
	 * Returns the isCommonFirstName attribute
	 * 
	 * @return isCommonFirstName boolean
	 */
	public boolean getCommonFirstName() {
		return this.isCommonFirstName;
	}
	
	/**
	 * Sets the isFirstName attribute
	 * 
	 * @param b boolean
	 */
	public void setFirstName(boolean b)
	{
		this.isFirstName = b;
	}

	/**
	 * Returns the isFirstName attribute
	 * 
	 * @return isFirstName boolean
	 */
	public boolean getFirstName() {
		return this.isFirstName;
	}
	
	/**
	 * Sets the isCommonLastName attribute
	 * 
	 * @param b boolean
	 */
	public void setCommonLastName(boolean b)
	{
		this.isCommonLastName = b;
	}

	/**
	 * Returns the isCommonLastName attribute
	 * 
	 * @return isCommonLastName boolean
	 */
	public boolean getCommonLastName() 
	{
		return this.isCommonLastName;
	}
	
	/**
	 * Sets the isLastName attribute
	 * 
	 * @param b boolean
	 */
	public void setLastName(boolean b)
	{
		this.isLastName = b;
	}

	/**
	 * Returns the isLastName attribute
	 * 
	 * @return isLastName boolean
	 */
	public boolean getLastName() 
	{
		return this.isLastName;
	}
	
	/**
	 * Sets the isCityState attribute
	 * 
	 * @param b boolean
	 */
	public void setCityState(boolean b)
	{
		this.isCityState = b;
	}

	/**
	 * Returns the isCityState attribute
	 * 
	 * @return isCityState boolean
	 */
	public boolean getCityState() 
	{
		return this.isCityState;
	}

	/**
	 * Sets the isCountry attribute
	 * 
	 * @param b boolean
	 */
	public void setCountry(boolean b)
	{
		this.isCountry = b;
	}

	/**
	 * Returns the isCountry attribute
	 * 
	 * @return isCountry boolean
	 */
	public boolean getCountry() 
	{
		return this.isCountry;
	}
	
	/**
	 * Sets the isPlace attribute
	 * 
	 * @param b boolean
	 */
	public void setPlace(boolean b)
	{
		this.isPlace = b;
	}

	/**
	 * Returns the isPlace attribute
	 * 
	 * @return isPlace boolean
	 */
	public boolean getPlace() 
	{
		return this.isPlace;
	}
}
