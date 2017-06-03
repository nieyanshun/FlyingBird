package org.flying.bird.protocol;

import org.flying.bird.protocol.Codec.CodecType;
import org.flying.bird.protocol.codec.HessianCodec;

public class HessianCodecTest {

	void testCodeC() {
		Student s = new Student();
		s.setUname("学生A");
		s.setAge(18);

		HessianCodec codec = new HessianCodec();
		byte[] b = codec.encode(s, CodecType.REQUEST);
		
		System.out.println(codec.decode(b));

	}
	public static void main(String[] args){
		new HessianCodecTest().testCodeC();
	}
}
