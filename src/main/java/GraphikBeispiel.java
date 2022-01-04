

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


// Beispiel zu Java 2D-Graphik für Genetische Algorithmen
// (C) Alexander del Pino

public class GraphikBeispiel {

	
	
	public void Graphik(Conformation conformation) {

		int height = 3440;
		int width = 1440;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2.setColor(Color.GREEN);
		g2.fillRect(0, 0, width, height);

		int cellSize = 40;
		System.out.println("matrix size " + conformation.conformation.size());

		String label = "Start";

		for (int i = 0; i < conformation.conformation.size(); i++){
				g2.setColor(conformation.conformation.get(i).isHydrophobic() ? Color.BLACK : Color.WHITE);
			    g2.fillRect(conformation.conformation.get(i).getPosition().x*60,
							conformation.conformation.get(i).getPosition().y*60,
							cellSize, cellSize);
			    if(i==0){

				g2.drawString(label, conformation.conformation.get(i).getPosition().x*60 , conformation.conformation.get(i).getPosition().y*60);
				}
			    if(i+1 < conformation.conformation.size()) {
					g2.setColor(Color.BLACK);
					if (conformation.conformation.get(i).getPosition().y == conformation.conformation.get(i + 1).getPosition().y) {
						if (conformation.conformation.get(i).getPosition().x > conformation.conformation.get(i + 1).getPosition().x)
						{g2.drawLine(conformation.conformation.get(i).getPosition().x * 60,
								conformation.conformation.get(i).getPosition().y * 60 + 20,
								conformation.conformation.get(i + 1).getPosition().x * 60 + 40,
								conformation.conformation.get(i + 1).getPosition().y * 60 + 20);}
						else
						{g2.drawLine(conformation.conformation.get(i).getPosition().x * 60 +40,
								conformation.conformation.get(i).getPosition().y * 60 + 20,
								conformation.conformation.get(i + 1).getPosition().x * 60 ,
								conformation.conformation.get(i + 1).getPosition().y * 60 + 20);}
					} else {
						if (conformation.conformation.get(i).getPosition().y >conformation.conformation.get(i + 1).getPosition().y) {
							g2.drawLine(conformation.conformation.get(i).getPosition().x * 60 + 20,
									conformation.conformation.get(i).getPosition().y * 60 ,
									conformation.conformation.get(i + 1).getPosition().x * 60 + 20,
									conformation.conformation.get(i + 1).getPosition().y * 60 + 40);
						} else {
							g2.drawLine(conformation.conformation.get(i).getPosition().x * 60 + 20,
									conformation.conformation.get(i).getPosition().y * 60 + 40,
									conformation.conformation.get(i + 1).getPosition().x * 60 + 20,
									conformation.conformation.get(i + 1).getPosition().y * 60);
						}
					}
				}
			}


		g2.setColor(new Color(255, 255, 255));

		Font font = new Font("Serif", Font.PLAIN, 20);
		g2.setFont(font);
		FontMetrics metrics = g2.getFontMetrics();
		int ascent = metrics.getAscent();
		int labelWidth = metrics.stringWidth(label);
		
		String folder = "/tmp/alex/ga";
		String filename = "bild.png";
		if (new File(folder).exists() == false) new File(folder).mkdirs();
		
		try {
			ImageIO.write(image, "png", new File(folder + File.separator + filename));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		
	}

	
}
