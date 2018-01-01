package com.accolite.mini_au;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class License 
{
	public License(String date_Status_Effective, String license_Expiration_Date, String license_Issue_Date,
			String license_Class, String state_Code, String license_Class_Code, String license_Number) {
		super();
		Date_Status_Effective = date_Status_Effective;
		License_Expiration_Date = license_Expiration_Date;
		License_Issue_Date = license_Issue_Date;
		License_Class = license_Class;
		State_Code = state_Code;
		License_Class_Code = license_Class_Code;
		License_Number = license_Number;
	}
	String Date_Status_Effective, License_Expiration_Date, License_Issue_Date;
	String License_Class, State_Code;
	String License_Class_Code, License_Number, State_ID;
	void display()
	{
		System.out.println(Date_Status_Effective+" "+License_Expiration_Date+" "+License_Issue_Date+" "+License_Class+" "+State_Code+" "+License_Class_Code+" "+License_Number);
	}
	boolean sortL(License a)
	{
		if(State_Code.equals(a.State_Code) && License_Number.equals(a.License_Number) && Date_Status_Effective.equals(a.Date_Status_Effective)) return true;
		return false;
	}
}
class LOA
{
	public LOA(String lOA_Issue_Date, String lOA_Name, String lOA_Status) {
		super();
		LOA_Issue_Date = lOA_Issue_Date;
		LOA_Name = lOA_Name;
		LOA_Status = lOA_Status;
	}

	String LOA_Issue_Date,LOA_Name,LOA_Status;
	void display()
	{
		System.out.println(LOA_Issue_Date+" "+LOA_Name+" "+LOA_Status);
		
	}
	
}
public class XMLParser {
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException
	{
		HashMap<String, ArrayList<License>> hm=new HashMap<String, ArrayList<License>>();
		HashMap<String, ArrayList<License>> hm1=new HashMap<String, ArrayList<License>>();
		HashMap<String, ArrayList<LOA>> hm2=new HashMap<String, ArrayList<LOA>>();
		try
		{
			File inputFile = new File("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Java Training\\src\\com\\accolite\\mini_au\\First.xml");
			
			
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        NodeList nList = doc.getElementsByTagName("CSR_Report");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	        	
	            Node nNode = (Node) nList.item(temp);
	            Element element =(Element)nNode;
	            NodeList reportHeaderList=element.getElementsByTagName("CSR_Report_Header");
	            Node reportHeader=reportHeaderList.item(0);
	            
	            NodeList CSRReportBody=element.getElementsByTagName("CSR_Report_Body");
	            for(int m=0;m<CSRReportBody.getLength();m++)
	            {
	            	
	            	Node ReportBody=CSRReportBody.item(m);
	            	Element elem=(Element)ReportBody;
	            	NodeList CSRProducerList= elem.getElementsByTagName("CSR_Producer");
		            for(int t=0;t<CSRProducerList.getLength();t++)
		            {
		            	
		            	Node CSRPro = (Node) CSRProducerList.item(temp);
			            Element CSRProducer =(Element)CSRPro;
			            String NIPRNumber=CSRProducer.getAttribute("NIPR_Number");
			            
			            ArrayList<License> al=new ArrayList<License>();
			            NodeList licenseList=CSRProducer.getElementsByTagName("License");
			            for(int g=0;g<licenseList.getLength();g++)
			            {
			            
			            	Node Lic=(Node)licenseList.item(g);
			            	Element licenseElement=(Element)Lic;
			            	String date_status_effective=licenseElement.getAttribute("Date_Status_Effective");
			            	String License_Class=licenseElement.getAttribute("License_Class");
			            	String License_Class_Code=licenseElement.getAttribute("License_Class_Code");
			            	String License_Expiration_Date=licenseElement.getAttribute("License_Expiration_Date");
			            	String License_Issue_Date=licenseElement.getAttribute("License_Issue_Date");
			            	String License_Number=licenseElement.getAttribute("License_Number");
			            	String State_Code=licenseElement.getAttribute("State_Code");
			            	License l=new License(date_status_effective,License_Expiration_Date, License_Issue_Date, License_Class, State_Code,License_Class_Code,License_Number);
			            	al.add(l);
			            
			            	hm.put(NIPRNumber, al);
			           			            }
		            }
	            }
	         }
	         
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			File inputFile = new File("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Java Training\\src\\com\\accolite\\mini_au\\second.xml");
			
			
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        NodeList nList = doc.getElementsByTagName("CSR_Report");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	        	 
	            Node nNode = (Node) nList.item(temp);
	            Element element =(Element)nNode;
	            NodeList reportHeaderList=element.getElementsByTagName("CSR_Report_Header");
	            Node reportHeader=reportHeaderList.item(0);
	            
	            NodeList CSRReportBody=element.getElementsByTagName("CSR_Report_Body");
	            for(int m=0;m<CSRReportBody.getLength();m++)
	            {
	            	Node ReportBody=CSRReportBody.item(m);
	            	Element elem=(Element)ReportBody;
	            	NodeList CSRProducerList= elem.getElementsByTagName("CSR_Producer");
		            for(int t=0;t<CSRProducerList.getLength();t++)
		            {
		            	Node CSRPro = (Node) CSRProducerList.item(temp);
			            Element CSRProducer =(Element)CSRPro;
			            String NIPRNumber=CSRProducer.getAttribute("NIPR_Number");
			            
			            ArrayList<License> al=new ArrayList<License>();
			            NodeList licenseList=CSRProducer.getElementsByTagName("License");
			            for(int g=0;g<licenseList.getLength();g++)
			            {
			            	Node Lic=(Node)licenseList.item(g);
			            	Element licenseElement=(Element)Lic;
			            	String date_status_effective=licenseElement.getAttribute("Date_Status_Effective");
			            	String License_Class=licenseElement.getAttribute("License_Class");
			            	String License_Class_Code=licenseElement.getAttribute("License_Class_Code");
			            	String License_Expiration_Date=licenseElement.getAttribute("License_Expiration_Date");
			            	String License_Issue_Date=licenseElement.getAttribute("License_Issue_Date");
			            	String License_Number=licenseElement.getAttribute("License_Number");
			            	String State_Code=licenseElement.getAttribute("State_Code");
			            	License l=new License(date_status_effective,License_Expiration_Date, License_Issue_Date, License_Class, State_Code,License_Class_Code,License_Number);
			            	al.add(l);
			            	
			            	hm1.put(NIPRNumber, al);
			            	String licenseNumber = licenseElement.getAttribute("License_Number");
			            	NodeList LOAlist=licenseElement.getElementsByTagName("LOA");
			            	ArrayList<LOA> l2=new ArrayList<LOA>();
			            	for(int j=0;j<LOAlist.getLength();j++)
			            	{
			            		Node LOA=(Node)LOAlist.item(j);
			            		Element LOAelement=(Element)LOA;
			            		String LOA_Issue_Date=LOAelement.getAttribute("LOA_Issue_Date");
			            		String LOA_Name=LOAelement.getAttribute("LOA_Name");
			            		String status=LOAelement.getAttribute("LOA_Status");
			            		LOA l3=new LOA(LOA_Issue_Date,LOA_Name,status);
			            		l2.add(l3);
			            		hm2.put(licenseNumber, l2);
			            	}
			            }
		            }
	            }
	         }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		PrintWriter writer = new PrintWriter("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Java Training\\src\\com\\accolite\\mini_au\\merge.txt", "UTF-8");
		PrintWriter writerIn = new PrintWriter("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Java Training\\src\\com\\accolite\\mini_au\\invalidLOA.txt", "UTF-8");
		PrintWriter writerInL = new PrintWriter("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Java Training\\src\\com\\accolite\\mini_au\\invalidL.txt", "UTF-8");
		ArrayList<License> invalidL=new ArrayList<License>();
		ArrayList<LOA> invalidLOA=new ArrayList<LOA>();
		Set<String> keyset=hm.keySet(),keyset2=hm1.keySet();
		
		Iterator<String> i=keyset.iterator();
		while(i.hasNext())
		{
			String key=i.next();
			if(key==null)
			{
				invalidL.addAll(hm.get(key));
				continue;
			}
			if(keyset2.contains(key))
			{
				
				
				ArrayList<License> l1=hm.get(key);
				ArrayList<License> l2=hm1.get(key);
				for(int i1=0;i1<l1.size();i1++)
				{
					
					if((l1.get(i1)).sortL(l2.get(i1)))
					{
						
						//l1.get(i1).display();
						License lic=l1.get(i1);
						String License_Number=lic.License_Number, StateCode=lic.State_Code,EffectiveDate=lic.Date_Status_Effective;
						if(License_Number==null || StateCode==null || EffectiveDate==null) 
						{ 
							
							invalidL.add(lic);
							writerInL.println(lic.Date_Status_Effective+" "+lic.License_Class+" "+lic.License_Class_Code+" "+lic.License_Expiration_Date+" "+lic.License_Number+" "+lic.State_Code);
							continue;
						}
						ArrayList<LOA> l3=hm2.get(License_Number);
						Iterator k=l3.iterator();
						
						while(k.hasNext())
						{
							LOA j=(LOA) k.next();
							if(j.LOA_Issue_Date==null || j.LOA_Status==null|| j.LOA_Name==null)
							{
								invalidLOA.add(j);
								writerIn.println(j.LOA_Issue_Date+" "+j.LOA_Name+" "+j.LOA_Status);
								continue;
							}
							writer.print(key+" ");
							writer.print(l1.get(i1).Date_Status_Effective+ "  "+l1.get(i1).State_Code+" "+l1.get(i1).License_Number);
							writer.println(" "+j.LOA_Issue_Date+" "+j.LOA_Name+" "+j.LOA_Status);
						}
					}
				}
				
			}
		}
		writer.close();
	}
}
