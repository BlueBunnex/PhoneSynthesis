
public class Main {
	
	public static void main(String[] args) {
		
		final int NUM_BANDS = 200;
		
		float[] freq = new float[NUM_BANDS];
		float[] amp  = new float[NUM_BANDS];
		
		FFTAnalysis.getBands("in_a.wav", NUM_BANDS, freq, amp);
		AudioSynthesizer.synthesize("out_a.wav", NUM_BANDS, freq, amp);
		
		FFTAnalysis.getBands("in_e.wav", NUM_BANDS, freq, amp);
		AudioSynthesizer.synthesize("out_e.wav", NUM_BANDS, freq, amp);
		
		FFTAnalysis.getBands("in_i.wav", NUM_BANDS, freq, amp);
		AudioSynthesizer.synthesize("out_i.wav", NUM_BANDS, freq, amp);
		
		FFTAnalysis.getBands("in_o.wav", NUM_BANDS, freq, amp);
		AudioSynthesizer.synthesize("out_o.wav", NUM_BANDS, freq, amp);
		
		FFTAnalysis.getBands("in_u.wav", NUM_BANDS, freq, amp);
		AudioSynthesizer.synthesize("out_u.wav", NUM_BANDS, freq, amp);
		
		
	}

}
