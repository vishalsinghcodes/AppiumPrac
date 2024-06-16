package AppiumFirstProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class RoughExperi {
	@Test
	public static void Onettezt() {
		
		
		String str = "[434,365][868,799]";
		String update = str.replaceAll("[^0-9]", " ");
		//System.out.println(update);
		String[] strar = update.split(" ");
		//Arrays.asList(strar).stream().forEach(s->System.out.println(s));
		//int[] cor = 
		List<String> corr = new ArrayList<>(Arrays.asList(strar));
		corr.removeAll(Arrays.asList("",null));
//		for(int i = 0;i<corr.size();i++) {
//			if(corr.get(i)==" ") {
//				corr.remove(i);
//			}
//		}
		corr.stream().forEach(s->System.out.println(s));
		int[] coordinates = corr.stream().mapToInt(Integer::parseInt).toArray();
		 System.out.println("Integer coordinates: " + Arrays.toString(coordinates));
	}
	
	
	@Test
	public void anothertest() {
		String a = "$123.11";
		System.out.println(a.split("\\$")[1]);
	}
	
	

}
