import java.io.File;
import java.io.FileInputStream;

import com.badlogic.audio.analysis.FFT;
import com.badlogic.audio.io.WaveDecoder;

public class AudioAnalyizer {
	
	public static void getBands(String inPath, int numBands, float[] freq, float[] amp) {
		
		final int TIME_SIZE = 4096;
		
		float[] samples = new float[TIME_SIZE];
		
		try {
			
			WaveDecoder decoder = new WaveDecoder(new FileInputStream(new File(inPath)));
			
			decoder.readSamples(samples);
			
		} catch (Exception e) {
			System.err.println("problem");
			return;
		}
		
		FFT fft = new FFT(TIME_SIZE, 44100);
		
		fft.forward(samples);
		
		for (int i=0; i<200; i++) {
			
			freq[i] = fft.indexToFreq(i);
			amp[i]  = fft.getFreq(freq[i]);
		}
		
//		for (int i=0; i<spectrum.length; i += 10) {
//			
//			int count = (int) Math.sqrt(spectrum[i] * 100);
//			
//			System.out.print(i + "hz|");
//			
//			for (; count != 0; count--)
//				System.out.print("#");
//			
//			System.out.println();
//		}
		
	}

}
