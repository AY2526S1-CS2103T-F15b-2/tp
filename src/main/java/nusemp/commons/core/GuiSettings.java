package nusemp.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import nusemp.commons.util.ToStringBuilder;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final boolean DEFAULT_IS_DARK_MODE = true;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final boolean isDarkTheme;

    /**
     * Constructs a {@code GuiSettings} with the default height, width, position and dark mode setting.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        isDarkTheme = DEFAULT_IS_DARK_MODE;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width, position, and dark mode setting
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, boolean isDarkTheme) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.isDarkTheme = isDarkTheme;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuiSettings)) {
            return false;
        }

        GuiSettings otherGuiSettings = (GuiSettings) other;
        return windowWidth == otherGuiSettings.windowWidth
                && windowHeight == otherGuiSettings.windowHeight
                && Objects.equals(windowCoordinates, otherGuiSettings.windowCoordinates)
                && isDarkTheme == otherGuiSettings.isDarkTheme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, isDarkTheme);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("windowWidth", windowWidth)
                .add("windowHeight", windowHeight)
                .add("windowCoordinates", windowCoordinates)
                .add("isDarkTheme", isDarkTheme)
                .toString();
    }
}
