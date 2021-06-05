package com.compname.prname.mod1;

import java.sql.*;
import java.util.Scanner;

public class Meths
{
	public int generateNewvalue(int oldValue)	
	{
        int newValue = oldValue + 1;
        int sum = 0;
        int n;
        do{
            sum = 0;
            int m = newValue;
            while(m > 0)
            {
                n = m % 10;
                sum = sum + n;
                m = m / 10;
            }
            newValue++;
        }while(sum%10 != 0 && sum != 1);
        newValue = newValue - 1;
        return newValue;
	}
	public void updateValue()
	{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Meths k=new Meths();
			String a=k.getValue();
			System.out.println("Updateing value from "+a);
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=1234");
			pstmt=con.prepareStatement("update newdb.tn1 set Value=(?) where CategoryCode=(?)");
			Meths f=new Meths();
			
			int u=f.generateNewvalue(Integer.parseInt(a));
			pstmt.setInt(1,u);
			pstmt.setString(2,a);
			pstmt.execute();
			System.out.println("Updated value to "+u);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
	

	public static String getValue()
	{
		Connection con=null;
		String m="";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Scanner s=new Scanner(System.in);
		System.out.println("Enter catcod ");				
		String catcod=s.next();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=1234");
			pstmt=con.prepareStatement("select * from newdb.tn1 where CategoryCode=?");
//			System.out.println("Enter catcod ");				
//			String catcod=s.next();
			pstmt.setString(1,catcod);	
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				String value=rs.getString(2);
				System.out.println("CategoryCode "+catcod+" value "+value);
				m=value;
			}
			else
				System.err.println("No record found");
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				rs.close();
				s.close();
				pstmt.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return m;	
	}
	
}
