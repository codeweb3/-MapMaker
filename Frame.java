import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame()
	{
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	    this.add(new Panel());
	    this.pack();
	}
}
