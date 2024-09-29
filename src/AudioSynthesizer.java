import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

// making sound : https://stackoverflow.com/questions/274718/javasound-writing-to-audio-file-with-a-stream
// getting FFT  : https://stackoverflow.com/questions/17565269/calculate-fft-from-audio-file

public class AudioSynthesizer {
	
	private static final int SAMPLE_RATE = 44100;
	
	public static void synthesize(String outPath, float sec, int numBands, float[] freq, float[] amp) {
		
		ByteArrayOutputStream b_out = new ByteArrayOutputStream();

        for (long t = 0; t < SAMPLE_RATE * sec; t++) {
        	
        	double value = 0;
        	
        	for (int i = 0; i < numBands; i++) {
        		
        		value += Math.sin(2.0 * Math.PI * freq[i] / ((double) SAMPLE_RATE) * t) * amp[i];
        	}
        	
        	b_out.write((byte) (value * 0.1));
        }
        
        writeAndClose(outPath, b_out);
	}
	
	public static void synthesizeLerp(String outPath, double start, double transition, double end, int numBands, float[] freq1, float[] amp1, float[] freq2, float[] amp2) {
		
		ByteArrayOutputStream b_out = new ByteArrayOutputStream();
		
		double length;
		long t = 0;
		
		// start
		length = SAMPLE_RATE * start;
		
		for (; t < length; t++) {
        	
        	double value = 0;
        	
        	for (int i = 0; i < numBands; i++) {
        		
        		value += Math.sin(2.0 * Math.PI * freq1[i] / ((double) SAMPLE_RATE) * t) * amp1[i];
        	}
        	
        	b_out.write((byte) (value * 0.1));
        }
		
		// transition
		length += SAMPLE_RATE * transition;
		
        for (; t < length; t++) {
        	
        	double value = 0;
        	
        	for (int i = 0; i < numBands; i++) {
        		
        		// we hate efficiency
        		double freq = lerp((t - SAMPLE_RATE * start) / (length - SAMPLE_RATE * start), freq1[i], freq2[i]);
        		double amp  = lerp((t - SAMPLE_RATE * start) / (length - SAMPLE_RATE * start), amp1[i],  amp2[i]);
        		
        		value += Math.sin(2.0 * Math.PI * freq / ((double) SAMPLE_RATE) * t) * amp;
        	}
        	
        	b_out.write((byte) (value * 0.1));
        }
        
        // end
        length += SAMPLE_RATE * end;
        
        for (; t < length; t++) {
        	
        	double value = 0;
        	
        	for (int i = 0; i < numBands; i++) {
        		
        		value += Math.sin(2.0 * Math.PI * freq2[i] / ((double) SAMPLE_RATE) * t) * amp2[i];
        	}
        	
        	b_out.write((byte) (value * 0.1));
        }
        
        // write
        writeAndClose(outPath, b_out);
	}
	
	private static double lerp(double fac, float v1, float v2) {
		return v1 * (1 - fac) + v2 * fac;
	}
	
	private static void writeAndClose(String path, ByteArrayOutputStream b_out) {
		
		try {
			
			FileOutputStream outputFile = new FileOutputStream(new File(path));
			
			ByteArrayInputStream b_in   = new ByteArrayInputStream(b_out.toByteArray());
	        AudioInputStream     ais    = new AudioInputStream(b_in, new AudioFormat(SAMPLE_RATE, 8, 1, true, false), b_out.size());
	        
	        AudioSystem.write(ais, Type.WAVE, outputFile);
	        
	        outputFile.close();
	        b_in.close();
	        ais.close();
	        
		} catch (IOException e) {
			System.err.println("failure writing");
		}
		
		try {
			b_out.close();
		} catch (IOException e) {}
	}

}
