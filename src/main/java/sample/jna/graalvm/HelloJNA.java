package sample.jna.graalvm;

import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class HelloJNA {
	
	public static void main(String args[]) {

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
