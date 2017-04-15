package com.icodejava.research.nlp.utils;
/**
 * @author Kushal Paudyal
 * www.icodejava.com | www.inepal.org
 * 
 * This class can convert a text written in unicode to Devanagari.
 * 
 * Alternate is also possible, if the Romanized English is in the proper ISO standard.
 */

import java.util.Arrays;
import java.util.List;

import com.icodejava.blog.published.utilities.FileUtilities;
import com.icodejava.research.nlp.NPTokenizer;

public class DevanagariUnicodeToRomanEnglish {
	
	
	static List<String> unicodeSet = Arrays.asList(new String[]{
            "१","२","३","४","५","६","७","८","९","०",
            "अ", "आ","इ", "ई", "उ", "ऊ","ए","ऐ","ओ","औ","अं","अ:","अँ",
            "क", "का", "कि", "की", "कु", "कू", "के", "कै", "को", "कौ", "कं", "क:", "क्","कृ",
            "ख", "खा", "खि", "खी", "खु", "खू", "खे", "खै", "खो", "खौ", "खं", "ख:", "ख्",
            "ग", "गा", "गि", "गी", "गु", "गू", "गे", "गै", "गो", "गौ", "गं", "ग:","ग्","गृ",
            "घ", "घा", "घि", "घी", "घु", "घू", "घे", "घै", "घो", "घौ", "घं", "घ:","घ्", "घृ",
            "ङ", "ङा", "ङि", "ङी", "ङु", "ङू", "ङे", "ङै", "ङो", "ङौ", "ङं", "ङ:","ङ्",
            "च", "चा", "चि", "ची", "चु", "चू", "चे", "चै", "चो", "चौ", "चं", "च:","च्",
            "छ", "छा", "छि", "छी", "छु", "छू", "छे", "छै", "छो", "छौ", "छं", "छ:","छ्",
            "ज", "जा", "जि", "जी", "जु", "जू", "जे", "जै", "जो", "जौ", "जं", "ज:","ज्","जृ",
            "झ", "झा", "झि", "झी", "झु", "झू", "झे", "झै", "झो", "झौ", "झं", "झ:","झ्","झृ",
            "ञ", "ञा", "ञि", "ञी", "ञु", "ञू", "ञे", "ञै", "ञो", "ञौ", "ञं", "ञ:","ञ्",
            "ट", "टा", "टि", "टी", "टु", "टू", "टे", "टै", "टो", "टौ", "टं", "ट:","ट्",
            "ठ", "ठा", "ठि", "ठी", "ठु", "ठू", "ठे", "ठै", "ठो", "ठौ", "ठं", "ठ:","ठ्",
            "ड", "डा", "डि", "डी", "डु", "डू", "डे", "डै", "डो", "डौ", "डं", "ड:","ड्",
            "ढ", "ढा", "ढि", "ढी", "ढु", "ढू", "ढे", "ढै", "ढो", "ढौ", "ढं", "ढ:","ढ्",
            "ण", "णा", "णि", "णी", "णु", "णू", "णे", "णै", "णो", "णौ", "णं", "ण:","ण्",
            "त", "ता", "ति", "ती", "तु", "तू", "ते", "तै", "तो", "तौ", "तं", "त:","त्","तृ",
            "थ", "था", "थि", "थी", "थु", "थू", "थे", "थै", "थो", "थौ", "थं", "थ:","थ्","थृ",
            "द", "दा", "दि", "दी", "दु", "दू", "दे", "दै", "दो", "दौ", "दं", "द:","द्","दृ",
            "ध", "धा", "धि", "धी", "धु", "धू", "धे", "धै", "धो", "धौ", "धं", "ध:","ध्",
            "न", "ना", "नि", "नी", "नु", "नू", "ने", "नै", "नो", "नौ", "नं", "न:","न्","नृ",
            "प", "पा", "पि", "पी", "पु", "पू", "पे", "पै", "पो", "पौ", "पं", "प:","प्","पृ",
            "फ", "फा", "फि", "फी", "फु", "फू", "फे", "फै", "फो", "फौ", "फं", "फ:","फ्",
            "ब", "बा", "बि", "बी", "बु", "बू", "बे", "बै", "बो", "बौ", "बं", "ब:","ब्","बृ",
            "भ", "भा", "भि", "भी", "भु", "भू", "भे", "भै", "भो", "भौ", "भं", "भ:","भ्","भृ",
            "म", "मा", "मि", "मी", "मु", "मू", "मे", "मै", "मो", "मौ", "मं", "म:","म्","मृ",
            "य", "या", "यि", "यी", "यु", "यू", "ये", "यै", "यो", "यौ", "यं", "य:","य्",
            "र", "रा", "रि", "री", "रु", "रू", "रे", "रै", "रो", "रौ", "रं", "र:","र्",
            "ल", "ला", "लि", "ली", "लु", "लू", "ले", "लै", "लो", "लौ", "लं", "ल:","ल्","लृ",
            "व", "वा", "वि", "वी", "वु", "वू", "वे", "वै", "वो", "वौ", "वं", "व:","व्","वृ",
            "श", "शा", "शि", "शी", "शु", "शू", "शे", "शै", "शो", "शौ", "शं", "श:", "श्",
            "ष", "षा", "षि", "षी", "षु", "षू", "षे", "षै", "षो", "षौ", "षं", "ष:","ष्",
            "स", "सा", "सि", "सी", "सु", "सू", "से", "सै", "सो", "सौ", "सं", "स:","स्","सृ","सँ",
            "ह", "हा", "हि", "ही", "हु", "हू", "हे", "है", "हो", "हौ", "हं", "ह:","ह्","हृ",
            "क्ष", "क्षा", "क्षि", "क्षी", "क्षु", "क्षू", "क्षे", "क्षै", "क्षो", "क्षौ", "क्षं", "क्ष:","क्ष्",
            "त्र", "त्रा", "त्रि", "त्री", "त्रु", "त्रू", "त्रे", "त्रै", "त्रो", "त्रौ", "त्रं", "त्र:","त्र्",
            "ज्ञ", "ज्ञा", "ज्ञि", "ज्ञी", "ज्ञु", "ज्ञू", "ज्ञे", "ज्ञै", "ज्ञो", "ज्ञौ", "ज्ञं", "ज्ञ:","ज्ञ्",
            //extra
            "ऋ","यूँ"
  });
  static List<String> romanizedEnglishSet = Arrays.asList(new String[]{
            "1","2","3","4","5","6","7","8","9","0",
            "a", "ā","i", "ī", "u", "ū","ē","ai","ō","au","aṁ","a:","aṅ",
            "ka", "kā", "ki", "kī", "ku", "kū", "kē", "kai", "ko", "kau", "kṁ", "ka:","k","kri",
            "kha", "khā", "khi", "khī", "khu", "khū", "khē", "khai", "kho", "khau", "khṁ", "kha:","kh",
            "ga", "gā", "gi", "gī", "gu", "gū", "gē", "gai", "go", "gau", "gṁ", "ga:","g","gri",
            "gha", "ghā", "ghi", "ghī", "ghu", "ghū", "ghē", "ghai", "gho", "ghau", "ghṁ", "gha:","gh","ghri",
            "ṅa", "ṅā", "ṅi", "ṅī", "ṅu", "ṅū", "ṅē", "ṅai", "ṅo", "ṅau", "ṅṁ", "ṅa:","ṅ",
            "cha", "chā", "chi", "chī", "chu", "chū", "chē", "chai", "cho", "chau", "chṁ", "cha:","ch",
            "chha", "chhā", "chhi", "chhī", "chhu", "chhū", "chhē", "chhai", "chho", "chhau", "chhṁ", "chha:","chh",
            "ja", "jā", "ji", "jī", "ju", "jū", "jē", "jai", "jo", "jau", "jṁ", "ja:","j","jri",
            "jha", "jhā", "jhi", "jhī", "jhu", "jhū", "jhē", "jhai", "jho", "jhau", "jhṁ", "jha:,","jh","jhri",
            "ña", "ñā", "ñi", "ñī", "ñu", "ñū", "ñē", "ñai", "ño", "ñau", "ñṁ", "ña:","ñ",
            "ṭa", "ṭā", "ṭi", "ṭī", "ṭu", "ṭū", "ṭē", "ṭai", "ṭo", "ṭau", "ṭṁ", "ṭa:","ṭ",
            "ṭha", "ṭhā", "ṭhi", "ṭhī", "ṭhu", "ṭhū", "ṭhē", "ṭhai", "ṭho", "ṭhau", "ṭhṁ", "ṭha:","ṭh",
            "ḍa", "ḍā", "ḍi", "ḍī", "ḍu", "ḍū", "ḍē", "ḍai", "ḍo", "ḍau", "ḍṁ", "ḍa:","ḍ",
            "ḍha", "ḍhā", "ḍhi", "ḍhī", "ḍhu", "ḍhū", "ḍhē", "ḍhai", "ḍho", "ḍhau", "ḍhṁ", "ḍha:","ḍh",
            "ṇa", "ṇā", "ṇi", "ṇī", "ṇu", "ṇū", "ṇē", "ṇai", "ṇo", "ṇau", "ṇṁ", "ṇa:","ṇ",
            "ta", "tā", "ti", "tī", "tu", "tū", "tē", "tai", "to", "tau", "tṁ", "ta:","t","tri",
            "tha", "thā", "thi", "thī", "thu", "thū", "thē", "thai", "tho", "thau", "thṁ", "tha:","th","thri",
            "da", "dā", "di", "dī", "du", "dū", "dē", "dai", "do", "dau", "dṁ", "da:","d","dri",
            "dha", "dhā", "dhi", "dhī", "dhu", "dhū", "dhē", "dhai", "dho", "dhau", "dhṁ", "dha:","dh",
            "na", "nā", "ni", "nī", "nu", "nū", "nē", "nai", "no", "nau", "nṁ", "na:","n","nri",
            "pa", "pā", "pi", "pī", "pu", "pū", "pē", "pai", "po", "pau", "pṁ", "pa:","p","pri",
            "pha", "phā", "phi", "phī", "phu", "phū", "phē", "phai", "pho", "phau", "phṁ", "pha:","ph",
            "ba", "bā", "bi", "bī", "bu", "bū", "bē", "bai", "bo", "bau", "bṁ", "ba:","b","bri",
            "bha", "bhā", "bhi", "bhī", "bhu", "bhū", "bhē", "bhai", "bho", "bhau", "bhṁ", "bha:","bh","bhri",
            "ma", "mā", "mi", "mī", "mu", "mū", "mē", "mai", "mo", "mau", "mṁ", "ma:","m","mri",
            "ya", "yā", "yi", "yī", "yu", "yū", "yē", "yai", "yo", "yau", "yṁ", "ya:","y",
            "ra", "rā", "ri", "rī", "ru", "rū", "rē", "rai", "ro", "rau", "rṁ", "ra:","r",
            "la", "lā", "li", "lī", "lu", "lū", "lē", "lai", "lo", "lau", "lṁ", "la:","l","lri",
            "va", "vā", "vi", "vī", "vu", "vū", "vē", "vai", "vo", "vau", "vṁ", "va:","v","vri",
            "sha", "shā", "shi", "shī", "shu", "shū", "shē", "shai", "sho", "shau", "shṁ", "sha:","sh",
            "sha", "shā", "shi", "shī", "shu", "shū", "shē", "shai", "sho", "shau", "shṁ", "sha:","sh",
            "sa", "sā", "si", "sī", "su", "sū", "sē", "sai", "so", "sau", "sṁ", "sa:","s","sri","sm̐",
            "ha", "hā", "hi", "hī", "hu", "hū", "hē", "hai", "ho", "hau", "hṁ", "ha:","h","hri",
            "ksha", "kshā", "kshi", "kshī", "kshu", "kshū", "kshē", "kshai", "ksho", "kshau", "kshṁ", "ksha:","ksh",
            "tra", "trā", "tri", "trī", "tru", "trū", "trē", "trai", "tro", "trau", "trṁ", "tra:","tr",
            "gya", "gyā", "gyi", "gyī", "gyu", "gyū", "gyē", "gyai", "gyo", "gyau", "gyṁ", "gya:","gy",
            //extra
            "r̥","yūm̐"

  });
	
	public static String set_of_matras = "ा ि ी ु ू ृ े ै ो ौ ं : ँ ॅ्" ;
	
	public static void main(String args []) {
		//convertUnicodeNepaliToRomanizedEnglish("२२३४५१७६८९०");
		//convertUnicodeNepaliToRomanizedEnglish("क ख ग घ ङ");
		
		//convertUnicodeNepaliToRomanizedEnglish("मेरो नाम कुशल हो");//नट वोर्किंग
		/*convertUnicodeNepaliToRomanizedEnglish("बिश्वास");
		convertUnicodeNepaliToRomanizedEnglish("राष्ट्रिय समिक्षा");
		convertUnicodeNepaliToRomanizedEnglish("कुखुरासँग कृष्ण हिँडेकी अर्गनाइजेसनकै आलुलाई ह्यांगरलाई");*/
		//convertUnicodeNepaliToRomanizedEnglish("अशंतुस्त");
/*		
		System.out.println();
		
		for(Character c: new String("अशंतुस्त").toCharArray() ) {
			System.out.println(c);
		}*/
		// Transformed: ashṁtust --> if half word, last word should not be half.
		
		//convertUnicodeNepaliToRomanizedEnglish("मेरो नाम कुशल हो . म  हलो कुटो, जुत्ता र  शिक्षा त्रिशुल मा बिश्वास गर्छुः");//नट वोर्किंग
		
		//System.out.println(unicodeSet.indexOf("रो"));
		//System.out.println(romanizedEnglishSet.get(330));
		
//		NPTokenizer.isMalformedWord("भनेझ्ैं");
//		NPTokenizer.isMalformedWord("भनेझैं");
//		
//		NPTokenizer.fixMalformedWord("िबचार");
//		NPTokenizer.fixMalformedWord("अोचार");
		
//      System.out.println("Unicode Set Length " + unicodeSet.size());
//
//       convertUnicodeNepaliToRomanizedEnglish("मुखकृति गृष्म हृदय तृप्ति दृश्य संग सँग सङ्ग सृजना श्रीजना");
     convertUnicodeNepaliToRomanizedEnglish("घृ नृ ट्र वृत बृत मृत् लृत जृत झृत झ्रित य्र ठ्रित थृत पृ फ्री भृत वृत्त ब्रित्ता पिठ्यूँ सिद्दिचरण माइसंसार निदाउँदा अँगुलो");
//       convertUnicodeNepaliToRomanizedEnglish("कुखुरासँग कृष्ण हिँडेकी अर्गनाइजेसनकै आलुलाई ह्यांगरलाई");
		
//		convertUnicodeNepaliToRomanizedEnglish("बझाङमा");
//		convertUnicodeNepaliToRomanizedEnglish("भलिबलको");
	
		
		//System.out.println("भनेझ्ैं".replaceAll("् ै ं ", " ै ं "));
		
		//conertFile("C:\\temp\\np4.txt");
	}


	
    private static void sanityCheck() {

        for(int i = 0; i < romanizedEnglishSet.size(); i ++) {
            System.out.println(romanizedEnglishSet.get(i) + "-->" + unicodeSet.get(i));
        }

    }
    
    private static void conertFile(String fileName) {

        String str = FileUtilities.readUTF8File(fileName);

        System.out.println("Original: " + str );

        System.out.println("\n\nConvereted" + convertUnicodeNepaliToRomanizedEnglish(str));

    }



	public static String convertUnicodeNepaliToRomanizedEnglish(String string) {
		if(string!=null) {
			string = string.trim();
		}
		String transformed = "";
		
		String convert = "";
		boolean waitToDoTranslation = false;
		boolean potentialProblem = false;
		char upcoming = 0;
		for (int i=0; i<string.length(); i++) {
			convert +=  string.charAt(i);
			//Peek ahead
			
			if(i+1 < string.length()) {
				upcoming = string.charAt(i+1);
				//System.out.println((int)upcoming);
			}
			
			//System.out.println("convert: " + convert + " chartAt(i) " + string.charAt(i) + "Upcoming: " + upcoming + " " + (int)upcoming + " Equality: " + (upcoming == 32));
			
			
			//if(!(upcoming==32)) {
				waitToDoTranslation = (i+1 < string.length()) && set_of_matras.indexOf(upcoming+"") >= 0 && !(upcoming==32);
			//}
			//waitToDoTranslation =  !upcoming.equals("");
			
			//System.out.println((int)c + " " +  c);
			if (!waitToDoTranslation) {
				if (unicodeSet.indexOf(convert) > 0) {
					int index = unicodeSet.indexOf(convert);
					// System.out.println("Found at: " + index);
					transformed += romanizedEnglishSet.get(index);

				} else {
					transformed += convert;
					potentialProblem = true;
				}
			}
			
			//reset
			if(!waitToDoTranslation) {
				convert = "";
			}
			
		}
		
		//IF WORDS END IN LA, RA, DA, KA, remove the A. Some exceptions apply.
		if(!(string.charAt(string.length() - 2) == '्')) {
		
			if(	transformed.endsWith("ka")
					|| transformed.endsWith("kha")
					|| transformed.endsWith("ga")
					|| transformed.endsWith("gha")
					|| transformed.endsWith("ṅa")
					|| transformed.endsWith("cha") //TODO: Exception kharcha
					|| transformed.endsWith("ja")
					|| transformed.endsWith("ṭa")
					|| transformed.endsWith("ṭha")
					|| transformed.endsWith("pa")
					|| transformed.endsWith("bha")
					|| transformed.endsWith("ma")
					|| transformed.endsWith("ra") //TODO: EXCEPTION KHERA, NIRA, TIRA, JAMERA, KSHETRA
					|| transformed.endsWith("sa")
					|| transformed.endsWith("sha")
					|| transformed.endsWith("ba")
					|| transformed.endsWith("va")
					|| transformed.endsWith("ta")
					|| transformed.endsWith("tha")
					||transformed.endsWith("la") 
					|| transformed.endsWith("da") 
					|| transformed.endsWith("dha") 
					
				
					
					
					) {
				transformed = transformed.substring(0, transformed.length() -1);
			}
			
			if(transformed.endsWith("ṅamā")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("ṅamā")) + "ṅmā";
			}
			
			if(transformed.endsWith("lako")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("lako")) + "lko";
			}
			
			if(transformed.endsWith("lakā")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("lakā")) + "lkā";
			}
			
			if(transformed.endsWith("sako")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("sako")) + "sko"; //e.g. सुट्केसको  > suṭkēsako > suṭkēsko 
			}
			
			if(transformed.endsWith("ramā")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("ramā")) + "rmā";
			}
			
			if(transformed.endsWith("lamā")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("lamā")) + "lmā"; //e.g. दालमा > dālamā > dālmā 
			}

			
			if(transformed.endsWith("ṇakā")) { //TODO: process exception for Krishnaka
				transformed = transformed.substring(0, transformed.lastIndexOf("ṇakā")) + "ṇkā"; //e.g. कारणका  > kāraṇakā > kāraṇkā
			}
			
			if(transformed.endsWith("valē")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("valē")) + "vlē"; //e.g. गरिवले > garivalē > garivlē
				
			}
			
			if(transformed.endsWith("palē")) {
				transformed = transformed.substring(0, transformed.lastIndexOf("palē")) + "plē"; //e.g. चित्ररुपले  > chitrarupalē > chitraruplē
				
			}
			
			//Need to fix the following
			//पालुङकी - Romanized: + pāluṅakī 
			//खलकले - Romanized: + khalakalē 
			
			
	//		3उँḍāko 
			//मुंनकर्मी - Romanized: + मुंnakarmī 
			//छिँटा - Romanized: + छिँṭā 
			//ओंठ - Romanized: + ओंṭh 
			//नभाँचिने - Romanized: + naभाँchinē 
			//पिंकी - Romanized: + पिंkī 
//			/साँचोझुठो - Romanized: + साँchojhuṭho 
			//हजारौँका - Romanized: + hajāरौँkā 
			//हुँdāsammamā 
			//पठाउँछौं - Romanized: + paṭhāउँछौं 
			//बडादशैं - Romanized: + baḍādaशैं
			// गोँगबु - Romanized: + गोँgabu 
			//बाँसबारीको - Romanized: + बाँsabārīko 
		}

		//System.out.println("Original: " + string + " Transformed: " + transformed);

		return transformed;
		
	
		
	}

	

}
