import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Panel extends JPanel {
	int w = 500, h = 500, blockw = 10, blockh = 10;
	int[] dict = {0, 0, 1, -1};
	double goProb = 0.9f;
	Cord start = new Cord(0, 0);
	int[][] map;
	public Panel()
	{
		this.setPreferredSize(new Dimension(w, h));
		map = new int[h][w];
		drawMaze();
	}
	
	void drawMaze()
	{
		LinkedList<Cord> q = new LinkedList<Cord>();
		q.add(start);
		map[start.x][start.y] = 1; 
		while(!q.isEmpty())
		{
			Cord x = q.getFirst();
			q.remove(0);
			for(int i = 0; i < 4; i++)
			{
				double f = Math.random();
				if(f > goProb) continue;
				int nx = x.x + dict[i] * blockw, ny = x.y + dict[(i+2)%4] * blockh;
				if(0 <= nx && nx < w && 0 <= ny && ny < h && map[nx][ny] == 0)
				{
					boolean flag = false;
					for(int j = 0; j < 3; j++)
					{
						for(int k = 0; k < 2; k++)
						{
							int nnx, nny;
							if(x.x == nx)
							{
								nnx = nx + (-1 + j) * blockw;
								nny = ny + k * blockh;;
							}
							else
							{
								nnx = nx + k * blockw;
								nny = ny + (-1 + j) * blockh;
							}
							if(0 <= nnx && nnx < w && 0 <= nny && nny < h && map[nnx][nny] == 1)
							{
								flag = true;
								break;
							}
						}
						if(flag == true) break;
					}
					if(flag == true) continue;
					q.add(new Cord(nx, ny));
					map[nx][ny] = 1;
				}
			}
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		for(int i = 0; i < w; i += blockw)
		{
			for(int j = 0 ; j < h; j += blockh)
			{
				if(!(map[i][j] == 1)) g.fillRect(i, j, blockw, blockh);
			}
		}
	}
}
