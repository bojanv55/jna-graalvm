package sample.jna.graalvm;

import com.sun.jna.Native;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class HelloJNA {
	
	public static void main(String args[]) {
		
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
