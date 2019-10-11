package drawing.gui;

import simulation.Hotel;

import javax.swing.*;

/**
 * Slider that enables the user to change the amount of HTE ticks every X amount of seconds.
 */
public class HTESlider extends JSlider {
    // TODO: The higher the slider value, the faster the simulation goes.
    private static final int HTE_MIN = 0;
    private static final int HTE_MAX = 1000;
    private static final int HTE_MINOR_SPACING = 100;
    private static final int HTE_MAJOR_SPACING = 500;

    public HTESlider(Hotel hotel) {
        super(JSlider.HORIZONTAL, HTE_MIN, HTE_MAX, hotel.getHotelTimer().getHTE());

        // Display labels at major tick marks
        setMinorTickSpacing(HTE_MINOR_SPACING);
        setMajorTickSpacing(HTE_MAJOR_SPACING);

        // Display labels at major and minor spacing values
        setPaintTicks(true);
        setPaintLabels(true);
        setSnapToTicks(true); // Snap the slider to only major and minor marks, no free floating values.
        setAlignmentX(LEFT);

        // Change HTE value on every slider value change. Should not mess up simulation as the dialog freezes the frame.
        addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                hotel.getHotelTimer().setHTE(source.getValue());
            }
        });
    }
}
