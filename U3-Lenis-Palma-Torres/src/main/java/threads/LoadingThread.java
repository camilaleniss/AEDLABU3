package threads;

import controller.LoadingController;
import javafx.concurrent.Task;
import model.FIBA;

public class LoadingThread extends Task<Void>{
	
	private FIBA fiba;
	private boolean listo;

	public LoadingThread() {
		super();
	}

	@Override
	public Void call() throws Exception {
		fiba = new FIBA();
//		Thread.sleep(2000);
		return null;
	}

	public FIBA getFiba() {
		return fiba;
	}
	
	
}
