
public class Main {
	
	public static void main(String[] args) {
		
		final int NUM_BANDS = 400;
		
		float[] freq1 = new float[NUM_BANDS];
		float[] amp1  = new float[NUM_BANDS];
		
		float[] freq2 = new float[NUM_BANDS];
		float[] amp2  = new float[NUM_BANDS];
		
		AudioAnalyizer.getBands("audio/in_o.wav", NUM_BANDS, freq1, amp1);
		AudioAnalyizer.getBands("audio/in_i.wav", NUM_BANDS, freq2, amp2);
		AudioSynthesizer.synthesizeLerp("audio/out_oi.wav", 1, 0.3, 1, NUM_BANDS, freq1, amp1, freq2, amp2);
		
//		FFTAnalysis.getBands("audio/in_a.wav", NUM_BANDS, freq, amp);
//		AudioSynthesizer.synthesize("audio/out_a.wav", NUM_BANDS, freq, amp);
//		
//		FFTAnalysis.getBands("audio/in_e.wav", NUM_BANDS, freq, amp);
//		AudioSynthesizer.synthesize("audio/out_e.wav", NUM_BANDS, freq, amp);
//		
//		FFTAnalysis.getBands("audio/in_i.wav", NUM_BANDS, freq, amp);
//		AudioSynthesizer.synthesize("audio/out_i.wav", NUM_BANDS, freq, amp);
//		
//		FFTAnalysis.getBands("audio/in_o.wav", NUM_BANDS, freq, amp);
//		AudioSynthesizer.synthesize("audio/out_o.wav", NUM_BANDS, freq, amp);
//		
//		FFTAnalysis.getBands("audio/in_u.wav", NUM_BANDS, freq, amp);
//		AudioSynthesizer.synthesize("audio/out_u.wav", NUM_BANDS, freq, amp);
		
	}

}
