package View;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ToggleButtonSwitch extends HBox {
	
	private final Label label = new Label();
	private final Button button = new Button();
	
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
	boolean isOn;
	
	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	private void init() {
		
		isOn=false;
		label.setText("OFF");
		label.setStyle("-fx-border-top-right-radius: 10px; ");
		
		getChildren().addAll(label, button);	
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		label.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		setStyle();
		bindProperties();
	}
	
	private void setStyle() {
		//Default Width
		setWidth(80);
		label.setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 4;");
		setAlignment(Pos.CENTER_LEFT);
	}
	
	private void bindProperties() {
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}
	
	public ToggleButtonSwitch() {
		init();
		switchedOn.addListener((a,b,c) -> {
			if (c) {
						isOn=true;
                		label.setText("ON");
                		setStyle("-fx-border-radius: 10px; ");
                		setStyle("-fx-background-color: green;");
                		label.toFront();
            		}
            		else {
            			isOn=false;
            			label.setText("OFF");
            			setStyle("-fx-border-radius: 10px; ");
        			setStyle("-fx-background-color: grey;");
                		button.toFront();
            		}
		});
	}
}