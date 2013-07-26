package cmu.axis.databean;


public class ItemBean {
	private long   id;
	private String item;
	private long   position;
	private String userName;
	
	public long   getId()            { return id;       }
    public String getItem()          { return item;     }
    public Long   getPosition()      { return position; }
    public String getUserName()      { return userName; }

    public void   setId(long x)         { id = x;        }
	public void   setItem(String s)     { item = s;      }
	public void   setPosition(long x)   { position = x;  }
	public void   setUserName(String s) { userName = s;  }
}
