import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Driver 
{
	public static void main(String[] args) 
	{
		int userId,followerId;
		int prevId = -1;
		
		// I passed in -1 because 0 is a valid user number.
		TwitterUser twitterObj = new TwitterUser(-1);
		TwitterUser followerObj;
		
		// allUsers arraylist created so I can control specific users in the array list for output.
		ArrayList <TwitterUser> allUsers = new ArrayList<TwitterUser>();
		ArrayList <TwitterUser> forNeighborhood = new ArrayList<TwitterUser>();
		
		try
		{
			// I'm creating a reading in object.
			FileReader fileRead = new FileReader("social_network.edgelist");
			try
			{
				BufferedReader reader = new BufferedReader(fileRead);
				String strLine = reader.readLine();
				
				// I loop through and split the lines between the users and their followers.
				// userId has all the user's IDs stored in that at position zero
				// and followerId does the same thing at postion 1.
				while(strLine != null)
				{
					System.out.print(strLine);
					String[] parts = strLine.split(" ");
					userId = Integer.parseInt(parts[0]);
					followerId = Integer.parseInt(parts[1]);
					
					// If the user id isn't the previous one then I print
					if(userId != prevId)
					{
						System.out.println(" New ID");
						
						followerObj = new TwitterUser(followerId);
						twitterObj = new TwitterUser(userId);
						
						twitterObj.addFollowers(followerObj);
						allUsers.add(twitterObj);
						System.out.println();
					}
					else
					{
						System.out.println(" Same ID");
						
						followerObj = new TwitterUser(followerId);
						
						twitterObj.addFollowers(followerObj);
						System.out.println();
					} 
					prevId = userId;
					strLine = reader.readLine();
				}
				reader.close();
			}
			catch(IOException ex)
			{
				System.out.println("An Issue occured at runtime.");
			}
		 }
		 catch (FileNotFoundException ex)
		 {
			 System.out.println("I couldn't find the file.");
		 }
		
		// Console output to test clone and getNeighborhood stuff.
		System.out.println("Clone testing case.");
		System.out.println("User 0 followers");
		System.out.println();
		System.out.println(allUsers.get(0).getFollowers().toString());
		
		try 
		{
			TwitterUser clonedThing = allUsers.get(0).clone();
			
			System.out.println("Emptying User 0's list.");
			System.out.println();
			
			clonedThing.setFollowers(new ArrayList<TwitterUser>());
			
			System.out.println(clonedThing.getFollowers().toString());
			System.out.println("User 0 cloned followers.");
			System.out.println(allUsers.get(0).getFollowers().toString());
			System.out.println();
		} 
		catch (CloneNotSupportedException ex) 
		{
			System.out.println("The user was unable to be deep copied.");
		}
		
		System.out.println("getNeighborhood testing case.");
		System.out.println();
		System.out.println("Lets test getNeighborhood at a depth of 1.");
		System.out.println("Getting neighborhood user: " + twitterObj.getUsersID() + " depth: 1");
		forNeighborhood = twitterObj.getNeighborhood(twitterObj, 1);
		System.out.println(forNeighborhood);
		System.out.println();
		
		
		twitterObj.clearNeighborhood();
		
		
		System.out.println("Lets test getNeighborhood at a depth of 1.");
		System.out.println("Getting neighborhood user: " + twitterObj.getUsersID() + " depth: 2");
		forNeighborhood = twitterObj.getNeighborhood(twitterObj, 2);
		System.out.println(forNeighborhood);
		System.out.println();
		
		twitterObj.clearNeighborhood();

	}
}