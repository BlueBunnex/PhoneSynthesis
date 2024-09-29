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
	
	public static void synthesize(String outPath, int numBands, float[] freq, float[] amp) {
		
		ByteArrayOutputStream b_out = new ByteArrayOutputStream();

        for (long t = 0; t < SAMPLE_RATE * 3; t++) {
        	
        	double value = 0;
        	
        	for (int i = 0; i < numBands; i++) {
        		
        		value += Math.sin(2.0 * Math.PI * freq[i] / ((double) SAMPLE_RATE) * t) * amp[i];
        	}
        	
        	b_out.write((byte) (value * 0.1));
        }

        // output byte stream to output file
        FileOutputStream outputFile;
		try {
			
			outputFile = new FileOutputStream(new File(outPath));
			
			ByteArrayInputStream b_in   = new ByteArrayInputStream(b_out.toByteArray());
	        AudioInputStream     ais    = new AudioInputStream(b_in, new AudioFormat(SAMPLE_RATE, 8, 1, true, false), b_out.size());
	        
	        AudioSystem.write(ais, Type.WAVE, outputFile);
	        
		} catch (IOException e) {
			System.err.println("failure writing");
		}
        
	}

}
