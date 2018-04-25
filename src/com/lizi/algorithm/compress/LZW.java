package com.lizi.algorithm.compress;

import com.lizi.datastructure.symboltable.ThreeST;
import com.lizi.tool.BinaryStdIn;
import com.lizi.tool.BinaryStdOut;
//LZW编码
public class LZW {
	private static final int R=256;
	private static final int L=4096; //2^12，表示不同的字符串数量最大值
	private static final int W=12;   //每个字符最大编码宽度
	public static void compress() {
		String input=BinaryStdIn.readString();
		ThreeST<Integer> st=new ThreeST<Integer>();//三向单词查找树
		
		for (int i = 0; i < R; i++) {//将单字符对应编码放入树中
			st.put(""+(char)i, i);
		}
		int code=R+1; //R为文件结束的编码
		while(input.length()>0){
			String string=st.longestPrefixOf(input);//找到最长的匹配前缀，则该字符串编码已经存在
			BinaryStdOut.write(st.get(string),W); //输出该字符12位编码
			
			int length=string.length();
			if(length<input.length()&&code<L)//是否已经是读取到输入源结尾或者统计数量已达最大
				st.put(input.substring(0, length+1), code++);//将新的字符串赋予新的编码，建立String->int的转换
			
			input=input.substring(length);//返回length-1之后的字符串,注意上一个编码的字符串的
			}//后缀（最后一个字符）依然在输入流中
		BinaryStdOut.write(R,W);//写入文件结束标记
		BinaryStdOut.close();
	}
	
	public static void decompress() {
		String[] st=new String[L];//基于字符串数组的符号表
		int i=0;
		for (int j = 0; j < R; j++) {
			st[j]=""+(char)j;//数组下标存储其字符
		}
		
		st[i++]=" ";//当前位置为结束标志，没有字符
		int codeWord=BinaryStdIn.readInt(W);
		String val=st[codeWord];//找寻下标对应字符串
		while (true) {
			BinaryStdOut.write(val);//将逆编码得到的字符串写入
			codeWord=BinaryStdIn.readInt(W);
			
			if(codeWord == R) break;//结束标志
			String s=st[codeWord];
			
			if(i==codeWord)//如果下一字符不可用
				s=val+val.charAt(0);//根据上一个字符串的首字母得到编码的字符串
			
			if(i < L)
				st[i++] =val +s.charAt(0);//建立int->String的转换
			
			val=s;
			
		}
		BinaryStdOut.close();
	}
}
