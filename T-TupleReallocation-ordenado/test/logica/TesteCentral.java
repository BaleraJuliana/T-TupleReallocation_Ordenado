package logica;

import org.junit.Before;
import org.junit.Test;

public class TesteCentral {
	
	private Central central;
	
	@Before
	public void init(){
		central = new Central();
	}
	
	@Test
	public void testeEntrada(){	
		central.entrada();
	}
}
