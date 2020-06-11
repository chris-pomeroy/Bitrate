package com.chris.bitrate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

public class App {
	
    private static FFprobe ffprobe;
    private static List<Bitrate> results = new ArrayList<>();
    public static void main( String[] args ) throws IOException {
    	
    	ffprobe = new FFprobe("C:\\ffmpeg\\bin\\ffprobe.exe");
    	
    	File dir = new File("D:\\Videos");

    	recursive(dir);
    	results.sort((b1,b2) -> b1.getBitrate().compareTo(b2.getBitrate()));
    	BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:\\Users\\Chris\\Desktop\\out.csv"));
    	for (Bitrate b : results)
    		writer.append(b.toString());
    }
    
    public static void recursive(File dir) {
    	for (File f : dir.listFiles()) {
    		if (f.isDirectory())
    			recursive(f);
    		else if (!f.getName().endsWith(".srt"))
    			bitrate(f);
    	}
    }

    public static void bitrate(File f) {
    	try {
    		FFmpegProbeResult probeResult = ffprobe.probe(f.getAbsolutePath().toString());
    		FFmpegFormat format = probeResult.getFormat();
    		results.add(new Bitrate(f,format.bit_rate));
    	}
    	catch (IOException e) {
    		System.err.println(f);
    		e.printStackTrace();
    	}
    }
}
