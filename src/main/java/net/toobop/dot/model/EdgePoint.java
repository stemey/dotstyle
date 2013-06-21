package net.toobop.dot.model;

public class EdgePoint
{
	private final String id;

	private final String port;

	public EdgePoint(String id)
	{
		super();
		this.id = id;
		port = null;
	}

	public EdgePoint(String id, String port)
	{
		super();
		this.id = id;
		this.port = port;
	}

	public static EdgePoint create(String edgeId)
	{
		String[] parts = edgeId.split(":");
		if (parts.length > 1)
		{
			return new EdgePoint(parts[0], parts[1]);
		}
		else
		{
			return new EdgePoint(parts[0]);
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgePoint other = (EdgePoint) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (port == null)
		{
			if (other.port != null)
				return false;
		}
		else if (!port.equals(other.port))
			return false;
		return true;
	}

	public String getFullId()
	{
		if (port == null)
		{
			return id;
		}
		else
		{
			return id + ":" + port;
		}
	}

	public String getId()
	{
		return id;
	}

	public String getPort()
	{
		return port;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return id + (port == null ? "" : ":" + port);
	}
}
