package Menu;

import java.awt.*;

public class Options extends BasicOptionLayout {
    /**
     * Constructor which initialize constructor from inherited class, which give basic layout.
     * Later it sets proper content for this layout.
     */
    public Options()
    {
        super();
        lText.setFont(new Font("SansSerif",Font.PLAIN,20));
        lText.setText("<html>Dodatkowe opcje: <br />Brak dodatkowych opcji</html>");

    }
}
