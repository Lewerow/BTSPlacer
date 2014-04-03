package views.listeners;

import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import views.DrawingPanel;
import calculations.BTS;
import calculations.TerrainGenerator;
import calculations.UniformRandomGenerator;

public class MenuOpenListenerTest {

	@Test
	public void shouldReactOnEvent() throws IOException {
		// given
		UniformRandomGenerator randomGenerator = mock(UniformRandomGenerator.class);

		TerrainGenerator tg = TerrainGenerator.getInstance();
		tg.setRandomGenerator(randomGenerator);
		DrawingPanel drawingPanel = new DrawingPanel();

		JFileChooser fc = mock(JFileChooser.class);
		when(fc.showOpenDialog(drawingPanel)).thenReturn(JFileChooser.APPROVE_OPTION);
		File sampleImageFile = new File("src/main/resources/sampleMap.jpg");
		when(fc.getSelectedFile()).thenReturn(sampleImageFile);

		MenuOpenListener listener = new MenuOpenListener(fc, drawingPanel);

		// when
		listener.actionPerformed(mock(ActionEvent.class));

		// then
		BTS[] btss = new BTS[30];
		for (int i = 0; i < btss.length; i++) {
			btss[i] = tg.getDefaultBTS();
		}

		Assertions.assertThat(listener.getTerrain().getBtss()).containsOnly(btss);
	}
}
