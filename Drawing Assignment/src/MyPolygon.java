import java.awt.Graphics2D;
import java.awt.Point;


public class MyPolygon extends MyShape{

	
	
	public MyPolygon(Point a, Point b, Point c, Point d, Point e, Point f) {
		super(a,b,c,d,e,f);
	}
	//start, end, middle, polyOne, polyTwo, polyThree

	@Override
	public void draw(Graphics2D g, boolean fill) {
		g.setColor(color);
		int x []= {start.x, polyOne.x, polyTwo.x,polyThree.x,end.x,start.x};
		int y []= {start.y, polyOne.y, polyTwo.y,polyThree.y,end.y,start.y};
		g.drawPolygon(x, y, 6);
		if(fill == true)
			g.fillPolygon(x,y,6);
		
	}

}
