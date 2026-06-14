import java.awt.Color;
import java.awt.Font;

public class AppTheme {

    public enum AppearanceMode {
        LIGHT,
        DARK
    }

    public enum ColorScheme {
        NORMAL("Normal"),
        SUMMER("Summer"),
        NIGHT_SKY("Night Sky");

        private final String label;

        ColorScheme(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public final Color background;
    public final Color backgroundEnd;
    public final Color cardStart;
    public final Color cardEnd;
    public final Color heroStart;
    public final Color heroEnd;
    public final Color heroText;
    public final Color heroSubText;
    public final Color primaryText;
    public final Color secondaryText;
    public final Color accent;
    public final Color accentHover;
    public final Color accentSoft;
    public final Color border;
    public final Color inputBackground;
    public final Color buttonText;
    public final Color errorText;

    public final Font titleFont;
    public final Font headingFont;
    public final Font heroCityFont;
    public final Font heroTempFont;
    public final Font heroIconFont;
    public final Font cardIconFont;
    public final Font bodyFont;
    public final Font smallFont;

    private AppTheme(
            Color background,
            Color backgroundEnd,
            Color cardStart,
            Color cardEnd,
            Color heroStart,
            Color heroEnd,
            Color heroText,
            Color heroSubText,
            Color primaryText,
            Color secondaryText,
            Color accent,
            Color accentHover,
            Color accentSoft,
            Color border,
            Color inputBackground,
            Color buttonText,
            Color errorText
    ) {
        this.background = background;
        this.backgroundEnd = backgroundEnd;
        this.cardStart = cardStart;
        this.cardEnd = cardEnd;
        this.heroStart = heroStart;
        this.heroEnd = heroEnd;
        this.heroText = heroText;
        this.heroSubText = heroSubText;
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
        this.accent = accent;
        this.accentHover = accentHover;
        this.accentSoft = accentSoft;
        this.border = border;
        this.inputBackground = inputBackground;
        this.buttonText = buttonText;
        this.errorText = errorText;

        this.titleFont = new Font("Segoe UI", Font.BOLD, 22);
        this.headingFont = new Font("Segoe UI", Font.BOLD, 20);
        this.heroCityFont = new Font("Segoe UI", Font.BOLD, 30);
        this.heroTempFont = new Font("Segoe UI", Font.BOLD, 56);
        this.heroIconFont = new Font("Segoe UI Emoji", Font.PLAIN, 72);
        this.cardIconFont = new Font("Segoe UI Emoji", Font.PLAIN, 36);
        this.bodyFont = new Font("Segoe UI", Font.PLAIN, 14);
        this.smallFont = new Font("Segoe UI", Font.PLAIN, 12);
    }

    public static AppTheme resolve(AppearanceMode mode, ColorScheme scheme) {
        if (mode == AppearanceMode.DARK) {
            return switch (scheme) {
                case SUMMER -> darkSummer();
                case NIGHT_SKY -> darkNightSky();
                default -> darkNormal();
            };
        }

        return switch (scheme) {
            case SUMMER -> lightSummer();
            case NIGHT_SKY -> lightNightSky();
            default -> lightNormal();
        };
    }

    private static AppTheme lightNormal() {
        return new AppTheme(
                color("#F1F5FB"), color("#DCE7F5"),
                color("#FFFFFF"), color("#F2F6FC"),
                color("#3B82F6"), color("#1D4ED8"),
                color("#FFFFFF"), color("#DBEAFE"),
                color("#1F2933"), color("#6B7280"),
                color("#2563EB"), color("#1D4ED8"), color("#DBEAFE"),
                color("#E5E7EB"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkNormal() {
        return new AppTheme(
                color("#0F172A"), color("#111827"),
                color("#1F2937"), color("#161F2E"),
                color("#1E3A8A"), color("#1E40AF"),
                color("#F9FAFB"), color("#BFDBFE"),
                color("#F9FAFB"), color("#9CA3AF"),
                color("#3B82F6"), color("#2563EB"), color("#1E3A8A"),
                color("#374151"), color("#111827"), color("#FFFFFF"),
                color("#F87171")
        );
    }

    private static AppTheme lightSummer() {
        return new AppTheme(
                color("#FFF7E8"), color("#FFEBC9"),
                color("#FFFFFF"), color("#FFF6E6"),
                color("#FB923C"), color("#F59E0B"),
                color("#FFFFFF"), color("#FFEDD5"),
                color("#7C2D12"), color("#B45309"),
                color("#F59E0B"), color("#D97706"), color("#FEF3C7"),
                color("#FDE68A"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkSummer() {
        return new AppTheme(
                color("#1C1408"), color("#2A1D0C"),
                color("#2E2418"), color("#221A10"),
                color("#B45309"), color("#92400E"),
                color("#FEF3C7"), color("#FED7AA"),
                color("#FEF3C7"), color("#D97706"),
                color("#FBBF24"), color("#F59E0B"), color("#92400E"),
                color("#92400E"), color("#1C1408"), color("#1C1408"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightNightSky() {
        return new AppTheme(
                color("#EEF1FF"), color("#DDE2FF"),
                color("#FFFFFF"), color("#F1F3FF"),
                color("#6366F1"), color("#4338CA"),
                color("#FFFFFF"), color("#E0E7FF"),
                color("#312E81"), color("#6366F1"),
                color("#4F46E5"), color("#4338CA"), color("#E0E7FF"),
                color("#C7D2FE"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkNightSky() {
        return new AppTheme(
                color("#070B1C"), color("#0B1026"),
                color("#1A2140"), color("#11162B"),
                color("#312E81"), color("#1E1B4B"),
                color("#E0E7FF"), color("#C7D2FE"),
                color("#E0E7FF"), color("#818CF8"),
                color("#6366F1"), color("#4F46E5"), color("#312E81"),
                color("#312E81"), color("#0B1026"), color("#FFFFFF"),
                color("#FCA5A5")
        );
    }

    private static Color color(String hex) {
        return Color.decode(hex);
    }
}
