package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		Candlestick candle = new Candlestick(10,20,20,10,10000,Calendar.getInstance());
		
		Assert.assertEquals(20,candle.getMinimo(),0.00001);
	}

}
