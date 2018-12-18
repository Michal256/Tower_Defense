package Menu;

import java.awt.*;

public class Credits extends BasicOptionLayout {
    /**
     * Constructor which initialize constructor from inherited class, which give basic layout.
     * Later it sets proper content for this layout.
     */
    public Credits()
    {
        super();

        lText.setFont(new Font("SansSerif",Font.PLAIN,20));
        lText.setText("<html>Gra stworzona przez: Michał Ziółek &#169;</html>");
    }
}
