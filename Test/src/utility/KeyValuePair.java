package utility;

public class KeyValuePair <K,V>
{
	private K key;
	private V value;
	
	public KeyValuePair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
	public K getKey()
	{
		return this.key;
	}
	
	public V getValue()
	{
		return this.value;
	}
	
	public void setKey(K key)
	{
		this.key = key;
	}
	
	public void setValue(V value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return "key: "+this.key+"; value:"+this.value+";";
	}

}
