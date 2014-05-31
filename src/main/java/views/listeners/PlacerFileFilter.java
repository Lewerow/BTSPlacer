package views.listeners;

import javax.swing.filechooser.FileFilter;
import java.io.File;


/**
 * Created by Vortim on 2014-05-31.
 */
public class PlacerFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(".placer") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "BTS Placer file type: .placer";
    }
}
