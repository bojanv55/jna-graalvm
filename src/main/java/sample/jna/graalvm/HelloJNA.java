package sample.jna.graalvm;

import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class HelloJNA {

	private static String getWindowsCPU_SerialNumber()
	{

		// Initially declaring and initializing an empty
		// string
		String result = "";

		// Try block to check for exceptions
		try {

			// Creating an object of File class
			File file
					= File.createTempFile("realhowto", ".vbs");

			// Deleting file while exiting
			file.deleteOnExit();

			// Creating an object of FileWriter class to
			// write on
			FileWriter fw = new java.io.FileWriter(file);

			// Remember the command
			String vbs1
					= "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n"
					+ "   (\"Select * from Win32_Processor\") \n"
					+ "For Each objItem in colItems \n"
					+ "    Wscript.Echo objItem.ProcessorId \n"
					+ "    exit for  ' do the first cpu only! \n"
					+ "Next \n";

			// Writing on file
			fw.write(vbs1);

			// Closing all file connections to
			// release memory spaces
			fw.close();

			Process p = Runtime.getRuntime().exec(
					"cscript //NoLogo " + file.getPath());

			BufferedReader input = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String line;

			while ((line = input.readLine()) != null) {
				result += line;
			}

			input.close();
		}

		// Catch block to handle the exceptions
		catch (Exception E) {

			// Print the exception along with the message
			System.err.println("Windows CPU Exp : "
					+ E.getMessage());
		}

		return result.trim();
	}
	
	public static void main(String args[]) {

		String cpuSerialNumber
				= getWindowsCPU_SerialNumber();

		// Calling the method1 to retrieve CPU serial number
		// and printing the same
		System.out.println(
				"CPU Serial Number of my Windows Machine: "
						+ cpuSerialNumber);

		Kernelz32 INSTANCE = Native.load("kernel32", Kernelz32.class, W32APIOptions.DEFAULT_OPTIONS);
		int tx = INSTANCE.GetTickCount();
    System.out.println(tx);

		System.out.println("Hello there!");
		System.out.println("JNA Version::" + Native.VERSION);

		SystemInfo systemInfo = new SystemInfo();
		OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
		HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
		ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();

		String vendor = operatingSystem.getManufacturer();
		String processorSerialNumber = computerSystem.getSerialNumber();
		String processorIdentifier = centralProcessor.getProcessorIdentifier().getProcessorID();
		int processors = centralProcessor.getLogicalProcessorCount();

		String delimiter = "#";

		String all = vendor +
				delimiter +
				processorSerialNumber +
				delimiter +
				processorIdentifier +
				delimiter +
				processors;

		System.out.println("Sve je " + all);
	}

}
