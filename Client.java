package Parkir;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Client {
	private static InetAddress host;
	private static final int PORT = 10;
	public static void main(String[] args)
	{
		try
		{
			host = InetAddress.getLocalHost();
			System.out.println("berhasil masuk....");
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID tidak ditemukan!\n");
			System.exit(1);
		}
		sendMessages();
	}
	private static void sendMessages()
	{
		Socket socket = null;
		try
		{
			socket = new Socket("192.168.1.11",PORT);
			Scanner networkInput =
					new Scanner(socket.getInputStream());
			PrintWriter networkOutput =
					new PrintWriter(
							socket.getOutputStream(),true);
			Scanner userEntry = new Scanner(System.in);
			int pil,pil2,pos;
			String plat;
			String server;
//------------------------------PROSES (MENU dll)------------------------------------------
			do
			{
				System.out.print(	"1. Parkir masuk \n"+
									"2. Parkir keluar \n"+
									"3. Keluar\n"+
									"Masukkan pilihan : ");
				pos = 1;
				networkOutput.println(pos);
				pil = userEntry.nextInt();
				networkOutput.println(pil);
				switch(pil){
				case 1:
					server = networkInput.nextLine();
					System.out.println(server+"Client parkir masuk");
					do{
						gatein(); //method ada di bagian bawah
						pos = 2;
						networkOutput.println(pos);
						pil2 = userEntry.nextInt();
						networkOutput.println(pil2);
						if(pil2==1){
								System.out.print("Masukkan plat nomor : ");
								plat = userEntry.next();
								networkOutput.println(plat);
								server = networkInput.nextLine();
								System.out.println(server);
						}else{
							System.out.println("");
						}
					}
					while(pil2<2);
					break;
				case 2:
					server = networkInput.nextLine();
					System.out.println(server+"Client parkir keluar");
					do{
						gateout(); //method ada di bagian bawah
						pos = 3;
						networkOutput.println(pos);
						pil2 = userEntry.nextInt();
						networkOutput.println(pil2);
						if(pil2==1){
							System.out.print("Masukkan nomor karcis : ");
							plat = userEntry.next();
							networkOutput.println(plat);
							server = networkInput.nextLine();
							System.out.println(server);
						}else{
							System.out.println("");
						}
					}while(pil2<2);
				}
			}while (pil<3);
			pos = 4;
			networkOutput.println(pos);
		}

//---------------------------------------------------------------------------------------
		
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println(
						"\nPenutupan Koneksi...");
				socket.close();
			}
			catch(IOException ioEx)
			{
				System.out.println(
						"Unable to disconnect!");
				System.exit(1);
			}
		}
	}
	private static void gateout() {
		System.out.println("");
		System.out.print(	"Menu Gate-Out\n"+
							"1.check Out\n"+
							"2.keluar\n"+
							"Masukkan pilihan anda : ");
	}
	private static void gatein() {
		System.out.println("");
		System.out.print(	"Menu Gate-In\n"+
							"1.Masukkan plat nomor\n"+
							"2.keluar\n"+
							"Masukkan pilihan anda : ");
	}
}
