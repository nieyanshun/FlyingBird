package org.flying.bird.rpc;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	byte[] arr={0x00,0x01};
		
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(arr));
		try {
			System.out.println(in.readShort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    }
}
