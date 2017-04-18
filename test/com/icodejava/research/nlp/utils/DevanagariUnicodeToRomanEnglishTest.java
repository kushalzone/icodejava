package com.icodejava.research.nlp.utils;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
public class DevanagariUnicodeToRomanEnglishTest {

	@Test
	public void test_001() {
		
		String input = "१२३४५६७८९०";
		String output = DevanagariUnicodeToRomanEnglish.convertWord(input);
		String expected = "1234567890";
		
		Assert.assertThat(output, is(expected));		
	}

	@Test
	public void test_002() {
		
		String input = "क ख ग घ ङ";
		String output = DevanagariUnicodeToRomanEnglish.convertWord(input);
		String expected = "ka kha ga gha ṅ";
		
		Assert.assertThat(output, is(expected));		
	}
	
	@Test
	public void test_003() {
		
		String input = "मेरो नाम कुशल हो";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "mēro nām kushal ho";
		
		Assert.assertThat(output, is(expected));		
	}
	
	@Test
	public void test_004() {
		
		String input = "राष्ट्रिय समिक्षा बिश्वास अशंतुस्त बझाङमा भलिबलको";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "rāshṭriya samikshā bishvās ashaṁtusta bajhāṅmā bhalibalko";
		
		Assert.assertThat(output, is(expected));		
	}
		
	@Test
	public void test_005() {
		
		String input = "कुखुरासँग कृष्ण हिँडेकी अर्गनाइजेसनकै आलुलाई ह्यांगरलाई छिँटा नभाँचिने";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "kukhurāsam̐g krishṇa him̐ḍēkī arganāijēsankai ālulāī hyāṁgarlāī chhim̐ṭā nabhām̐chinē";
		
		Assert.assertThat(output, is(expected));		
	}
	
	@Test
	public void test_006() {
		
		String input = "घृ नृ ट्र वृत बृत मृत् लृत जृत झृत झ्रित य्र ठ्रित थृत पृ फ्री भृत वृत्त ब्रित्ता पिठ्यूँ सिद्दिचरण माइसंसार निदाउँदा अँगुलो";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "ghri nri ṭra vrit brit mrit lrit jrit jhrit jhrit yra ṭhrit thrit pri phrī bhrit vritta brittā piṭhyūm̐ siddicharaṇa māisaṁsār nidāum̐dā am̐gulo";
		
		Assert.assertThat(output, is(expected));		
	}
	
	@Test
	public void test_007() {
		
		String input = "मुखकृति गृष्म हृदय तृप्ति दृश्य संग सँग सङ्ग सृजना श्रीजना";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "mukhakriti grishma hridaya tripti drishya saṁg sam̐g saṅga srijanā shrījanā";
		
		Assert.assertThat(output, is(expected));		
	}

	
	@Test
	public void test_008() {
		
		String input = "मेरो नाम कुशल हो . म  हलो कुटो, जुत्ता र  शिक्षा त्रिशुल मा बिश्वास गर्छुः";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "mēro nām kushal ho . ma halo kuṭo, juttā ra shikshā trishul mā bishvās garchhu:";
		
		Assert.assertThat(output, is(expected));		
	}
	
	@Test
	public void test_009() {
		
		String input = "गर्छुः";
		String output = DevanagariUnicodeToRomanEnglish.convertSentence(input);
		String expected = "garchhu:";
		
		Assert.assertThat(output, is(expected));		
	}

	//पालुङकी - Romanized: + pāluṅakī 
	//खलकले - Romanized: + khalakalē 
	
	
//		3उँḍāko 
	//मुंनकर्मी - Romanized: + मुंnakarmī 
	//ओंठ - Romanized: + ओंṭh 
	//नभाँचिने - Romanized: + naभाँchinē 
	//पिंकी - Romanized: + पिंkī 
//	/साँचोझुठो - Romanized: + साँchojhuṭho 
	//हजारौँका - Romanized: + hajāरौँkā 
	//हुँdāsammamā 
	//पठाउँछौं - Romanized: + paṭhāउँछौं 
	//बडादशैं - Romanized: + baḍādaशैं
	// गोँगबु - Romanized: + गोँgabu 
	//बाँसबारीको - Romanized: + बाँsabārīko 
	//एक्काइशौं - Romanized: + ēkkāiशौं
	
	//
	


}
