import java.util.List;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragTesting extends Application{
	StackPane c1;
	double sceneX;
	double sceneY;
	double translateX;
	double translateY;
	Group root;
	Scene scene;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setWidth(500);
		stage.setHeight(500);
		
		root = new Group();
		scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		
		c1 = new StackPane();
		Circle c = new Circle(200, 200, 50, Color.DARKGRAY);
		c1.getChildren().add(c);
		
		VBox vb = new VBox();
		  /*vb.setStyle("-fx-padding: 10;" + 
                  "-fx-border-style: solid inside;" + 
                  "-fx-border-width: 2;" +
                  "-fx-border-insets: 5;" + 
                  "-fx-border-radius: 5;" + 
                  "-fx-border-color: blue;");*/
		  vb.setAlignment(Pos.BASELINE_CENTER);
		
		c1.getChildren().add(vb);
		c1.setTranslateX(100);
		c1.setTranslateY(100);
		
		root.getChildren().addAll(c1);
		
		StackPane box1 = addTextStack(new Text("Hi"));
		box1.setTranslateX(100);
		
		StackPane box2 = addTextStack(new Text("Bye"));
		box2.setTranslateX(250);
		
		StackPane box3 = addTextStack(new Text("Ok"));
		box3.setTranslateX(400);
		
		
		
		stage.show();
	}
	
	public StackPane addTextStack(Text text){
		Rectangle r = new Rectangle(100, 50, Color.LIGHTGRAY);
		Text t = text;
		t.setFont(new Font(null, 20));
		StackPane stack = new StackPane();
		stack.getChildren().addAll(r, t);

		r.setTranslateX(0);
		r.setTranslateY(0);
		
		r.setStroke(Color.BLACK);
		stack.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				sceneX = e.getSceneX();
				sceneY = e.getSceneY();
				translateX = ((StackPane) e.getSource()).getTranslateX();
				translateY = ((StackPane) e.getSource()).getTranslateY();
				stack.toFront();
			}
			
		});
		
		stack.setOnMouseDragged(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				
				double dx = e.getSceneX() - sceneX;
				double dy = e.getSceneY() - sceneY;
				((StackPane)e.getSource()).setTranslateX(translateX + dx);
				((StackPane)e.getSource()).setTranslateY(translateY + dy);
				if(stack.getTranslateX() < 0) stack.setTranslateX(0);
				if(stack.getTranslateX() +stack.getWidth() > scene.getWidth()) stack.setTranslateX(scene.getWidth() - stack.getWidth());
				if(stack.getTranslateY() < 0) stack.setTranslateY(0);
				if(stack.getTranslateY() + stack.getHeight() > scene.getHeight()) stack.setTranslateY(scene.getHeight() - stack.getHeight());
			}
			
		});
		
		stack.setOnMouseReleased(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent e){
				//if(Math.abs(c1.getTranslateX() - (stack.getWidth() / 2) - stack.getTranslateX()) < c1.getWidth() / 2 + (stack.getWidth() / 2)
				//		&& Math.abs(c1.getTranslateY() - (stack.getHeight() / 2) - stack.getTranslateY()) < c1.getHeight() / 2 + (stack.getHeight() / 2)){		
				if(stack.intersects(c1.getBoundsInLocal()))	{
					
						((VBox) c1.getChildren().get(1)).getChildren().add(t);
						root.getChildren().removeAll(stack);
						
						Circle c = (Circle) c1.getChildren().get(0);
						c.setRadius(c.getRadius() + 10);
				}
			
			}
			
		});
		root.getChildren().addAll(stack);
		return stack;
	}
	
	
}


/*
stack.toBack();
if(e.getSource().getClass().equals(StackPane.class) && stack.intersects(((Node) e.getSource()).getBoundsInLocal()))	{
StackPane sp = (StackPane) 
System.out.println("Hi");
if(sp.getChildren().contains(Circle.class)){
	
	if(!sp.getChildren().contains(t)){
		if(sp.getChildren().contains(Text.class)) sp.getChildren().remove(Text.class);
		root.getChildren().removeAll(stack);
		sp.getChildren().add(t);
	} 
}
}
*/
