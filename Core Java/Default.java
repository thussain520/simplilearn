import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Lockers {
	Path path = Paths.get("./LockersFiles");
	public void createFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("You have selected option 1 \nPlease enter a filename you want to create");
		String filename = sc.nextLine();
		if(filename != "") {
			File file = new File(path+"/"+filename);
			try {
				if(file.exists())
				{
					System.out.println("The file is already exists, please try another file");
				}
				else
				{
					if(file.createNewFile())
					{
						System.out.println("File Created Successfully....");
						System.out.println("Do you want to write something to the file?\nPress Yes or No.");
						String ch = sc.nextLine();
						if(ch.equals("Yes")||ch.equals("yes"))
						{
							String data = sc.nextLine();
							try
							{
								Files.writeString(file.toPath(), data);
								System.out.println("Data is written to the file successfully!");
								System.out.println("Do you want to read the file?\nPress Yes or No");
								String ch1 = sc.nextLine();
								if(ch1.equals("Yes")||ch1.equals("yes"))
								{
									System.out.println(Files.readString(file.toPath()));
								}
								else
								{
									System.out.println("Thanks for using our application");
								}
							}
							catch(Exception e) {
								System.out.println("File isn't exist or not Writable");
							}
						
						}
						else
						{
							System.out.println("Thanks for using this application");
						}
					}
				}
			} catch (IOException e) {
				System.out.println("There is an error to create a new File, please try again..");
				e.printStackTrace();
			}
		}
		else
			System.out.println("You have entered a blank filename, please input a valid filename!");
	}
	public void displayFiles() {
		File files = new File(path.toString());
		String[] filesList = files.list();
		List<String> fileList = Arrays.asList(filesList);  
		Collections.sort(fileList);
		
		if(!fileList.isEmpty())
		{
			for (String file: fileList) {
				System.out.println(file);
			}
		}
		else
		{
			System.out.println("There is no file available to display");
		}
	}
	public void deleteFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("You have selected option 2 \nPlease enter a filename you want to delete");
		String filename = sc.nextLine();
		if(filename != "") {
			File file = new File(path + "/" + filename);
			if(file.exists())
			{
				if(file.delete())
				{
					System.out.println("File Deleted Successfully...");
				}
			}
			else
			{
				System.out.println("There is no such file exists, please check your file name!");
			}
		}
	}
	public void searchFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("You have selected option 3 \nPlease enter a filename you want to search");
		String filename = sc.nextLine();
		if(filename != "") {
			int count = 0;
			File files = new File(path.toString());
			String[] filesList = files.list();
			for (String file: filesList) {
				if(file.equals(filename)) {
					System.out.println("File found");
					count++;
				}
			}
			if(count==0) {
				System.out.println("There is no such file found");
			}
		}
	}
	public void userInterface(Lockers locFiles)
	{
		Scanner sc = new Scanner(System.in);
		int choice =0;
		do {
			System.out.println("***Welcome to User Interface Menu***\nPlease select an option from the following...");
			System.out.println("1. Add a user specified file");
			System.out.println("2. Delete a user specified file");
			System.out.println("3. Search a user specified file");
			System.out.println("4. Back to main menu");
			if(sc.hasNextInt()) {
				choice = sc.nextInt();	
				switch(choice) {
					case 1:
						locFiles.createFile();
					break;
					case 2:
						locFiles.deleteFile();
					break;
					case 3:
						locFiles.searchFile();
					break;
					case 4:
						locFiles.mainMenu(locFiles);
					break;
					default:
						System.out.println("You have entered a wrong choice");
				}
			}
			else
			{
				System.out.println("Please enter a valid number");
				sc.nextLine();
			}
		}while(choice != 5);
	}
	public void mainMenu(Lockers locFiles)
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("This is the First Simplilearn Project.....\n***Created by Talib Hassan***\n***Email Id - thussain520@gmail.com***\n**** Main Menu ****\nPlease select an option from the following");
			System.out.println("1. Display all files in ascending order");
			System.out.println("2. Go to the User Interface");
			System.out.println("3. Exit or Quit");
			
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				switch(choice) {
				case 1:
					locFiles.displayFiles();
					break;
				case 2:
					locFiles.userInterface(locFiles);
					break;
				case 3:
					System.out.println("Thanks for using this application!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input, please enter valid input!");
				}
			}
			else
			{
				System.out.println("Please enter a valid number");
				sc.nextLine();
			}
		}while(choice != 4);
	}
}

public class Default {

	public static void main(String[] args) {
		Lockers locFiles = new Lockers();
		locFiles.mainMenu(locFiles);
	}

}
