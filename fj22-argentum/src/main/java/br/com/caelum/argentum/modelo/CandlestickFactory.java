package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao>
	negociacoes){
		double maximo = 0;
		double minimo = Double.MAX_VALUE;
		double volume = 0;
		
		for(Negociacao negociacao : negociacoes){
			volume += negociacao.getVolume();
			
			if(negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			} else if(negociacao.getPreco() < minimo){
				minimo = negociacao.getPreco();
			}
		}
		
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() -1).getPreco();
		
		return new Candlestick (abertura,fechamento,minimo,maximo,volume,data);
		
	}

	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
		// TODO Auto-generated method stub
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		List<Negociacao> negociacoesDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for(Negociacao negociacao : todasNegociacoes){
			//se nao for mesmo dia fecha candle e reinicia variavel
			if(!negociacao.isMesmoDia(dataAtual)){
				Candlestick candleDoDia = constroiCandleParaData(dataAtual,
						negociacoesDia);
				candles.add(candleDoDia);
				negociacoesDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDia.add(negociacao);
		}
		//adicao do ultimo candle
		Candlestick candleDoDia = constroiCandleParaData(dataAtual,negociacoesDia);
		candles.add(candleDoDia);
		return candles;
	}
}
