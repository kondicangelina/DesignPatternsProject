package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class DrawingObserver  implements PropertyChangeListener{

	DrawingFrame mFrame;
	public DrawingObserver(DrawingFrame frame) {
		this.mFrame=frame;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String fun=event.getPropertyName();
		switch(fun) {
		case "enableEditAndDelete":
			mFrame.getBtnDelete().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnEdit().setEnabled((boolean) event.getNewValue());
			break;
		case "disapleEditAndDelete":
			mFrame.getBtnDelete().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnEdit().setEnabled((boolean) event.getNewValue());
			break;
		case "enableOnlyDelete":
			mFrame.getBtnDelete().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnEdit().setEnabled((boolean) event.getOldValue());
			break;
		case "disableUndoAndRedo":
			mFrame.getBtnDelete().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnEdit().setEnabled((boolean) event.getNewValue());
			break;
		case "disabledUndo":
			mFrame.getBtnUndo().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnRedo().setEnabled((boolean) event.getOldValue());
			break;
		case "disabledRedo":
			mFrame.getBtnRedo().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnUndo().setEnabled((boolean) event.getOldValue());
			break;
		case "enableUndo":
			mFrame.getBtnUndo().setEnabled((boolean) event.getNewValue());
			break;
		case "enableRedo":
			mFrame.getBtnRedo().setEnabled((boolean) event.getNewValue());
			break;
		case "enableMoveToZ":
			mFrame.getBtnBringToBack().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnBringToFront().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnToBack().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnToFront().setEnabled((boolean) event.getNewValue());
			break;
		case "disableToBack":
			mFrame.getBtnBringToBack().setEnabled(false);
			mFrame.getBtnToBack().setEnabled(false);
			break;
		case "disableToFront":
			mFrame.getBtnToFront().setEnabled(false);
			mFrame.getBtnBringToFront().setEnabled(false);
			break;
		case "disableToFrontAndToBack":
			mFrame.getBtnToFront().setEnabled(false);
			mFrame.getBtnToBack().setEnabled(false);
			mFrame.getBtnBringToFront().setEnabled(false);
			mFrame.getBtnBringToBack().setEnabled(false);
			break;
		case "disableMoveToZ":
			mFrame.getBtnBringToBack().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnBringToFront().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnToBack().setEnabled((boolean) event.getNewValue());
			mFrame.getBtnToFront().setEnabled((boolean) event.getNewValue());
			break;
			
			
		}
	}

}
