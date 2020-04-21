package com.chris.bitrate;

import java.io.File;

public class Bitrate
{
	private File file;
	private Long bitrate;
	
	public Bitrate(File file, long bitrate) {
		this.file = file;
		this.bitrate = bitrate;
	}
	
	public File getFile() {
		return file;
	}
	
	public Long getBitrate() {
		return bitrate;
	}
	
	public String toString() {
		return file.getPath() + "," + bitrate + System.lineSeparator();
	}
}
