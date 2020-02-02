package disk

class FDEManufacturerCode(val code: UByte, val name: String, val jname: String) {

    override fun toString() = name

    companion object {

        fun fromByte(c: UByte): FDEManufacturerCode {
            for(k in KNOWN) {
                if(c == k.code) {
                    return k
                }
            }
            return UNKNOWN(c)
        }

        val UNLICENSED = FDEManufacturerCode(0x00u, "<unlicensed>", "<非公認>")
        val NINTENDO = FDEManufacturerCode(0x01u, "Nintendo", "任天堂")
        val CAPCOM = FDEManufacturerCode(0x08u, "Capcom", "カプコン")
        val JALECO = FDEManufacturerCode(0x0Au, "Jaleco", "ジャレコ")
        val HUDSON_SOFT = FDEManufacturerCode(0x18u, "Hudson Soft", "ハドソン")
        val IREM = FDEManufacturerCode(0x49u, "Irem", "アイレム")
        val GAKKEN = FDEManufacturerCode(0x4Au, "Gakken", "学習研究社")
        val BULLETPROOF_SOFTWARE = FDEManufacturerCode(0x8Bu, "BulletProof Software", "(BPS)	BPS")
        val PACK_IN_VIDEO = FDEManufacturerCode(0x99u, "Pack-In-Video", "パックインビデオ")
        val TECMO = FDEManufacturerCode(0x9Bu, "Tecmo", "テクモ")
        val IMAGINEER = FDEManufacturerCode(0x9Cu, "Imagineer", "イマジニア")
        val SCORPION_SOFT = FDEManufacturerCode(0xA2u, "Scorpion Soft", "スコーピオンソフト")
        val KONAMI = FDEManufacturerCode(0xA4u, "Konami", "コナミ")
        val KAWADA_CO_LTD = FDEManufacturerCode(0xA6u, "Kawada Co., Ltd.", "河田")
        val TAKARA = FDEManufacturerCode(0xA7u, "Takara", "タカラ")
        val ROYAL_INDUSTRIES = FDEManufacturerCode(0xA8u, "Royal Industries", "ロイヤル工業")
        val TOEI_ANIMATION = FDEManufacturerCode(0xACu, "Toei Animation", "東映動画")
        val NAMCO = FDEManufacturerCode(0xAFu, "Namco", "ナムコ")
        val ASCII_CORPORATION = FDEManufacturerCode(0xB1u, "ASCII Corporation", "アスキー")
        val BANDAI = FDEManufacturerCode(0xB2u, "Bandai", "バンダイ")
        val SOFT_PRO_INC = FDEManufacturerCode(0xB3u, "Soft Pro Inc.", "ソフトプロ")
        val HAL_LABORATORY = FDEManufacturerCode(0xB6u, "HAL Laboratory", "HAL研究所")
        val SUNSOFT = FDEManufacturerCode(0xBBu, "Sunsoft", "サンソフト")
        val TOSHIBA_EMI = FDEManufacturerCode(0xBCu, "Toshiba EMI", "東芝EMI")
        val TAITO = FDEManufacturerCode(0xC0u, "Taito", "タイトー")
        val SUNSOFT_ASK_CO_LTD = FDEManufacturerCode(0xC1u, "Sunsoft / Ask Co., Ltd.",	"サンソフト アスク講談社")
        val KEMCO = FDEManufacturerCode(0xC2u, "Kemco", "ケムコ")
        val SQUARE = FDEManufacturerCode(0xC3u, "Square", "スクウェア")
        val TOKUMA_SHOTEN = FDEManufacturerCode(0xC4u, "Tokuma Shoten", "徳間書店")
        val DATA_EAST = FDEManufacturerCode(0xC5u, "Data East", "データイースト")
        val TONKIN = FDEManufacturerCode(0xC6u, "Tonkin", "House/Tokyo Shoseki	トンキンハウス")
        val EAST_CUBE = FDEManufacturerCode(0xC7u, "East Cube", "イーストキューブ")
        val KONAMI_ULTRA_PALCOM = FDEManufacturerCode(0xCAu, "Konam / Ultra / Palcom",	"コナミ")
        val NTVIC = FDEManufacturerCode(0xCBu, "NTVIC / VAP",	"バップ")
        val USE_CO_LTD = FDEManufacturerCode(0xCCu, "Use Co., Ltd.", "ユース")
        val PONY_CANYON = FDEManufacturerCode(0xCEu, "Pony Canyon / FCI",	"ポニーキャニオン")
        val SOFTEL = FDEManufacturerCode(0xD1u, "Sofel", "ソフエル")
        val BOTHTEC_INC = FDEManufacturerCode(0xD2u, "Bothtec, Inc.", "ボーステック")
        val HIRO_CO_LTD = FDEManufacturerCode(0xDBu, "Hiro Co., Ltd.", "ヒロ")
        val ATHENA = FDEManufacturerCode(0xE7u, "Athena", "アテナ")
        val ATLUS = FDEManufacturerCode(0xEBu, "Atlus", "アトラス")

        private val KNOWN = arrayOf(
            UNLICENSED,
            NINTENDO,
            CAPCOM,
            JALECO,
            HUDSON_SOFT,
            IREM,
            GAKKEN,
            BULLETPROOF_SOFTWARE,
            PACK_IN_VIDEO,
            TECMO,
            IMAGINEER,
            SCORPION_SOFT,
            KONAMI,
            KAWADA_CO_LTD,
            TAKARA,
            ROYAL_INDUSTRIES,
            TOEI_ANIMATION,
            NAMCO,
            ASCII_CORPORATION,
            BANDAI,
            SOFT_PRO_INC,
            HAL_LABORATORY,
            SUNSOFT,
            TOSHIBA_EMI,
            TAITO,
            SUNSOFT_ASK_CO_LTD,
            KEMCO,
            SQUARE,
            TOKUMA_SHOTEN,
            DATA_EAST,
            TONKIN,
            EAST_CUBE,
            KONAMI_ULTRA_PALCOM,
            NTVIC,
            USE_CO_LTD,
            PONY_CANYON,
            SOFTEL,
            BOTHTEC_INC,
            HIRO_CO_LTD,
            ATHENA,
            ATLUS
        )

        val UNKNOWN = { c:UByte -> FDEManufacturerCode(c, "Unknown", "Unknown")}
    }
}