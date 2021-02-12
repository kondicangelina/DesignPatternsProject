package strategy;

import java.io.File;

public class LoadManager implements Load{
	private Load load;
public LoadManager(Load load) {
	this.load = load;
}	
	@Override
	public void load(File file) {
		load.load(file);
	}

}
