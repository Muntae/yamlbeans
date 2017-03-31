package com.esotericsoftware.yamlbeans;


import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.esotericsoftware.yamlbeans.tokenizer.Token;
import com.esotericsoftware.yamlbeans.tokenizer.TokenType;
import com.esotericsoftware.yamlbeans.tokenizer.Tokenizer;
import com.esotericsoftware.yamlbeans.tokenizer.Tokenizer.TokenizerException;

public class TokenizerTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Purpose: Getting the next token correctly
	 * Input: getNextToken() gets the next token and moves to the next token
	 * Expected: 
	 * 			return Token.STREAM_START
	 * 			return Token.BLOCK_MAPPING_START
	 * 			return Token.KEY
	 * 			...
	 * 			return Token.STREAM.END
	 * 			return null
	 */
	
	@Test
	public void testGetNextToken() throws FileNotFoundException {
		Tokenizer tokenizer = new Tokenizer(new FileReader("test/test1.yml"));
		assertEquals(tokenizer.getNextToken(), Token.STREAM_START);
		assertEquals(tokenizer.getNextToken(), Token.BLOCK_MAPPING_START);
		assertEquals(tokenizer.getNextToken(), Token.KEY);
		assertEquals(tokenizer.getNextToken().toString(), "<scalar value='12' plain='true' style=''>");
		assertEquals(tokenizer.getNextToken(), Token.VALUE);
		assertEquals(tokenizer.getNextToken().toString(), "<scalar value='13' plain='true' style=''>");
		assertEquals(tokenizer.getNextToken(), Token.BLOCK_END);
		assertEquals(tokenizer.getNextToken(), Token.STREAM_END);
		assertNull(tokenizer.getNextToken());
	}

	/**
	 * Purpose: Ensuring that the constructor Tokenizer(Reader) is working properly
	 * Input: Tokenizer(Reader) FileReader("test/test1.yml") -> Tokenizer(FileReader("test/test1.yml"))
	 * Expected:
	 * 			peekNextToken() = Token.STREAM_START
	 * 			Tokenizer(BufferedReader(FileReader("test/test1.yml"))) = Tokenizer(FileReader("test/test1.yml"))
	 * 			Tokenizer(null) throws IllegalArgumentException.class
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testTokenizerReader() throws FileNotFoundException {		
		Tokenizer tokenizer = new Tokenizer(new FileReader("test/test1.yml"));
		assertEquals(tokenizer.peekNextToken(), Token.STREAM_START);
		Tokenizer tokenizer_nbuffered = new Tokenizer((new BufferedReader(new FileReader("test/test1.yml"))));
		
		Iterator tokenizer_iter = tokenizer.iterator();
		Iterator tokenizer_nbuffered_iter = tokenizer_nbuffered.iterator();
		
		while(tokenizer_iter.hasNext() || tokenizer_nbuffered_iter.hasNext()){
			assertEquals(tokenizer_iter.next().toString(), tokenizer_nbuffered_iter.next().toString());
		}
		
		Tokenizer tokenizer_null = new Tokenizer((FileReader)null);
	}

	/**
	 * Purpose: Ensuring that the constructor Tokenizer(String) is working properly
	 * Input: Tokenizer(String) FileReader("12: 13") = Tokenizer(FileReader("test/test1.yml")), The content of "test/test1.yml" is "12: 13"
	 * Expected:
	 * 			 Tokenizer(FileReader("12: 13")) = Tokenizer(FileReader("test/test1.yml")), The content of "test/test1.yml" is "12: 13"
	 */
	
	@Test
	public void testTokenizerString() throws FileNotFoundException {
		Iterator tokenizer_iter = new Tokenizer(new FileReader("test/test1.yml")).iterator();
		Iterator tokenizer_string_iter = new Tokenizer("12: 13").iterator();
		
		while(tokenizer_iter.hasNext() || tokenizer_string_iter.hasNext()){
			assertEquals(tokenizer_iter.next().toString(), tokenizer_string_iter.next().toString());
		}
	}
	
	/**
	 * Purpose: peeking the next token correctly
	 * Input: peekNextToken() peeks the next token and does not move to the next token
	 * Expected:
	 * 			return Token.STREAM_START
	 * 			(moves by another function)
	 * 			return Token.BLOCK_MAPPING_START
	 * 			(moves by another function)
	 * 			return Token.KEY
	 * 			(moves by another function)
	 * 			...
	 * 			return Token.STREAM.END
	 * 			(moves by another function)
	 * 			return null
	 */

	@Test
	public void testPeekNextToken() throws FileNotFoundException {
		Tokenizer tokenizer = new Tokenizer(new FileReader("test/test1.yml"));
		assertEquals(tokenizer.peekNextToken(), Token.STREAM_START);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken(), Token.BLOCK_MAPPING_START);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken(), Token.KEY);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken().toString(), "<scalar value='12' plain='true' style=''>");
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken(), Token.VALUE);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken().toString(), "<scalar value='13' plain='true' style=''>");
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken(), Token.BLOCK_END);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextToken(), Token.STREAM_END);
		tokenizer.getNextToken();
		assertNull(tokenizer.peekNextToken());
	}

	/**
	 * Purpose: peeking the type of the next token correctly
	 * Input: peekNextToken() peeks the type of the next token and does not move to the next token
	 * Expected:
	 * 			return TokenType.STREAM_START
	 * 			(moves by another function)
	 * 			return TokenType.BLOCK_MAPPING_START
	 * 			(moves by another function)
	 * 			return TokenType.KEY
	 * 			(moves by another function)
	 * 			...
	 * 			return TokenType.STREAM.END
	 * 			(moves by another function)
	 * 			return null
	 */

	@Test
	public void testPeekNextTokenType() throws FileNotFoundException {
		Tokenizer tokenizer = new Tokenizer(new FileReader("test/test1.yml"));
		assertEquals(tokenizer.peekNextTokenType(), TokenType.STREAM_START);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.BLOCK_MAPPING_START);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.KEY);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.SCALAR);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.VALUE);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.SCALAR);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.BLOCK_END);
		tokenizer.getNextToken();
		assertEquals(tokenizer.peekNextTokenType(), TokenType.STREAM_END);
		tokenizer.getNextToken();
		assertNull(tokenizer.peekNextTokenType());
	}



	/**
	 * Purpose: Ensuring that the iterator's functions are working properly
	 * Input: iterator().hasNext() returns the same value as (Tokenizer.peekNextToken != null)
	 * 		  iterator().next() returns the same value as Tokenizer.getNextToken()
	 * Expected:
	 * 			Tokenizer.iterator().hasNext() = (Tokenizer.peekNextToken != null)
	 * 			Tokenizer.iterator().next() = Tokenizer.getNextToken()
	 * 			Tokenizer.iterator().remove() throws UnsupportedOperationException.class
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testIterator() throws FileNotFoundException {
		Tokenizer tokenizer = new Tokenizer(new FileReader("test/test1.yml"));
		Iterator iter = new Tokenizer(new FileReader("test/test1.yml")).iterator();
		assertEquals(iter.hasNext(), tokenizer.peekNextToken() != null);
		assertEquals(iter.next(), tokenizer.getNextToken());
		iter.remove();
	}

	/**
	 * Purpose: Getting the next token in a closed reader
	 * Input: close() Tokenizer.reader -> Tokenizer.reader.close()
	 * Expected:
	 * 			Tokenizer.getNextToken() = Token.STREAM_START
	 * 			Tokenizer.getNextToken() throws TokenizerException.class
	 */

	@Test(expected = TokenizerException.class)
	public void testClose() throws IOException {
		Tokenizer tokenizer = new Tokenizer(new FileReader("test/test1.yml"));
		tokenizer.close();
		
		assertEquals(tokenizer.getNextToken(), Token.STREAM_START);
		tokenizer.getNextToken();
	}

}
