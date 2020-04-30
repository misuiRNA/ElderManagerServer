package test;

import java.util.Arrays;

import nettyServer.boot.LinkBoot;

public class Start {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		LinkBoot.start(1514);
		
		
		String str="108.958523,34.270461ra108.957014,34.264345ra108.947636,34.263719ra108.951193,34.271028ra108.958523,34.270461ra";
		String[] strs=str.split("ra");
		System.out.println(Arrays.toString(strs));
		for(int i=0;i<strs.length;i++) {
			String[] point=strs[i].split("?");
			System.out.println(Arrays.toString(point));
		}
		
	}

}
