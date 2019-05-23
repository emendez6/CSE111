import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

class connect{
	public static Connection Connect(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:Phase2");
	
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
		return(conn);
	}
}

class phase3 extends connect{

	static Scanner scr = new Scanner(System.in);
	static Scanner scnr = new Scanner(System.in);
	public static int j;
	public static String petstorename;
	public static String animalname;
	public static String animaltype;
	public static String animallocation;
	public static String animalgender;
	public static int animalquantity;
	public static int total;
	public static int maxamnt;
	static boolean valid;//any input is valid
	static boolean v;
	static boolean vt;
	static boolean vlc;
	public static int k;

	Connection conn = connect.Connect();


public void p4(String name)
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT * FROM Animals, facts WHERE a_name IN (SELECT l_name from petStoreView GROUP BY l_name) AND a_name = '"+name+"' AND f_name = a_name GROUP BY a_name");
		while(r.next())
		{
			System.out.println("");
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.print("Type: ");
			System.out.println(r.getString("a_type"));
			System.out.print("Color: ");
			System.out.println(r.getString("a_color"));
			System.out.print("Female Height: ");
			System.out.println(r.getString("a_fheight"));
			System.out.print("Female Weight: ");
			System.out.println(r.getString("a_fweight"));
			System.out.print("Male Height: ");
			System.out.println(r.getString("a_mheight"));
			System.out.print("Male Weight: ");
			System.out.println(r.getString("a_mweight"));
			System.out.print("Fact: ");
			System.out.println(r.getString("f_fact"));
			System.out.println("______________________");			

		}
		r.close();
	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void sum(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial, String petstore) {
    int s = 0;
       if (s == target){
            System.out.println("sum("+Arrays.toString(partial.toArray())+")="+target);
		
			try
			{
				Statement statement = conn.createStatement();
				ResultSet r2 = statement.executeQuery("SELECT l_name FROM petstoreView WHERE p_price ='"+target+"' AND p_name = '"+petstore+"'");				
				
				System.out.print(r2.getString("l_name"));
				System.out.print(" ");
			}
			
				
			catch(SQLException e){
					System.err.println(e.getMessage());
			}
			

		
		}
	

       if (s >= target)
            return;
     

  
	

}



public void maxPricep(int maxamount,String storename)
{ArrayList<Integer> result = new ArrayList<Integer>();
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r1 = statement.executeQuery("SELECT p_price FROM petstoreView WHERE p_name = '"+storename+"'");

		while(r1.next()){			
			if(maxamount >= r1.getInt("p_price"))
			{	
				ResultSetMetaData rsmd = r1.getMetaData();
				int columnum = rsmd.getColumnCount();
				result.add(r1.getInt(1));
			}
		}		
		r1.close();
		
		
	}
	
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}


public void updatep(int quantity, String name, String storename)
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT p_quantity FROM petstoreView WHERE p_name = '"+storename+"' AND l_name = '"+name+"' ");
		int qnty = r.getInt("p_quantity");
		qnty -= quantity;
		ResultSet rs = statement.executeQuery("UPDATE Petstore SET p_quantity = '"+qnty+"' WHERE p_name = '"+storename+"' AND p_domestickey = (SELECT l_domestickey FROM Lifestyle WHERE l_name = '"+name+"')");

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
	
}

public void p3(String name, int quantity, String storename)
{
	try
	{
		total = 0;
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT p_price FROM petstoreView WHERE p_name = '"+storename+"' AND l_name = '"+name+"' ");
		int sumprice = r.getInt("p_price");
		for(int h = 1; h <= quantity; h++)
		{
			total += sumprice;
		}
		System.out.println(total);

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}

public void p2(String storename)
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT l_name, p_quantity FROM petstoreView WHERE p_name = '"+storename+"' ");
		while(r.next())
		{
			System.out.print(r.getString("l_name"));
			System.out.print("-");
			System.out.println(r.getString("p_quantity"));			
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void p1()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT DISTINCT p_name FROM Petstore");
		while(r.next())
		{			
			System.out.println(r.getString("p_name"));
		
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void a3()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT z_name, a_name, aq_name FROM Animals, Zoos, Aquarium, Lifestyle WHERE l_captivitykey = z_captivitykey AND l_name = a_name AND l_captivitykey = aq_captivitykey");
		while(r.next())
		{			
			System.out.print("Zoo: ");	
			System.out.println(r.getString("z_name"));
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.print("Aquarium: ");
			System.out.println(r.getString("aq_name"));
			System.out.println("");
			System.out.println("______________________");
		
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void a2()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT a_name, aq_name FROM Animals, Aquarium, Lifestyle WHERE l_name = a_name AND l_captivitykey = aq_captivitykey");
		while(r.next())
		{			
			System.out.print("Aquarium: ");
			System.out.println(r.getString("aq_name"));
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.println("______________________");
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void a12()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT * FROM View1,aquariumView, lcView WHERE View1.a_name = l_name AND l_name = lcView.a_name GROUP BY l_name");
		while(r.next())
		{			
			System.out.println("");
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.print("Type: ");
			System.out.println(r.getString("a_type"));
			System.out.print("Color: ");
			System.out.println(r.getString("a_color"));
			System.out.print("Female Height: ");
			System.out.println(r.getString("a_fheight"));
			System.out.print("Female Weight: ");
			System.out.println(r.getString("a_fweight"));
			System.out.print("Male Height: ");
			System.out.println(r.getString("a_mheight"));
			System.out.print("Male Weight: ");
			System.out.println(r.getString("a_mweight"));
			System.out.print("Location: ");
			System.out.println(r.getString("lc_name"));
			System.out.print("Fact: ");
			System.out.println(r.getString("f_fact"));
			System.out.println("______________________");
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void a1(String type, String location, String gender)
{	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT DISTINCT a_type FROM View1, aquariumView WHERE l_name = a_name");
		vt = false;
		vlc = false;
		while(rs.next())
		{
			if(type.equals(rs.getString("a_type")))
			{
				vt = true;
			}
		}
		rs.close();
		ResultSet rst = statement.executeQuery("SELECT DISTINCT lc_name FROM lcView");
		while(rst.next())
		{
			if(location.equals(rst.getString("lc_name")))
			{
				vlc = true;
			}
			
		}
		rst.close();
		if(vt == true && vlc == true)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView,aquariumView, lcView WHERE maleview.a_name = lcView.a_name AND a_type = '"+type+"' AND lc_name = '"+location+"' AND maleview.a_name = l_name GROUP BY l_name");
				while(r.next())
				{			
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");
				}
				r.close();
				
			}
			if(gender.equals("F") || gender.equals("f"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView, aquariumView,lcView WHERE femaleview.a_name = lcView.a_name AND a_type = '"+type+"' AND lc_name = '"+location+"' AND l_name = femaleView.a_name GROUP BY l_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");				
				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM View1, lcView,aquariumview WHERE a_type = '"+type+"' AND lc_name = '"+location+"' AND View1.a_name = l_name AND l_name = lcView.a_name GROUP BY l_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Female Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Female Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Male Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Male Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");	
				}
				r.close();
			}
		}
		if(vt == true && vlc == false)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView,aquariumView,lcView WHERE a_type = '"+type+"' AND l_name = maleView.a_name AND  maleView.a_name = lcView.a_name GROUP BY a_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			
				}
				r.close();
				
			}
			if(gender.equals("F") || gender.equals("f"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView,aquariumView,lcView WHERE a_type = '"+type+"' AND l_name = femaleView.a_name AND femaileView = lcView.a_name GROUP BY femaleView.a_name");
				while(r.next())
				{			
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");
				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM View1,aquariumView, lcView WHERE a_type = '"+type+"' AND View1.a_name = l_name AND View1.a_name = lcView.a_name GROUP BY l_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Female Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Female Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Male Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Male Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");
				}
				r.close();
			}
		}
		if(vt == false && vlc == true)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView, lcView, aquariumView WHERE maleView.a_name = lcView.a_name AND lc_name = '"+location+"' AND l_name = maleView.a_name GROUP BY maleView.a_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			
			
				}
				r.close();
				
			}
			if(gender.equals("F") || gender.equals("f"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView,lcView,aquariumView WHERE femaleView.a_name = lcView.a_name AND lc_name = '"+location+"' AND l_name = femaleView.a_name GROUP BY femaleView.a_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			
			
				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM View1,aquariumView,lcView WHERE View1.a_name = c_name AND l_name = lcView.a_name AND lc_name = '"+location+"' GROUP BY c_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Female Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Female Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Male Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Male Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			
			
				}
				r.close();
			}
		}
		if(vt == false && vlc == false)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView,aquariumView, lcView WHERE maleView.a_name = l_name AND lcView.a_name = l_name GROUP BY l_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");
		
				}
				r.close();
						
			}
			if(gender.equals("F") || gender.equals("f"))
			{	
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView,aquariumView,lcView WHERE femaleView.a_name = l_name AND lcView.a_name = l_name GROUP BY l_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");
				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				a12();
			}
		}
	
	}
	catch(SQLException e){
	System.err.println(e.getMessage());
	}

}
public void z3()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT z_name, a_name, aq_name FROM Animals, Zoos, Aquarium, Lifestyle WHERE l_captivitykey = z_captivitykey AND l_name = a_name AND l_captivitykey = aq_captivitykey");		
		while(r.next())
		{	
			System.out.print("Zoo: ");	
			System.out.println(r.getString("z_name"));
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.print("Aquarium: ");
			System.out.println(r.getString("aq_name"));
			System.out.println("");
			System.out.println("______________________");

		
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}

public void z2()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT a_name, z_name FROM Animals, Zoos, Lifestyle WHERE l_captivitykey = z_captivitykey AND a_name = l_name");
		while(r.next())
		{	
			System.out.print("Zoo: ");
			System.out.println(r.getString("z_name"));
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.println("______________________");
			
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void z12()
{
	try
	{
		Statement statement = conn.createStatement();
		ResultSet r = statement.executeQuery("SELECT * FROM View1,zooView,lcView WHERE View1.a_name = c_name AND lcView.a_name = c_name GROUP BY c_name");
		while(r.next())
		{
			System.out.println("");
			System.out.print("Name: ");
			System.out.println(r.getString("a_name"));
			System.out.print("Type: ");
			System.out.println(r.getString("a_type"));
			System.out.print("Color: ");
			System.out.println(r.getString("a_color"));
			System.out.print("Female Height: ");
			System.out.println(r.getString("a_fheight"));
			System.out.print("Female Weight: ");
			System.out.println(r.getString("a_fweight"));
			System.out.print("Male Height: ");
			System.out.println(r.getString("a_mheight"));
			System.out.print("Male Weight: ");
			System.out.println(r.getString("a_mweight"));
			System.out.print("Location: ");
			System.out.println(r.getString("lc_name"));
			System.out.print("Fact: ");
			System.out.println(r.getString("f_fact"));
			System.out.println("______________________");
		}
		r.close();

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}

public void z1(String type, String location, String gender)
{
	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT DISTINCT a_type FROM View1, zooView WHERE c_name = a_name");
		vt = false;//if type is in table
		vlc = false;//if location is in table
		while(rs.next())
		{
			if(type.equals(rs.getString("a_type")))
			{
				vt = true;
			}
		}
		rs.close();
		ResultSet rst = statement.executeQuery("SELECT DISTINCT lc_name FROM lcView");
		while(rst.next())
		{
			if(location.equals(rst.getString("lc_name")))
			{
				vlc = true;
			}
			
		}
		rst.close();

		if(vt == true && vlc == true)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView, lcView WHERE maleview.a_name = lcView.a_name AND a_type = '"+type+"' AND lc_name = '"+location+"' GROUP BY maleView.a_name");

				while(r.next())
				{				
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");
				}
				r.close();
				
			}
			if(gender.equals("F") || gender.equals("f"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView, lcView WHERE femaleview.a_name = lcView.a_name AND a_type = '"+type+"' AND lc_name = '"+location+"' GROUP BY femaleView.a_name");

				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			

				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM View1, lcView,zooView WHERE a_type = '"+type+"' AND lc_name = '"+location+"' AND View1.a_name = c_name AND c_name = lcView.a_name GROUP BY c_name");

				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Female Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Female Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Male Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Male Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			

				}
				r.close();
			}
		}
		if(vt == true && vlc == false)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView,lcView WHERE a_type = '"+type+"' AND maleView.a_name = lcView.a_name GROUP BY maleView.a_name");

				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			

				}
				r.close();
				
			}
			if(gender.equals("F") || gender.equals("f"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView,lcView WHERE a_type = '"+type+"' AND lcView.a_name = femaleView.a_name GROUP BY femaleView.a_name");

				while(r.next())
				{	
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			

				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM View1,zooView,lcView WHERE a_type = '"+type+"' AND View1.a_name = c_name AND c_name = lcView.a_name GROUP BY c_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Female Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Female Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Male Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Male Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");				

				}
				r.close();
			}
		}
		if(vt == false && vlc == true)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView, lcView WHERE maleView.a_name = lcView.a_name AND lc_name = '"+location+"' GROUP BY maleView.a_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			
				}
				r.close();
				
			}
			if(gender.equals("F") || gender.equals("f"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView,lcView WHERE femaleView.a_name = lcView.a_name AND lc_name = '"+location+"' GROUP BY femaleView.a_name");
				while(r.next())
				{	
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");		
			
				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM View1,zooView,lcView WHERE View1.a_name = c_name AND c_name = lcView.a_name AND lc_name = '"+location+"' GROUP BY c_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Female Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Female Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Male Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Male Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");			
			
				}
				r.close();
			}
		}
		if(vt == false && vlc == false)
		{
			if(gender.equals("M") || gender.equals("m"))
			{
				ResultSet r = statement.executeQuery("SELECT * FROM maleView,lcView WHERE maleView.a_name = lcView.a_name GROUP BY maleView.a_name");
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_mheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_mweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");		
				}
				r.close();
						
			}
			if(gender.equals("F") || gender.equals("f"))
			{	
				ResultSet r = statement.executeQuery("SELECT * FROM femaleView, lcView WHERE femaleView.a_name = lcView.a_name GROUP BY femaleView.a_name");	
				while(r.next())
				{
					System.out.println("");
					System.out.print("Name: ");
					System.out.println(r.getString("a_name"));
					System.out.print("Type: ");
					System.out.println(r.getString("a_type"));
					System.out.print("Color: ");
					System.out.println(r.getString("a_color"));
					System.out.print("Height: ");
					System.out.println(r.getString("a_fheight"));
					System.out.print("Weight: ");
					System.out.println(r.getString("a_fweight"));
					System.out.print("Location: ");
					System.out.println(r.getString("lc_name"));
					System.out.print("Fact: ");
					System.out.println(r.getString("f_fact"));
					System.out.println("______________________");		
				}
				r.close();
			}
			if(!gender.equals("F") && !gender.equals("f") && !gender.equals("M") && !gender.equals("m"))
			{
				z12();
			}
		}
	
	}
	catch(SQLException e){
	System.err.println(e.getMessage());
	}
	
}

public void f1()
{
	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT f_fact FROM facts ORDER BY RANDOM()");
		System.out.println("");		
		System.out.println(rs.getString("f_fact"));

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
	
}

public void f2(){
	try{		
		Statement statement = conn.createStatement();
		System.out.println("Enter animal name:");
		animalname = scnr.nextLine();
		ResultSet rst = statement.executeQuery("SELECT f_name FROM facts");
		v = false;
		while(rst.next()){
		if(animalname.equals(rst.getString("f_name")))
		{	
			ResultSet rs = statement.executeQuery("SELECT f_fact, f_name FROM facts WHERE '"+animalname+"' = f_name");
			v = true;	
			while(rs.next())
			{
				System.out.println("");
				System.out.println(rs.getString("f_fact"));
			}
			rs.close();
		}}
		if(v == false)
		{
			System.out.println("");
			System.out.println("Invalid animal entry, try again");
		}

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}

public void f3(){
	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT f_fact FROM facts, animals WHERE a_name = f_name");
		while(rs.next())
		{
			System.out.println("");
			System.out.println(rs.getString("f_fact"));
		}
		rs.close();
	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}

public void j1()
{
	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT j_joke FROM jokes ORDER BY RANDOM()");
		System.out.println(rs.getString("j_joke"));

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
	
}

public void j2(){
	try{		
		Statement statement = conn.createStatement();
		System.out.println("Enter animal name:");
		animalname = scnr.nextLine();
		ResultSet rst = statement.executeQuery("SELECT j_name FROM jokes");
		v = false;
		while(rst.next()){
		if(animalname.equals(rst.getString("j_name")))
		{	
			ResultSet rs = statement.executeQuery("SELECT j_joke, j_name FROM jokes WHERE '"+animalname+"' = j_name");
			v = true;	
			while(rs.next())
			{
				System.out.println("");
				System.out.println(rs.getString("j_joke"));
			}
			rs.close();
		}}
		if(v == false)
		{
			System.out.println("Invalid animal entry, try again");
		}

	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
public void j3(){
	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT j_joke FROM jokes, animals WHERE a_name = j_name");
		while(rs.next())
		{
			System.out.println("");
			System.out.println(rs.getString("j_joke"));
		}
		rs.close();
	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
}
	
public void generic(){

	try{		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Animals GROUP BY a_name");		
		while(rs.next()){
			System.out.println("");
			System.out.print("Name: ");
			System.out.println(rs.getString("a_name"));
			System.out.print("Color: ");
			System.out.println(rs.getString("a_color"));
			System.out.print("Type: ");
			System.out.println(rs.getString("a_type"));
			System.out.print("Female Height: ");
			System.out.println(rs.getString("a_fheight"));
			System.out.print("Female Weight: ");
			System.out.println(rs.getString("a_fweight"));
			System.out.print("Male Height: ");
			System.out.println(rs.getString("a_mheight"));
			System.out.print("Male Weight: ");
			System.out.println(rs.getString("a_mweight"));
			//System.out.print("Fact: ");
			//System.out.println(r.getString("f_fact"));
			System.out.println("______________________");


		}
		rs.close();
	}
	catch(SQLException e){
		System.err.println(e.getMessage());
	}
	System.out.println(" ");
	System.out.println("To return to previous menu press 7");
	j = scr.nextInt();
	if(j == 7)
	{
		menu();
	}
	else
	{
		System.out.println("Invalid input, try again");
	}
	
	

}
public void petstore(){
	System.out.println("");		
	System.out.println("  Type one of the following Pet Stores, press 7 to return to main menu ");
	System.out.println("-----------------------------------------------------------------------");
	System.out.println("");
	valid = true;
	p1();//print name of stores
	System.out.println("---------------------------------------");
	System.out.println("");
	System.out.println("Enter the name of the petstore");
	petstorename = scnr.nextLine();

	while(valid == true)
	{		
		if(petstorename.equals("7"))
		{
			menu();
			valid = false;
		}
		if(petstorename.equals("Armstrong Pet World") || petstorename.equals("Pet Smart") || petstorename.equals("Mrs & Mr Garret Pet Shop")){
			System.out.println("");
			System.out.println("  Here is a list of the animals and the quanties");
			System.out.println("---------------------------------------------------");
			System.out.println("");			
			p2(petstorename);//print name and quantity of animals in specific store
			System.out.println("--------------------------------");
			System.out.println("");
			System.out.println("Would you like to :");
			System.out.println("1.Purchase an animal or ");
			System.out.println("2.View the characteristic of a desired animal?");
			j = scr.nextInt();

			if(j == 1)
			{
				System.out.println("How would you like to determine your purchase?");
				System.out.println("1.Enter animal name and quantity");
				System.out.println("2.Enter max amount of money you want to spend and recieve a list of options ");
				j = scr.nextInt();

				if(j == 1)
				{
					System.out.println("Enter animal name:");
					animalname = scnr.nextLine();
					System.out.println("Enter the quantity:");
					animalquantity = scr.nextInt();
					p3(animalname,animalquantity,petstorename);//purchase
					updatep(animalquantity, animalname, petstorename);//update
					System.out.println("Thank you for your purchase :)");
					menu();
				}
				if(j == 2)
				{
					System.out.println("Enter max amount");
					maxamnt = scr.nextInt();
					maxPricep(maxamnt,petstorename);//print the options
					System.out.println();
					System.out.println("Enter the total amount of animals you want to buy:");
					total = scr.nextInt();
					for(int k = 0; k <= total; k++)
					{
						System.out.println("Enter animal name:");
						animalname = scnr.nextLine();
						System.out.println("Enter the quantity:");
						animalquantity = scr.nextInt();
						total -= animalquantity;
						updatep(animalquantity, animalname, petstorename);
					}
					System.out.println("Thank you for your purchase :)");
					menu();
				}

				
						
			}
			if(j == 2)
			{
				System.out.println("");
				System.out.println("Enter animal name");
				animalname = scnr.nextLine();
				p4(animalname);
				System.out.println("");
				j = scr.nextInt();
			}
			if(j == 7)
			{
				petstore();
				valid = false;
			}

			
		}
		else
		{
			System.out.println("Invalid pet store, please try again");
			petstore();	
		}
	}
	
}
public void aquarium(){
	System.out.println("");		
	System.out.println("  Select one of the following, press 7 to return to main menu ");
	System.out.println("---------------------------------------------------------------");
	System.out.println("");
	System.out.println("1.Learn about captive animals in the aquariums");
	System.out.println("2.View the list of animals in the aquariums");
	System.out.println("3.List the animals in an aquarium as well as in a zoo");
	valid = true;
	while (valid == true)
	{
		j = scr.nextInt();
		if (j == 1)
		{
			System.out.println("");
			System.out.println("  Select one of the following:");
			System.out.println("--------------------------------");
			System.out.println("");
			System.out.println("1.Input type, location, gender");
			System.out.println("2.Recieve all information regarding captive animals");	
			k = scr.nextInt();
			if(k == 1)
			{
				
				System.out.println("");
				System.out.println("Type: ");
				animaltype = scnr.nextLine();
				System.out.println("location: ");
				animallocation = scnr.nextLine();
				System.out.println("Gender: ");
				animalgender = scnr.nextLine();
				a1(animaltype, animallocation, animalgender);
			}
			if(k == 2)
			{
				a12();
			}
			if(k != 1 && k != 2)
			{ 
				System.out.println("Invalid entry, try again");
			}
		}
		if (j == 2)
		{
			a2();
		}
		if (j == 3)
		{
			a3();
		}
		if (j == 7)
		{
			menu();
			valid = false;
		}
		if (j != 7 && j != 1 && j != 2 && j != 3)
		{
			System.out.println("Entry is invalid, try again");
		}
		
	}
}

public void zoo(){
	System.out.println("");		
	System.out.println("  Select one of the following, press 7 to return to main menu ");
	System.out.println("---------------------------------------------------------------");
	System.out.println("");
	System.out.println("1.Learn about the captive animals");
	System.out.println("2.View the list of animals in the zoos");
	System.out.println("3.List the animals in a zoo as well as in an aquarium");
	valid = true;
	
	while (valid == true)
	{
		j = scr.nextInt();
		if (j == 1)
		{
			System.out.println("");
			System.out.println("  Select one of the following:");
			System.out.println("--------------------------------");
			System.out.println("");
			System.out.println("1.Input type, location, gender");
			System.out.println("2.Recieve all information regarding captive animals");	
			k = scr.nextInt();
			if(k == 1)
			{
				System.out.println("");
				System.out.println("Type: ");
				animaltype = scnr.nextLine();
				System.out.println("location: ");
				animallocation = scnr.nextLine();
				System.out.println("Gender: ");
				animalgender = scnr.nextLine();
				z1(animaltype, animallocation, animalgender);
			}
			if(k == 2)
			{
				z12();
			}
			if(k != 1&& k != 2)
			{
				System.out.println("Invalid entry, try again");
			}
		}
		if (j == 2)
		{
			z2();
		}
		if (j == 3)
		{
			z3();
		}
		if (j == 7)
		{
			menu();
			valid = false;
		}
		if (j != 7 && j != 1 && j != 2 && j != 3)
		{
			System.out.println("Entry is invalid, try again");
		}
	}	
}

public void funfacts(){
	System.out.println("");		
	System.out.println("  Select one of the following, press 7 to return to main menu ");
	System.out.println("---------------------------------------------------------------");
	System.out.println("");
	System.out.println("1.Read a fun fact about any animal");
	System.out.println("2.Read a fun fact about a specific animal");
	System.out.println("3.Read all the fun facts available");
	valid = true;
	
	while (valid == true)
	{
		j = scr.nextInt();
		if (j == 1)
		{
			System.out.println("");
			f1();
		}
		if (j == 2)
		{
			System.out.println("");
			f2();
		}
		if (j == 3)
		{
			System.out.println("");
			f3();
		}
		if (j == 7)
		{
			menu();
			valid = false;
		}
		if (j != 7 && j != 1 && j != 2 && j != 3)
		{
			System.out.println("Entry is invalid, try again");
		}
	}

}

public void joke(){
	System.out.println("");		
	System.out.println("  Select one of the following, press 7 to return to main menu ");
	System.out.println("---------------------------------------------------------------");
	System.out.println("");
	System.out.println("1.Read a joke about any animal");
	System.out.println("2.Read a joke about a specific animal");
	System.out.println("3.Read all the jokes available");
	valid = true;
	
	while (valid == true)
	{
		j = scr.nextInt();
		if (j == 1)
		{
			System.out.println("");
			j1();
		}
		if (j == 2)
		{
			System.out.println("");
			j2();
		}
		if (j == 3)
		{
			System.out.println("");
			j3();
		}
		if (j == 7)
		{
			menu();
			valid = false;
		}
		if (j != 7 && j != 1 && j != 2 && j != 3 && j != 8)
		{
			System.out.println("Entry is invalid, try again");
		}
		if(j == 8)
		{
			joke();
		}
	}

}

public void menu(){
	
	System.out.println("|-------------------------------------------|");
	System.out.println("|                                           |");	
	System.out.println("|     Welcome to the world of Animals!      |");
	System.out.println("|                                           |");
	System.out.println("|-------------------------------------------|");
	System.out.println("Select one of the following options, to exit press 0");
	System.out.println("");
	System.out.println("1.Read a joke");
	System.out.println("2.Read fun facts");
	System.out.println("3.Visit a Zoo");
	System.out.println("4.Visit an Aquarium");
	System.out.println("5.Visit a petstore");
	System.out.println("6.Discover the characteristics about animals");
	j = scr.nextInt();
	
	while(j != 0)
	{
		if(j == 1)
		{
			joke();
		}
		if(j == 2)
		{
			funfacts();
		}
		if(j == 3)
		{
			zoo();	
		}
		if(j == 4)
		{
			aquarium();
		}
		if(j == 5)
		{
			petstore();
		}
		if(j == 6)
		{
			generic();
		}
		if(j != 1 && j != 2 && j != 3 && j != 4 && j != 5 && j != 6 && j!= 0)
		{
			System.out.println("Invalid entry, try again");
			j = scr.nextInt();
		}
	}
	if(j == 0)
	{
		System.exit(0);
	}
	
}
}
public class main1 extends phase3
{
	public static void main(String[] args){
		main1 obj = new main1();
		obj.menu();
	
	}
}

