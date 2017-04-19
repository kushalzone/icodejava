package com.icodejava.research.nlp.test;

import java.util.List;

import com.icodejava.blog.published.utilities.FileUtilities;
import com.icodejava.research.nlp.tokenizer.NepaliTokenizer;

public class NPTokenizerTest {
	

	public static void main (String args []) {
		//textReplaceTest();
		//cleanBadExamplesTest();
		//cleanSentences();
		//System.out.println(NPTokenizer.cleanWordToken("िदपक"));
		System.out.println(NepaliTokenizer.isMalformedWord("अाऊन"));
//		String str;
//		System.out.println(str = NPTokenizer.cleanSentence(",        ,       .        ,    -     (     -  ).  ’         (      ) ,                    .            .        –  .                ’   ’  मन परे हरियो नपरे रातो"));
//		System.out.println(str = NPTokenizer.cleanSentence(",               ’       .                                    +                 + +  +          ,          =   मन परे हरियो नपरे रातो,"));
//		System.out.println(str= NPTokenizer.cleanSentence(",         ,               .                                                   ,           .                .        .            ,                                    ,            ’                     ****      :  मन परे हरियो नपरे रातो"));
//		System.out.println(str= NPTokenizer.cleanSentence(""));
		//NPTokenizer.printChracters(str);
		
		
		
		
	}
	
	
	
	
	private static void textReplaceTest() {
		//String text = "संशाेधन प्रस्तावकाे विराेध गरेकाे भन्दै पूर्वप्रधानमन्त्री केपी ओलीको पुत्ला दहन";
		//पाैडेलकाे
		//दाह््िर
		//औपचारिक÷अनौपचारिक //DONE
		//हिलकोे
		//हैन माओवादीकै
		//सम्झ्ानाकै
		//पुगियाे
		//िदपक //DONE
		//जनसंंगठनहरुको
		// अती भयो है यो चहीं
		//भरिपर्ूण्ा
		
		//String text="    आधुनिक";
		String text = "नपु¥याउँदा"; //नपुर्याउँदा
		
		text = NepaliTokenizer.cleanWordToken(text);
		
		System.out.println(text);
		
		for(Character c:text.toCharArray()) {
			System.out.println(c + " " + (int)c);
		}
		
/*		System.out.println(text.indexOf('\u2360'));
		
		text = text.replaceAll("(ा े){1}", " ो");
		
		System.out.println("Cleaned Text" + text);*/
	}
	
	public static void cleanSentences() {
		//String sentence = "Follow Tweets about \"#hamrakura\" धेरै हेरिएको महिना भरि | वेब भरि पुरै गाउँवासी काँग्रेसमा प्रवेश, चुनावको मुखैमा ध्वस्त भयो एमालेको गढ ओलीकै क्षेत्रमा अनेरास्ववियु तेस्रो, झापामा फहरायो नेविसंघले झण्डा नेविसंघले जितेका ९० क्याम्पसमा को को भए अध्यक्ष";
		String sentence = "100% YES, But, SAMAJ ISTHIR HUDAINA, CHALAYAMANA HUNCHHA, AWASEKATA, CHAHANA RA SAMAYA ANUSAR CHALNU PARCHHA. ARABA KA MAHILA LE HAMRA DIDI BAHINELE LAGAUNE SADHARAN PENT VEST LAGAYA BHANE TYAHAKO SAMAJLE TAPAI LE JASARI YAHA THUKNU BHAYO TYAKO 100 GUNA BADI TYAHA THUKCHHA, TARA YAHA SAMANYA BHAISAKEKO CHHA. KEHI SAMAYA AGHI PENT LAGAYAKO KETI DEKHADA MALAINI LAJ LAGTHYO TARA ALHILE SAMANYA LAGCHHA. GUPTANGA NAI DEKHAUDAI HIDEKA TA CHHAINANA HOLANI. MATHIKO TASBIR SAMMA CHAI HAMILE PACHAUNAI PARCHHA RADHA JEE.NATRA BIDESI KALAKARKO Demand HUNCHHA. 7 0 Share जानुका सुवेदी लेख्नुहुन्छ,    |   २०७३ फागुन ६ गते १२:१२ लेखिकाको िवचार संग म पनि सहमत छु";
		
		System.out.println(sentence=NepaliTokenizer.cleanSentence(sentence));
		
		for(Character c: sentence.toCharArray()) {
			System.out.println(c + " " + (int) c);
		}
		
	}
	

	private static void cleanBadExamplesTest() {
		List<String> lines = FileUtilities.readUTF8FileAsList("src/com/icodejava/research/nlp/test/bad_examples.txt");
		
		//System.out.println(lines.size());
	
		for(String line: lines) {
			String clean = NepaliTokenizer.cleanWordToken(line+"()[]{}^");
			if(clean.trim().length() > 0) {
				System.out.println(clean.trim());
			}
		}
	}

}
