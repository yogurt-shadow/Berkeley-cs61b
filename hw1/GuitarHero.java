import es.datastructur.synthesizer.GuitarString;

public class GuitarHero{

	public int convert(char x){
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		return keyboard.indexOf(x);
	}


	public static void main(String[] args){
		GuitarHero h = new GuitarHero();
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] guitar = new GuitarString[37];
		for(int i = 0; i < 37; i++){
			double concert = 440.0 * Math.pow(2, (i - 24.0) / 12.0);
			guitar[i] = new GuitarString(concert);
		}

		 while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
               if(h.convert(key) >= 0){
               	int number = h.convert(key);
               	guitar[number].pluck();
               }
            }

         double sample = 0;
         for(int ii = 0; ii < 37; ii++){
         	sample += guitar[ii].sample();
         }
        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
           for(int j = 0; j < 37; j++){
           	guitar[j].tic();
           }
        }


	}
}