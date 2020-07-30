package com.learning.nio.test;

import com.learning.BaseTest;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest extends BaseTest {

    @Test
    public void testFileChannel() throws IOException {
        RandomAccessFile file = new RandomAccessFile("test.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytes = channel.read(buffer);
        while (bytes != -1){
            logger.info("bytes : {}", bytes);
            buffer.flip();
            while (buffer.hasRemaining()){
                logger.info("- {}", (char)buffer.get());
            }
            buffer.clear();
            bytes = channel.read(buffer);
        }
        file.close();
    }
}
