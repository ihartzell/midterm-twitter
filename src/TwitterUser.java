import java.util.ArrayList;

public class TwitterUser implements Comparable<TwitterUser>,Cloneable
{
	// I'm assigning my usersID variable to my int argument.
	// I'm creating a brand new array list.
	public TwitterUser(int arg)
	{
		this.usersID = arg;
		this.followers = new ArrayList<TwitterUser>();
	}
	
	private int usersID;
	private ArrayList<TwitterUser>followers = new ArrayList<TwitterUser>();
	private ArrayList<TwitterUser>neighborhood = new ArrayList<TwitterUser>();
	
	public int getUsersID() 
	{
		return usersID;
	}
	
	public void setUsersID(int usersID) 
	{
		this.usersID = usersID;
	}
	
	public ArrayList<TwitterUser> getFollowers() 
	{
		return followers;
	}
	
	public void setFollowers(ArrayList<TwitterUser> followers) 
	{
		this.followers = followers;
	}
	
	public void addFollowers(TwitterUser parameter)
	{
		followers.add(parameter);
	}
	
	
	// return 1 means > . returns -1 means <.returns 0 means equivallent.
	@Override
	public int compareTo(TwitterUser twitterUserObj) 
	{
		if (twitterUserObj.usersID > twitterUserObj.getUsersID())
		{
			return 1;
		}
		else if (twitterUserObj.usersID < twitterUserObj.getUsersID())
		{
			return -1;
		}
		else
		{
			return 0;
		}
		
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + usersID;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		TwitterUser other = (TwitterUser) obj;
		if (usersID != other.usersID)
		{
			return false;
		}
		return true;
	}

	// I'm creating a TwitterUser object that I can assign to the usersID field variable.
	// Then Icheck to see if the followers array list equals nothing.
	// Then I make a new array list casting the followers array list and cloning it.
	// Then I use the userObj I created to make the followers this new hard clone copy.
	@SuppressWarnings("unchecked")
	public TwitterUser clone() throws CloneNotSupportedException
	{
		TwitterUser clonedObj = new TwitterUser(0);
		
		ArrayList<TwitterUser> clonedFollowersOfUsers = new ArrayList<>();
		
		clonedObj.usersID = this.usersID;
		
		if(this.followers != null)
		{
			clonedFollowersOfUsers = (ArrayList<TwitterUser>) this.followers.clone();
			
			clonedObj.setFollowers(clonedFollowersOfUsers);
		}
		
		return clonedObj;
	}
	// Recursive method.
	public ArrayList<TwitterUser> getNeighborhood(TwitterUser users, int depth)
	{
		if(depth > 0)
		{
			for(TwitterUser user: users.getFollowers())
			{
				if(neighborhood.contains(users))
				{
					getNeighborhood(user, depth -1);
				}
				else
				{
					neighborhood.add(user);
					getNeighborhood(user, depth -1);
				}
			}
		}
		return neighborhood;
	}
	// Using the Integer wrapper class because usersID is an int.
	// This makes a strong version of the users ID.
	public String toString()
	{
		return Integer.toString(usersID);
	}
	
	// Clears the neighborhood array list.
	public void clearNeighborhood()
	{
		neighborhood.clear();
	}
}
