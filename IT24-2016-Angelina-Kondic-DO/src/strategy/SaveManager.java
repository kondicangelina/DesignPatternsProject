package strategy;

import java.io.File;

public class SaveManager implements Save{
	private Save save;

	public SaveManager(Save save) {
		this.save=save;
	}
	@Override
	public void save(File file) {
		this.save.save(file);
	}
}
