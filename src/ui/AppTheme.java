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
        NIGHT_SKY("Night Sky"),
        FOREST("Forest"),
        OCEAN("Ocean"),
        SUNSET("Sunset"),
        LAVENDER("Lavender"),
        CRIMSON("Crimson"),
        SLATE("Slate"),
        MINT("Mint"),
        GOLD("Gold"),
        GRAPE("Grape"),
        CORAL("Coral");

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
                case FOREST -> darkForest();
                case OCEAN -> darkOcean();
                case SUNSET -> darkSunset();
                case LAVENDER -> darkLavender();
                case CRIMSON -> darkCrimson();
                case SLATE -> darkSlate();
                case MINT -> darkMint();
                case GOLD -> darkGold();
                case GRAPE -> darkGrape();
                case CORAL -> darkCoral();
                default -> darkNormal();
            };
        }

        return switch (scheme) {
            case SUMMER -> lightSummer();
            case NIGHT_SKY -> lightNightSky();
            case FOREST -> lightForest();
            case OCEAN -> lightOcean();
            case SUNSET -> lightSunset();
            case LAVENDER -> lightLavender();
            case CRIMSON -> lightCrimson();
            case SLATE -> lightSlate();
            case MINT -> lightMint();
            case GOLD -> lightGold();
            case GRAPE -> lightGrape();
            case CORAL -> lightCoral();
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

    private static AppTheme lightForest() {
        return new AppTheme(
                color("#F0F7F0"), color("#DCEFDC"),
                color("#FFFFFF"), color("#F1F8F1"),
                color("#34A853"), color("#15803D"),
                color("#FFFFFF"), color("#DCFCE7"),
                color("#14532D"), color("#15803D"),
                color("#16A34A"), color("#15803D"), color("#DCFCE7"),
                color("#BBF7D0"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkForest() {
        return new AppTheme(
                color("#07140C"), color("#0B2014"),
                color("#11271A"), color("#0C1E12"),
                color("#166534"), color("#14532D"),
                color("#DCFCE7"), color("#BBF7D0"),
                color("#DCFCE7"), color("#4ADE80"),
                color("#22C55E"), color("#16A34A"), color("#166534"),
                color("#166534"), color("#07140C"), color("#07140C"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightOcean() {
        return new AppTheme(
                color("#ECFEFF"), color("#CFFAFE"),
                color("#FFFFFF"), color("#F0FDFF"),
                color("#06B6D4"), color("#0E7490"),
                color("#FFFFFF"), color("#CFFAFE"),
                color("#164E63"), color("#0E7490"),
                color("#0891B2"), color("#0E7490"), color("#CFFAFE"),
                color("#A5F3FC"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkOcean() {
        return new AppTheme(
                color("#04141A"), color("#07212B"),
                color("#0C2A35"), color("#08202A"),
                color("#0E7490"), color("#155E75"),
                color("#CFFAFE"), color("#A5F3FC"),
                color("#CFFAFE"), color("#22D3EE"),
                color("#06B6D4"), color("#0891B2"), color("#155E75"),
                color("#155E75"), color("#04141A"), color("#04141A"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightSunset() {
        return new AppTheme(
                color("#FFF1F2"), color("#FFE4E6"),
                color("#FFFFFF"), color("#FFF5F6"),
                color("#FB7185"), color("#E11D48"),
                color("#FFFFFF"), color("#FFE4E6"),
                color("#881337"), color("#BE123C"),
                color("#F43F5E"), color("#E11D48"), color("#FFE4E6"),
                color("#FECDD3"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkSunset() {
        return new AppTheme(
                color("#1A0A10"), color("#2A0E18"),
                color("#2B1019"), color("#200B12"),
                color("#BE123C"), color("#9F1239"),
                color("#FFE4E6"), color("#FECDD3"),
                color("#FFE4E6"), color("#FB7185"),
                color("#F43F5E"), color("#E11D48"), color("#9F1239"),
                color("#9F1239"), color("#1A0A10"), color("#1A0A10"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightLavender() {
        return new AppTheme(
                color("#F5F3FF"), color("#EDE9FE"),
                color("#FFFFFF"), color("#F7F5FF"),
                color("#A78BFA"), color("#7C3AED"),
                color("#FFFFFF"), color("#EDE9FE"),
                color("#4C1D95"), color("#7C3AED"),
                color("#8B5CF6"), color("#7C3AED"), color("#EDE9FE"),
                color("#DDD6FE"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkLavender() {
        return new AppTheme(
                color("#120B23"), color("#1B1133"),
                color("#221542"), color("#190F30"),
                color("#6D28D9"), color("#5B21B6"),
                color("#EDE9FE"), color("#DDD6FE"),
                color("#EDE9FE"), color("#A78BFA"),
                color("#8B5CF6"), color("#7C3AED"), color("#5B21B6"),
                color("#5B21B6"), color("#120B23"), color("#120B23"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightCrimson() {
        return new AppTheme(
                color("#FEF2F2"), color("#FEE2E2"),
                color("#FFFFFF"), color("#FFF5F5"),
                color("#F87171"), color("#DC2626"),
                color("#FFFFFF"), color("#FEE2E2"),
                color("#7F1D1D"), color("#B91C1C"),
                color("#DC2626"), color("#B91C1C"), color("#FEE2E2"),
                color("#FECACA"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkCrimson() {
        return new AppTheme(
                color("#1A0808"), color("#260C0C"),
                color("#2A0E0E"), color("#200A0A"),
                color("#B91C1C"), color("#991B1B"),
                color("#FEE2E2"), color("#FECACA"),
                color("#FEE2E2"), color("#F87171"),
                color("#EF4444"), color("#DC2626"), color("#991B1B"),
                color("#991B1B"), color("#1A0808"), color("#1A0808"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightSlate() {
        return new AppTheme(
                color("#F8FAFC"), color("#E2E8F0"),
                color("#FFFFFF"), color("#F1F5F9"),
                color("#64748B"), color("#334155"),
                color("#FFFFFF"), color("#E2E8F0"),
                color("#0F172A"), color("#475569"),
                color("#475569"), color("#334155"), color("#E2E8F0"),
                color("#CBD5E1"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkSlate() {
        return new AppTheme(
                color("#0F141B"), color("#161D27"),
                color("#1E293B"), color("#151E2E"),
                color("#475569"), color("#334155"),
                color("#F1F5F9"), color("#CBD5E1"),
                color("#F1F5F9"), color("#94A3B8"),
                color("#64748B"), color("#475569"), color("#334155"),
                color("#334155"), color("#0F141B"), color("#F1F5F9"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightMint() {
        return new AppTheme(
                color("#F0FDFA"), color("#CCFBF1"),
                color("#FFFFFF"), color("#F0FDFA"),
                color("#2DD4BF"), color("#0D9488"),
                color("#FFFFFF"), color("#CCFBF1"),
                color("#134E4A"), color("#0F766E"),
                color("#14B8A6"), color("#0D9488"), color("#CCFBF1"),
                color("#99F6E4"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkMint() {
        return new AppTheme(
                color("#04140F"), color("#07211A"),
                color("#0C2A24"), color("#08201B"),
                color("#0F766E"), color("#115E59"),
                color("#CCFBF1"), color("#99F6E4"),
                color("#CCFBF1"), color("#2DD4BF"),
                color("#14B8A6"), color("#0D9488"), color("#115E59"),
                color("#115E59"), color("#04140F"), color("#04140F"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightGold() {
        return new AppTheme(
                color("#FEFCE8"), color("#FEF9C3"),
                color("#FFFFFF"), color("#FEFCE8"),
                color("#FACC15"), color("#CA8A04"),
                color("#FFFFFF"), color("#FEF9C3"),
                color("#713F12"), color("#A16207"),
                color("#EAB308"), color("#CA8A04"), color("#FEF9C3"),
                color("#FEF08A"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkGold() {
        return new AppTheme(
                color("#161103"), color("#221A06"),
                color("#2A2008"), color("#1F1705"),
                color("#A16207"), color("#854D0E"),
                color("#FEF9C3"), color("#FEF08A"),
                color("#FEF9C3"), color("#FACC15"),
                color("#EAB308"), color("#CA8A04"), color("#854D0E"),
                color("#854D0E"), color("#161103"), color("#161103"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightGrape() {
        return new AppTheme(
                color("#FDF4FF"), color("#FAE8FF"),
                color("#FFFFFF"), color("#FDF4FF"),
                color("#E879F9"), color("#C026D3"),
                color("#FFFFFF"), color("#FAE8FF"),
                color("#701A75"), color("#A21CAF"),
                color("#C026D3"), color("#A21CAF"), color("#FAE8FF"),
                color("#F5D0FE"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkGrape() {
        return new AppTheme(
                color("#190A1C"), color("#260E2A"),
                color("#2C1030"), color("#210B24"),
                color("#A21CAF"), color("#86198F"),
                color("#FAE8FF"), color("#F5D0FE"),
                color("#FAE8FF"), color("#E879F9"),
                color("#C026D3"), color("#A21CAF"), color("#86198F"),
                color("#86198F"), color("#190A1C"), color("#190A1C"),
                color("#FCA5A5")
        );
    }

    private static AppTheme lightCoral() {
        return new AppTheme(
                color("#FFF7ED"), color("#FFEDD5"),
                color("#FFFFFF"), color("#FFF7ED"),
                color("#FB923C"), color("#EA580C"),
                color("#FFFFFF"), color("#FFEDD5"),
                color("#7C2D12"), color("#C2410C"),
                color("#F97316"), color("#EA580C"), color("#FFEDD5"),
                color("#FED7AA"), color("#FFFFFF"), color("#FFFFFF"),
                color("#DC2626")
        );
    }

    private static AppTheme darkCoral() {
        return new AppTheme(
                color("#1A0D05"), color("#281307"),
                color("#2C1409"), color("#200E06"),
                color("#C2410C"), color("#9A3412"),
                color("#FFEDD5"), color("#FED7AA"),
                color("#FFEDD5"), color("#FB923C"),
                color("#F97316"), color("#EA580C"), color("#9A3412"),
                color("#9A3412"), color("#1A0D05"), color("#1A0D05"),
                color("#FCA5A5")
        );
    }

    private static Color color(String hex) {
        return Color.decode(hex);
    }
}
