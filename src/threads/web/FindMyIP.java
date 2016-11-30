package threads.web;

// CS2113
// T. Wood

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FindMyIP {

	/**
	 * Prints out your IP address
	 */
	public static void main(String[] args) {

		try {
		    InetAddress addr = InetAddress.getLocalHost();

		    // Get IP Address
		    byte[] ipAddr = addr.getAddress();

		    // Get hostname
		    String hostname = addr.getHostAddress();
		    System.out.println("My IP = " + hostname);
		} catch (UnknownHostException e) {}

	}

}
