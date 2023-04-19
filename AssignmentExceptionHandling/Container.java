

public class Container implements Comparable<Container>{
	private String UUID ;
	private String shortname;
	private String longname;
	
	Container(String UUID,String shortname,String longname)
	{
		this.UUID=UUID;
		this.shortname=shortname;
		this.longname=longname;
	}
	public String getUUID()
	{
		return UUID;
	}
	public String getShortName()
	{
		return shortname;
	}
	public String getLongName()
	{
		return longname;
	}
	public int compareTo(Container c)
	{
		if(shortname.charAt(9)>c.getShortName().charAt(9))
			return 1;
		else
		return -1;
	}

}
