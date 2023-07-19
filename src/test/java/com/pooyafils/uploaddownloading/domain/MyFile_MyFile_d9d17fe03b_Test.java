package com.pooyafils.uploaddownloading.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFileTest {

    @Test
    public void testMyFileConstructor() {
        MyFile myFile = new MyFile();
        assertEquals(null, myFile.getId());
        assertEquals(null, myFile.getName());
        assertEquals(null, myFile.getFileStore());
    }

    @Test
    public void testMyFileConstructorWithParameters() {
        MyFile myFile = new MyFile(1L, "test", null);
        assertEquals(1L, myFile.getId());
        assertEquals("test", myFile.getName());
        assertEquals(null, myFile.getFileStore());
    }
}
